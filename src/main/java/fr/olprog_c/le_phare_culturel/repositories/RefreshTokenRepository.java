package fr.olprog_c.le_phare_culturel.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.olprog_c.le_phare_culturel.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
  Optional<RefreshToken> findByToken(String token);

  @Query("SELECT rt FROM RefreshToken rt WHERE rt.expiryDate < :expiryDate")
  List<RefreshToken> findAllByExpiryDateBefore(@Param("expiryDate") Instant expiryDate);

  void deleteByToken(String token);
}
