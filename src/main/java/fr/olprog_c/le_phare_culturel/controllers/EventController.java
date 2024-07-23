package fr.olprog_c.le_phare_culturel.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import fr.olprog_c.le_phare_culturel.dtos.event.EventDetailSlimReponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.event.EventResponseWithoutGroupDTO;
import fr.olprog_c.le_phare_culturel.dtos.mapper.EventDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.services.EventService;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = RouteDefinition.Events.EVENTS_WITH_ID_URL)
    public ResponseEntity<EventDetailSlimReponseDTO> getEventsByID(@PathVariable long eventid) {
        return eventService.findByID(eventid)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    @GetMapping(value = RouteDefinition.Events.EVENTS_URL)
    public ResponseEntity<EventResponseWithoutGroupDTO> getAllEvents(
            @RequestParam(required = false, name = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, name = "size", defaultValue = "20") Integer pageSize) {

        Page<EventEntity> events = eventService.findAll(pageNumber, pageSize);

        System.out.println(events.getSize());

        return ResponseEntity.ok(EventDTOMapper.convertPageToResponseWithoutGroupDTO(events));

    }

}
