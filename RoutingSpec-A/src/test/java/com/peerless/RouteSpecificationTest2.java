

/**
 * Testing isSatisfiedBy is not pretty.
 * It gets even uglier when we add another condition: we have to modify every test
 */
public class RouteSpecificationTest2 {
  
  @Test
  public void isSatisfiedByValidItinerary() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL", 
      new ReadyDate("14-APR-2022"), 
      new ArrivalDate("26-JUL-2022"));
    Itinerary validItinerary = new Itinerary(
        new Leg("HKG", "LGB", new DepartureDate("18-APR-2022")),
        new Leg("LGB", "DAL", new ArrivalDate("24-JUL-2022")));
    
    assert(subject.isSatisfiedBy(validItinerary));
  }

  
  @Test void isNotSatisfiedWhenDepartureTimeIsTooEarly() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
        new ReadyDate("14-APR-2022"), 
        new ArrivalDate("26-JUL-2022"));
    Itinerary suspectItinerary = new Itinerary(
        new Leg("HKG", "LGB", new DepartureDate("18-APR-2022")),
        new Leg("LGB", "DAL", new ArrivalDate("24-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(suspectItinerary));
  }

  @Test void isNotSatisfiedWhenStartLocationDoesntMatch() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
        new ReadyDate("14-APR-2022"),
        new ArrivalDate("26-JUL-2022"));
    Itinerary suspectItinerary = new Itinerary(
        new Leg("SNG", "LGB", new DepartureDate("18-APR-2022")),
        new Leg("LGB", "DAL", new ArrivalDate("24-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(suspectItinerary));
  }

  @Test
  public void isNotSatisfiedWhenEndLocationDoesntMatch() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
        new ReadyDate("14-APR-2022"),
        new ArrivalDate("26-JUL-2022"));
    Itinerary suspectItinerary = new Itinerary(
        new Leg("SNG", "LGB", new DepartureDate("18-APR-2022")),
        new Leg("LGB", "SEA", new ArrivalDate("24-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(suspectItinerary));
  }

  @Test
  public void isNotSatisfiedWhenItArrivesTooLate() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
        new ReadyDate("14-APR-2022"),
        new ArrivalDate("26-JUL-2022"));
    Itinerary suspectItinerary = new Itinerary(
        new Leg("HKG", "LGB", new DepartureDate("18-APR-2022")),
        new Leg("LGB", "DAL", new ArrivalDate("28-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(suspectItinerary));
  }

  @Test
  public void isNotSatisfiedWhenEverythingIsWrong() {
    RouteSpecification subject = new RoutingSpecification("HKG", "DAL",
        new ReadyDate("14-APR-2022"),
        new ArrivalDate("26-JUL-2022"));
    Itinerary terribleItinerary = new Itinerary(
        new Leg("SNG", "SEA", new DepartureDate("10-APR-2022"), new ArrivalDate("28-JUL-2022")));
    
    assert(!subject.isSatisfiedBy(terribleItinerary));
  }
}
