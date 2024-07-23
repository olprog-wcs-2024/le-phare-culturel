package fr.olprog_c.le_phare_culturel.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
@DynamicUpdate
public class BaseCommonEntity {

  @CreationTimestamp
  protected LocalDateTime createdDate;

  @UpdateTimestamp
  protected LocalDateTime updatedDate;

  protected LocalDateTime deletedDate;
}
