package fr.olprog_c.le_phare_culturel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @Query(value = "SELECT * FROM events ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<EventEntity> findRandomEvent();
}
