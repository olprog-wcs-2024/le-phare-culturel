package fr.olprog_c.le_phare_culturel.dtos.mapper;

import fr.olprog_c.le_phare_culturel.dtos.event.*;
import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;
import fr.olprog_c.le_phare_culturel.entities.*;
import fr.olprog_c.le_phare_culturel.models.event.EventGroupModelDTO;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDTOMapper {

	public static EventResponseDTO convertPageToResponseDTO(Page<EventEntity> event) {
		List<EventEntity> events = event.getContent();

		if (events == null) {
			return new EventResponseDTO(
					event.getTotalPages(),
					event.getTotalElements(),
					event.getSize(),
					null);
		}

		// events.stream().map();
		return new EventResponseDTO(
				event.getTotalPages(),
				event.getTotalElements(),
				event.getSize(),
				events.stream()
						.map(EventDTOMapper::convertDetailResponseDTO)
						.toList());
	}

	public static EventResponseWithoutGroupDTO convertPageToResponseWithoutGroupDTO(Page<EventEntity> event) {
		List<EventEntity> events = event.getContent();

		if (events == null) {
			return new EventResponseWithoutGroupDTO(
					event.getTotalPages(),
					event.getTotalElements(),
					event.getSize(),
					null);
		}

		// events.stream().map();
		return new EventResponseWithoutGroupDTO(
				event.getTotalPages(),
				event.getTotalElements(),
				event.getSize(),
				events.stream()
						.map(EventDTOMapper::convertDetailResponseWithoutGroupDTO)
						.toList());
	}

	public static EventDetailResponseDTO convertDetailResponseDTO(EventEntity event) {
		return new EventDetailResponseDTO(
				event.getUid(),
				convertImagesDTO(event.getImages()),
				event.getDescription(),
				event.getLongDescription(),
				event.getTitle(),
				convertLocationPlaceDTO(event.getLocation()),
				event.getTarifs(),
				dateRange(event.getFirstTiming(), event.getLastTiming()),
				event.getImageCredits(),
				convertDateDetails(event.getFirstTiming()),
				convertDateDetails(event.getLastTiming()),
				event.getReferencedEventGroups().stream()
						.map(EventDTOMapper::convertGroupDTO)
						.toList()

		);
	}

	public static EventDetailSlimResponseDTO convertDetailSlimResponseDTO(EventEntity event) {
		return new EventDetailSlimResponseDTO(
				event.getUid(),
				convertImagesDTO(event.getImages()),
				event.getDescription(),
				event.getLongDescription(),
				event.getTitle(),
				convertLocationPlaceDTO(event.getLocation()),
				event.getTarifs(),
				dateRange(event.getFirstTiming(), event.getLastTiming()),
				event.getImageCredits(),
				convertDateDetails(event.getFirstTiming()),
				convertDateDetails(event.getLastTiming()),
				event.getReferencedEventGroups().stream()
						.map(EventDTOMapper::convertGroupSlimDTO)
						.toList()

		);
	}

	public static EventDetailResponseGroupsCountDTO convertDetailResponseWithCountGroupDTO(EventEntity event) {
		return new EventDetailResponseGroupsCountDTO(
				event.getUid(),
				convertImagesDTO(event.getImages()),
				event.getDescription(),
				event.getLongDescription(),
				event.getTitle(),
				convertLocationPlaceDTO(event.getLocation()),
				event.getTarifs(),
				dateRange(event.getFirstTiming(), event.getLastTiming()),
				event.getImageCredits(),
				convertDateDetails(event.getFirstTiming()),
				convertDateDetails(event.getLastTiming()),
				event.getReferencedEventGroups().stream()
						.map(EventDTOMapper::convertGroupCountDTO)
						.toList()

		);
	}

	public static EventDetailResponseWithoutGroupDTO convertDetailResponseWithoutGroupDTO(EventEntity event) {
		return new EventDetailResponseWithoutGroupDTO(
				event.getUid(),
				convertImagesDTO(event.getImages()),
				event.getDescription(),
				event.getLongDescription(),
				event.getTitle(),
				convertLocationPlaceDTO(event.getLocation()),
				event.getTarifs(),
				dateRange(event.getFirstTiming(), event.getLastTiming()),
				event.getImageCredits(),
				convertDateDetails(event.getFirstTiming()),
				convertDateDetails(event.getLastTiming())

		);
	}

	private static EventLocationPlaceDTO convertLocationPlaceDTO(LocationEntity location) {
		return new EventLocationPlaceDTO(
				new EventLocationCoordinatesDTO(
						location.getLongitude(),
						location.getLatitude()),
				location.getName(),
				location.getAddress(),
				location.getPostalCode(),
				location.getCity());
	}

	private static EventImagesDTO convertImagesDTO(List<ImageEntity> images) {
		Map<String, String> imagesMap = new HashMap<>();

		for (ImageEntity img : images) {
			if (img != null) {
				if (img.getType().equals("thumbnail")) {
					imagesMap.put("thumbnail", img.getFilename());
				} else {
					imagesMap.put("base", img.getFilename());
				}
			}
		}

		return new EventImagesDTO(
				imagesMap.get("thumbnail"),
				imagesMap.get("base"));

	}

	private static String dateRange(TTimingEntity firstTiming, TTimingEntity lastTiming) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String first = firstTiming.getBegin().format(formatter);
		String last = lastTiming.getEnd().format(formatter);
		return first + " - " + last;

	}

	private static EventDateDetailsDTO convertDateDetails(TTimingEntity timing) {
		return new EventDateDetailsDTO(
				timing.getBegin(),
				timing.getEnd());
	}

	public static EventGroupModelDTO convertGroupDTO(EventGroupUserEntity event) {
		List<EventMessageDTO> messages = event.getReferencedGroupsMessages()
				.stream()
				.map(EventGroupUserMessageMapper::toDTO)
				.toList();
		List<UserSlimResponseDTO> participantList = event.getReferencedUserList()
				.stream()
				.map(UserDTOMapper::responseSlimDTO)
				.toList();

        /*
         Long id,
        @JsonProperty("group_name") String groupName,
        @JsonProperty("time_meet") Instant timeMeet,
        @JsonProperty("group_size") int groupMaxSize,
        String description,
        UserResponseDTO author,
        List<UserSlimResponseDTO> participants,
        List<EventMessageDTO> messages
         */
		return new EventGroupModelDTO(
				event.getId(),
				event.getRelatedEvents(),
				event.getGroupName(),
				event.getTimeMeet(),
				event.getGroupMaxSize(),
				event.getDescription(),
				UserDTOMapper.responseDTO(event.getReferencedUserAuthor()),
				participantList,
				messages);

	}

	public static EventGroupSlimDTO convertGroupSlimDTO(EventGroupUserEntity event) {
		List<EventMessageSlimDTO> messages = event.getReferencedGroupsMessages()
				.stream()
				.map(EventGroupUserMessageMapper::toSlimDTO)
				.toList();

		return new EventGroupSlimDTO(
				event.getId(),
				event.getGroupName(),
				event.getTimeMeet(),
				event.getGroupMaxSize(),
				event.getDescription(),
				UserDTOMapper.responseSlimDTO(event.getReferencedUserAuthor()),
				messages);

	}

	public static EventGroupCountDTO convertGroupCountDTO(EventGroupUserEntity event) {
		int countMessages = event.getReferencedGroupsMessages().size();

		return new EventGroupCountDTO(event.getGroupName(), countMessages);

	}

	public static EventByUserComingSoonResponseDTO convertEventEntityToEventByUserComingSoonResponseDTO(EventEntity event) {
		return new EventByUserComingSoonResponseDTO(
				event.getUid(),
				convertImagesDTO(event.getImages()),
				event.getDescription(),
				event.getLongDescription(),
				event.getTitle());
	}

	public EventEntity convertFromEventIDDtoEventEntitySlim(EventsSlimIDDto slimIDDto) {
		EventEntity entity = new EventEntity();
		entity.setUid(slimIDDto.uid());
		return entity;
	}
}
