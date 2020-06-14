package t4upl.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import t4upl.model.Continent;
import t4upl.model.Nation;
import t4upl.model.Person;

public class BasicTest {

  // --------------------------------------------
  // forEach() SECTION

  @Test
  public void forEach_printAllPeople() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);

    //when

    //then
    //change to true if you see in console pritned people
    Assertions.assertTrue(false);
  }

  @Test
  public void forEach_addAllPeopleToSet() {
    //Note: Usually for this functionality we would use collect(), this is just for learning

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);

    Set<Person> personSet = new HashSet<>();

    //when

    //then
    Assertions.assertEquals(2, personSet.size());
    Assertions.assertTrue(personSet.contains(hans));
    Assertions.assertTrue(personSet.contains(ivan));
  }

  @Test
  public void forEach_insertAllPeopleIntoDatabase() {
    //Note: use 'save' method of PeopleRepositoryMock

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);
    PeopleRepositoryMock peopleRepositoryMock = new PeopleRepositoryMock();

    //when

    //then
    List<Person> peopleInDatabase = peopleRepositoryMock.findAll();
    Assertions.assertEquals(2, peopleInDatabase.size());
    Assertions.assertTrue(peopleInDatabase.contains(hans));
    Assertions.assertTrue(peopleInDatabase.contains(ivan));
  }

  private class PeopleRepositoryMock {

    private List<Person> people = new ArrayList<>();

    void save(Person person) {
      people.add(person);
    }

    List<Person> findAll() {
      return new ArrayList<>(people);
    }
  }

  // --------------------------------------------
  // collectors() SECTION
  @Test
  public void collect_convertPeopleListToSet() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);
    Set<Person> personSet = new HashSet<>();

    //when

    //then
    Assertions.assertTrue(personSet.contains(hans));
    Assertions.assertTrue(personSet.contains(ivan));
  }

  @Test
  public void collect_getMapOfPeopleWithNameAsKeyAndPersonAsValueFromPeopleList() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);
    Map<String, Person> peopleMap = new HashMap<>();

    //when

    //then
    Assertions.assertEquals(2, peopleMap.size());
    Assertions.assertEquals(hans, peopleMap.get("Hans"));
    Assertions.assertEquals(ivan, peopleMap.get("Ivan"));
  }

  @Test
  public void collect_getPeopleListFromMapOfPeopleWithNameAsKeyAndPersonAsValue() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = new ArrayList<>();
    Map<String, Person> peopleMap = new HashMap<String, Person>() {{
      put("Hans", hans);
      put("Ivan", ivan);
    }};

    //when

    //then
    Assertions.assertEquals(2, people.size());
    Assertions.assertTrue(people.contains(hans));
    Assertions.assertTrue(people.contains(ivan));
  }

  // --------------------------------------------
  // filter() SECTION

  @Test
  public void filter_findPeopleWithNameStartingWithH() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person carmen = new Person("Carmen", "Spain", 82);
    Person henry = new Person("Henry", "England", 32);
    List<Person> people = Arrays.asList(hans, ivan, carmen, henry);
    List<Person> peopleWithNameStartingWithH = new ArrayList<>();

    //when

    //then
    Assertions.assertEquals(2, peopleWithNameStartingWithH.size());
    Assertions.assertTrue(peopleWithNameStartingWithH.contains(hans));
    Assertions.assertTrue(peopleWithNameStartingWithH.contains(henry));
  }

  @Test
  public void filter_findPeopleWithAgeOverFifty() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person carmen = new Person("Carmen", "Spain", 82);
    Person henry = new Person("Henry", "England", 32);
    List<Person> people = Arrays.asList(hans, ivan, carmen, henry);
    List<Person> peopleWithAgeOverFifty = new ArrayList<>();

    //when

    //then
    Assertions.assertEquals(2, peopleWithAgeOverFifty.size());
    Assertions.assertTrue(peopleWithAgeOverFifty.contains(ivan));
    Assertions.assertTrue(peopleWithAgeOverFifty.contains(carmen));
  }

  @Test
  public void filter_findEntriesWhereKeyIsEqualToPersonName() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person carmen = new Person("Carmen", "Spain", 82);
    Person henry = new Person("Henry", "England", 32);
    Map<String, Person> peopleMap = new HashMap<String, Person>() {{
      put("Hans", hans);
      put("FooBar", ivan);
      put("Carmen", carmen);
      put("Totally not Henry", henry);
    }};
    Map<String, Person> peopleMapWithNamesAsKeys = new HashMap<String, Person>();

    //when

    //then
    Assertions.assertEquals(2, peopleMapWithNamesAsKeys.size());
    Assertions.assertEquals(hans, peopleMapWithNamesAsKeys.get("Hans"));
    Assertions.assertEquals(carmen, peopleMapWithNamesAsKeys.get("Carmen"));
  }

  // --------------------------------------------
  // map() SECTION

  @Test
  public void map_getListOfNames() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);
    List<String> names = new ArrayList<>();

    //when

    //then
    Assertions.assertTrue(names.contains("Hans"));
    Assertions.assertTrue(names.contains("Ivan"));
  }

  @Test
  public void map_mapPeopleToCsvRows() {
    //Note: use toCsv(Person) function provided

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);
    List<String> csvRows = new ArrayList<>();

    //when

    //then
    Assertions.assertTrue(csvRows.contains("Hans,Germany,28"));
    Assertions.assertTrue(csvRows.contains("Ivan,Russia,65"));
  }

  private String toCsv(Person person) {
    return person.getName() + "," + person.getCountryName() + "," + person.getAge();
  }

  // --------------------------------------------
  // flatMap() SECTION

  @Test
  public void flatMap_getAllPeopleInNations() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);

    Nation germans = new Nation(Arrays.asList(hans, heinrich));
    Nation russians = new Nation(Arrays.asList(ivan, vladimir));
    List<Nation> nations = Arrays.asList(germans, russians);

    List<Person> people = new ArrayList<>();

    //when

    //then
    Assertions.assertEquals(4, people.size());
    Assertions.assertTrue(people.contains(hans));
    Assertions.assertTrue(people.contains(heinrich));
    Assertions.assertTrue(people.contains(ivan));
    Assertions.assertTrue(people.contains(vladimir));
  }

  @Test
  public void flatMap_getAllPeopleInContinents() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);

    Person hayato = new Person("Hayao", "Japan", 35);
    Person akira = new Person("Akira", "Japan", 78);

    Nation germans = new Nation(Arrays.asList(hans, heinrich));
    Nation russians = new Nation(Arrays.asList(ivan, vladimir));
    Nation japanese = new Nation(Arrays.asList(hayato, akira));

    Continent europe = new Continent(Arrays.asList(germans, russians));
    Continent asia = new Continent(Collections.singletonList(japanese));

    List<Continent> continents = Arrays.asList(europe, asia);
    List<Person> people = new ArrayList<>();

    //when

    //then
    Assertions.assertEquals(6, people.size());
    Assertions.assertTrue(people.contains(hans));
    Assertions.assertTrue(people.contains(heinrich));
    Assertions.assertTrue(people.contains(ivan));
    Assertions.assertTrue(people.contains(vladimir));
    Assertions.assertTrue(people.contains(hayato));
    Assertions.assertTrue(people.contains(akira));
  }

  @Test
  public void debug_findPersonThatCausesNullPointerExceptionAndReasonWhy() {
    //Note: Debugging in streams can be achieved using:
    //  1. peek() - non-terminal forEach()
    //  2. debugger - you need to move body of lambda to new line to put breakpoint (Idea)

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person(null, "Germany", 43);
    Person ivan = new Person("Ivan", null, null);
    Person vladimir = new Person(null, null, 27);
    Person hayato = new Person("Hayao", "Japan", null);
    Person akira = new Person("Akira", "Japan", 78);

    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir, hayato, akira);
    Person personCausingNpe = null;

    //when
    people.stream() //
      .filter(person -> person != personCausingNpe) //
      .filter(person -> person.getName() != null) //
      .map(person -> //
        new Person(person.getName(), person.getCountryName(), person.getAge())) //
      .filter(person -> person.getCountryName() != null) //
      .map(person -> person.getAge() + 100) //
      .forEach(age -> System.out.println(age));

    //then
    //intentionally left empty
  }

  @Test
  public void codeStyle() {
    //Note: General rules for code style formatting when dealing with stream api
    //  1. one line per instruction, dot on new line
    //  2. Add // at the end of line to prevent auto formatting
    //  3. Try to use full names for arguments in lambdas e.g. 'person' and not 'prsn'
    //  4. Line break after  '->'  {@link https://google.github.io/styleguide/javaguide.html#s4.5.1-line-wrapping-where-to-break}

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person(null, "Germany", 43);

    List<Person> people = Arrays.asList(hans, heinrich);

    //when
    people.stream() //
      .map(person -> person.getAge()) //
      .filter(age -> age < 18)  //
      .map(age -> age > 70) //
      .forEach(age -> System.out.println(age));

    //then
    //set to true when you read note
    Assertions.assertTrue(false);
  }

  @Test
  public void verticalProcessing_explainOrderOfStatementsPrintedToConsole() {
    //Note: Streams process element vertically i.e. first elements goes through all operations of stream,
    // then second element goes through all operations and so on until all elements are processed

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);

    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);

    //when
    people.stream() //
      .peek(person -> System.out.println("-----------------")) //
      .peek(person -> System.out.println(person)) //
      .map(person -> //
        new Person(person.getName().toUpperCase(), person.getCountryName().toUpperCase(), //
          person.getAge())) //
      .peek(
        person -> System.out.println((person.getName() + " from " + person.getCountryName()))) //
      .map(person -> person.getAge()) //
      .forEach(age -> System.out.println(age));

    //then
    //set to true when you read note
    Assertions.assertTrue(false);
  }

  @Test
  public void lazy_explainOrderOfStatementsPrintedToConsole() {
    //Note: Streams are lazy i.e. they don't execute until they see terminal operation

    //given
    Person hans = new Person("Hans", "Germany", 28);
    List<Person> people = Collections.singletonList(hans);

    //when
    Stream<Person> peopleStream = people.stream() //
      .peek(person -> System.out.println("1"));

    System.out.println("2");

    peopleStream = peopleStream.map(person -> //
      new Person(person.getName().toUpperCase(), person.getCountryName().toUpperCase(), //
        person.getAge())) //
      .peek(person -> System.out.println("3"));

    System.out.println("4");

    peopleStream //
      .map(person -> person.getAge()) //
      .forEach(age -> System.out.println("5"));

    //then
    //set to true when you read note
    Assertions.assertTrue(false);
  }

  @Test
  public void streamsCloseAfterTerminalOperation() {
    //Note: Streams close after terminal operations and are unusable. Calling operation on closed stream causes exception.

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);

    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);

    List<Person> germans = new ArrayList<>();
    List<Person> germansOverForty = new ArrayList<>();
    Stream<Person> germansStream = people.stream() //
      .filter(person -> person.getCountryName().equals("Germany")); //

    //when
    germans = germansStream //
      .collect(Collectors.toList());
    germansOverForty = germansStream //
      .filter(german -> german.getAge() > 40) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(2, germans.size());
    Assertions.assertEquals(1, germansOverForty.size());

    Assertions.assertTrue(germans.contains(heinrich));
    Assertions.assertTrue(germans.contains(hans));
    Assertions.assertTrue(germansOverForty.contains(heinrich));
  }


}

