package fr.olprog_c.le_phare_culturel.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.olprog_c.le_phare_culturel.dtos.user.UserPostRequestDTO;
import fr.olprog_c.le_phare_culturel.dtos.user.UserPostResponseDTO;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private static UserRepository userRepository;
    /* ****** ****** utile Object.andThen(save()) ****** ****** */
    private static UserEntity userEntity;

    /**
     * UserService constructor
     *
     * @param userRepository The UserRepository to be used by this service
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Converts a UserEntity to UserPostResponseDTO
     *
     * @param user The user to be converted
     * @return The converted UserPostResponseDTO
     * @throws BadCredentialsException If the conversion fails
     */
    public UserPostResponseDTO convertEntityToResponseDTO(UserEntity user) {
        try {
            return (new  ObjectMapper()).convertValue(user, UserPostResponseDTO.class);
        } catch (Exception e) {
            throw new BadCredentialsException(" Erreur de conversion : " + e.getMessage());
        }
    }

    /**
     * Updates a UserEntity based on a UserPostRequestDTO
     *
     * @param body The request DTO containing the new user data
     * @param user The entity to be updated
     * @return This service, for chaining
     * @throws BadCredentialsException If the old and new password are identical or the replacement password is different
     */
    public UserService convertRequestDtoToEntity(UserPostRequestDTO body, UserEntity user) {
        userEntity = user;

        // case of changing password
        if ((body.newPassword() != null && !body.newPassword().isEmpty())
                && (body.password() != null && !body.password().isEmpty())
        ) {
            if (Objects.equals(AuthService.passwordEncoding(body.newPassword()), user.getPassword())) {
                throw new BadCredentialsException("l ancien et le nouveau Mot de passe sont identiques");
            }
            if (body.password().equals(body.newPassword())) {
                user.setPassword(AuthService.passwordEncoding(body.newPassword()));
            } else {
                throw new BadCredentialsException("Mot de passe de remplacement different");
            }
            // user.setUpdatedDate(LocalDateTime.now());
        }
        // case of profile profile description
        if (body.firstName() != null && !body.firstName().isEmpty()) {
            user.setFirstName(body.firstName());
        }
        if (body.lastName() != null && !body.lastName().isEmpty()) {
            user.setLastName(body.lastName());
        }
        if (body.profileDescription() != null && !body.profileDescription().isEmpty()) {
            user.setProfileDescription(body.profileDescription());
        }
        if (body.email() != null && !body.email().isEmpty()) {
            user.setEmail(body.email());
        }
        if (body.profileNickname() != null && !body.profileNickname().isEmpty()) {
            user.setProfileNickname(body.profileNickname());
        }
        if (body.avatar() != null && !body.avatar().isEmpty()) {
            user.setAvatar(body.avatar());

        }

        return this;
    }

    /**
     * Saves the UserEntity to the database
     *
     * @return True if the user was successfully saved, false otherwise
     */
    public boolean save() {
        Optional<UserEntity> existingUserEntity = userRepository.findById(userEntity.getId());
        boolean entityPresent = existingUserEntity.isPresent();
        boolean saved = false;
        if (entityPresent) {
            UserEntity savedEntity = userRepository.save(userEntity);
            if (!existingUserEntity.get().equals(savedEntity)) {
                saved = true;
            }
        }
        userEntity = null;
        return saved;
    }
}
