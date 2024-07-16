package fr.olprog_c.le_phare_culturel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.dtos.AuthRegisterPostDTO;
import fr.olprog_c.le_phare_culturel.dtos.mapper.AuthDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.ConfirmationTokenEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.exceptions.CustomException;
import fr.olprog_c.le_phare_culturel.repositories.UserRepository;

@Service
public class AuthService {

  @Value("${app.url}")
  private String appUrl;

  private final AuthDTOMapper authDTOMapper;
  private final UserRepository userRepository;
  private final ConfirmationTokenService confirmationTokenService;
  private final EmailService emailService;

  public AuthService(AuthDTOMapper authDTOMapper, UserRepository userRepository,
      ConfirmationTokenService confirmationTokenService, EmailService emailService) {
    this.authDTOMapper = authDTOMapper;
    this.userRepository = userRepository;
    this.confirmationTokenService = confirmationTokenService;
    this.emailService = emailService;
  }

  public void register(AuthRegisterPostDTO authRegisterPostDTO) throws CustomException {
    try {
      UserEntity user = authDTOMapper.registerDtoToEntity(authRegisterPostDTO);
      this.userRepository.save(user);

      ConfirmationTokenEntity token = new ConfirmationTokenEntity(user);
      confirmationTokenService.saveConfirmationToken(token);

      String link = appUrl + "/confirm?token=" + token.getToken();
      emailService.sendSimpleMessage(user.getEmail(), "Confirm your email", link);

    } catch (Exception e) {
      throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  public void confirmUser(String token) {
    Optional<ConfirmationTokenEntity> optionalToken = confirmationTokenService.getToken(token);
    optionalToken.ifPresent(t -> {
      UserEntity user = t.getUser();
      user.setUserEnabled(true);
      userRepository.save(user);
      confirmationTokenService.deleteToken(t.getId());
    });
  }

}
