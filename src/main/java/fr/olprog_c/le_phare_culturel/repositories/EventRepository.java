package fr.olprog_c.le_phare_culturel.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @Query(value = "SELECT * FROM events ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<EventEntity> findRandomEvent();

    // filtrer les événements par lasttiming l'attribut end
    Page<EventEntity> findAllByOrderByLastTiming_endAsc(Pageable pageable);

}
