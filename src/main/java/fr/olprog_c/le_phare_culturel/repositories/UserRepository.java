package fr.olprog_c.le_phare_culturel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_c.le_phare_culturel.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  boolean existsByEmail(String email);
}
