package routing;

import acmeroutingco.AcmeRoutingService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class RoutingService {
  public void route(Cargo cargo) {
    String origin = cargo.getOrigin();
    String destination = cargo.getDestination();

    String route = AcmeRoutingService.getRoute(origin, destination);
    String[] locations = route.split(",");

    cargo.setItinerary(new Itinerary());
    IntStream.range(1, locations.length).forEach(
        i -> cargo.getItinerary().add(new Leg(locations[i - 1], locations[i])));
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
    route(tempCargo);
    tempCargo.getItinerary().getLegs().forEach(cargo.getItinerary()::add);
  }
}
