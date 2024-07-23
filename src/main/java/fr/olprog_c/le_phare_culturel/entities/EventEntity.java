package fr.olprog_c.le_phare_culturel.entities;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "events")
public class EventEntity extends BaseCommonEntity {

  @Id
  private Long uid;

  @OneToMany(cascade = CascadeType.ALL)
  private List<ImageEntity> images;

  private String dateRange;

  private String imageCredits;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(columnDefinition = "TEXT")
  private String longDescription;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String tarifs;

  private String type;

  @Column(columnDefinition = "TEXT")
  private String slug;

  @ManyToOne(cascade = CascadeType.ALL)
  private LocationEntity location;

  @ManyToOne(cascade = CascadeType.ALL)
  private TTimingEntity lastTiming;

  @ManyToOne(cascade = CascadeType.ALL)
  private TTimingEntity firstTiming;

  @OneToMany(mappedBy = "relatedEvents", fetch = FetchType.EAGER)
  private Collection<EventGroupUserEntity> referencedEventGroups;

}
