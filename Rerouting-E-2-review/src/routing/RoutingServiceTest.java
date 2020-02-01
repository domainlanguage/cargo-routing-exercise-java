package routing;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class RoutingServiceTest {

  @Test
  public void initialRoutingBackwardCompatibility() {
    final RoutingService service = new RoutingService();

    final Itinerary expected = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"));

    //Side-effect operation still works, although detailed testing would focus on
    //the function, because the most complex calculations have been isolated there.
    final Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("DAL");
    service.route(cargo);
    assertThat(cargo.getItinerary(), is(expected));
  }

  @Test
  public void initialRouting2Legs() {
    final Itinerary expected = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"));

    final RoutingService service = new RoutingService();
    assertThat(service.findRoute("HKG", "DAL"), is(expected));
  }

  @Test
  public void reroutingBackwardCompatibility() {
    //Side-effect operation still works, although detailed testing would focus on function.
    final Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("DAL");
    cargo.setItinerary(new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL")));

    final RoutingService service = new RoutingService();
    service.reroute(cargo, "LGB", "SEA");

    // Post condition: cargo destination is new Destination
    assertThat(cargo.getDestination(), is("SEA"));

    //Post condition: itinerary is preserved up to reroute point
    final Leg expectedFirstLeg = new Leg("HKG", "LGB");
    assertThat(cargo.getItinerary().legs().get(0), is(expectedFirstLeg));

    // Post condition: itinerary ends at new destination
    assertThat(cargo.getItinerary().end(), is("SEA"));

    // Post condition: itinerary is connected
    assertTrue(cargo.getItinerary().isConnected());
  }

  @Test
  public void rerouting() {
    final Itinerary initial = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"));

    final RoutingService service = new RoutingService();
    final Itinerary rerouted = service.findReroute(initial, "LGB", "SEA");

    //itinerary is preserved up to reroutePoint
    final Leg expectedFirstLeg = new Leg("HKG", "LGB");
    assertThat(rerouted.legs().get(0), is(expectedFirstLeg));

    //itinerary ends at new destination
    assertThat(rerouted.end(), is("SEA"));

    //itinerary is connected
    assertTrue(rerouted.isConnected());
  }
}
