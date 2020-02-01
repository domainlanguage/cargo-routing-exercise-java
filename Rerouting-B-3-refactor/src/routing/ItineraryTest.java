package routing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ItineraryTest {

  @Test
  public void truncateAt() {
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));
    itin.add(new Leg("LGB", "DAL"));

    assertThat(itin.getLegs().size(), is(2));
    itin.truncateAt("LGB");
    assertThat(itin.getLegs().size(), is(1));

    assertThat(itin.getLegs().get(0).start(), is("HKG"));
    assertThat(itin.getLegs().get(0).end(), is("LGB"));
  }

  @Test
  public void add() {
    // This method already existed, but was untested.
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));

    assertThat(itin.getLegs().size(), is(1));
    itin.add(new Leg("LGB", "SEA"));
    assertThat(itin.getLegs().size(), is(2));

    assertThat(itin.getLegs().get(0).start(), is("HKG"));
    assertThat(itin.getLegs().get(0).end(), is("LGB"));
    assertThat(itin.getLegs().get(1).start(), is("LGB"));
    assertThat(itin.getLegs().get(1).end(), is("SEA"));
  }

  @Test
  public void extendOn() {
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));
    final List<Leg> legs = new ArrayList<>();
    legs.add(new Leg("LGB", "DAL"));
    legs.add(new Leg("DAL", "CHI"));
    itin.extendOn(legs);
    assertThat(itin.getLegs().size(), is(3));
    assertThat(itin.getLegs().get(0).start(), is("HKG"));
    assertThat(itin.getLegs().get(0).end(), is("LGB"));
    assertThat(itin.getLegs().get(1).start(), is("LGB"));
    assertThat(itin.getLegs().get(1).end(), is("DAL"));
    assertThat(itin.getLegs().get(2).start(), is("DAL"));
    assertThat(itin.getLegs().get(2).end(), is("CHI"));
  }

  @Test
  public void isConnected() {
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));
    itin.add(new Leg("LGB", "SEA"));
    assertTrue(itin.isConnected());

    final Itinerary itin2 = new Itinerary();
    itin2.add(new Leg("HKG", "LGB"));
    itin2.add(new Leg("SEA", "DAL"));
    assertFalse(itin2.isConnected());
  }

  @Test
  public void goesThrough() {
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));
    itin.add(new Leg("LGB", "DAL"));
    assertTrue(itin.goesThrough("HKG"));
    assertTrue(itin.goesThrough("LGB"));
    assertTrue(itin.goesThrough("DAL"));
    assertFalse(itin.goesThrough("SEA"));
  }

  @Test
  public void start() {
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));
    itin.add(new Leg("LGB", "DAL"));
    itin.add(new Leg("DAL", "CHI"));
    assertThat(itin.start(), is("HKG"));
  }

  @Test
  public void end() {
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));
    itin.add(new Leg("LGB", "DAL"));
    itin.add(new Leg("DAL", "CHI"));
    assertThat(itin.end(), is("CHI"));
  }
}
