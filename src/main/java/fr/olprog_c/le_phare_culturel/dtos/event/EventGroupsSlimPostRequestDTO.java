package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EventGroupsSlimPostRequestDTO(
        @NotNull @Pattern(regexp = "^[\\p{L}]{2,50}$") @JsonProperty("group_name") String groupName,
        @NotNull @Pattern(regexp = "^\\d{2}:\\d{{2}$") @JsonProperty("time_meet") String timeMeet,

        @NotNull @Size(min = 5, max = 255) String description,

        @Min(2) @Max(255) @JsonProperty("group_size") int groupMaxSize) {

}
