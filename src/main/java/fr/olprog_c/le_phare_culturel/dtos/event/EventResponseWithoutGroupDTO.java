package fr.olprog_c.le_phare_culturel.dtos.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EventResponseWithoutGroupDTO(
    @JsonProperty("total_pages") Integer totalPages,
    @JsonProperty("total_elements") Long totalElements,
    Integer size,
    List<EventDetailReponseWithoutGroupDTO> events) {
}
