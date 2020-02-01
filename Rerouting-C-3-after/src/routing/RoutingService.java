package routing;

import acmeroutingco.AcmeRoutingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RoutingService {
  public void route(final Cargo cargo) {
    final String origin = cargo.getOrigin();
    final String destination = cargo.getDestination();

    final String route = AcmeRoutingService.getRoute(origin, destination);
    final String[] locations = route.split(",");

    final List<Leg> legs = new ArrayList<>();
    IntStream.range(1, locations.length)
        .forEach(i -> legs.add(new Leg(locations[i - 1], locations[i])));

    cargo.setItinerary(new Itinerary(legs));
  }

  public void reroute(final Cargo cargo, final String reroutePoint, final String newDestination) {
    if (!cargo.getItinerary().goesThrough(reroutePoint)) {
      throw new IllegalArgumentException();
    }

    final Cargo tempCargo = new Cargo();
    tempCargo.setOrigin(reroutePoint);
    tempCargo.setDestination(newDestination);
    route(tempCargo);
    final List<Leg> appendedLegs = tempCargo.getItinerary().legs();

    final Itinerary rerouted = cargo.getItinerary()
        .truncatedAt(reroutePoint)
        .extendedOn(appendedLegs);
    cargo.setItinerary(rerouted);
    cargo.setDestination(newDestination);
  }
}
