package fr.olprog_c.le_phare_culturel.dtos.event;
/* ****** ****** ****** ****** */
// EventParticipantsGroupDTO.java
/* ****** ****** ****** ****** */
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;

import java.util.List;

public class EventParticipantsGroupDTO {
    private long id;
    private String name;
    private long count;
    private List<UserEntity> participantList;

    @JsonProperty("id")
    public long getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(long value) {
        this.id = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("count")
    public long getCount() {
        return (participantList != null) ? (long) participantList.size() : 0;
    }

    @JsonProperty("count")
    public void setCount(long value) {
        this.count = value;
    }

    @JsonProperty("users")
    public List<UserEntity> getParticipantList() {
        return participantList;
    }

    @JsonProperty("users")
    public void setParticipantList(List<UserEntity> value) {
        this.participantList = value;
    }
}