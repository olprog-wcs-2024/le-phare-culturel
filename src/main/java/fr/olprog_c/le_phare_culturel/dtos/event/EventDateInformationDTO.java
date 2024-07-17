package fr.olprog_c.le_phare_culturel.dtos.event;


/* ****** ****** ****** ****** */
// EventDateInformation.java
/* ****** ****** ****** ****** */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class EventDateInformationDTO {
    private String eventCalendar;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private LocalDate firstdateBegin;
    private LocalDate firstdateEnd;
    private LocalDate lastdateBegin;
    private LocalDate lastdateEnd;
    private EventDateDetailsDTO eventDateDetails;
    private List<EventDatesCalendarDetailDTO> eventDatesCalendarDetailDTOS;

    @JsonProperty("event_calendar")
    public String getEventCalendar() {
        return eventCalendar;
    }

    @JsonProperty("event_calendar")
    public void setEventCalendar(String value) {
        this.eventCalendar = value;
    }

    @JsonProperty("creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setCreationDate(LocalDate value) {
        this.creationDate = value;
    }

    @JsonProperty("update_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getUpdateDate() {
        return updateDate;
    }

    @JsonProperty("update_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setUpdateDate(LocalDate value) {
        this.updateDate = value;
    }

    @JsonProperty("firstdate_begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getFirstdateBegin() {
        return firstdateBegin;
    }

    @JsonProperty("firstdate_begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setFirstdateBegin(LocalDate value) {
        this.firstdateBegin = value;
    }

    @JsonProperty("firstdate_end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getFirstdateEnd() {
        return firstdateEnd;
    }

    @JsonProperty("firstdate_end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setFirstdateEnd(LocalDate value) {
        this.firstdateEnd = value;
    }

    @JsonProperty("lastdate_begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getLastdateBegin() {
        return lastdateBegin;
    }

    @JsonProperty("lastdate_begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setLastdateBegin(LocalDate value) {
        this.lastdateBegin = value;
    }

    @JsonProperty("lastdate_end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getLastdateEnd() {
        return lastdateEnd;
    }

    @JsonProperty("lastdate_end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setLastdateEnd(LocalDate value) {
        this.lastdateEnd = value;
    }

    @JsonProperty("event_date_details")
    public EventDateDetailsDTO getEventDateDetails() {
        return eventDateDetails;
    }

    @JsonProperty("event_date_details")
    public void setEventDateDetails(EventDateDetailsDTO value) {
        this.eventDateDetails = value;
    }

    @JsonProperty("event_dates_calendar_details")
    public List<EventDatesCalendarDetailDTO> getEventDatesCalendarDetails() {
        return eventDatesCalendarDetailDTOS;
    }

    @JsonProperty("event_dates_calendar_details")
    public void setEventDatesCalendarDetails(List<EventDatesCalendarDetailDTO> value) {
        this.eventDatesCalendarDetailDTOS = value;
    }
}