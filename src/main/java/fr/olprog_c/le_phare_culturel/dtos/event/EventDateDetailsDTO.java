package fr.olprog_c.le_phare_culturel.dtos.event;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

// EventDateDetails.java
/* ****** ****** ****** ****** */
public class EventDateDetailsDTO {
    private double price;
    private LocalDate begin;
    private LocalDate end;

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(double value) {
        this.price = value;
    }

    @JsonProperty("begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getBegin() {
        return begin;
    }

    @JsonProperty("begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setBegin(LocalDate value) {
        this.begin = value;
    }

    @JsonProperty("end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public LocalDate getEnd() {
        return end;
    }

    @JsonProperty("end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setEnd(LocalDate value) {
        this.end = value;
    }
}