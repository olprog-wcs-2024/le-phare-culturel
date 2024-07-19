package fr.olprog_c.le_phare_culturel.dtos.event;
/* ****** ****** ****** ****** */
// EventDatesCalendarDetail.java
/* ****** ****** ****** ****** */
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EventDatesCalendarDetailDTO {
    private String title;
    private List<EventDetailsDTO> eventDetailsDTOS;

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String value) {
        this.title = value;
    }

    @JsonProperty("WeekDays")
    public List<EventDetailsDTO> getWeekDays() {
        return eventDetailsDTOS;
    }

    @JsonProperty("WeekDays")
    public void setWeekDays(List<EventDetailsDTO> value) {
        this.eventDetailsDTOS = value;
    }
}
