package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;

import java.time.LocalDate;
import java.util.List;

public class EventEntityGETResponseDTO {
    private String uid;
    private String slug;
    private String description;
    private String descriptionLongFr;
    private EventInfoGETResponseDTO info;

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String value) {
        this.uid = value;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String value) {
        this.slug = value;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.description = value;
    }

    @JsonProperty("description_long_fr")
    public String getDescriptionLongFr() {
        return descriptionLongFr;
    }

    @JsonProperty("description_long_fr")
    public void setDescriptionLongFr(String value) {
        this.descriptionLongFr = value;
    }

    @JsonProperty("info")
    public EventInfoGETResponseDTO getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(EventInfoGETResponseDTO value) {
        this.info = value;
    }
}






