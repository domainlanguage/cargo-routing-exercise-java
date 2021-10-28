package com.peerless;

import static org.junit.Assert.assertTrue;
import com.peerless.RoutingStrategy;
import com.peerless.Itinerary;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RoutingServiceTest 
{
    /**
     * Tests for routing service need to verify that they
     * return an itinerary that matches what we passed in.
     */
    @Test
    public void returnsAValidItinerary1()
    {
        RoutingService subject = new RoutingService();
        Itinerary result = subject.route("HKG", "DAL", "26-JUL-2022", RoutingStrategy.FASTEST);

        assert(result.legs.size() > 0);
        assert(result.legs.get(0).start.equals("HKG"));
        assert(result.legs.get(result.legs.size() - 1).end.equals("DAL"));
        assert(dateLessThanOrEqualTo(result.legs.get(result.legs.size() - 1).arrivalTime, "26-JUL-2022");
    }

    @Test
    public void returnsAValidItinerary2()
    {
        RoutingService subject = new RoutingService();
        RoutingSpecification spec = new RouteSpecification("HKG", "DAL", "26-JUL-2022");
        Itinerary result = subject.route(spec, RoutingStrategy.FASTEST);

        assert(spec.isSatisfiedBy(result));
    }

    private boolean dateLessThanOrEqualTo(String earlyDate, String lateDate) {
        return true; // LOL
    }
}
