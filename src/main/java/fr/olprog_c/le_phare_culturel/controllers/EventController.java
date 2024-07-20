package fr.olprog_c.le_phare_culturel.controllers;

import fr.olprog_c.le_phare_culturel.controllers.routes.EventParametersConstants;
import fr.olprog_c.le_phare_culturel.controllers.routes.RouteDefinition;
import fr.olprog_c.le_phare_culturel.dtos.event.*;
import fr.olprog_c.le_phare_culturel.services.EventService;
import jdk.jfr.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Fetches a single event by its id
     *
     * @param eventid The id of event to fetch.
     * @return The fetched EventEntityResponseDTO.
     */
    @GetMapping(value = RouteDefinition.Events.EVENTS_WITH_ID_URL)
    public ResponseEntity<EventEntityResponseDTO> getEventsByID(@PathVariable int eventid) {
        System.out.println("Receive GET Events ID : " + eventid);
        return ResponseEntity.ok(eventService.findByID(eventid));
    }

    /**
     * Fetches a single event by its id. Currently returns an empty {@link EventEntityResponseDTO} object.
     *
     * @param eventid The id of the event to fetch.
     * @return An empty {@link EventEntityResponseDTO} object.
     */
    @GetMapping(value = RouteDefinition.Events.EVENTS_WITH_ID_GROUP_LIST_URL)
    public ResponseEntity<EventEntityResponseDTO> getEventByIDGroupList(@PathVariable int eventid) {
        System.out.println("Receive GET Events ID for Group List : " + eventid);
        // ROOT NODE
        EventEntityResponseDTO responseDTO = new EventEntityResponseDTO();

        responseDTO.setTitle("Event " + (new Random())
                .ints(127, 1826)
                .findFirst().getAsInt());

        responseDTO.setDateDebut((new Date()).toString());
        responseDTO.setDateFin((new Date()).toString());

        responseDTO.setId((new Random())
                .ints(127, 1826)
                .findFirst().getAsInt()
        );
        // PREPARE GROUP FOR EVENT INFO
        List<EventParticipantsGroupDTO> groupParticipant = new ArrayList<EventParticipantsGroupDTO>();

        EventParticipantsGroupDTO eventParticipantsGroup = new EventParticipantsGroupDTO();


        eventParticipantsGroup.setID((long) (new Random())
                .ints(1, 22)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setCount((long) (new Random())
                .ints(1, 32)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setName("Group name (" + Arrays.toString(Character.toChars(65 +
                (new Random())
                        .ints(1, 8)
                        .findFirst().getAsInt()
        ))
                + ")");
        groupParticipant.add(eventParticipantsGroup);
        /* ****** ****** ****** ****** */

        eventParticipantsGroup.setID((long) (new Random())
                .ints(1, 22)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setCount((long) (new Random())
                .ints(1, 32)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setName("Group name (" + Arrays.toString(Character.toChars(65 +
                (new Random())
                        .ints(1, 8)
                        .findFirst().getAsInt()
        ))
                + ")");
        groupParticipant.add(eventParticipantsGroup);
        /* ****** ****** ****** ****** */
        eventParticipantsGroup.setID((long) (new Random())
                .ints(1, 22)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setCount((long) (new Random())
                .ints(1, 32)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setName("Group name (" + Arrays.toString(Character.toChars(65 +
                (new Random())
                        .ints(1, 8)
                        .findFirst().getAsInt()
        ))
                + ")");
        groupParticipant.add(eventParticipantsGroup);
        /* ****** ****** ****** ****** */

        EventInfoResponseDTO eventInfoResponseDTO = new EventInfoResponseDTO();

         responseDTO.setTitle("Event " + (new Random())
                .ints(127, 1826)
                .findFirst().getAsInt());
        responseDTO.setDateDebut((new Date()).toString());
        responseDTO.setDateFin((new Date()).toString());


        EventCategoryDescriptor eventCategory = new EventCategoryDescriptor();
        List<EventCategoryDescriptor> categoryDescriptorList = new ArrayList<EventCategoryDescriptor>();

        eventCategory.setCategoryName("Yolo");
        eventCategory.setColor(EventColorDescriptor.RED);
        categoryDescriptorList.add(eventCategory);
        eventInfoResponseDTO.setCategories(categoryDescriptorList);
        /* ****** ****** ****** ****** */
        eventInfoResponseDTO.setParticipantsGroups(groupParticipant);
        // SET SUBNODE CONTAINING ALL INFORMATION FORMAT
        responseDTO.setInfo(eventInfoResponseDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Currently returns an empty {@link EventEntityResponseDTO} object.
     *
     * @param eventid The id of event to fetch.
     * @param groupid The id of backend group to fetch.
     * @return An empty {@link EventEntityResponseDTO} object.
     */
    @GetMapping(value = RouteDefinition.Events.EVENTS_WITH_ID_GROUP_WITH_ID_URL)
    public ResponseEntity<EventEntityResponseDTO> getEventByIDGroupByID(@PathVariable int eventid, @PathVariable int groupid) {
        System.out.println("Receive GET Events ID for Group ID : " + eventid + " :: " + groupid);
        EventEntityResponseDTO responseDTO = new EventEntityResponseDTO();

         responseDTO.setTitle("Event " + (new Random())
                .ints(127, 1826)
                .findFirst().getAsInt());
        responseDTO.setDateDebut((new Date()).toString());
        responseDTO.setDateFin((new Date()).toString());


        responseDTO.setId((new Random())
                .ints(127, 1826)
                .findFirst().getAsInt()
        );

        // PREPARE GROUP FOR EVENT INFO
        List<EventParticipantsGroupDTO> groupParticipant = new ArrayList<EventParticipantsGroupDTO>();
        EventParticipantsGroupDTO eventParticipantsGroup = new EventParticipantsGroupDTO();

        eventParticipantsGroup.setID((long) (new Random())
                .ints(1, 22)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setCount((long) (new Random())
                .ints(1, 32)
                .findFirst().getAsInt()
        );
        eventParticipantsGroup.setName("Group name (" + Arrays.toString(Character.toChars(65 +
                        (new Random())
                                .ints(1, 8)
                                .findFirst().getAsInt()
                )
        )
                + ")");
        groupParticipant.add(eventParticipantsGroup);
        /* ****** ****** ****** ****** */

        EventInfoResponseDTO eventInfoResponseDTO = new EventInfoResponseDTO();

         responseDTO.setTitle("Event " + (new Random())
                .ints(127, 1826)
                .findFirst().getAsInt());
        responseDTO.setDateDebut((new Date()).toString());
        responseDTO.setDateFin((new Date()).toString());


        eventInfoResponseDTO.setParticipantsGroups(groupParticipant);
        // SET SUBNODE CONTAINING ALL INFORMATION FORMAT

        EventCategoryDescriptor eventCategory = new EventCategoryDescriptor();
        List<EventCategoryDescriptor> categoryDescriptorList = new ArrayList<EventCategoryDescriptor>();

        eventCategory.setCategoryName("Youpi");
        eventCategory.setColor(EventColorDescriptor.DARK_BLUE);
        categoryDescriptorList.add(eventCategory);
        eventInfoResponseDTO.setCategories(categoryDescriptorList);
        /* ****** ****** ****** ****** */


        responseDTO.setInfo(eventInfoResponseDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Fetches all events with a filter. The filter is a map of key-value pairs.
     *
     * @param filters The filter map.
     * @return A list of filtered EventEntityResponseDTO.
     */
    @GetMapping(value = RouteDefinition.Events.EVENTS_URL)
    public ResponseEntity<Iterable<EventEntityResponseDTO>> getAllEventsWithFiltering(@RequestParam Map<String, String> filters) {
        int pageNumber = (filters.containsKey(EventParametersConstants.PAGE_NUMBER.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_NUMBER.getStringValue())) : 0);
        int pageSize = (filters.containsKey(EventParametersConstants.PAGE_SIZE.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_SIZE.getStringValue())) : EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
        System.out.println("Receive GET Events filters : " + filters + " :: pageNumber" + pageNumber + " :: pageSize :: " + pageSize);

        return ResponseEntity.ok(eventService.findAllByFiltering(pageNumber, pageSize, filters));
    }

    /**
     * Fetches all events by filtering tags.
     *
     * @param tags    The tags to filter events.
     * @param filters Additional filters.
     * @return A list of filtered EventEntityResponseDTO.
     */
    @GetMapping(RouteDefinition.Events.TAGS_URL)
    public ResponseEntity<Iterable<EventEntityResponseDTO>> getEventsFromFilteringTags(
            @PathVariable(required = false, name = "tags") String tags,
            @RequestParam Map<String, String> filters) {
        int pageNumber = (filters.containsKey(EventParametersConstants.PAGE_NUMBER.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_NUMBER.getStringValue())) : 0);
        int pageSize = (filters.containsKey(EventParametersConstants.PAGE_SIZE.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_SIZE.getStringValue())) : EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
        System.out.println("Receive GET Events TAGS filters : " + filters + " :: pageNumber" + pageNumber + " :: pageSize :: " + pageSize);

        return ResponseEntity.ok(eventService.findAllByFiltering(pageNumber, pageSize, filters));
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
    public ResponseEntity<Iterable<EventEntityResponseDTO>> getEventsFromFiltering(
            @PathVariable(required = false, name = "filters") String tagsFilter,
            @RequestParam Map<String, String> filters) {

        int pageNumber = (filters.containsKey(EventParametersConstants.PAGE_NUMBER.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_NUMBER.getStringValue())) : 0);
        int pageSize = (filters.containsKey(EventParametersConstants.PAGE_SIZE.getStringValue()) ? Integer.parseInt(filters.get(EventParametersConstants.PAGE_SIZE.getStringValue())) : EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
        System.out.println("Receive GET Events filters : " + tagsFilter + " :: " + filters + " :: pageNumber" + pageNumber + " :: pageSize :: " + pageSize);

        return ResponseEntity.ok(eventService.findAllByFiltering(pageNumber, pageSize, filters));
    }
}
