package t4upl.model.stream;

import java.util.List;

public class Continent {

  private final List<Nation> nations;

  public Continent(List<Nation> nations) {
    this.nations = nations;
  }

  public List<Nation> getNations() {
    return nations;
  }
}
