package t4upl.model.optional;

import java.util.Optional;

public class House {

  private Cellar cellar;

  public House(Cellar cellar) {
    this.cellar = cellar;
  }

  public Optional<Cellar> getCellar() {
    return Optional.ofNullable(cellar);
  }
}
