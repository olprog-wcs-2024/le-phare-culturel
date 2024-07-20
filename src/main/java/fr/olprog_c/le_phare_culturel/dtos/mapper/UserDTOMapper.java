package fr.olprog_c.le_phare_culturel.dtos.mapper;

import fr.olprog_c.le_phare_culturel.dtos.user.UserResponseDTO;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;

public class UserDTOMapper {

  public static UserResponseDTO responseDTO(UserEntity user) {
    return new UserResponseDTO(
        user.getFirstName(),
        user.getLastName(),
        user.getProfileNickname(),
        user.getEmail(),
        user.getAvatar(),
        user.getProfileDescription());

  }
}
