package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.olprog_c.le_phare_culturel.dtos.user.UserResponseDTO;

import java.time.LocalDateTime;

public record EventMessageDTO(
		long id,
		String text,
		UserResponseDTO author,
		@JsonProperty("date")
		LocalDateTime dateCreation
		) {
}
