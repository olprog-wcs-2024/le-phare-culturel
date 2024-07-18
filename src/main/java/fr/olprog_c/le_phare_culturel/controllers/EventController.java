package fr.olprog_c.le_phare_culturel.controllers;

import fr.olprog_c.le_phare_culturel.controllers.routes.EventParametersConstants;
import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import fr.olprog_c.le_phare_culturel.dtos.event.EventEntityResponseDTO;
import fr.olprog_c.le_phare_culturel.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Fetches a single event by its id
     *
     * @param id The id of event to fetch.
     * @return The fetched EventEntityResponseDTO.
     */
    @GetMapping(value = RouteDefinition.Events.EVENTS_WITH_URL)
    public EventEntityResponseDTO getAllEventsWithFiltering(@PathVariable int id) {
        System.out.println("Receive GET Events ID : " + id);
        return eventService.findByID(id);
    }

    /**
     * Fetches all events with a filter. The filter is a map of key-value pairs.
     *
     * @param filters The filter map.
     * @return A list of filtered EventEntityResponseDTO.
     */
    @GetMapping(value = RouteDefinition.Events.EVENTS_URL)
    public List<EventEntityResponseDTO> getAllEventsWithFiltering(@RequestParam Map<String, String> filters) {
        System.out.println("Receive GET Events filters : " + filters);
        int pageNumber = (filters.containsKey(EventParametersConstants.PAGE_NUMBER.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_NUMBER.getStringValue())) : 0);
        int pageSize = (filters.containsKey(EventParametersConstants.PAGE_SIZE.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_SIZE.getStringValue())) : EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());

        return eventService.findAllByFiltering(pageNumber, pageSize, filters);
    }

    /**
     * Fetches all events by filtering tags.
     *
     * @param tags    The tags to filter events.
     * @param filters Additional filters.
     * @return A list of filtered EventEntityResponseDTO.
     */
    @GetMapping(RouteDefinition.Events.TAGS_URL)
    public List<EventEntityResponseDTO> getEventsFromFilteringTags(
            @PathVariable(required = false, name = "tags") String tags,
            @RequestParam Map<String, String> filters) {
        System.out.println("Receive GET Events : " + tags + " :: " + filters);
        int pageNumber = (filters.containsKey(EventParametersConstants.PAGE_NUMBER.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_NUMBER.getStringValue())) : 0);
        int pageSize = (filters.containsKey(EventParametersConstants.PAGE_SIZE.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_SIZE.getStringValue())) : EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());

        return eventService.findAllByFiltering(pageNumber, pageSize, filters);
    }

    /**
     * Fetches all events with a filter. If a tagsFilter is provided,
     * only events containing those tags will be returned.
     *
     * @param tagsFilter The tags to filter events.
     * @param filters    Additional filters.
     * @return A list of filtered EventEntityResponseDTO.
     */
    @GetMapping(RouteDefinition.Events.FILTER_URL)
    public List<EventEntityResponseDTO> getEventsFromFiltering(
            @PathVariable(required = false, name = "filters") String tagsFilter,
            @RequestParam Map<String, String> filters) {
        System.out.println("Receive GET Events filters : " + tagsFilter + " :: " + filters);
        int pageNumber = (filters.containsKey(EventParametersConstants.PAGE_NUMBER.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_NUMBER.getStringValue())) : 0);
        int pageSize = (filters.containsKey(EventParametersConstants.PAGE_SIZE.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_SIZE.getStringValue())) : EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());

        return eventService.findAllByFiltering(pageNumber, pageSize, filters);
    }
}
