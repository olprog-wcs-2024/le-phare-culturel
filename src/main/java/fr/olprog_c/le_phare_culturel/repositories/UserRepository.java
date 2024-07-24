package fr.olprog_c.le_phare_culturel.repositories;

import java.util.List;
import java.util.Optional;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  boolean existsByEmail(String email);

  @Query("SELECT e FROM EventEntity e JOIN e.referencedEventGroups g WHERE g.referencedUserAuthor = :user OR :user MEMBER OF g.referencedUserList")
  List<EventEntity> findAllEventsByUser(UserEntity user, Sort sort);
}
