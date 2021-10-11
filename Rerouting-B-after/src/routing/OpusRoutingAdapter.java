package routing;

import acmeroutingco.AcmeRoutingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * The calls to 'route' need to use Opus's ubiquitous language,
 *
 * For the purposes of the exercise, consider this class off-limits.
 * It is maintained by the Routing team.
 */
public class OpusRoutingAdapter {
  public Itinerary route(String origin, String destination) {

    String route = AcmeRoutingService.getRoute(origin, destination);
    String[] locations = route.split(",");

    List<Leg> legs = new ArrayList<>();
    IntStream.range(1, locations.length).forEach(
        i -> legs.add(new Leg(locations[i - 1], locations[i])));
    return new Itinerary(legs);
  }
}
