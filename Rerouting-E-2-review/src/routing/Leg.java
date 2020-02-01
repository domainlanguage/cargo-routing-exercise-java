package routing;

import java.util.Objects;

/**
 * Value Object
 */

public class Leg {
  private final String start;
  private final String end;

  public Leg(final String start, final String end) {
    this.start = start;
    this.end = end;
  }

  public String end() {
    return end;
  }

  public String start() {
    return start;
  }

  @Override
  public String toString() {
    return "Leg{" +
        "start='" + start + '\'' +
        ", end='" + end + '\'' +
        '}';
  }

  // Auto-generated hash and equals based on value of start and end.
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Leg leg = (Leg) o;
    return Objects.equals(start, leg.start) &&
        Objects.equals(end, leg.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }
}
