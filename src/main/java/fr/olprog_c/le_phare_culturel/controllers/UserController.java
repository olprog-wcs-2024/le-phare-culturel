package fr.olprog_c.le_phare_culturel.controllers;

import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import fr.olprog_c.le_phare_culturel.dtos.event.EventWrapFutureOrLastByUser;
import fr.olprog_c.le_phare_culturel.dtos.user.UserAvatarPutRequestDTO;
import fr.olprog_c.le_phare_culturel.dtos.user.UserNewPasswordPutRequestDTO;
import fr.olprog_c.le_phare_culturel.dtos.user.UserRequestDTO;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(RouteDefinition.Users.PROFILE_URL)
    public ResponseEntity<?> getProfileInfo(@AuthenticationPrincipal UserEntity user) {
        System.out.println("Received Get Request :" + user);
        return ResponseEntity.ok(userService.convertEntityToResponseDTO(user));
    }

    @PutMapping(RouteDefinition.Users.PROFILE_URL)
    public ResponseEntity<?> setProfileInfo(
            @Valid @RequestBody UserRequestDTO body,
            @AuthenticationPrincipal UserEntity user) {
        System.out.println("Received Get Request :" + user);
        if (userService.convertRequestDtoToEntity(body, user).save()) {
            return ResponseEntity.ok(userService.convertEntityToResponseDTO(user));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping(RouteDefinition.Users.CHANGE_PASSWORD_URL)
    public ResponseEntity<?> putNewPassword(
            @Valid @RequestBody UserNewPasswordPutRequestDTO body,
            @AuthenticationPrincipal UserEntity user) {
        System.out.println("Received Put Request :" + user);
        if (userService.convertRequestNewPasswordDtoToEntity(body, user).save()) {
            return ResponseEntity.ok(userService.convertEntityToResponseDTO(user));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping(RouteDefinition.Users.AVATAR_URL)
    public ResponseEntity<?> putNewAvatar(
            @Valid @RequestBody UserAvatarPutRequestDTO body,
            @AuthenticationPrincipal UserEntity user) {
        System.out.println("Received Put Request :" + user);
        if (userService.convertRequestAvatarDtoToEntity(body, user).save()) {
            return ResponseEntity.ok(userService.convertEntityToResponseDTO(user));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping(RouteDefinition.Users.EVENTS_URL)
    public ResponseEntity<EventWrapFutureOrLastByUser> getGroups(@AuthenticationPrincipal UserEntity user) {
        EventWrapFutureOrLastByUser events = this.userService.findAllEventsByUser(user);
        if (events.future().isEmpty() && events.last().isEmpty()) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "No events found for user");
        }
        return ResponseEntity.ok(events);
    }
}
