package fr.olprog_c.le_phare_culturel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_c.le_phare_culturel.services.AuthService;

@RestController
public class ConfirmationTokenController {

  private final AuthService authService;

  public ConfirmationTokenController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping("/confirm")
  public ResponseEntity<String> confirmUser(@RequestParam("token") String token) {
    authService.confirmUser(token);
    return ResponseEntity.ok("User confirmed successfully");
  }
}
