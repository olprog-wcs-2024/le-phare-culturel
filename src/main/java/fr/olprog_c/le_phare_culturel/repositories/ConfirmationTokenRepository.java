package fr.olprog_c.le_phare_culturel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_c.le_phare_culturel.entities.ConfirmationTokenEntity;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, Long> {

  Optional<ConfirmationTokenEntity> findByToken(String token);

}
