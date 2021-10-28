
public class CargoReadyDateSpecificationTest {

  @Test
  public void isSatisfiedByLaterDeparture() {
    Specification subject = new CargoReadyDateSpecification(new ReadyDate("18-APR-2022"));
    Itinerary validItinerary = new Itinerary(new Leg("AAA", "BBB", new DepartureDate("20-JUL-2023")));

    assert(subject.isSatisfiedBy(validItinerary));
  }

  @Test
  public void isNotSatisfiedByEarlierDeparture() {
    Specification subject = new CargoReadyDateSpecification(new ReadyDate("18-APR-2022"));
    Itinerary validItinerary = new Itinerary(new Leg("AAA", "BBB", new DepartureDate("20-MAR-2022")));

    assert(!subject.isSatisfiedBy(validItinerary));
  }

  // TODO: what should we do when the dates are the same?
}
