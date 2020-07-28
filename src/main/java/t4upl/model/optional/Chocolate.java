package t4upl.model.optional;

import java.util.Objects;

public class Chocolate {

  private String tasteOfChocolate;

  public Chocolate(String tasteOfChocolate) {
    this.tasteOfChocolate = tasteOfChocolate;
  }

  public String getTasteOfChocolate() {
    return tasteOfChocolate;
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
    return Objects.equals(tasteOfChocolate, chocolate.tasteOfChocolate);
  }

  @Override
  public int hashCode() {

    return Objects.hash(tasteOfChocolate);
  }
}
