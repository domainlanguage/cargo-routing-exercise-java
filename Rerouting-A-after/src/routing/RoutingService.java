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

  public void reroute(Cargo cargo, String reroutePoint) {

    cargo.getItinerary().truncateAt(reroutePoint);

    Cargo tempCargo = new Cargo();
    tempCargo.setOrigin(reroutePoint);
    tempCargo.setDestination(cargo.getDestination());
    route(tempCargo);
    tempCargo.getItinerary().getLegs().forEach(cargo.getItinerary()::add);
  }
}
