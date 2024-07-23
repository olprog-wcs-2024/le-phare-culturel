package fr.olprog_c.le_phare_culturel.api.models;

import java.time.OffsetDateTime;

public record TTiming(
    OffsetDateTime end,
    OffsetDateTime begin) {
}
