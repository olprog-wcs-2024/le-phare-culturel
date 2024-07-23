package fr.olprog_c.le_phare_culturel.controllers;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import fr.olprog_c.le_phare_culturel.services.GroupService;

@RestController
public class GroupController {

  private final GroupService groupService;
  private final EventRepository eventRepository;

  public GroupController(GroupService groupService, EventRepository eventRepository) {
    this.groupService = groupService;
    this.eventRepository = eventRepository;
  }

  // @GetMapping(RouteDefinition.Events.EVENTS_WITH_ID_URL)
  // public List<EventGroupUserEntity> findAll(@PathVariable Long eventid) {
  // return groupService.findByEvent(eventid);
  // }
}
