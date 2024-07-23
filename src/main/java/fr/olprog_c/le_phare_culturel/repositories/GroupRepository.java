package fr.olprog_c.le_phare_culturel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;

public interface GroupRepository extends JpaRepository<EventGroupUserEntity, Long> {
    List<EventGroupUserEntity> findByRelatedEvents_Uid(Long eventId);

    Optional<EventGroupUserEntity> findByGroupName(String groupName);

}
