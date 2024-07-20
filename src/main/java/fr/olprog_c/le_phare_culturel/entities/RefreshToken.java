package fr.olprog_c.le_phare_culturel.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

  @Id
  private String token;
  private String username;
  private Instant expiryDate;

}
