package fr.olprog_c.le_phare_culturel.controllers;

import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import fr.olprog_c.le_phare_culturel.dtos.user.UserPostRequestDTO;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    /* ******* ****** ****** ****** */
    private final UserService userService;

    /* ******* ****** ****** ****** */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* ******* ****** ****** ****** */
    @GetMapping(RouteDefinition.Users.PROFILE_URL)
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserEntity user) {
        System.out.println("Received Get Request :" + user);
        return ResponseEntity.ok(userService.convertEntityToResponseDTO(user));
    }

    /* ******* ****** ****** ****** */
    @PutMapping(RouteDefinition.Users.CHANGE_PASSWORD_URL)
    public ResponseEntity<?> putNewPassword(
            @Valid @RequestBody UserPostRequestDTO body,
            @AuthenticationPrincipal UserEntity user
    ) {
        System.out.println("Received Put Request :" + user);
        if (userService.convertRequestDtoToEntity(body, user).save()) {
            return ResponseEntity.ok(userService.convertEntityToResponseDTO(user));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    /* ******* ****** ****** ****** */
    @PutMapping(RouteDefinition.Users.AVATAR_URL)
    public ResponseEntity<?> putNewAvatar(
            @Valid @RequestBody UserPostRequestDTO body,
            @AuthenticationPrincipal UserEntity user
    ) {
        System.out.println("Received Put Request :" + user);
        if (userService.convertRequestDtoToEntity(body, user).save()) {
            return ResponseEntity.ok(userService.convertEntityToResponseDTO(user));
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
