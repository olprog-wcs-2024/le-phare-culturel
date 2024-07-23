
package fr.olprog_c.le_phare_culturel.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Event(
    Image image,
    boolean featured,
    String dateRange,
    String imageCredits,
    OriginAgenda originAgenda,
    String description,
    String longDescription,
    String title,
    String tarifs,
    String slug,
    long uid,
    TTiming lastTiming,
    TTiming firstTiming,
    Location location,
    @JsonProperty("type-devenement") int typeDevenement,
    long status) {
}
