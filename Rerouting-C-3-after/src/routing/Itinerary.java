package routing;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

public record Itinerary(List<Leg>legs) {

  public Itinerary {
    this.legs = new ArrayList<>(legs);
  }

  public Itinerary(final Leg... legs) {
    this(asList(legs));
  }

  public List<Leg> legs() {
    return unmodifiableList(legs);
  }

  public Itinerary truncatedAt(final String cutPoint) {
    return new Itinerary(legs.stream()
        .takeWhile(not(l -> l.start().equals(cutPoint)))
        .collect(toList()));
  }

  public Itinerary extendedOn(final List<Leg> extensionLegs) {
    return new Itinerary(concat(legs.stream(), extensionLegs.stream()).collect(toList()));
  }

  public boolean isConnected() {
    if (legs.size() < 2)
      return true;
    for (int i = 1; i < legs.size(); i++) {
      final Leg left = legs.get(i - 1);
      final Leg right = legs.get(i);
      if (!left.end().equals(right.start()))
        return false;
    }
    return true;
  }

  public boolean goesThrough(final String location) {
    return legs.stream()
        .anyMatch(l -> l.start().equals(location) || l.end().equals(location));
  }

  public Leg lastLeg() {
    return legs.get(legs.size() - 1);
  }

  public String start() {
    return legs.get(0).start();
  }

  public String end() {
    return lastLeg().end();
  }
}