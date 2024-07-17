package fr.olprog_c.le_phare_culturel.dtos.event;

// EventImagesDTO.java
/* ****** ****** ****** ****** */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EventImagesDTO {
    private List<String> thumbnail;
    private String eventImageOriginal;
    private List<EventImagesDetailsDTO> eventImagesDetailsDTOList;

    @JsonProperty("thumbnail")
    public List<String> getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(List<String> value) {
        this.thumbnail = value;
    }

    @JsonProperty("event_image_original")
    public String getEventImageOriginal() {
        return eventImageOriginal;
    }

    @JsonProperty("event_image_original")
    public void setEventImageOriginal(String value) {
        this.eventImageOriginal = value;
    }

    @JsonProperty("event_images_list")
    public List<EventImagesDetailsDTO> getEventImagesList() {
        return eventImagesDetailsDTOList;
    }

    @JsonProperty("event_images_list")
    public void setEventImagesList(List<EventImagesDetailsDTO> value) {
        this.eventImagesDetailsDTOList = value;
    }
}
