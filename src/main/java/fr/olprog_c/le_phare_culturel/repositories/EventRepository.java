package fr.olprog_c.le_phare_culturel.repositories;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends
        JpaRepository<EventEntity, Integer>,
        JpaSpecificationExecutor<EventEntity>
{
    List<EventEntity> findEventsBySpecification(Specification<EventEntity> specification);
}
