package fr.olprog_c.le_phare_culturel.dtos.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.olprog_c.le_phare_culturel.models.event.EventGroupModelDTO;

public record EventDetailResponseDTO(
    Long uid,
    EventImagesDTO images,
    String description,
    @JsonProperty("long_description") String longDescription,
    String title,
    EventLocationPlaceDTO location,
    String tarifs,
    String dateRange,
    @JsonProperty("image_credits") String imageCredits,
    @JsonProperty("first_timing") EventDateDetailsDTO firstTiming,
    @JsonProperty("last_timing") EventDateDetailsDTO lastTiming,
    List<EventGroupModelDTO> groups) {
}
