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

    assertThat(itin.getLegs().get(0).getStart(), is("HKG"));
    assertThat(itin.getLegs().get(0).getEnd(), is("LGB"));
  }

}
