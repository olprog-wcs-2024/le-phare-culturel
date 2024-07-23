package fr.olprog_c.le_phare_culturel.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserMessageResponseDTO(
    Long id,
    @JsonProperty("nickname") String profileNickname,
    @JsonProperty("avatar") String avatar

) {
}
