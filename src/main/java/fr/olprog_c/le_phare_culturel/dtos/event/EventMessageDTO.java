package fr.olprog_c.le_phare_culturel.dtos.event;

import fr.olprog_c.le_phare_culturel.dtos.user.UserResponseDTO;

public record EventMessageDTO(
		String text,
		UserResponseDTO author) {
}
