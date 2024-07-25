package fr.olprog_c.le_phare_culturel.dtos.mapper;
import fr.olprog_c.le_phare_culturel.dtos.user.UserResponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.models.event.EventGroupModelDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTOMapper {

    public static UserResponseDTO responseDTO(UserEntity user) {
        return new UserResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getProfileNickname(),
                user.getEmail(),
                user.getAvatar(),
                user.getProfileDescription(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());

    }

    public static UserSlimResponseDTO responseSlimDTO(UserEntity user) {
        return new UserSlimResponseDTO(
                user.getProfileNickname(),
                user.getAvatar());

    }

    public static UserResponseDTO convertUserEntityToUserEntityResponseDTO(UserEntity useruserEntity) {
        return new UserResponseDTO(
        useruserEntity.getFirstName(),
        useruserEntity.getLastName(),
        useruserEntity.getProfileNickname(),
        useruserEntity.getEmail(),
        useruserEntity.getAvatar(),
        useruserEntity.getProfileDescription(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());
    }

    public static UserEntity convertUserResponseDtoUserEntity(UserResponseDTO userResponseDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userResponseDTO.firstName());
        userEntity.setLastName(userResponseDTO.lastName());
        userEntity.setProfileNickname(userResponseDTO.profileNickname());
        userEntity.setEmail(userResponseDTO.email());
        userEntity.setAvatar(userResponseDTO.avatar());
        userEntity.setProfileDescription(userResponseDTO.profileDescription());
        return userEntity;
    }
    public static EventGroupUserEntity convertEventGroupModelDTOToEventGroupUserEntity(EventGroupModelDTO eventGroupModelDTO){
        EventEntity relatedEventEntity = new EventEntity();
        relatedEventEntity.setUid(eventGroupModelDTO.id());
        return new EventGroupUserEntity(
                eventGroupModelDTO.id(),
                eventGroupModelDTO.groupName(),
                eventGroupModelDTO.groupMaxSize(),
                eventGroupModelDTO.timeMeet(),
                eventGroupModelDTO.description(),
                UserDTOMapper.convertUserResponseDtoUserEntity(eventGroupModelDTO.author()),
                relatedEventEntity,
                UserDTOMapper.convertUserSlimResponseDtoUserEntityList(eventGroupModelDTO.participants()),
                EventMessageDTOMapper.convertEventMessageDtoToEventMessageEntityList(eventGroupModelDTO.messages())
        );
    }

    private static List<UserEntity> convertUserSlimResponseDtoUserEntityList(List<UserSlimResponseDTO> participants) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserSlimResponseDTO participant : participants) {
            UserEntity userEntity = new UserEntity();
            userEntity.setProfileNickname(participant.profileNickname());
            userEntity.setAvatar(participant.avatar());
            userEntities.add(userEntity);
        }
        return userEntities;
    }
}