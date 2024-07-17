package fr.olprog_c.le_phare_culturel.dtos.event;
/* ****** ****** ****** ****** */
// EventDetailsDTO.java
/* ****** ****** ****** ****** */
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventDetailsDTO {
    private String daysName;
    private long daysNumber;
    private double price;

    @JsonProperty("days_name")
    public String getDaysName() {
        return daysName;
    }

    @JsonProperty("days_name")
    public void setDaysName(String value) {
        this.daysName = value;
    }

    @JsonProperty("days_number")
    public long getDaysNumber() {
        return daysNumber;
    }

    @JsonProperty("days_number")
    public void setDaysNumber(long value) {
        this.daysNumber = value;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(double value) {
        this.price = value;
    }
}
