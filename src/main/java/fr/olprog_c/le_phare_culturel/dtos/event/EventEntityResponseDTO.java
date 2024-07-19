package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventEntityResponseDTO {
    private int id;
    private String uid;
    private String title;
    private String slug;
    private String description;
    private String descriptionLongFr;
    private EventImagesDTO images;

    @JsonProperty("start")
    private String dateDebut;

    @JsonProperty("end")
    private String dateFin;

    private EventColorDescriptor backgroundColor;

    private EventDateInformationDTO eventDateInformationDTO;
    private EventInfoResponseDTO info;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

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

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.description = (value !=null ? value.substring(0, 55):value);
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
    public EventInfoResponseDTO getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(EventInfoResponseDTO value) {
        this.info = value;
    }


    @JsonProperty("images")
    public EventImagesDTO getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(EventImagesDTO value) {
        this.images = value;
    }

    @JsonProperty("dates_calendar")
    public EventDateInformationDTO getDatesCalendar() {
        return eventDateInformationDTO;
    }

    @JsonProperty("dates_calendar")
    public void setDatesCalendar(EventDateInformationDTO value) {
        this.eventDateInformationDTO = value;
    }

    @JsonProperty("start")
    public String getDateDebut() {
        return dateDebut;
    }

    @JsonProperty("start")
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    @JsonProperty("end")
    public String getDateFin() {
        return dateFin;
    }
    @JsonProperty("end")
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @JsonProperty("backgroundColor")
    public EventColorDescriptor getBackgroundColor() {
        return backgroundColor;
    }

    @JsonProperty("backgroundColor")
    public void setBackgroundColor(EventColorDescriptor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}






