package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;

import java.time.LocalDateTime;

public record EventMessageSlimDTO(
        String text,
        UserSlimResponseDTO author,
        @JsonProperty("date")
        LocalDateTime dateCreation
) {
}
