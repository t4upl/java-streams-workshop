package t4upl.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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


}