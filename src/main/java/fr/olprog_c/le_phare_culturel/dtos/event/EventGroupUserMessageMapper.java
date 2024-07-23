package fr.olprog_c.le_phare_culturel.dtos.event;

import fr.olprog_c.le_phare_culturel.dtos.mapper.UserDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserMessageEntity;

public class EventGroupUserMessageMapper {

    public static EventMessageDTO toDTO(EventGroupUserMessageEntity messageEntity) {
        return new EventMessageDTO(
                messageEntity.getMessageText(),
                UserDTOMapper.responseDTO(messageEntity.getReferencedUserAuthor()));
    }

    public static EventMessageSlimDTO toSlimDTO(EventGroupUserMessageEntity messageEntity) {
        return new EventMessageSlimDTO(
                messageEntity.getMessageText(),
                UserDTOMapper.responseSlimDTO(messageEntity.getReferencedUserAuthor()));
    }
}
