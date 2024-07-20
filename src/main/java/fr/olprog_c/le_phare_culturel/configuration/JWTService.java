package fr.olprog_c.le_phare_culturel.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JWTService {
  public static final String ACCESS_TOKEN_NAME = "access_token";
  public static final String REFRESH_TOKEN_NAME = "refresh_token";

  private final UserDetailsService userDetailsService;

  @Value("#{${jwt.accessTokenExpirationMinutes:1} * 60}")
  private Long accessTokenExpirationMs;

  @Value("#{${jwt.refreshTokenExpirationMinutes:1440} * 60}")
  private Long refreshTokenExpirationMs;

  @Value("${jwt.secret:keysecret}")
  private String secret;

  private SecretKey secretSigningKeyForJWT;

  public JWTService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @PostConstruct
  private void init() {
    this.secretSigningKeyForJWT = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public Map<String, String> generateTokensForEmail(String email) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    String accessToken = generateToken(userDetails, accessTokenExpirationMs);
    String refreshToken = generateToken(userDetails, refreshTokenExpirationMs);
    return Map.of(ACCESS_TOKEN_NAME, accessToken, REFRESH_TOKEN_NAME, refreshToken);
  }

  private String generateToken(UserDetails userDetails, Long expirationMs) {
    final long now = System.currentTimeMillis() / 1000;
    final long expirationDate = now + expirationMs;

    UserEntity user = (UserEntity) userDetails;

    Map<String, Object> claims = Map.of(
        "email", userDetails.getUsername(),
        "roles", user.getUserRole(),
        Claims.EXPIRATION, expirationDate,
        Claims.ISSUED_AT, now,
        Claims.SUBJECT, userDetails.getUsername());

    return Jwts.builder()
        .claims(claims)
        .signWith(secretSigningKeyForJWT)
        .compact();
  }

  public Claims getDecodedTokenClaims(String token) {
    if (token == null || token.isEmpty()) {
      throw new BadCredentialsException("Token invalid");
    }

    return (Claims) Jwts.parser()
        .verifyWith(secretSigningKeyForJWT)
        .build()
        .parse(token)
        .getPayload();
  }

  public boolean isTokenExpired(String token) {
    try {
      return new Date().after(getDecodedTokenClaims(token).getExpiration());
    } catch (JwtException e) {
      return false;
    }
  }

  // public boolean validateToken(String token) {
  // try {
  // Claims claims = getDecodedTokenClaims(token);
  // return !isTokenExpired(token);
  // } catch (JwtException e) {
  // return false;
  // }
  // }

  public String extractRoles(String token) {
    try {
      return (String) getDecodedTokenClaims(token).get("roles");
    } catch (JwtException e) {
      return null;
    }
  }

  public String extractUsername(String token) {
    try {
      return getDecodedTokenClaims(token).getSubject();
    } catch (JwtException e) {
      return null;
    }
  }
}
