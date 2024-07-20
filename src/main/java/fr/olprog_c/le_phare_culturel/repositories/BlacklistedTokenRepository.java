package fr.olprog_c.le_phare_culturel.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.olprog_c.le_phare_culturel.entities.BlacklistedToken;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, String> {
  Optional<BlacklistedToken> findByToken(String token);

  @Query("SELECT bt FROM BlacklistedToken bt WHERE bt.expiryDate < :expiryDate")
  List<BlacklistedToken> findAllByExpiryDateBefore(@Param("expiryDate") Instant expiryDate);

}
