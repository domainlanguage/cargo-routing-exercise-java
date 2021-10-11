package routing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoutingService {
  OpusRoutingAdapter routingAdapter = new OpusRoutingAdapter();

  public void route(Cargo cargo) {
      routingAdapter.route(cargo);
  }

  public void reroute(Cargo cargo, String reroutePoint) {
    Itinerary itinerary = cargo.getItinerary();
    List<Leg> legs = new ArrayList<>();
    legs.addAll(cargo.getItinerary().getLegs());

    AtomicBoolean pastReroutePoint = new AtomicBoolean(false);

    legs.forEach(l -> {
          if (l.getStart().equals(reroutePoint))
            pastReroutePoint.set(true);
          if (pastReroutePoint.get()) cargo.getItinerary().remove(l);
        }
    );

    Cargo tempCargo = new Cargo();
    tempCargo.setOrigin(reroutePoint);
    tempCargo.setDestination(cargo.getDestination());
    routingAdapter.route(tempCargo);
    tempCargo.getItinerary().getLegs().forEach(cargo.getItinerary()::add);
  }
}
