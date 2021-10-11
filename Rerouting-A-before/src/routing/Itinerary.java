package routing;

import java.util.ArrayList;
import java.util.List;

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
}
