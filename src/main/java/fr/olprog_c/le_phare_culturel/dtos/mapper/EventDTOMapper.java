package fr.olprog_c.le_phare_culturel.dtos.mapper;

import fr.olprog_c.le_phare_culturel.dtos.event.EventEntityResponseDTO;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;

public class EventDTOMapper {

    public static EventEntityResponseDTO convertEntityToResponseDTO(EventEntity eventEntity)
    {
        return new EventEntityResponseDTO();
    }

    public static EventEntity convertEventDtoToEntity( EventEntityResponseDTO responseDTO)
    {
        return new EventEntity();
    }
}
