package t4upl.model.optional;

import java.util.Optional;

public class BoxOfChocolates {

  private Chocolate chocolate;

  public BoxOfChocolates(Chocolate chocolate) {
    this.chocolate = chocolate;
  }

  public Optional<Chocolate> getChocolate() {
    return Optional.ofNullable(chocolate);
  }


}
