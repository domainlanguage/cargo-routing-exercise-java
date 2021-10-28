package com.peerless;

import java.util.ArrayList;
import java.util.List;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

public class Itinerary {
  private List<Leg> legs = new ArrayList<Leg>();

  public void add(Leg leg) {
    legs.add(leg);
  }

  public List<Leg> getLegs() {
    return legs;
  }

  public void remove(Leg leg) {
    legs.remove(leg);
  }

  public void truncateAt(String reroutePoint) {
    legs = legs.stream()
        .takeWhile(not(l -> l.getStart().equals(reroutePoint)))
        .collect(toList());
  }
}
