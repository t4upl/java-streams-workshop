package t4upl.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
    people.stream() //
      .forEach(person -> System.out.println(person));

    //then
    //change to true if you see in console pritned people
    Assertions.assertTrue(true);
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
    people.stream() //
      .forEach(person -> personSet.add(person));

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
    people.stream() //
      .forEach(person -> peopleRepositoryMock.save(person));

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
    personSet = people.stream() //
      .collect(Collectors.toSet());

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
    peopleMap = people.stream() //
      .collect(Collectors.toMap(person -> person.getName(), person -> person));

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
    people = peopleMap.values().stream() //
      .collect(Collectors.toList());

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
    peopleWithNameStartingWithH = people.stream() //
      .filter(person -> person.getName().startsWith("H")) //
      .collect(Collectors.toList());

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
    peopleWithAgeOverFifty = people.stream() //
      .filter(person -> person.getAge() > 50) //
      .collect(Collectors.toList());

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
    peopleMapWithNamesAsKeys = peopleMap.entrySet().stream() //
      .filter(entry -> entry.getValue().getName().equals(entry.getKey())) //
      .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

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
    names = people.stream() //
      .map(person -> person.getName()) //
      .collect(Collectors.toList());

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
    csvRows = people.stream() //
      .map(person -> toCsv(person)) //
      .collect(Collectors.toList());

    //then
    Assertions.assertTrue(csvRows.contains("Hans, Germany, 28"));
    Assertions.assertTrue(csvRows.contains("Ivan, Russia, 65"));
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
    people = nations.stream() //
      .flatMap(nation -> nation.getPeople().stream()) //
      .collect(Collectors.toList());

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
    Person akira = new Person("Akira", "Russia", 78);

    Nation germans = new Nation(Arrays.asList(hans, heinrich));
    Nation russians = new Nation(Arrays.asList(ivan, vladimir));
    Nation japanese = new Nation(Arrays.asList(hayato, akira));

    Continent europe = new Continent(Arrays.asList(germans, russians));
    Continent asia = new Continent(Collections.singletonList(japanese));

    List<Continent> continents = Arrays.asList(europe, asia);
    List<Person> people = new ArrayList<>();

    //when
    people = continents.stream() //
      .flatMap(continent -> continent.getNations().stream()) //
      .flatMap(nation -> nation.getPeople().stream()) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(6, people.size());
    Assertions.assertTrue(people.contains(hans));
    Assertions.assertTrue(people.contains(heinrich));
    Assertions.assertTrue(people.contains(ivan));
    Assertions.assertTrue(people.contains(vladimir));
    Assertions.assertTrue(people.contains(hayato));
    Assertions.assertTrue(people.contains(akira));
  }







}

