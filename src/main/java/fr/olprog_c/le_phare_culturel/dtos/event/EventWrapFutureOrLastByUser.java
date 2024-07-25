package fr.olprog_c.le_phare_culturel.dtos.event;

import java.util.List;

public record EventWrapFutureOrLastByUser(
		List<EventByUserComingSoonOrLastEventsResponseDTO> future,
		List<EventByUserComingSoonOrLastEventsResponseDTO> last
) {
}
