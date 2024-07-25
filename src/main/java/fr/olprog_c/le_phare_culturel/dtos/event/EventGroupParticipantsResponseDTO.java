package fr.olprog_c.le_phare_culturel.dtos.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.olprog_c.le_phare_culturel.dtos.user.UserSlimResponseDTO;

public record EventGroupParticipantsResponseDTO(
        long id,
        @JsonProperty("group_name") String groupName,
        @JsonProperty("time_meet") String timeMeet,
        @JsonProperty("group_size") int groupMaxSize,
        String description,
        UserSlimResponseDTO author,
        @JsonProperty("event_name") String eventName,
        @JsonProperty("participants") List<UserSlimResponseDTO> participantList,
        @JsonProperty("messages") List<EventMessageSlimDTO> messageList) {
}
