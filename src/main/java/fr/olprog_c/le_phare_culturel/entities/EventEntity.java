package fr.olprog_c.le_phare_culturel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class EventEntity extends BaseCommonEntity {

  @Column(nullable = false, length = 50)
  @ColumnDefault("\"Nouveau Titre\"")
  private String titre;

  @Column(nullable = false, length = 1000)
  @ColumnDefault("\"Nouvel Evenement\"")
  private String description;

  @CreationTimestamp
  private LocalDate dateDebut;

  @CreationTimestamp
  private LocalDate dateFin;

  @Column(nullable = false)
  private boolean isActive;

  @OneToOne
  @JoinColumn(name = "referenced_events_address_id")
  private AddressEventEntity referencedAddressEventsID;

  @OneToMany(mappedBy = "relatedEventsId", fetch = FetchType.LAZY)
  private Collection<EventMediaEntity> referencedEventsMediaID;

  @OneToMany(mappedBy = "relatedEventsId", fetch = FetchType.LAZY)
  private Collection<EventGroupUserEntity> referencedEventGroupsID;

  @ManyToMany()
  @JoinTable(name = "fk_events_referer_keywords",
          joinColumns = @JoinColumn(name = "events_id"),
          inverseJoinColumns = @JoinColumn(name = "keywords_id") )
  private Collection<EventKeywordEntity> referencedKeywordsList;
}
