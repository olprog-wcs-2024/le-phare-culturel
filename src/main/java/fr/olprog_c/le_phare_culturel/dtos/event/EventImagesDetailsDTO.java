package fr.olprog_c.le_phare_culturel.dtos.event;
/* ****** ****** ****** ****** */
// EventImagesDetailsDTO.java
/* ****** ****** ****** ****** */

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventImagesDetailsDTO {
    private String url;
    private String imageCredits;

    @JsonProperty("url")
    public String getURL() {
        return url;
    }

    @JsonProperty("url")
    public void setURL(String value) {
        this.url = value;
    }

    @JsonProperty("image_credits")
    public String getImageCredits() {
        return imageCredits;
    }

    @JsonProperty("image_credits")
    public void setImageCredits(String value) {
        this.imageCredits = value;
    }
}
