package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EventByUserComingSoonOrLastEventsResponseDTO(
        Long uid,
        EventImagesDTO images,
        String description,
        @JsonProperty("date_range") String dateRange,
        String title,
        @JsonProperty("group_id") Long groupId) {
}
