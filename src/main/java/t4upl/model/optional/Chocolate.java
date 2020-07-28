package t4upl.model.optional;

import java.util.Objects;

public class Chocolate {

  private String tasteOfChoclate;

  public Chocolate(String tasteOfChoclate) {
    this.tasteOfChoclate = tasteOfChoclate;
  }

  public String getTasteOfChoclate() {
    return tasteOfChoclate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Chocolate chocolate = (Chocolate) o;
    return Objects.equals(tasteOfChoclate, chocolate.tasteOfChoclate);
  }

  @Override
  public int hashCode() {

    return Objects.hash(tasteOfChoclate);
  }
}
