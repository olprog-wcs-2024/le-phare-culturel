package fr.olprog_c.le_phare_culturel.services;

import fr.olprog_c.le_phare_culturel.EventFiltersSpecification;
import fr.olprog_c.le_phare_culturel.controllers.routes.EventParametersConstants;
import fr.olprog_c.le_phare_culturel.dtos.event.EventEntityResponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.mapper.EventDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    /**
     * Event Service constructor, injecting the dependent services.
     *
     * @param eventRepository Instance of Event Repository.
     */
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * This method fetches paged results of EventEntity.
     *
     * @param pageNumber Integer specifying zero based page index, must not be negative.
     * @param pageSize   Integer specifying the size of the page to be returned, must not be negative.
     * @return List of EventEntity.
     */
    public List<EventEntity> findAllInLimit(int pageNumber, int pageSize) {
        pageNumber = Math.max(pageNumber, EventParametersConstants.DEFAULT_PAGE_OFFSET.getIntegerValue());
        pageSize = Math.max(pageSize, EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
        return eventRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * This method fetches EventEntity by its ID.
     *
     * @param id Integer specifying the ID of the event, must not be negative.
     * @return EventEntity instance if found, else null.
     */
    public EventEntityResponseDTO findByID(int id) {
        Optional<EventEntity> retrievedEvent = eventRepository.findById(Math.max(0, id));
        if (retrievedEvent.isPresent()) {
            return retrievedEvent.map(EventDTOMapper::convertEntityToResponseDTO).orElse(null);
        }
        return null;
    }

    /**
     * This method fetches paged DTOs of EventEntityGETResponseDTO converted from EventEntity.
     *
     * @param pageNumber Integer specifying zero based page index, must not be negative.
     * @param pageSize   Integer specifying the size of the page to be returned, must not be negative.
     * @return List of EventEntityGETResponseDTO.
     */
    public List<EventEntityResponseDTO> findAllInLimitDTO(int pageNumber, int pageSize) {
        pageNumber = Math.max(pageNumber, EventParametersConstants.DEFAULT_PAGE_OFFSET.getIntegerValue());
        pageSize = Math.max(pageSize, EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
        List<EventEntity> eventEntityList = findAllInLimit(pageNumber, pageSize);

        return eventEntityList.stream().map(EventDTOMapper::convertEntityToResponseDTO).toList();
    }

    /**
     * This method fetches filtered and paged DTOs of EventEntityGETResponseDTO converted from EventEntity.
     *
     * @param pageNumber Integer specifying zero based page index, must not be negative.
     * @param pageSize   Integer specifying the size of the page to be returned, must not be negative.
     * @param filters    Key-Value pair of attributes and their values to filter the events by.
     * @return List of EventEntityGETResponseDTO.
     */
    public List<EventEntityResponseDTO> findAllByFiltering(int pageNumber, int pageSize, Map<String, String> filters) {
        pageNumber = Math.max(pageNumber, EventParametersConstants.DEFAULT_PAGE_OFFSET.getIntegerValue());
        pageSize = Math.max(pageSize, EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        final Specification<EventEntity> filtersSpecificationPredicates =
                EventFiltersSpecification.getFiltersSpecificationPredicates(filters);

        List<EventEntity> filteredEventList = findAllInLimit(pageNumber, pageSize);
        //eventRepository.findEventsBySpecification(filtersSpecificationPredicates).stream().limit(pageSize).toList();
        // .subList(pageNumber * pageSize, (1 + pageNumber) * pageSize);

        return filteredEventList.stream().map(EventDTOMapper::convertEntityToResponseDTO).toList();
    }
}
