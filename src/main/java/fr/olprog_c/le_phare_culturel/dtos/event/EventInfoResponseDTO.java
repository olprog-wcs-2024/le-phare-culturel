package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;

import java.util.List;

// EventInfoResponseDTO.java
public class EventInfoResponseDTO {
    private String registration;
    private String onlineaccessLink;
    private List<EventLocationPlaceDTO> locationPlace;
    private List<UserEntity> author;
    private List<EventAccessConditionDTO> eventAccessConditionDTOS;
    private String attendancemode;
    private String status;
    private List<String> accessibility;
    private List<EventKeywordDescriptor> keywordsFr;
    private List<EventCategoryDescriptor> categories;
    private EventImagesDTO images;
    private EventDateInformationDTO eventDateInformationDTO;
    private List<EventParticipantsGroupDTO> eventParticipantsGroupDTOS;

    @JsonProperty("registration")
    public String getRegistration() {
        return registration;
    }

    @JsonProperty("registration")
    public void setRegistration(String value) {
        this.registration = value;
    }

    @JsonProperty("onlineaccess_link")
    public String getOnlineaccessLink() {
        return onlineaccessLink;
    }

    @JsonProperty("onlineaccess_link")
    public void setOnlineaccessLink(String value) {
        this.onlineaccessLink = value;
    }

    @JsonProperty("location_place")
    public List<EventLocationPlaceDTO> getLocationPlace() {
        return locationPlace;
    }

    @JsonProperty("location_place")
    public void setLocationPlace(List<EventLocationPlaceDTO> value) {
        this.locationPlace = value;
    }

    @JsonProperty("author")
    public List<UserEntity> getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(List<UserEntity> value) {
        this.author = value;
    }

    @JsonProperty("conditions_fr")
    public List<EventAccessConditionDTO> getEventAccessCondition() {
        return eventAccessConditionDTOS;
    }

    @JsonProperty("conditions_fr")
    public void setEventAccessCondition(List<EventAccessConditionDTO> value) {
        this.eventAccessConditionDTOS = value;
    }

    @JsonProperty("attendancemode")
    public String getAttendancemode() {
        return attendancemode;
    }

    @JsonProperty("attendancemode")
    public void setAttendancemode(String value) {
        this.attendancemode = value;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        this.status = value;
    }

    @JsonProperty("accessibility")
    public List<String> getAccessibility() {
        return accessibility;
    }

    @JsonProperty("accessibility")
    public void setAccessibility(List<String> value) {
        this.accessibility = value;
    }

    @JsonProperty("keywords_fr")
    public List<EventKeywordDescriptor> getKeywordsFr() {
        return keywordsFr;
    }

    @JsonProperty("keywords_fr")
    public void setKeywordsFr(List<EventKeywordDescriptor> value) {
        this.keywordsFr = value;
    }

    @JsonProperty("categories")
    public List<EventCategoryDescriptor> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<EventCategoryDescriptor> value) {
        this.categories = value;
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

    @JsonProperty("participants_groups")
    public List<EventParticipantsGroupDTO> getParticipantsGroups() {
        return eventParticipantsGroupDTOS;
    }

    @JsonProperty("participants_groups")
    public void setParticipantsGroups(List<EventParticipantsGroupDTO> value) {
        this.eventParticipantsGroupDTOS = value;
    }
}

