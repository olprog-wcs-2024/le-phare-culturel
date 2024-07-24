package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EventByUserComingSoonResponseDTO(
        Long uid,
        EventImagesDTO images,
        String description,
        @JsonProperty("long_description") String longDescription,
        String title) {
}
