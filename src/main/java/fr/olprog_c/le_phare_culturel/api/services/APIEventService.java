package fr.olprog_c.le_phare_culturel.api.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.olprog_c.le_phare_culturel.api.Mapper;
import fr.olprog_c.le_phare_culturel.api.models.Event;
import fr.olprog_c.le_phare_culturel.api.models.EventResponse;
import fr.olprog_c.le_phare_culturel.repositories.EventRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transient
public class APIEventService {

    @Value("${openagenda.api.key}")
    private String apiKey;

    @Value("${openagenda.api.agenda.id}")
    private String agendaId;

    private final WebClient webClient;
    private final EventRepository eventRepository;

    public APIEventService(WebClient webClient, EventRepository eventRepository) {
        this.webClient = webClient;
        this.eventRepository = eventRepository;
    }

    public CompletableFuture<EventResponse> fetchEvents() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDate = sdf.format(date);

        String url = String.format(
                "https://api.openagenda.com/v2/agendas/%s/events?key=%s&size=400&timings[gte]=%s&monolingual=fr&detailed=1",
                agendaId, apiKey, formattedDate);
        System.out.println(url);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(EventResponse.class).toFuture();
    }

    public void save(List<Event> event) {
        // Save the events in the database
        try {
            log.info("Saving events.....");
            event.stream().map(Mapper::apiToEntity).forEach(eventRepository::save);
            log.info("Events saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving events: " + e.getMessage());

        }
    }
}
