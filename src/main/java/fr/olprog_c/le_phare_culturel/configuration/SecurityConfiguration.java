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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JWTFilter jwtFilter;

    public SecurityConfiguration(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                // .csrf(csrf -> csrf
                // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                // .csrf((csrf) -> csrf
                // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))

                // .csrf((csrf) -> csrf
                // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler()))
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                RouteDefinition.Auth.LOGIN_URL,
                                RouteDefinition.Auth.LOGOUT_URL,
                                RouteDefinition.Auth.REGISTER_URL,
                                RouteDefinition.Auth.EMAIL_CONFIRMATION_URL,
                                /* ****** ****** ****** ****** */
                                // RouteDefinition.Users.PROFILE_URL,
                                RouteDefinition.Users.CHANGE_PASSWORD_URL,
                                // RouteDefinition.Users.AVATAR_URL,
                                RouteDefinition.Auth.REFRESH_TOKEN_URL,
                                RouteDefinition.Auth.AUTH_STATUS_URL,
                                /* ****** ****** ****** ****** */
                                RouteDefinition.Events.EVENTS_URL,
                                RouteDefinition.Events.EVENTS_WITH_ID_URL,

                                RouteDefinition.Events.EVENTS_WITH_ID_GROUP_LIST_URL,
                                // RouteDefinition.Events.EVENTS_WITH_ID_GROUP_WITH_ID_URL,
                                RouteDefinition.Events.FILTER_URL,
                                RouteDefinition.Events.TAGS_URL,
                                RouteDefinition.Events.TAGS_FILTER_URL,
                                /* ****** ****** ****** ****** */
                                RouteDefinition.Groups.TAGS_URL,
                                RouteDefinition.Groups.GROUPS_URL)
                        .permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            Map<String, ?> errors = Map.of(
                                    "status", HttpServletResponse.SC_UNAUTHORIZED,
                                    "error_message", "Non autorisé",
                                    "raw_message", authException.getMessage());
                            response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            Map<String, ?> errors = Map.of(
                                    "status", HttpServletResponse.SC_FORBIDDEN,
                                    "error_message", "Accès interdit",
                                    "raw_message", accessDeniedException.getMessage());
                            response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
                        }))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200", "https://le-phare-culturel.fr"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addExposedHeader("Set-Cookie");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
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
