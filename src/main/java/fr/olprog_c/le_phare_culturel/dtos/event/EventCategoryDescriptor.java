package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

public class EventCategoryDescriptor {
    @JsonProperty("category_name")
    private String categoryName;

    private EventColorDescriptor color = EventColorDescriptor.RED;

    @JsonProperty("category_name")
    public String getCategoryName() {
        return categoryName;
    }

    @JsonProperty("category_name")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public EventColorDescriptor getColor() {
        return color;
    }

    public void setColor(EventColorDescriptor color) {
        this.color = color;
    }
}
