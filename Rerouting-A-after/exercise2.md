# Exercise A2: Assertions

See the background in [README.md](./README.md).

Really, you can do this before or after (or instead of) Exercise A1 as specified in the README.

## The Problem

That `reroute` method in [RoutingService.java](./src/routing/RoutingService.java) makes a lot of assumptions.
And whatever code calls it makes some assumptions about what it does. Can we document those?

## The Discussion

In a group, brainstorm preconditions, postconditions, and invariants for this function.
what does it expect to be true before it starts? what can its caller expect to be true when it returns?
What do we expect to hold true at both times?

## The Exercise

Make the tests illustrate each of the following postconditions:

 * Itinerary is preserved up to reroute point.
 * Itinerary ends at cargo destination.
 * Itinerary is connected.

Reflect: how would you express these more clearly if you were to change code as well as tests?