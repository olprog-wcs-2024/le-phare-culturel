package fr.olprog_c.le_phare_culturel.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UserAvatarPutRequestDTO(
            @NotNull String url) {

}
