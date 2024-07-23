package fr.olprog_c.le_phare_culturel.dtos.event;

import java.util.List;

import fr.olprog_c.le_phare_culturel.entities.UserEntity;

public record EventParticipantsGroupDTO(
    long id,
    String name,
    long count,
    List<UserEntity> participantList,
    List<EventMessageDTO> messages) {
  public Integer getCount() {
    return participantList.size();
  }
}
