package fr.olprog_c.le_phare_culturel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "blacklisted_tokens")
public class BlacklistedToken {

  @Id
  private String token;
  private Instant expiryDate;

}
