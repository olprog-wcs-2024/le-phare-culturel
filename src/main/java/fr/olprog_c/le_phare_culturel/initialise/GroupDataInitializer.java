package fr.olprog_c.le_phare_culturel.initialise;

import java.time.Instant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import fr.olprog_c.le_phare_culturel.repositories.GroupRepository;
import fr.olprog_c.le_phare_culturel.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
@Order(3)
public class GroupDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final GroupRepository groupRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            UserEntity user1 = userRepository.findByEmail("john-wick@local.com").orElseThrow();
            UserEntity user2 = userRepository.findByEmail("john-doe@local.fr").orElseThrow();

            EventEntity event1 = eventRepository.findRandomEvent().orElseThrow();
            EventEntity event2 = eventRepository.findRandomEvent().orElseThrow();

            EventGroupUserEntity group1 = new EventGroupUserEntity();
            group1.setGroupName("The Hitmen");
            group1.setDescription("A group of professional hitmen");
            group1.setGroupMaxSize(5);
            group1.setReferencedUserAuthor(user1);
            group1.setRelatedEvents(event1);
            group1.setTimeMeet(Instant.now().plusSeconds(3600).toString());
            groupRepository.save(group1);

            EventGroupUserEntity group2 = new EventGroupUserEntity();
            group2.setGroupName("Test Group");
            group2.setDescription("A group of professional hitmen");
            group2.setGroupMaxSize(10);
            group2.setReferencedUserAuthor(user2);
            group2.setRelatedEvents(event2);
            group2.setTimeMeet(Instant.now().plusSeconds(3600).toString());
            groupRepository.save(group2);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
