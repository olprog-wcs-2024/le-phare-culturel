package fr.olprog_c.le_phare_culturel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import jakarta.persistence.criteria.Predicate;

public class EventFiltersSpecification {

  /**
   * This method generates a Specification for a CriteriaQuery to filter
   * EventEntities by the phoneBrand and phoneName columns.
   *
   * @param search the keyword used to filter the phoneBrand and phoneName
   * @return a Specification for a CriteriaQuery
   */
  public static Specification<EventEntity> searchPhone(String search) {
    return (root, query, criteriaBuilder) -> {
      Predicate brandPredicate = criteriaBuilder.like(root.get("phoneBrand"), likePattern(search));
      Predicate namePredicate = criteriaBuilder.like(root.get("phoneName"), likePattern(search));
      return criteriaBuilder.or(namePredicate, brandPredicate);
    };
  }

  /**
   * This method generates a Specification for a CriteriaQuery to filter
   * EventEntities by various columns.
   * The columns to filter by are determined by the keys of the filters Map.
   *
   * @param filters a Map where keys are the columns to filter by, and values are
   *                the keywords used for the filtering
   * @return a Specification for a CriteriaQuery
   */
  public static Specification<EventEntity> getFiltersSpecificationPredicates(Map<String, String> filters) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      for (Map.Entry<String, String> filter : filters.entrySet()) {

        // column / FieldName like getValue
        predicates.add(criteriaBuilder.like(root.get(filter.getKey()), likePattern(filter.getValue())));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

  /**
   * This method prepares a given string for use with a SQL LIKE query
   * by enclosing the input string with percentage characters ("%").
   *
   * @param value The string to be used in the SQL LIKE query
   * @return The input string enclosed with "%" character
   */
  private static String likePattern(String value) {
    return "%" + value + "%";
  }
}
