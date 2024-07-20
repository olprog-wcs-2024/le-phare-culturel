package fr.olprog_c.le_phare_culturel.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserNewPasswordPutRequestDTO(
        @JsonProperty("password")
        String password,

        @JsonProperty("confirm_password")
        String newPassword
) {
}
