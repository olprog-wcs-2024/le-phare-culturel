package fr.olprog_c.le_phare_culturel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "events_keywords_category")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class EventKeywordCategoryEntity extends BaseCommonEntity {

  @Column(name = "category_name")
  private String categoryName;

  @ManyToMany(mappedBy = "referencedCategoryId", fetch = FetchType.LAZY)
  private Collection<EventKeywordEntity> referencedKeywordsCategoryId;
}
