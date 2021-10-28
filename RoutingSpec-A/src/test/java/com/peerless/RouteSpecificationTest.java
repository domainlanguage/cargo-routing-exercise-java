

/**
 * Testing isSatisfiedBy is not pretty.
 * In fact, there might be a bug in the checking of end location, and we wouldn't see it.
 */
public class RouteSpecificationTest {
  
  @Test
  public void isSatisfiedByValidItinerary() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL", "26-JUL-2022");
    Itinerary validItinerary = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL", new ArrivalDate("24-JUL-2022")));
    
    assert(subject.isSatisfiedBy(validItinerary));
  }

  @Test void isNotSatisfiedWhenStartLocationDoesntMatch() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
      new ArrivalDate("26-JUL-2022"));
    Itinerary suspectItinerary = new Itinerary(
        new Leg("SNG", "LGB"),
        new Leg("LGB", "DAL", new ArrivalDate("24-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(suspectItinerary));
  }

  @Test
  public void isNotSatisfiedWhenEndLocationDoesntMatch() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL", 
      new ArrivalDate("26-JUL-2022"));
    Itinerary suspectItinerary = new Itinerary(
        new Leg("SNG", "LGB"),
        new Leg("LGB", "SEA", new ArrivalDate("24-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(suspectItinerary));
  }

  @Test
  public void isNotSatisfiedWhenItArrivesTooLate() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
      new ArrivalDate("26-JUL-2022"));
    Itinerary suspectItinerary = new Itinerary(
        new Leg("HKG", "LGB"),
        new Leg("LGB", "DAL", new ArrivalDate("28-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(suspectItinerary));
  }

  @Test
  public void isNotSatisfiedWhenEverythingIsWrong() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
      new ArrivalDate("26-JUL-2022"));
    Itinerary terribleItinerary = new Itinerary(
        new Leg("SNG", "SEA", new ArrivalDate("28-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(terribleItinerary));
  }
}
