package fr.olprog_c.le_phare_culturel.initialise;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import fr.olprog_c.le_phare_culturel.api.models.Event;
import fr.olprog_c.le_phare_culturel.api.services.APIEventService;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
@Order(1)
public class RunCommandLineRunner implements CommandLineRunner, Ordered {

    private final APIEventService apiEventService;
    private final EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        if (eventRepository.count() > 0) {
            System.out.println("Events already fetched from API");
            return;
        }
        System.out.println("Fetching events from API....");
        CompletableFuture<Void> fetchEventsFuture = apiEventService.fetchEvents().thenAccept(eventResponse -> {
            List<Event> events = List.of(eventResponse.events());
            apiEventService.save(events);
        });

        // Wait for the asynchronous operation to complete
        fetchEventsFuture.get();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
