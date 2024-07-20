package fr.olprog_c.le_phare_culturel.configuration;

import java.io.IOException;
import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTFilter extends OncePerRequestFilter {
  private final JWTService jwtService;
  private final UserDetailsService userDetailsService;

  private final ObjectMapper objectMapper;

  public JWTFilter(JWTService jwtService, UserDetailsService userDetailsService, ObjectMapper objectMapper) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
    this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws IOException {

    try {
      String jwtToken = null;
      boolean isTokenExpired = true;
      String username = null;

      final Cookie[] cookies = request.getCookies();

      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals(JWTService.ACCESS_TOKEN_NAME)) {
            jwtToken = cookie.getValue();
          }
        }
      }
      if (jwtToken != null) {
        isTokenExpired = jwtService.isTokenExpired(jwtToken);
        username = jwtService.extractUsername(jwtToken);
      }

      if (!isTokenExpired && username != null && (SecurityContextHolder.getContext().getAuthentication() == null)) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }

      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json");
      Map<String, ?> errors = Map.of("status", HttpServletResponse.SC_UNAUTHORIZED, "error_message", "JWT expiré");
      response.getWriter().write(objectMapper.writeValueAsString(errors));
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType("application/json");
      Map<String, ?> errors = Map.of("status", HttpServletResponse.SC_BAD_REQUEST, "error_message", "JWT invalide");
      response.getWriter().write(objectMapper.writeValueAsString(errors));
    }
  }
}
