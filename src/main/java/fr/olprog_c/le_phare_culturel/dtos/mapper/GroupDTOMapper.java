package fr.olprog_c.le_phare_culturel.dtos.mapper;

import java.io.ObjectStreamClass;
import java.util.Collection;
import java.util.List;

import fr.olprog_c.le_phare_culturel.dtos.event.*;
import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserMessageEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.models.event.EventGroupModelDTO;

public class GroupDTOMapper {

    public static EventGroupParticipantsResponseDTO convertGroupEntityToEventGroupParticipantsResponseDTO(
            EventGroupUserEntity eventGroupUser) {

        UserSlimResponseDTO author = ((eventGroupUser.getReferencedUserAuthor() != null)
                ? UserDTOMapper.responseSlimDTO(eventGroupUser.getReferencedUserAuthor())
                : null);

        Collection<EventGroupUserMessageEntity> messagesEntitiesList = eventGroupUser.getReferencedGroupsMessages();
        if (messagesEntitiesList != null) {
            List<EventMessageSlimDTO> messages = messagesEntitiesList.stream()
                    .map(EventGroupUserMessageMapper::toSlimDTO)
                    .toList();

            List<UserEntity> participantsList = (List<UserEntity>) eventGroupUser.getReferencedUserList();

            List<UserSlimResponseDTO> participantsListDto = (participantsList != null ? participantsList.stream()
                    .map(UserDTOMapper::responseSlimDTO).toList() : null);

            return new EventGroupParticipantsResponseDTO(
                    eventGroupUser.getId(),
                    eventGroupUser.getGroupName(),
                    eventGroupUser.getTimeMeet(),
                    eventGroupUser.getGroupMaxSize(),
                    eventGroupUser.getDescription(),
                    author,
                    eventGroupUser.getRelatedEvents().getTitle(),
                    participantsListDto,
                    messages);
        }

        return new EventGroupParticipantsResponseDTO(
                eventGroupUser.getId(),
                eventGroupUser.getGroupName(),
                eventGroupUser.getTimeMeet(),
                eventGroupUser.getGroupMaxSize(),
                eventGroupUser.getDescription(),
                author,
                eventGroupUser.getRelatedEvents().getTitle(),
                null,
                null);

    }
    /*
     * ******
     * public static EventGroupUserEntity
     * convertGroupRequestDTOToEventGroupUserEntity(EventGroupModelDTO
     * eventGroupModelDTO) {
     * List<UserEntity> participantsList =
     * eventGroupModelDTO.participants().stream().map(UserDTOMapper::convert).toList
     * ();
     * List<EventGroupUserMessageEntity> messagesList =
     * eventGroupModelDTO.messages().stream().map(EventGroupUserMessageMapper::
     * fromDTO).toList();
     * return new EventGroupUserEntity(
     * eventGroupModelDTO.id(),
     * eventGroupModelDTO.groupName(),
     * eventGroupModelDTO.groupMaxSize(),
     * eventGroupModelDTO.timeMeet(),
     * eventGroupModelDTO.description(),
     * UserDTOMapper::convertUserResponseDtoUserEntity(eventGroupModelDTO.author()),
     * participantsList,
     * messagesList);
     * 
     * } ******
     */

}
