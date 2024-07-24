package fr.olprog_c.le_phare_culturel.controllers;

import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import fr.olprog_c.le_phare_culturel.dtos.event.EventDetailSlimResponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.event.EventGroupSlimDTO;
import fr.olprog_c.le_phare_culturel.dtos.event.EventGroupsSlimPostRequestDTO;
import fr.olprog_c.le_phare_culturel.dtos.event.EventMessageSlimDTO;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserMessageEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import fr.olprog_c.le_phare_culturel.services.EventService;
import fr.olprog_c.le_phare_culturel.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupController {

	private final GroupService groupService;
	private final EventRepository eventRepository;
	private final EventService eventService;

	public GroupController(GroupService groupService, EventRepository eventRepository, EventService eventService) {
		this.groupService = groupService;
		this.eventRepository = eventRepository;
		this.eventService = eventService;
	}

	/**
	 * Find groups by ID.
	 *
	 * @param groupid a int variable, representing the id of the group to be
	 *                returned
	 * @return ResponseEntity with an instance of EventGroupUserEntity wrapped in
	 * it, if the group was found,
	 * otherwise ResponseEntity with an instance of null wrapped in it.
	 */
	@GetMapping(RouteDefinition.Groups.GROUPS_WITH_ID_URL)
	public ResponseEntity<?> findGroupsByID(@PathVariable int groupid) {
		EventGroupUserEntity entity = groupService.findGroupById(groupid);
		if (entity != null) {
			return ResponseEntity.ok(groupService.convertEntityToResponseDTO(entity));
		}
		return ResponseEntity.badRequest().body(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@GetMapping(RouteDefinition.Events.EVENTS_WITH_ID_GROUP_LIST_URL)
	public ResponseEntity<?> findGroupsListByEventID(@PathVariable int eventid) {
		Optional<EventDetailSlimResponseDTO> entityDto = eventService.findByID(eventid);
		if (entityDto.isPresent()) {
			List<EventGroupSlimDTO> groupsListDto = entityDto.get().groups();
			return ResponseEntity.ok(groupsListDto);
		}
		return ResponseEntity.badRequest().body(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * Create a new group.
	 *
	 * @param user    the authenticated user who will become the author of the group
	 * @param body    a DTO object encapsulating the required details for creating
	 *                the group
	 * @param eventid the id of the event to which the new group will be related
	 * @return an EventGroupParticipantsResponseDTO object encapsulating the newly
	 * created group's details if successful,
	 * or null if the group could not be created.
	 */
	@PostMapping(RouteDefinition.Events.EVENTS_WITH_ID_GROUP_LIST_URL)
	public ResponseEntity<?> createNewGroup(
			@AuthenticationPrincipal UserEntity user,
			@RequestBody EventGroupsSlimPostRequestDTO body,
			@PathVariable int eventid) {
		if (user == null) {
			throw new HttpServerErrorException(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "User not authenticated");
		}

		Optional<EventEntity> eventRelated = eventRepository.findById((long) eventid);
		if (eventRelated.isPresent()) {
			EventGroupUserEntity newGroupUserEntity = groupService.convertDTOToEntity(body);
			newGroupUserEntity.setReferencedUserAuthor(user);
			newGroupUserEntity.setRelatedEvents(eventRelated.get());
			// les group sont entre 0 (param groupsize) 255
			newGroupUserEntity.setGroupMaxSize(Math.min(Math.max(0, body.groupMaxSize()), 255));
			EventGroupUserEntity newGroupEntity = groupService.saveEntity(newGroupUserEntity);

			if (newGroupEntity != null) {
				return ResponseEntity.ok(groupService.convertEntityToResponseDTO(newGroupEntity));
			}
		}
		return ResponseEntity.badRequest().body(HttpStatus.UNPROCESSABLE_ENTITY);
	}


	/**
	 * Add user to group.
	 *
	 * @param user    a UserEntity, representing the user being added to the group
	 * @param eventid a int variable, representing the id of the event the group is
	 *                related to
	 * @param groupid a int variable, representing the id of the group the user will
	 *                be added to
	 * @return EventGroupParticipantsResponseDTO object, carrying updated group's
	 * details, if user was added
	 * to the group, otherwise return null.
	 */
	@PutMapping(RouteDefinition.Events.EVENTS_WITH_ID_GROUP_WITH_ID_USERS_URL)
	public ResponseEntity<?> addUserToGroupID(
			@AuthenticationPrincipal UserEntity user,
			@PathVariable int eventid,
			@PathVariable int groupid) {
		if (user == null) {
			return ResponseEntity.badRequest().body(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
		EventGroupUserEntity groupUser = groupService.findGroupById(groupid);
		if (groupUser != null) {
			groupUser.getReferencedUserList().add(user);
			EventGroupUserEntity groupUserSaved = groupService.saveEntity(groupUser);
			if (groupUserSaved != null) {
				return ResponseEntity.ok(groupService.convertEntityToResponseDTO(groupUserSaved));
			}
		}
		return ResponseEntity.badRequest().body(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@PutMapping(RouteDefinition.Events.EVENTS_WITH_ID_GROUP_WITH_ID_MESSAGING_URL)
	public ResponseEntity<?> addUserMessageToGroupID(
			@AuthenticationPrincipal UserEntity user,
			@RequestBody EventMessageSlimDTO messageSlimDTO,
			@PathVariable int eventid,
			@PathVariable int groupid) {
		if (user == null) {
			return ResponseEntity.badRequest().body(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
		EventGroupUserEntity groupUser = groupService.findGroupById(groupid);

		if (groupUser != null) {
			EventGroupUserMessageEntity newMessageEntity = new EventGroupUserMessageEntity();
			newMessageEntity.setMessageText(messageSlimDTO.text());
			newMessageEntity.setReferencedUserAuthor(user);
			newMessageEntity.setRelatedEventsGroups(groupUser);

			return ResponseEntity.ok(groupService.convertEntityToResponseDTO(groupService.findGroupById(groupid)));

		}
		return ResponseEntity.badRequest().body(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * Delete user from group.
	 *
	 * @param user    a UserEntity, representing the user being deleted from the
	 *                group
	 * @param eventid a int variable, representing the id of the event the group is
	 *                related to
	 * @param groupid a int variable, representing the id of the group the user will
	 *                be deleted from
	 * @return EventGroupParticipantsResponseDTO object, carrying updated group's
	 * details, if user was deleted
	 * from the group, otherwise return null.
	 */
	@DeleteMapping(RouteDefinition.Events.EVENTS_WITH_ID_GROUP_WITH_ID_USERS_URL)
	public ResponseEntity<?> deleteUserFromGroupID(
			@AuthenticationPrincipal UserEntity user,
			@PathVariable int eventid,
			@PathVariable int groupid) {
		if (user == null) {
			return ResponseEntity.badRequest().body(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
		EventGroupUserEntity groupUser = groupService.findGroupById(groupid);
		if (groupUser != null) {
			groupUser.getReferencedUserList().remove(user);
			EventGroupUserEntity groupUserSaved = groupService.saveEntity(groupUser);
			if (groupUserSaved != null) {
				return ResponseEntity.ok(groupService.convertEntityToResponseDTO(groupUserSaved));
			}
		}
		return ResponseEntity.badRequest().body(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@DeleteMapping(RouteDefinition.Events.EVENTS_WITH_ID_GROUP_WITH_ID_URL)
	public ResponseEntity<?> deleteGroup(
			@AuthenticationPrincipal UserEntity user,
			@PathVariable int eventid,
			@PathVariable int groupid) {
		if (user == null) {
			return ResponseEntity.badRequest().body(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
		// nope just SET tbis damn Capture the FLAG
		EventGroupUserEntity groupUser = groupService.findGroupById(groupid);
		if (groupUser != null) {
			return ResponseEntity.badRequest().body(HttpStatus.EXPECTATION_FAILED);
		}
		return ResponseEntity.badRequest().body(HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
