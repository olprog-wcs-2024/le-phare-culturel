package fr.olprog_c.le_phare_culturel.dtos;

public record AuthLoginPostResponseDTO(
    String email,
    String nickname,
    String description,
    String avatar,
    String firstname,
    String lastname) {
}
