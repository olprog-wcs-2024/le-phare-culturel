package fr.olprog_c.le_phare_culturel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import fr.olprog_c.le_phare_culturel.repositories.GroupRepository;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final EventRepository eventRepository;

    public GroupService(GroupRepository groupRepository, EventRepository eventRepository) {
        this.groupRepository = groupRepository;
        this.eventRepository = eventRepository;
    }

    public List<EventGroupUserEntity> findAll() {
        return groupRepository.findAll();
    }

    public List<EventGroupUserEntity> findByEvent(long eventid) {
        return groupRepository.findByRelatedEvents_Uid(eventid);
    }

}
