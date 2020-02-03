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
  public void truncatedAt() {
    // A value object must be created whole.
    final Itinerary original = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"));

    final Itinerary expectedTruncated = new Itinerary(new Leg("HKG", "LGB"));
    assertThat(original.truncatedAt("LGB"), is(expectedTruncated));

    // Note: truncatedAt is not just a rename of truncateAt.
    // it is a side-effect-free function.
    final Itinerary expectedUnmodified = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"));
    assertThat(original, is(expectedUnmodified));
  }

  @Test
  public void editingInputListIsProtected() {
    // A value object must be created whole.
    final List<Leg> legs = new ArrayList<>();
    legs.add(new Leg("HKG", "LGB"));
    legs.add(new Leg("LGB", "DAL"));
    final Itinerary itin = new Itinerary(legs);
    assertThat(itin.legs().size(), is(2));

    legs.add(new Leg("DAL", "STO"));
    assertThat(itin.legs().size(), is(2));
    assertThat(legs.size(), is(3));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void returnedLegsUnmodifiable() {
    // A value object must be created whole.
    final List<Leg> legs =
        new ArrayList<>(List.of(new Leg("HKG", "LGB"),
            new Leg("LGB", "DAL")));
    final Itinerary itin = new Itinerary(legs);
    itin.legs().add(new Leg("DAL", "STO"));
  }


  @Test
  public void extendedOn() {
    final Itinerary original = new Itinerary(new Leg("HKG", "LGB"));

    final Itinerary expectedExtended = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL"),
        new Leg("DAL", "CHI"));

    final List<Leg> newLegs = List.of(
        new Leg("LGB", "DAL"),
        new Leg("DAL", "CHI"));
    assertThat(original.extendedOn(newLegs), is(expectedExtended));

    // Note: extendedOn is not just a rename of extendOn.
    // it is a side-effect-free function.
    final Itinerary expectedUnmodified = new Itinerary(new Leg("HKG", "LGB"));
    assertThat(original, is(expectedUnmodified));
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
