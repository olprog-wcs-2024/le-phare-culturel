package fr.olprog_c.le_phare_culturel.api.models;

public record EventResponse(
    int total,
    Event[] events) {
}
