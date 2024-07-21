package fr.olprog_c.le_phare_culturel.api;

import java.util.ArrayList;
import java.util.List;

import fr.olprog_c.le_phare_culturel.api.models.Event;
import fr.olprog_c.le_phare_culturel.api.models.Variant;
import fr.olprog_c.le_phare_culturel.entities.EventEntity;
import fr.olprog_c.le_phare_culturel.entities.ImageEntity;
import fr.olprog_c.le_phare_culturel.entities.LocationEntity;
import fr.olprog_c.le_phare_culturel.entities.TTimingEntity;

public class Mapper {

  public static EventEntity apiToEntity(Event event) {
    EventEntity entity = new EventEntity();
    entity.setUid(event.uid());
    List<ImageEntity> images = new ArrayList<>();
    for (Variant variant : event.image().variants()) {
      images.add(new ImageEntity(variant.filename(), variant.type()));
    }
    entity.setImages(images);
    entity.setDateRange(event.dateRange());
    entity.setImageCredits(event.imageCredits());
    entity.setTitle(event.title());
    entity.setDescription(event.description());
    entity.setLongDescription(event.longDescription());
    entity.setTarifs(event.tarifs());
    entity.setEtiquette(mapEtiquette(event.etiquette()));
    entity.setType(mapEventType(event.typeDevenement()));
    LocationEntity location = new LocationEntity();
    location.setAddress(event.location().address());
    location.setCity(event.location().city());
    location.setLatitude(event.location().latitude());
    location.setLongitude(event.location().longitude());
    location.setName(event.location().name());
    entity.setLocation(location);

    TTimingEntity firstTiming = new TTimingEntity();
    firstTiming.setBegin(event.firstTiming().begin());
    firstTiming.setEnd(event.firstTiming().end());
    entity.setFirstTiming(firstTiming);

    TTimingEntity lastTiming = new TTimingEntity();
    lastTiming.setBegin(event.lastTiming().begin());
    lastTiming.setEnd(event.lastTiming().end());
    entity.setLastTiming(lastTiming);

    return entity;
  }

  private static String mapEtiquette(final int etiquette) {
    System.out.println(etiquette);
    return switch (etiquette) {
      case 26 -> "Ouvert";
      case 27 -> "Annulé";
      case 28 -> "Complet";
      default -> throw new IllegalArgumentException("Unknown event type: " + etiquette);
    };

  }

  public static String mapEventType(final int eventType) {
    return switch (eventType) {
      case 1 -> "Cinema - projection";
      case 14 -> "Concert";
      case 15 -> "Evenement sportif";
      case 16 -> "Exposition";
      case 17 -> "Fête / festival";
      case 18 -> "Marché / braderie";
      case 19 -> "Nature / environnement";
      case 20 -> "Nocturne";
      case 21 -> "Rencontre / conférence / débat";
      case 22 -> "Salon / foire";
      case 23 -> "Spectacle";
      case 24 -> "Stages et ateliers";
      case 25 -> "Visite";
      default -> "Sans valeur";
    };
  }

  private static List<String> extractKeywords(Object keywords) {
    if (keywords instanceof List<?>) {
      List<?> keywordList = (List<?>) keywords;
      List<String> result = new ArrayList<>();
      for (Object keyword : keywordList) {
        if (keyword instanceof String) {
          result.add((String) keyword);
        } else {
          throw new IllegalArgumentException("Keyword is not a string: " + keyword);
        }
      }
      return result;
    } else {
      throw new IllegalArgumentException("Keywords are not a list: " + keywords);
    }
  }
}
