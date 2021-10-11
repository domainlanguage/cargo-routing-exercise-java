# Exercise A1: Refactor the reroute method

## The Situation So Far

Based on a scenario:

> A cargo is going from HKG to DAL, and it has an itinerary going HKG->LGB->DAL.
> While the cargo is on its way from HKG to LGB, the customer calls to change its destination to SEA.

We have this nice model:

```
  ┌─────────────┐
  │ Cargo       │
  ├─────────────┤
  │ origin      │
  │ destination │      ┌────────────┐
  │ itinerary   ├─────►│ Itinerary  │
  └─────────────┘      ├────────────┤   ┌───────────┐
                       │ legs       ├──►│ Leg       │
                       └────────────┘   ├───────────┤
                                        │ start     │
                                        │ end       │
                                        └───────────┘
```

(where origin, destination, start, and end are ports, represented by their port code in a String)

## The Problem

Then one day we look at the code for rerouting the cargo, and we see the implementation or `reroute` in
[RoutingService.java](./src/routing/RoutingService.java).

It's, ah, not pretty.

## The Discussion

What is it doing? It's hard to tell. It certainly doesn't express our model. We picked this model because it works with
this scenario!

Maybe the developer who wrote this code wasn't in that meeting. Maybe they didn't know this should be elegant.

Perhaps the messy code is giving us a clue that our model isn't as well-suited as we thought. Maybe it is missing some
concepts, like some behaviors on the domain classes.

After you figure out what this code is doing: how could we express this operation more clearly?

... hopefully by the time you look at this, we've had that discussion in class.

## Exercise A1: Refactor

You know you want to refactor this code. Now is your chance.

In class, we talked about some missing concepts in the domain model. Please:

* implement `truncateAt` in the Itinerary class, and use it in `reroute`.
* without other changes to the rest of the classes, do what helps you feel better in `reroute`.

Run the tests before and after.

Reflect: how would you make this more clear if you were changing the function's interface?

## Moving on

After another day of class, continue with [Exercise A2](./exercise2.md)