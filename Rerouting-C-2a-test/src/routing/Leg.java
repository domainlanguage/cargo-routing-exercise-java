package routing;

public class Leg {
  String start;
  String end;

  public Leg(String start, String end) {
    this.start = start;
    this.end = end;
  }

  public String end() {
    return end;
  }

  public String start() {
    return start;
  }
}
