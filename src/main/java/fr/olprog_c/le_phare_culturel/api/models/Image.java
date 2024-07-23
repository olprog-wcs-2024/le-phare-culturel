package fr.olprog_c.le_phare_culturel.api.models;

public record Image(
    String filename,
    Size size,
    Variant[] variants,
    String base) {
}
