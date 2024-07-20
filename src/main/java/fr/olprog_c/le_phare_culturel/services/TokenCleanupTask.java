package fr.olprog_c.le_phare_culturel.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.olprog_c.le_phare_culturel.repositories.BlacklistedTokenRepository;
import fr.olprog_c.le_phare_culturel.repositories.RefreshTokenRepository;

@Component
public class TokenCleanupTask {

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private BlacklistedTokenRepository blacklistedTokenRepository;

  @Scheduled(cron = "0 0 0 * * ?") // tous les jours à minuit
  public void cleanUpExpiredTokens() {
    Instant now = Instant.now();
    refreshTokenRepository.deleteAll(refreshTokenRepository.findAllByExpiryDateBefore(now));
    blacklistedTokenRepository.deleteAll(blacklistedTokenRepository.findAllByExpiryDateBefore(now));
  }
}
