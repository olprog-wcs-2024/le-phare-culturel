package fr.olprog_c.le_phare_culturel.dtos.event;
/* ****** ****** ****** ****** */
// EventAccessConditionDTO.java
/* ****** ****** ****** ****** */
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventAccessConditionDTO {
    private long ageMin;
    private long ageMax;

    @JsonProperty("age_min")
    public long getAgeMin() {
        return ageMin;
    }

    @JsonProperty("age_min")
    public void setAgeMin(long value) {
        this.ageMin = value;
    }

    @JsonProperty("age_max")
    public long getAgeMax() {
        return ageMax;
    }

    @JsonProperty("age_max")
    public void setAgeMax(long value) {
        this.ageMax = value;
    }
}

