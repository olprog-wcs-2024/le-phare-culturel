package fr.olprog_c.le_phare_culturel.dtos.event;

import fr.olprog_c.le_phare_culturel.dtos.mapper.UserDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserMessageEntity;

public class EventGroupUserMessageMapper {


    public static EventGroupUserMessageEntity fromDTO(EventMessageDTO messageEntityDto) {
        EventGroupUserMessageEntity messageEntity = new EventGroupUserMessageEntity();
        messageEntity.setId(messageEntityDto.id());
        messageEntity.setMessageText(messageEntityDto.text());
        messageEntity.setReferencedUserAuthor(UserDTOMapper.convertUserResponseDtoUserEntity(messageEntityDto.author()));
        messageEntity.setCreatedDate(messageEntityDto.dateCreation());
        return messageEntity;
    }

    public static EventMessageDTO toDTO(EventGroupUserMessageEntity messageEntity) {
        return new EventMessageDTO(
                messageEntity.getId(),
                messageEntity.getMessageText(),
                UserDTOMapper.responseDTO(messageEntity.getReferencedUserAuthor()),
                messageEntity.getCreatedDate());
    }

    public static EventMessageSlimDTO toSlimDTO(EventGroupUserMessageEntity messageEntity) {
        return new EventMessageSlimDTO(
                messageEntity.getMessageText(),
                UserDTOMapper.responseSlimDTO(messageEntity.getReferencedUserAuthor()),
                messageEntity.getCreatedDate()
        );
    }
}
