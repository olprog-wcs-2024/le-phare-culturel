package fr.olprog_c.le_phare_culturel.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class EventKeywordDescriptor {
    @JsonProperty("keyword_name")
    String keywordName;
    EventColorDescriptor color = EventColorDescriptor.RED;
}
