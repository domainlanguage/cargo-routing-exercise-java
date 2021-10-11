package routing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoutingService {
  OpusRoutingAdapter routingAdapter = new OpusRoutingAdapter();

  public void route(Cargo cargo) {
    Itinerary itinerary = routingAdapter.route(cargo.getOrigin(), cargo.getDestination());
    cargo.setItinerary(itinerary);
  }

  public void reroute(final Cargo cargo, final String reroutePoint) {
    final Cargo tempCargo = new Cargo();
    tempCargo.setOrigin(reroutePoint);
    tempCargo.setDestination(cargo.getDestination());
    route(tempCargo);
    final List<Leg> appendedLegs = tempCargo.getItinerary().legs();

    final Itinerary rerouted = cargo.getItinerary()
        .truncatedAt(reroutePoint)
        .extendedOn(appendedLegs);
    cargo.setItinerary(rerouted);
  }
}
