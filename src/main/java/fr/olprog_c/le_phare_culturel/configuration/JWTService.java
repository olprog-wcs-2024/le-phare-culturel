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
  public static final String COOKIE_TOKEN_NAME = "token";

  private final UserDetailsService userDetailsService;

  @Value("#{${jwt-expiration:1440}* 60 * 1000}")
  private Long tokenExpiration;
  @Value("${jwt-secret:keysecret}")
  private String secret;

  private SecretKey secretSigningKeyForJWT;
  private Map<String, Object> encodeClaims = null;

  public JWTService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  public long getTokenExpiration() {
    return this.tokenExpiration;
  }

  /* ****** ****** ****** ****** */
  @PostConstruct
  private void init() {
    this.secretSigningKeyForJWT = Keys.hmacShaKeyFor(
        secret.getBytes(StandardCharsets.UTF_8));
    this.encodeClaims = null;
  }

  /* ****** ****** ****** ****** */
  public Map<String, String> generateEncodedTokenForEmail(String email) {
    return generateEncodedToken(userDetailsService.loadUserByUsername(email));
  }

  /* ****** ****** ****** ****** */
  public Map<String, String> generateEncodedToken(UserDetails argUserDetails) {
    final Long jwtDateNow = System.currentTimeMillis();
    final Date jwtExpirationDate = new Date(jwtDateNow + tokenExpiration);

    UserEntity user = (UserEntity) argUserDetails;

    this.encodeClaims = Map.of(
        "email", argUserDetails.getUsername(),
        "roles", user.getUserRole(),
        Claims.EXPIRATION, jwtExpirationDate,
        Claims.ISSUED_AT, jwtDateNow,
        Claims.SUBJECT, argUserDetails.getUsername()

    );
    // claims.put("roles", argUserDetails.getAuthorities());
    final String localEncodedToken = Jwts.builder()
        .claims(encodeClaims)
        // .setSubject(argUserDetails.getUsername())
        // .setIssuedAt(jwtDateNow)
        // .setExpiration(jwtExpirationDate)
        .signWith(secretSigningKeyForJWT)
        .compact();
    return Map.of(COOKIE_TOKEN_NAME, localEncodedToken);
  }

  /* ****** ****** ****** ****** */
  public Claims getDecodedTokenClaims(String argToken) {
    if (argToken == null || argToken.isEmpty()) {
      throw new BadCredentialsException("token invalid");
    }

    return (Claims) Jwts.parser()
        .verifyWith(secretSigningKeyForJWT)
        .build()
        .parse(argToken)
        .getPayload();
  }

  /* ****** ****** ****** ****** */
  public boolean isTokenExpired(String argToken) {
    try {
      return (new Date()).after(getDecodedTokenClaims(argToken).getExpiration());
    } catch (JwtException e) {
      return false;
    }
  }

  public String extractRoles(String token) {
    try {
      return (String) getDecodedTokenClaims(token).get("role");
    } catch (JwtException e) {
      return null;
    }
  }

  /* ****** ****** ****** ****** */
  public String extractUsername(String argToken) {
    try {
      return getDecodedTokenClaims(argToken).getSubject();
    } catch (JwtException e) {
      return null;
    }

  }

}
