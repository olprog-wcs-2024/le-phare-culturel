package fr.olprog_c.le_phare_culturel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.entities.ConfirmationTokenEntity;
import fr.olprog_c.le_phare_culturel.repositories.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;

  public void saveConfirmationToken(ConfirmationTokenEntity token) {
    confirmationTokenRepository.save(token);
  }

  public Optional<ConfirmationTokenEntity> getToken(String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  public void deleteToken(Long id) {
    confirmationTokenRepository.deleteById(id);
  }

}
