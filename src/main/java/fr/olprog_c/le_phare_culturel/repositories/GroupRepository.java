package fr.olprog_c.le_phare_culturel.repositories;

import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<EventGroupUserEntity, Long> {
	List<EventGroupUserEntity> findByRelatedEvents_Uid(Long eventId);

	Optional<EventGroupUserEntity> findByGroupName(String groupName);

	EventGroupUserEntity findByid(long groupid);
}

