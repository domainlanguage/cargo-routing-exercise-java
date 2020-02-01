package routing;

import org.junit.Test;

import java.util.List;

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
    assertThat(cargo.getItinerary().getLegs().size(), is(2));
    assertThat(cargo.getItinerary().getLegs().get(0).start(), is("HKG"));
    assertThat(cargo.getItinerary().getLegs().get(0).end(), is("LGB"));
    assertThat(cargo.getItinerary().getLegs().get(1).start(), is("LGB"));
    assertThat(cargo.getItinerary().getLegs().get(1).end(), is("DAL"));
  }

  @Test
  public void initialRouting1Leg() {
    final Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("SEA");
    final RoutingService service = new RoutingService();
    service.route(cargo);
    assertThat(cargo.getItinerary().getLegs().size(), is(1));
    assertThat(cargo.getItinerary().getLegs().get(0).start(), is("HKG"));
    assertThat(cargo.getItinerary().getLegs().get(0).end(), is("SEA"));
  }

  @Test
  public void rerouting() {
    final Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("DAL");
    cargo.getItinerary().add(new Leg("HKG", "LGB"));
    cargo.getItinerary().add(new Leg("LGB", "DAL"));

    final RoutingService service = new RoutingService();
    service.reroute(cargo, "LGB", "SEA");

    // Post condition: cargo destination is new destination (formerly precondition)
    assertThat(cargo.getDestination(), is("SEA"));

    // Post condition: itinerary is preserved up to reroute point
    assertThat(cargo.getItinerary().getLegs().get(0).start(), is("HKG"));
    assertThat(cargo.getItinerary().getLegs().get(0).end(), is("LGB"));

    // Post condition: itinerary ends at new destination
    final int itinLength = cargo.getItinerary().getLegs().size();
    assertThat(cargo.getItinerary().getLegs().get(itinLength - 1).end(), is("SEA"));

    // Post condition: itinerary is connected
    final List<Leg> legs = cargo.getItinerary().getLegs();
    boolean isConnected = true;
    if (legs.size() >= 2) {
      for (int i = 1; i < legs.size(); i++) {
        final Leg left = legs.get(i - 1);
        final Leg right = legs.get(i);
        if (!left.end().equals(right.start())) {
          isConnected = false;
          break;
        }
      }
    }
    assertTrue(isConnected);
  }
}
