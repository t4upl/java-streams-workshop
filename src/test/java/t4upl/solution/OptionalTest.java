package t4upl.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import t4upl.model.optional.BoxOfChocolates;
import t4upl.model.optional.Cellar;
import t4upl.model.optional.Chocolate;
import t4upl.model.optional.House;

public class OptionalTest {

  @Test
  public void filter_getVanillaChocolateIfPresentOrChocolateWithEmptyStringTaste() {
    //Note: implement getVanillaChocolate method

    //given
    BoxOfChocolates vanillaBoxOfChocolates = new BoxOfChocolates(new Chocolate("vanilla"));
    BoxOfChocolates strawberryBoxOfChocolates = new BoxOfChocolates(new Chocolate("strawberry"));
    BoxOfChocolates emptyBoxOfChocolates = new BoxOfChocolates(null);

    Chocolate vanillaChocolateFiltered = null;
    Chocolate strawberryChocolateFiltered = null;
    Chocolate emptyChocolateFiltered = null;

    //when
    vanillaChocolateFiltered = getVanillaChocolate(vanillaBoxOfChocolates);
    strawberryChocolateFiltered = getVanillaChocolate(strawberryBoxOfChocolates);
    emptyChocolateFiltered = getVanillaChocolate(emptyBoxOfChocolates);

    //then
    Assertions.assertEquals(new Chocolate("vanilla"), vanillaChocolateFiltered);
    Assertions.assertEquals(new Chocolate(""), strawberryChocolateFiltered);
    Assertions.assertEquals(new Chocolate(""), emptyChocolateFiltered);
  }

  private Chocolate getVanillaChocolate(BoxOfChocolates vanillaBoxOfChocolates) {
    return vanillaBoxOfChocolates.getChocolate() //
      .filter(chocolate -> chocolate.getTasteOfChoclate().equals("vanilla")) //
      .orElse(new Chocolate(""));
  }

  @Test
  public void map_returnUpperCasedChocolateTasteOrEmptyString() {
    //Note: implement getChocolateTasteUpperCased method

    //given
    BoxOfChocolates vanillaBoxOfChocolates = new BoxOfChocolates(new Chocolate("vanilla"));
    BoxOfChocolates emptyBoxOfChocolates = new BoxOfChocolates(null);

    String vanillaChocolateTasteUpperCased = null;
    String emptyChocolateTasteUpperCased = null;

    //when
    vanillaChocolateTasteUpperCased = getChocolateTasteUpperCased(vanillaBoxOfChocolates);
    emptyChocolateTasteUpperCased = getChocolateTasteUpperCased(emptyBoxOfChocolates);

    //then
    Assertions.assertEquals("VANILLA", vanillaChocolateTasteUpperCased);
    Assertions.assertEquals("", emptyChocolateTasteUpperCased);
  }

  private String getChocolateTasteUpperCased(BoxOfChocolates vanillaBoxOfChocolates) {
    return vanillaBoxOfChocolates.getChocolate() //
      .map(chocolate -> chocolate.getTasteOfChoclate().toUpperCase()) //
      .orElse("");
  }


  @Test
  public void flatMap_returnChocolateTasteOrEmptyString() {
    //Note: implement getChocolateTasteInHouse method

    //given
    BoxOfChocolates vanillaBoxOfChocolates = new BoxOfChocolates(new Chocolate("vanilla"));
    BoxOfChocolates emptyBoxOfChocolates = new BoxOfChocolates(null);

    Cellar cellarWithBoxOfChocolates = new Cellar(vanillaBoxOfChocolates);
    Cellar cellarWithEmptyBoxOfChocolates = new Cellar(emptyBoxOfChocolates);
    Cellar cellarWithoutBoxOfChocolates = new Cellar(null);

    House houseWithCellarWithBoxOfChocolates = new House(cellarWithBoxOfChocolates);
    House houseWithCellarWithEmptyBoxOfChocolates = new House(cellarWithEmptyBoxOfChocolates);
    House houseWithCellarWithoutBoxOfChocolates = new House(cellarWithoutBoxOfChocolates);
    House houseWithoutCellar = new House(null);

    String chocolateTasteInHouseWithCellarWithBoxOfChocolates = null;
    String chocolateTasteInHouseWithCellarWithEmptyBoxOfChocolates = null;
    String chocolateTasteInHouseWithCellarWithoutBoxOfChocolates = null;
    String chocolateTasteInHouseWithoutCellar = null;

    //when
    chocolateTasteInHouseWithCellarWithBoxOfChocolates = getChocolateTasteInHouse(
      houseWithCellarWithBoxOfChocolates);
    chocolateTasteInHouseWithCellarWithEmptyBoxOfChocolates = getChocolateTasteInHouse(
      houseWithCellarWithEmptyBoxOfChocolates);
    chocolateTasteInHouseWithCellarWithoutBoxOfChocolates = getChocolateTasteInHouse(
      houseWithCellarWithoutBoxOfChocolates);
    chocolateTasteInHouseWithoutCellar = getChocolateTasteInHouse(houseWithoutCellar);

    //then
    Assertions.assertEquals("vanilla", chocolateTasteInHouseWithCellarWithBoxOfChocolates);
    Assertions.assertEquals("", chocolateTasteInHouseWithCellarWithEmptyBoxOfChocolates);
    Assertions.assertEquals("", chocolateTasteInHouseWithCellarWithoutBoxOfChocolates);
    Assertions.assertEquals("", chocolateTasteInHouseWithoutCellar);
  }

  private String getChocolateTasteInHouse(House house) {
    return house.getCellar() //
      .flatMap(cellar -> cellar.getBoxOfChocolates()) //
      .flatMap(boxOfChocolates -> boxOfChocolates.getChocolate()) //
      .map(chocolate -> chocolate.getTasteOfChoclate()) //
      .orElse("");
  }


}
