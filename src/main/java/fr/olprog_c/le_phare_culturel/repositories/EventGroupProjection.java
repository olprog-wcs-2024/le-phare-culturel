package fr.olprog_c.le_phare_culturel.repositories;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;

public interface EventGroupProjection {
    EventEntity getEvent();

    Long getGroupId();
}
