package fr.olprog_c.le_phare_culturel.dtos.event;

/* ****** ****** ****** ****** */
// EventLocationPlaceDTO.java
/* ****** ****** ****** ****** */
import com.fasterxml.jackson.annotation.JsonProperty;

public record EventLocationPlaceDTO(
    @JsonProperty("coordinates") EventLocationCoordinatesDTO eventLocationCoordinatesDTO,
    String name,
    String address,
    String postalCode,
    String city) {
}
