package fr.olprog_c.le_phare_culturel.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDTO(
    @JsonProperty("firstname") String firstName,

    @JsonProperty("lastname") String lastName,

    @JsonProperty("nickname") String profileNickname,

    @JsonProperty("email") String email,

    @JsonProperty("avatar") String avatar,

    @JsonProperty("description") String profileDescription) {
}
