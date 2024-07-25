package fr.olprog_c.le_phare_culturel.models.event;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.olprog_c.le_phare_culturel.dtos.event.EventMessageDTO;
import fr.olprog_c.le_phare_culturel.dtos.user.UserResponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;

public record EventGroupModelDTO(
        Long id,
        EventEntity relatedEventsID,
        @JsonProperty("group_name") String groupName,
        @JsonProperty("time_meet") String timeMeet,
        @JsonProperty("group_size") int groupMaxSize,
        String description,
        UserResponseDTO author,
        List<UserSlimResponseDTO> participants,
        List<EventMessageDTO> messages
) {
}
