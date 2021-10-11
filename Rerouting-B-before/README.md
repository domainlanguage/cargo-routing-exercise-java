# Exercise B: Value Objects

Before we go any further, let's address the mutability in this code.

Fifteen years ago when DDD was new, immutable value objects were a novel idea and difficult to implement in Java. Today,
they are recognized as a wise default for objects that hold data.

## The Situation

```
  ┌─────────────┐
  │ Cargo       │
  ├─────────────┤
  │ origin      │
  │ destination │      ┌──────────────┐
  │ itinerary   ├─────►│ Itinerary    │
  └─────────────┘      ├──────────────┤    ┌───────────┐
                       │ legs         ├────┤ Leg       │
                       │ add()        │    ├───────────┤
                       │ truncateAt() │    │ start     │
                       └──────────────┘    │ end       │
                                           └───────────┘
```

Currently, all of Cargo, Itinerary, and Leg are mutable. We regularly alter itineraries, and also set itineraries on
Cargo.

## The Discussion

Which of these three should be an Entity, and which should be a Value Object?

When we make something into a value object, how does this change its method names?

## The Exercise

Make Itinerary and Leg into value objects.

* Start with [ItineraryTest](./src/routing/ItineraryTest.md). How does immutability change your testing style?
* Change method names to reflect the new behavior.
* Let the IDE generate code where you can.
* Does this make `reroute` even more painful?

## Bonus Exercise

Go ahead and add behavior to Itinerary to make the code clearer.

* `reroute` will benefit from a method that's the opposite of truncate. Make `extendedOn` accept new legs return a new
  Itinerary with those on the end.
* In the RoutingServiceTest, can we replace complicated postconditions with method calls? For instance, move some logic
  into an `isConnected` method on Itinerary.

Reflect: what new tests are needed? Does that give you more confidence in the tests of postconditions?
