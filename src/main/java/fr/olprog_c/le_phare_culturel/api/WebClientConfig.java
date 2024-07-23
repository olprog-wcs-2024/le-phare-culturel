package fr.olprog_c.le_phare_culturel.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient webClient(WebClient.Builder builder) {
    final int size = 16 * 1024 * 1024; // 16 MB
    ExchangeStrategies strategies = ExchangeStrategies.builder()
        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
        .build();

    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(HttpClient.create()))
        .exchangeStrategies(strategies)
        .build();
  }
}
