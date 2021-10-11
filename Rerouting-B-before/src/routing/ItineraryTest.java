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
  public void add() {
    // This method already existed, but was untested.
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));

    assertThat(itin.getLegs().size(), is(1));
    itin.add(new Leg("LGB", "SEA"));
    assertThat(itin.getLegs().size(), is(2));

    assertThat(itin.getLegs().get(0).getStart(), is("HKG"));
    assertThat(itin.getLegs().get(0).getEnd(), is("LGB"));
    assertThat(itin.getLegs().get(1).getStart(), is("LGB"));
    assertThat(itin.getLegs().get(1).getEnd(), is("SEA"));
  }

  @Test
  public void truncateAt() {
    final Itinerary itin = new Itinerary();
    itin.add(new Leg("HKG", "LGB"));
    itin.add(new Leg("LGB", "DAL"));

    assertThat(itin.getLegs().size(), is(2));
    itin.truncateAt("LGB");
    assertThat(itin.getLegs().size(), is(1));

    assertThat(itin.getLegs().get(0).getStart(), is("HKG"));
    assertThat(itin.getLegs().get(0).getEnd(), is("LGB"));
  }

}
