package fr.olprog_c.le_phare_culturel.dtos.mapper;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.olprog_c.le_phare_culturel.dtos.AuthLoginPostDTO;
import fr.olprog_c.le_phare_culturel.dtos.AuthLoginPostResponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.AuthRegisterPostDTO;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.enums.UserRoleEnum;

@Component
public class AuthDTOMapper {

  private final PasswordEncoder passwordEncoder;

  public AuthDTOMapper(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public UserEntity registerDtoToEntity(AuthRegisterPostDTO dto) {
    UserEntity user = new UserEntity();
    user.setEmail(dto.email());
    user.setAvatar(dto.avatar());
    user.setLastName(dto.lastname());
    user.setUserRole(UserRoleEnum.USER);
    user.setFirstName(dto.firstname());
    user.setProfileNickname(dto.profileNickname());
    user.setProfileDescription(dto.profileDescription());
    user.setPassword(this.passwordEncoder.encode(dto.password()));
    return user;
  }

  public static AuthLoginPostResponseDTO responseDTO(UserEntity user) {
    return new AuthLoginPostResponseDTO(user.getEmail(), user.getProfileNickname(), user.getProfileDescription(),
        user.getAvatar(), user.getFirstName(), user.getLastName());
  }
}
