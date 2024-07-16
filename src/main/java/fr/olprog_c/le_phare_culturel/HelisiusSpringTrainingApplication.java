package fr.olprog_c.le_phare_culturel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HelisiusSpringTrainingApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelisiusSpringTrainingApplication.class, args);
  }

}
