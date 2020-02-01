package acmeroutingco;

public class AcmeRoutingService {
  public static String getRoute(String start, String end) {
    // You peeked inside the black box!

    // This is really just a stub that pretends to be what a third-party
    // routing service might look like. As you can see, it simply mimics
    // a few cases. The real point here is that this is the sort of
    // interface one might encounter when working with a legacy third-party API.

    if (start.equals("HKG") && end.equals("DAL")) {
      return "HKG,LGB,DAL";
    }
    if (start.equals("HKG") && end.equals("SEA")) {
      return "HKG,SEA";
    }
    if (start.equals("LGB") && end.equals("SEA")) {
      return "LGB,SEA";
    }
    return "";
  }
}
