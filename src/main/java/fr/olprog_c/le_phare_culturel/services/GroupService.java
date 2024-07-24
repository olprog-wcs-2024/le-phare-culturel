package fr.olprog_c.le_phare_culturel.services;

import fr.olprog_c.le_phare_culturel.dtos.event.EventGroupParticipantsResponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.event.EventGroupsSlimPostRequestDTO;
import fr.olprog_c.le_phare_culturel.dtos.mapper.GroupDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import fr.olprog_c.le_phare_culturel.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GroupService {

	private final GroupRepository groupRepository;
	private final EventRepository eventRepository;

	public GroupService(GroupRepository groupRepository, EventRepository eventRepository) {
		this.groupRepository = groupRepository;
		this.eventRepository = eventRepository;
	}

	public List<EventGroupUserEntity> findAll() {
		return groupRepository.findAll();
	}

	public EventGroupUserEntity findGroupById(long groupid) {
		return groupRepository.findByid(groupid);
	}

	public List<EventGroupUserEntity> findAllGroupsByRelatedEventId(long eventid) {
		return groupRepository.findByRelatedEvents_Uid(eventid);
	}

	public EventGroupUserEntity convertDTOToEntity(EventGroupsSlimPostRequestDTO body) {
		EventGroupUserEntity groupUserEntity = new EventGroupUserEntity();
		groupUserEntity.setGroupName(body.groupName());
		groupUserEntity.setTimeMeet(body.timeMeet());
		groupUserEntity.setDescription(body.description());
		// groupUserEntity.setReferencedUserAuthor(body.author());
		return groupUserEntity;
	}

	public EventGroupUserEntity saveEntity(EventGroupUserEntity groupUserEntityToSave) {
		Optional<EventGroupUserEntity> entity = Optional.of(this.groupRepository.save(groupUserEntityToSave));
		return entity.orElse(null);
	}

	public EventGroupParticipantsResponseDTO convertEntityToResponseDTO(EventGroupUserEntity newGroupEntity) {
		return GroupDTOMapper.convertGroupEntityToEventGroupParticipantsResponseDTO(newGroupEntity);
	}
}
