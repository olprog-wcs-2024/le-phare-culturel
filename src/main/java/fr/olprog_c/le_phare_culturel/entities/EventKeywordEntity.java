package fr.olprog_c.le_phare_culturel.entities;

import fr.olprog_c.le_phare_culturel.dtos.event.EventColorDescriptor;
import fr.olprog_c.le_phare_culturel.dtos.event.EventKeywordDescriptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Collection;

@Entity
@Table(name = "events_keywords")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class EventKeywordEntity extends BaseCommonEntity {

  @Column(nullable = false)
  @ColumnDefault("\"New Keyword\"")
  private String keyword;

  @Column(nullable = true)
  @ColumnDefault("\"New Keyword description\"")
  private String description;

  @Enumerated(EnumType.STRING)
  private EventColorDescriptor color = EventColorDescriptor.RED;

  @ManyToMany()
  @JoinTable(name = "fk_keywords_referer_category", joinColumns = @JoinColumn(name = "keywords_id"), inverseJoinColumns = @JoinColumn(name = "category_id")

  )
  private Collection<EventKeywordCategoryEntity> referencedCategoryId;
}
