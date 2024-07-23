package fr.olprog_c.le_phare_culturel.initialise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserMessageEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.repositories.GroupRepository;
import fr.olprog_c.le_phare_culturel.repositories.MessageRepository;
import fr.olprog_c.le_phare_culturel.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
@Order(4)
public class MessageDataInitializer implements CommandLineRunner {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            UserEntity user1 = userRepository.findByEmail("john-wick@local.com").orElseThrow();
            UserEntity user2 = userRepository.findByEmail("john-doe@local.fr").orElseThrow();

            EventGroupUserEntity group1 = groupRepository.findByGroupName("The Hitmen").orElseThrow();
            EventGroupUserEntity group2 = groupRepository.findByGroupName("Test Group").orElseThrow();

            EventGroupUserMessageEntity message1 = new EventGroupUserMessageEntity();
            message1.setMessageText("Hello there");
            message1.setReferencedUserAuthor(user1);
            message1.setRelatedEventsGroups(group1);
            messageRepository.save(message1);

            EventGroupUserMessageEntity message2 = new EventGroupUserMessageEntity();
            message2.setMessageText("Hello there");
            message2.setReferencedUserAuthor(user2);
            message2.setRelatedEventsGroups(group2);
            messageRepository.save(message2);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
