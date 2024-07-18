package fr.olprog_c.le_phare_culturel.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserPostRequestDTO(
        @JsonProperty("firstname")
        String firstName,

        @JsonProperty("lastname")
        String lastName,

        @JsonProperty("nickname")
        String profileNickname,

        @JsonProperty("email")
        String email,

        @JsonProperty("picture")
        String avatar,

        @JsonProperty("description")
        String profileDescription,

        @JsonProperty("confirm_password")
        String password,

        @JsonProperty("confirm_password")
        String newPassword
) {
}
