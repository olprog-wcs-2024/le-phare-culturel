package fr.olprog_c.le_phare_culturel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.dtos.event.EventDetailSlimReponseDTO;
import fr.olprog_c.le_phare_culturel.dtos.mapper.EventDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    public Page<EventEntity> findAll(int pageNumber, int pageSize) {
        return eventRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Optional<EventDetailSlimReponseDTO> findByID(long id) {
        Optional<EventEntity> retrievedEvent = eventRepository.findById(Math.max(0, id));
        if (retrievedEvent.isPresent()) {
            return retrievedEvent.map(EventDTOMapper::convertDetailSlimResponseDTO);
        }
        return Optional.empty();
    }

    // public List<EventEntityResponseDTO> findAllInLimitDTO(int pageNumber, int
    // pageSize) {
    // pageNumber = Math.max(pageNumber,
    // EventParametersConstants.DEFAULT_PAGE_OFFSET.getIntegerValue());
    // pageSize = Math.max(pageSize,
    // EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
    // List<EventEntity> eventEntityList = findAllInLimit(pageNumber, pageSize);
    //
    // return
    // eventEntityList.stream().map(EventDTOMapper::convertEntityToResponseDTO).toList();
    // }
    //
    // public List<EventEntityResponseDTO> findAllByFiltering(int pageNumber, int
    // pageSize, Map<String, String> filters) {
    // pageNumber = Math.max(pageNumber,
    // EventParametersConstants.DEFAULT_PAGE_OFFSET.getIntegerValue());
    // pageSize = Math.max(pageSize,
    // EventParametersConstants.DEFAULT_PAGE_SIZE.getIntegerValue());
    // Pageable pageable = PageRequest.of(pageNumber, pageSize,
    // Sort.by("id").descending());
    //
    // final Specification<EventEntity> filtersSpecificationPredicates =
    // EventFiltersSpecification
    // .getFiltersSpecificationPredicates(filters);
    //
    // List<EventEntity> filteredEventList = findAllInLimit(pageNumber, pageSize);
    // //
    // eventRepository.findEventsBySpecification(filtersSpecificationPredicates).stream().limit(pageSize).toList();
    // // .subList(pageNumber * pageSize, (1 + pageNumber) * pageSize);
    //
    // return
    // filteredEventList.stream().map(EventDTOMapper::convertEntityToResponseDTO).toList();
    // }

}
