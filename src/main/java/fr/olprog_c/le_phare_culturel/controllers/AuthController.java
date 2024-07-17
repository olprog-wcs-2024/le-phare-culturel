package fr.olprog_c.le_phare_culturel.controllers;

import java.util.Map;

import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import fr.olprog_c.le_phare_culturel.configuration.JWTService;
import fr.olprog_c.le_phare_culturel.dtos.AuthLoginPostDTO;
import fr.olprog_c.le_phare_culturel.dtos.AuthRegisterPostDTO;
import fr.olprog_c.le_phare_culturel.dtos.mapper.AuthDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
public class AuthController {

  private final AuthService authService;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthController(AuthService authService, JWTService jwtService, AuthenticationManager authenticationManager) {
    this.authService = authService;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping(RouteDefinition.Auth.LOGIN_URL)
  public ResponseEntity<?> login(@Valid @RequestBody AuthLoginPostDTO dto, HttpServletResponse response) {
    try {
      final Authentication authenticate = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
      if (authenticate.isAuthenticated()) {
        UserEntity user = (UserEntity) authenticate.getPrincipal();
        Map<String, String> token = jwtService.generateEncodedTokenForEmail(user.getEmail());
        ResponseCookie cookie = ResponseCookie.from("token", token.get(JWTService.COOKIE_TOKEN_NAME))
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(7 * 24 * 60 * 60)
            .build();

        response.addHeader("set-cookie", cookie.toString());
        return ResponseEntity.ok(AuthDTOMapper.responseDTO(user));
      }

    } catch (BadCredentialsException b) {
      throw new BadCredentialsException("Je ne vous connais pas!!");
    }
    return null;
  }

  @PostMapping(RouteDefinition.Auth.REGISTER_URL)
  public void register(@Valid @RequestBody AuthRegisterPostDTO dto) {
    if (dto.confirmPassword().equals(dto.password())) {
      this.authService.register(dto);
    } else {
      throw new HttpServerErrorException(HttpStatus.PRECONDITION_REQUIRED, "Les mots de passes ne sont pas identiques");
    }
  }

}
