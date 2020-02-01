package routing;

import java.util.ArrayList;
import java.util.List;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

public class Itinerary {
  private List<Leg> legs = new ArrayList<>();

  public void add(final Leg leg) {
    legs.add(leg);
  }

  public List<Leg> getLegs() {
    return legs;
  }

  public void remove(final Leg leg) {
    legs.remove(leg);
  }

  public void truncateAt(final String reroutePoint) {
    legs = legs.stream()
        .takeWhile(not(l -> l.start().equals(reroutePoint)))
        .collect(toList());
  }

  public void extendOn(final List<Leg> appendedLegs) {
    legs.addAll(appendedLegs);
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

  public Leg getLastLeg() {
    return legs.get(legs.size() - 1);
  }

  public String start() {
    return legs.get(0).start();
  }

  public String end() {
    return getLastLeg().end();
  }
}
