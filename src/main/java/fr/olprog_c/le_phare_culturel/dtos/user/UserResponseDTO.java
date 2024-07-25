package fr.olprog_c.le_phare_culturel.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.olprog_c.le_phare_culturel.dtos.event.EventGroupSlimDTO;
import fr.olprog_c.le_phare_culturel.dtos.event.EventsSlimIDDto;

import java.util.Collection;

public record UserResponseDTO(
        @JsonProperty("firstname") String firstName,

        @JsonProperty("lastname") String lastName,

        @JsonProperty("nickname") String profileNickname,

        @JsonProperty("email") String email,

        @JsonProperty("avatar") String avatar,

        @JsonProperty("description") String profileDescription,

        @JsonProperty("groups")Collection<EventGroupSlimDTO> groups,

        @JsonProperty("events_past")Collection<EventsSlimIDDto> eventsPast,

        @JsonProperty("events_future")Collection<EventsSlimIDDto> eventsFuture

        ) {
}
