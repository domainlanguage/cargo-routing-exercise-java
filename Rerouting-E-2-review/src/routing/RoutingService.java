package routing;

import acmeroutingco.AcmeRoutingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RoutingService {

  class AcmeRoutingToOpusTranslator() {
    public Itinerary translate(String acmeRoute) {
      final String[] locations = route.split(",");

      final List<Leg> legs = new ArrayList<>();
      IntStream.range(1, locations.length)
          .forEach(i -> legs.add(new Leg(locations[i - 1], locations[i])));
      return new Itinerary(legs);
    }
  }

  public Itinerary findRoute(final String origin, final String destination) {
    return new AcmeRoutingToOpusTranslator().translate(AcmeRoutingService.getRoute(origin, destination));
  }

  public Itinerary findReroute(final Itinerary initial, final String reroutePoint, final String newDestination) {
    return initial.truncatedAt(reroutePoint).continuingOn(findRoute(reroutePoint, newDestination));
  }

  @Deprecated
  public void route(final Cargo cargo) {
    final Itinerary itinerary = findRoute(cargo.getOrigin(), cargo.getDestination());
    cargo.setItinerary(itinerary);
  }

  @Deprecated
  public void reroute(final Cargo cargo, final String reroutePoint, final String newDestination) {
    final Itinerary rerouted = findReroute(cargo.getItinerary(), reroutePoint, newDestination);
    cargo.setItinerary(rerouted);
    cargo.setDestination(newDestination);
  }
}
