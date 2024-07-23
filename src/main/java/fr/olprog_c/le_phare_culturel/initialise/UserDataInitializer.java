package fr.olprog_c.le_phare_culturel.initialise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.enums.UserRoleEnum;
import fr.olprog_c.le_phare_culturel.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
@Order(2)
public class UserDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {

            UserEntity user1 = new UserEntity();
            user1.setFirstName("John");
            user1.setLastName("Wick");
            user1.setProfileDescription("I am a professional hitman");
            user1.setProfileNickname("John Wick");
            user1.setEmail("john-wick@local.com");
            user1.setUserEnabled(true);
            user1.setPassword(passwordEncoder.encode("password"));
            user1.setUserRole(UserRoleEnum.ADMIN);
            userRepository.save(user1);

            UserEntity user2 = new UserEntity();
            user2.setFirstName("John");
            user2.setLastName("Doe");
            user2.setProfileDescription("I am a professional hitman");
            user2.setProfileNickname("John Doe");
            user2.setEmail("john-doe@local.fr");
            user2.setUserEnabled(true);
            user2.setPassword(passwordEncoder.encode("password"));
            user2.setUserRole(UserRoleEnum.USER);
            userRepository.save(user2);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
