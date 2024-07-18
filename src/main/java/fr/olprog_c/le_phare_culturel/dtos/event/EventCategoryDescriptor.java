package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventCategoryDescriptor {
    @JsonProperty("category_name")
    public String categoryName;

    EventColorDescriptor color = EventColorDescriptor.RED;
}
