
public class CompositeSpecificationTest {

  class HappySpecification implements Specification { 
    public boolean isSatisfiedBy(Itinerary input) {
      return true;
    }  
  }

  class ImpossibleSpecification implements Specification { 
    public boolean isSatisfiedBy(Itinerary input) {
      return false;
    }  
  }

  @Test
  public void isSatisfiedWhenAllComponentsAreSatisfied() {
    Specification subject = new CompositeSpecification(
      new HappySpecification(),
      new HappySpecification());
    Itinerary anyOldItinerary = new Itinerary();

    assert(subject.isSatisfiedBy(anyOldItinerary))
  }

  @Test
  public void isNotSatisfiedWhenAnyComponentsIsNotSatisfied() {
    Specification subject = new CompositeSpecification(
      new HappySpecification(),
      new ImpossibleSpecification(),
      new HappySpecification());
    Itinerary anyOldItinerary = new Itinerary();

    assert(!subject.isSatisfiedBy(anyOldItinerary));
  }

  // ... and some more cases for thoroughness
}
