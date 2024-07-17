package fr.olprog_c.le_phare_culturel.controllers;

import fr.olprog_c.le_phare_culturel.controllers.routes.EventParametersConstants;
import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import fr.olprog_c.le_phare_culturel.dtos.event.EventEntityGETResponseDTO;
import fr.olprog_c.le_phare_culturel.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(
            {
                    RouteDefinition.Events.TAGS_URL,
                    RouteDefinition.Events.FILTER_URL,
                    RouteDefinition.Events.EVENTS_URL
            })
    public List<EventEntityGETResponseDTO> getAllEventsByFiltering(
            @PathVariable(required = false, name = "filters") String tagsFilter,
            @PathVariable(required = false, name = "tags") String tags,
            @RequestParam Map<String, String> filters
    ) {
        int pageNumber = (filters.containsKey(EventParametersConstants.PAGE_NUMBER.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_NUMBER.getIntegerValue())) : 0);
        ;
        int pageSize = (filters.containsKey(EventParametersConstants.PAGE_SIZE.getIntegerValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_SIZE.getIntegerValue())) : 0);

        return eventService.findAllByFiltering(pageNumber, pageSize, filters);
    }
}
