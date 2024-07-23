package fr.olprog_c.le_phare_culturel.initialise;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserEntity;
import fr.olprog_c.le_phare_culturel.entities.EventGroupUserMessageEntity;
import fr.olprog_c.le_phare_culturel.entities.UserEntity;
import fr.olprog_c.le_phare_culturel.enums.UserRoleEnum;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import fr.olprog_c.le_phare_culturel.repositories.GroupRepository;
import fr.olprog_c.le_phare_culturel.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
// @Component
// @Order(2)
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<UserEntity> users = createUsers();
        List<EventEntity> events = eventRepository.findAll();

        if (events.isEmpty()) {
            System.out.println("No events found. Make sure the events are loaded first.");
            return;
        }

        List<EventGroupUserEntity> groups = createGroups(users, events);
        createMessages(groups, users);
    }

    private List<UserEntity> createUsers() {
        List<UserEntity> users = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            UserEntity user = new UserEntity();
            user.setFirstName("User" + i);
            user.setLastName("Last" + i);
            user.setEmail("user" + i + "@example.com");
            user.setProfileDescription("Description for User" + i);
            user.setProfileNickname("Nickname" + i);
            user.setPassword(passwordEncoder.encode("password"));
            user.setUserRole(i == 1 ? UserRoleEnum.ADMIN : UserRoleEnum.USER);
            user.setUserEnabled(true);
            userRepository.save(user);
            users.add(user);
        }

        return users;
    }

    private List<EventGroupUserEntity> createGroups(List<UserEntity> users, List<EventEntity> events) {
        List<EventGroupUserEntity> groups = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            EventGroupUserEntity group = new EventGroupUserEntity();
            group.setGroupName("Group_" + i + "_" + Instant.now().toEpochMilli());
            group.setDescription("Description for Group " + i);
            group.setGroupSize(5 + i);
            group.setReferencedUserAuthor(users.get(i % users.size()));
            group.setRelatedEvents(events.get(i % events.size()));
            group.setTimeMeet(Instant.now().plusSeconds(3600 * i));
            groupRepository.save(group);
            groups.add(group);
        }

        return groups;
    }

    private void createMessages(List<EventGroupUserEntity> groups, List<UserEntity> users) {
        for (EventGroupUserEntity group : groups) {
            List<EventGroupUserMessageEntity> messages = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                EventGroupUserMessageEntity message = new EventGroupUserMessageEntity();
                message.setMessageText("Message " + i + " for " + group.getGroupName());
                message.setRelatedEventsGroups(group);
                message.setReferencedUserAuthor(users.get(i % users.size()));
                messages.add(message);
            }
            group.setReferencedGroupsMessages(messages);
            groupRepository.save(group);
        }
    }
}
