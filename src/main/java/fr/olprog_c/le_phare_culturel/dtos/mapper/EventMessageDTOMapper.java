package fr.olprog_c.le_phare_culturel.dtos.mapper;

import fr.olprog_c.le_phare_culturel.dtos.event.EventMessageDTO;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserMessageEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventMessageDTOMapper {

    public static Collection<EventGroupUserMessageEntity> convertEventMessageDtoToEventMessageEntityList(List<EventMessageDTO> messages) {
        Collection<EventGroupUserMessageEntity> messageEntities = new ArrayList<EventGroupUserMessageEntity>();
        for (EventMessageDTO message : messages) {
            EventGroupUserMessageEntity messageEntity = new EventGroupUserMessageEntity();
            messageEntity.setMessageText("Nouveau message ...");
            messageEntity.setCreatedDate(message.dateCreation());
            messageEntity.setUpdatedDate(message.dateCreation());
            messageEntities.add(messageEntity);
        }
        return messageEntities;
    }
}
