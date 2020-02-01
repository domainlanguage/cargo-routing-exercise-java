package routing;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ItineraryTest {

  @Test
  public void truncatedAt() {
    final Itinerary original =
        new Itinerary(
            new Leg("HKG", "LGB"),
            new Leg("LGB", "DAL"));

    assertThat(original.getLegs().size(), is(2));
    final Itinerary truncated = original.truncatedAt("LGB");

    assertThat(truncated.getLegs().size(), is(1));
    assertThat(truncated.getLegs().get(0).start(), is("HKG"));
    assertThat(truncated.getLegs().get(0).end(), is("LGB"));

    // Assert that original itinerary has not mutated
    assertThat(original.getLegs().size(), is(2));
    assertThat(original.getLegs().get(0).start(), is("HKG"));
    assertThat(original.getLegs().get(0).end(), is("LGB"));
    assertThat(original.getLegs().get(1).start(), is("LGB"));
    assertThat(original.getLegs().get(1).end(), is("DAL"));
  }

  // Deleted test for mutator add(). Method will be deleted.

  @Test
  public void extendedOn() {
    final Itinerary original = new Itinerary(new Leg("HKG", "LGB"));
    final List<Leg> legs = List.of(
        new Leg("LGB", "DAL"),
        new Leg("DAL", "CHI"));

    final Itinerary extended = original.extendedOn(legs);
    assertThat(extended.getLegs().size(), is(3));
    assertThat(extended.getLegs().get(0).start(), is("HKG"));
    assertThat(extended.getLegs().get(0).end(), is("LGB"));
    assertThat(extended.getLegs().get(1).start(), is("LGB"));
    assertThat(extended.getLegs().get(1).end(), is("DAL"));
    assertThat(extended.getLegs().get(2).start(), is("DAL"));
    assertThat(extended.getLegs().get(2).end(), is("CHI"));

    // Assert that original has not mutated
    assertThat(original.getLegs().size(), is(1));
    assertThat(original.getLegs().get(0).start(), is("HKG"));
    assertThat(original.getLegs().get(0).end(), is("LGB"));
  }

  @Test
  public void isConnected() {
    final Itinerary itin = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "SEA"));
    assertTrue(itin.isConnected());

    final Itinerary itin2 = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("SEA", "DAL"));
    assertFalse(itin2.isConnected());
  }

  @Test
  public void goesThrough() {
    final Itinerary itin = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"));
    assertTrue(itin.goesThrough("HKG"));
    assertTrue(itin.goesThrough("LGB"));
    assertTrue(itin.goesThrough("DAL"));
    assertFalse(itin.goesThrough("SEA"));
  }

  @Test
  public void start() {
    final Itinerary itin = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"),
        new Leg("DAL", "CHI"));
    assertThat(itin.start(), is("HKG"));
  }

  @Test
  public void end() {
    final Itinerary itin = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"),
        new Leg("DAL", "CHI"));
    assertThat(itin.end(), is("CHI"));
  }
}
