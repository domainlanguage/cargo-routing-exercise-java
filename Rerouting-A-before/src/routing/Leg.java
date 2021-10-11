package routing;

public class Leg {
  String start;
  String end;

  public Leg(String start, String end) {
    this.start = start;
    this.end = end;
  }

  public String getEnd() {
    return end;
  }

  public String getStart() {
    return start;
  }
}
