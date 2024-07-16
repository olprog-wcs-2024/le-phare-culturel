package fr.olprog_c.le_phare_culturel.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  private final JWTFilter jwtFilter;

  public SecurityConfiguration(JWTFilter jWTFilters) {
    jwtFilter = jWTFilters;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(
            request -> {
              request
                  .requestMatchers(
                      RouteDefinition.LOGIN_URL,
                      RouteDefinition.REGISTER_URL,
                      RouteDefinition.LOGOUT_URL,
                      "/confirm",
                      RouteDefinition.LOGIN_RESET_PASSWORD_URL)
                  .permitAll()
                  .anyRequest().authenticated();
            })
        .addFilterBefore(
            jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint((request, response, authException) -> {
              response.setContentType("application/json;charset=UTF-8");
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              Map<String, ?> errors = Map.of("status", HttpServletResponse.SC_UNAUTHORIZED,
                  "error_message", "Non autorisé", "raw_message", authException.getMessage());
              response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
            })
            .accessDeniedHandler((request, response, accessDeniedException) -> {
              response.setContentType("application/json;charset=UTF-8");
              response.setStatus(HttpServletResponse.SC_FORBIDDEN);
              Map<String, ?> errors = Map.of("status", HttpServletResponse.SC_FORBIDDEN,
                  "error_message", "Accès interdit", "raw_message", accessDeniedException.getMessage());
              response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
            }))
        .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    return request -> {
      var cors = new CorsConfiguration();
      cors.setAllowedOrigins(List.of("*"));
      cors.setAllowedMethods(List.of("*"));
      cors.setAllowedHeaders(List.of("*"));
      return cors;
    };
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
