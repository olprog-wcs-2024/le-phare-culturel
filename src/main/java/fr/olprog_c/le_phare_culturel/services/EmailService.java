package fr.olprog_c.le_phare_culturel.services;

import fr.olprog_c.le_phare_culturel.repositories.UserRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

  private final JavaMailSender mailSender;
  private final UserRepository userRepository;

  public EmailService(JavaMailSender mailSender, UserRepository userRepository) {
    this.mailSender = mailSender;
	  this.userRepository = userRepository;
  }

  @Async
  public void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setFrom("no-reply@le-phare-culturel.fr");
    helper.setText(htmlBody, true);

    try {
      mailSender.send(message);
    } catch (MailException e) {
      System.err.println(e.getMessage());
      userRepository.findByEmail(to).ifPresent(user -> {
        user.setUserEnabled(true);
        userRepository.save(user);
      });
    }
  }
}
