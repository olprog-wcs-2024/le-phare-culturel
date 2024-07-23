package fr.olprog_c.le_phare_culturel.dtos.event;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;

public record EventGroupSlimDTO(
        Long id,
        @JsonProperty("group_name") String groupName,
        @JsonProperty("time_meet") Instant timeMeet,
        String description,
        UserSlimResponseDTO author,
        List<EventMessageSlimDTO> messages) {
}
