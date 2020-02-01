package routing;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class RoutingServiceTest {

  @Test
  public void initialRouting2Legs() {
    final Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("DAL");
    final RoutingService service = new RoutingService();
    service.route(cargo);

    final Itinerary expected = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"));
    assertThat(cargo.getItinerary(), is(expected));
  }

  @Test
  public void initialRouting1Leg() {
    final Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("SEA");
    final RoutingService service = new RoutingService();
    service.route(cargo);

    final Itinerary expected = new Itinerary(new Leg("HKG", "SEA"));
    assertThat(cargo.getItinerary(), is(expected));
  }

  @Test
  public void rerouting() {
    final Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("DAL");
    cargo.setItinerary(new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL")));

    final RoutingService service = new RoutingService();
    service.reroute(cargo, "LGB", "SEA");

    // Post condition: cargo destination is new destination
    assertThat(cargo.getDestination(), is("SEA"));

    // Post condition: itinerary is preserved up to reroute point
    assertThat(cargo.getItinerary().legs().get(0).start(), is("HKG"));
    assertThat(cargo.getItinerary().legs().get(0).end(), is("LGB"));

    // Post condition: itinerary ends at new destination
    assertThat(cargo.getItinerary().end(), is("SEA"));

    // Post condition: itinerary is connected
    assertTrue(cargo.getItinerary().isConnected());
  }
}
