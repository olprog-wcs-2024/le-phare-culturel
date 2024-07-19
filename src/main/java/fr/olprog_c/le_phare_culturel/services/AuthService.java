package fr.olprog_c.le_phare_culturel.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import fr.olprog_c.le_phare_culturel.dtos.AuthRegisterPostDTO;
import fr.olprog_c.le_phare_culturel.dtos.mapper.AuthDTOMapper;
import fr.olprog_c.le_phare_culturel.entities.ConfirmationTokenEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.repositories.UserRepository;
import jakarta.mail.MessagingException;

@Service
public class AuthService {

    @Value("${app.url}")
    private String appUrl;

    private final AuthDTOMapper authDTOMapper;
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;
    /* ****** ****** ****** ****** */
    public AuthService(AuthDTOMapper authDTOMapper, UserRepository userRepository, ConfirmationTokenService confirmationTokenService, EmailService emailService) {
        this.authDTOMapper = authDTOMapper;
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
    }
    /* ****** ****** ****** ****** */
    public static String passwordEncoding(String passwordToEncode) {
        return (new BCryptPasswordEncoder()).encode(passwordToEncode);
    }
    /* ****** ****** ****** ****** */
    public void register(AuthRegisterPostDTO authRegisterPostDTO) throws HttpServerErrorException {
        try {
            UserEntity user = authDTOMapper.registerDtoToEntity(authRegisterPostDTO);

            if (this.userRepository.existsByEmail(user.getEmail())) {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Email déjà présent.");
            }

            this.userRepository.save(user);

            ConfirmationTokenEntity token = new ConfirmationTokenEntity(user);
            confirmationTokenService.saveConfirmationToken(token);

            String link = appUrl + "/confirm?token=" + token.getToken();
            String htmlBody = buildEmail(user.getProfileNickname(), link);
            try {
                emailService.sendHtmlMessage(user.getEmail(), "Confirm your email", htmlBody);
            } catch (MessagingException e) {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    /* ****** ****** ****** ****** */
    public void confirmUser(String token) {
        Optional<ConfirmationTokenEntity> optionalToken = confirmationTokenService.getToken(token);
        optionalToken.ifPresent(t -> {
            UserEntity user = t.getUser();
            user.setUserEnabled(true);
            userRepository.save(user);
            confirmationTokenService.deleteToken(t.getId());
        });
    }
    /* ****** ****** ****** ****** */
    private String buildEmail(String name, String link) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                <style>
                  body { font-family: Arial, sans-serif; }
                  .header { background-color: #f2f2f2; padding: 10px; text-align: center; }
                  .content { margin: 20px; }
                  .footer { background-color: #f2f2f2; padding: 10px; text-align: center; font-size: 12px; }
                </style>
                </head>
                <body>
                <div class='header'>
                  <img src='https://imgur.com/2V6Aodm.png' alt='Logo' style='width:100px;'>
                </div>
                <div class='content'>
                  <h1>Bienvenue %s !</h1>
                  <p>Merci de vous être enregistré. Veuillez cliquer sur le lien ci-dessous pour activer votre compte :</p>
                  <a href='%s'>Activez maintenant</a>
                </div>
                <div class='footer'>
                  <p>&copy; %d - Le Phare Culturel. All rights reserved.</p>
                </div>
                </body>
                </html>
                """.formatted(name, link, LocalDate.now().getYear());
    }

}
