package fr.olprog_c.le_phare_culturel.dtos.event;

import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;

public record EventMessageSlimDTO(
        String text,
        UserSlimResponseDTO author) {
}
