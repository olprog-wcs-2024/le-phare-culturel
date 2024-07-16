package fr.olprog_c.le_phare_culturel.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "confirmation_token")
public class ConfirmationTokenEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String token;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdDate;

  @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private UserEntity user;

  public ConfirmationTokenEntity(UserEntity user) {
    this.token = UUID.randomUUID().toString();
    this.user = user;
  }

  public ConfirmationTokenEntity() {
  }

}
