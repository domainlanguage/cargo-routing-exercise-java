
package routing;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoutingServiceTest {
  @Test
  public void initialRouting2Legs() {
    Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("DAL");
    RoutingService service = new RoutingService();
    service.route(cargo);
    assertThat(cargo.getItinerary().getLegs().size(), is(2));
    assertThat(cargo.getItinerary().getLegs().get(0).getStart(), is("HKG"));
    assertThat(cargo.getItinerary().getLegs().get(0).getEnd(), is("LGB"));
    assertThat(cargo.getItinerary().getLegs().get(1).getStart(), is("LGB"));
    assertThat(cargo.getItinerary().getLegs().get(1).getEnd(), is("DAL"));
  }

  @Test
  public void initialRouting1Leg() {
    Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("SEA");
    RoutingService service = new RoutingService();
    service.route(cargo);
    assertThat(cargo.getItinerary().getLegs().size(), is(1));
    assertThat(cargo.getItinerary().getLegs().get(0).getStart(), is("HKG"));
    assertThat(cargo.getItinerary().getLegs().get(0).getEnd(), is("SEA"));
  }

  @Test
  public void rerouting() {
    Cargo cargo = new Cargo();
    cargo.setOrigin("HKG");
    cargo.setDestination("DAL");
    RoutingService service = new RoutingService();
    service.route(cargo);
    cargo.setDestination("SEA");
    service.reroute(cargo, "LGB");
    assertThat(cargo.getItinerary().getLegs().size(), is(2));
    assertThat(cargo.getItinerary().getLegs().get(0).getStart(), is("HKG"));
    assertThat(cargo.getItinerary().getLegs().get(0).getEnd(), is("LGB"));
    assertThat(cargo.getItinerary().getLegs().get(1).getStart(), is("LGB"));
    assertThat(cargo.getItinerary().getLegs().get(1).getEnd(), is("SEA"));
  }
}
