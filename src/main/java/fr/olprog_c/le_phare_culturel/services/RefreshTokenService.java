package fr.olprog_c.le_phare_culturel.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.entities.RefreshToken;
import fr.olprog_c.le_phare_culturel.repositories.RefreshTokenRepository;

@Service
public class RefreshTokenService {

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  public RefreshToken createRefreshToken(String username) {
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken.setUsername(username);
    refreshToken.setExpiryDate(Instant.now().plusMillis(604800000)); // 7 jours
    return refreshTokenRepository.save(refreshToken);
  }

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public void deleteByToken(String token) {
    refreshTokenRepository.deleteByToken(token);
  }

  public void verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().isBefore(Instant.now())) {
      refreshTokenRepository.delete(token);
      throw new RuntimeException("Refresh token is expired");
    }
  }
}
