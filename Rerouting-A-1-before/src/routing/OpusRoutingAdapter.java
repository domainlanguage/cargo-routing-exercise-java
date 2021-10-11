package routing;

import acmeroutingco.AcmeRoutingService;

import java.util.stream.IntStream;

/**
 * The calls to 'route' need to use Opus's ubiquitous language,
 *
 * For the purposes of the exercise, consider this class off-limits.
 * It is maintained by the Routing team.
 */
public class OpusRoutingAdapter {
  public void route(Cargo cargo) {
    String origin = cargo.getOrigin();
    String destination = cargo.getDestination();

    String route = AcmeRoutingService.getRoute(origin, destination);
    String[] locations = route.split(",");

    cargo.setItinerary(new Itinerary());
    IntStream.range(1, locations.length).forEach(
        i -> cargo.getItinerary().add(new Leg(locations[i - 1], locations[i])));
  }
}
