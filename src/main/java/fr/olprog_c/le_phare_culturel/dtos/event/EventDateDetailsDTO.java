package fr.olprog_c.le_phare_culturel.dtos.event;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

// EventDateDetails.java
/* ****** ****** ****** ****** */
public record EventDateDetailsDTO(

    @JsonProperty("begin") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC") OffsetDateTime begin,

    @JsonProperty("end") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC") OffsetDateTime end) {
}
