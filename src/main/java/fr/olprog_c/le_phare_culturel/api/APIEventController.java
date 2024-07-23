package fr.olprog_c.le_phare_culturel.api;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_c.le_phare_culturel.api.services.APIEventService;

@RestController
public class APIEventController {

    private final APIEventService apiEventService;

    public APIEventController(APIEventService apiEventService) {
        this.apiEventService = apiEventService;
    }

    // @GetMapping("/get-events")
    // public Mono<EventResponse> getEvents() {
    // Mono<EventResponse> response = apiEventService.fetchEvents();
    // response.subscribe(eventResponse -> {
    // List<Event> events = List.of(eventResponse.events());
    // apiEventService.save(events);
    // });
    // return response;
    // }
}
