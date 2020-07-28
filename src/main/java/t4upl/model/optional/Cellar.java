package t4upl.model.optional;

import java.util.Optional;

public class Cellar {

  private BoxOfChocolates boxOfChocolates;

  public Cellar(BoxOfChocolates boxOfChocolates) {
    this.boxOfChocolates = boxOfChocolates;
  }

  public Optional<BoxOfChocolates> getBoxOfChocolates() {
    return Optional.ofNullable(boxOfChocolates);
  }
}
