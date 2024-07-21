package fr.olprog_c.le_phare_culturel.services;

import java.time.Instant;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.olprog_c.le_phare_culturel.repositories.BlacklistedTokenRepository;

@Component
public class TokenCleanupTask {

  private final BlacklistedTokenRepository blacklistedTokenRepository;

  public TokenCleanupTask(BlacklistedTokenRepository blacklistedTokenRepository) {
    this.blacklistedTokenRepository = blacklistedTokenRepository;
  }

  @Scheduled(cron = "0 0 0 * * ?") // tous les jours à minuit
  public void cleanUpExpiredTokens() {
    Instant now = Instant.now();
    blacklistedTokenRepository.deleteAll(blacklistedTokenRepository.findAllByExpiryDateBefore(now));
  }
}
