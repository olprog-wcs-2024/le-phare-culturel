package fr.olprog_c.le_phare_culturel.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

  private String etiquette;

  private String type;

  @ManyToOne(cascade = CascadeType.ALL)
  private LocationEntity location;

  @ManyToOne(cascade = CascadeType.ALL)
  private TTimingEntity lastTiming;

  @ManyToOne(cascade = CascadeType.ALL)
  private TTimingEntity firstTiming;

}
