package t4upl.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import t4upl.model.stream.Person;

public class AdvancedOneTest {

  // --------------------------------------------
  //region ARRAY

  @Test
  public void convertArrayOfPeopleToListUsingStream() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person[] peopleArray = {hans, ivan};
    List<Person> people = new ArrayList<>();

    //when
    people = Arrays.stream(peopleArray) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(2, people.size());
    Assertions.assertEquals(hans, people.get(0));
    Assertions.assertEquals(ivan, people.get(1));
  }


  @Test
  public void convertPeopleListToArrayUsingStream() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);
    Person[] peopleArray = new Person[0];

    //when
    peopleArray = people.stream() //
      .toArray(Person[]::new);

    //then
    Assertions.assertEquals(2, peopleArray.length);
    Assertions.assertEquals(hans, peopleArray[0]);
    Assertions.assertEquals(ivan, peopleArray[1]);
  }

  //endregion

  // --------------------------------------------
  //region JOINING, GROUPING_BY, PARTITIONING_BY
  @Test
  public void joining_convertPeopleToCommaSeparatedNames() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, ivan);
    String commaSeparatedNames = "";

    //when
    commaSeparatedNames = people.stream() //
      .map(person -> person.getName())
      .collect(Collectors.joining(","));

    //then
    Assertions.assertEquals("Hans,Ivan", commaSeparatedNames);
  }

  @Test
  public void groupingBy_groupPeopleByCountry() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);
    Map<String, List<Person>> countryToPeople = new HashMap<>();

    //when
    countryToPeople = people.stream() //
      .collect(Collectors.groupingBy(person -> person.getCountry()));

    //then
    Assertions.assertEquals(2, countryToPeople.size());
    Assertions.assertEquals(Arrays.asList(hans, heinrich), countryToPeople.get("Germany"));
    Assertions.assertEquals(Arrays.asList(ivan, vladimir), countryToPeople.get("Russia"));
  }


  @Test
  public void groupingBy_groupPeopleByFirstLetterOfName() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);
    Map<Character, List<Person>> firstLetterOfNameToPeople = new HashMap<>();

    //when
    firstLetterOfNameToPeople = people.stream() //
      .collect(Collectors.groupingBy(person -> person.getName().charAt(0)));

    //then
    Assertions.assertEquals(3, firstLetterOfNameToPeople.size());
    Assertions.assertEquals(Arrays.asList(hans, heinrich), firstLetterOfNameToPeople.get('H'));
    Assertions.assertEquals(Collections.singletonList(ivan), firstLetterOfNameToPeople.get('I'));
    Assertions
      .assertEquals(Collections.singletonList(vladimir), firstLetterOfNameToPeople.get('V'));
  }

  @Test
  public void groupingBy_getCountOfPeopleByCountry() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, heinrich, ivan);

    Map<String, Long> countryToPeopleCount = new HashMap<>();

    //when
    countryToPeopleCount = people.stream() //
      .collect(Collectors.groupingBy(person -> person.getCountry(), Collectors.counting()));

    //then
    Assertions.assertEquals(2, countryToPeopleCount.size());
    Assertions.assertEquals(2, countryToPeopleCount.get("Germany"));
    Assertions.assertEquals(1, countryToPeopleCount.get("Russia"));
  }


  @Test
  public void partitioningBy_partitionPeopleByBeingGerman() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);
    Map<Boolean, List<Person>> isGermanToPeople = new HashMap<>();

    //when
    isGermanToPeople = people.stream() //
      .collect(Collectors.partitioningBy(person -> person.getCountry().equals("Germany")));

    //then
    Assertions.assertEquals(2, isGermanToPeople.size());
    Assertions.assertEquals(Arrays.asList(hans, heinrich), isGermanToPeople.get(true));
    Assertions.assertEquals(Arrays.asList(ivan, vladimir), isGermanToPeople.get(false));
  }

  //endregion

  // --------------------------------------------
  //region SKIP, LIMIT, DISTINCT

  @Test
  public void skip_getAllCsvRowsExceptForHeader() {
    //given
    String header = "Name,Country,Age";
    String hans = "Hans,Germany,28";
    String heinrich = "Heinrich,Germany,43";
    String ivan = "Ivan,Russia,65";
    String vladimir = "Vladimir,Russia,27";
    List<String> csvRows = Arrays.asList(header, hans, heinrich, ivan, vladimir);
    List<String> csvRowsWithoutHeader = new ArrayList<>();

    //when
    csvRowsWithoutHeader = csvRows.stream() //
      .skip(1) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(Arrays.asList(hans, heinrich, ivan, vladimir), csvRowsWithoutHeader);
  }

  @Test
  public void limit_getThreeFirstPeople() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);
    List<Person> firstThreePeople = new ArrayList<>();

    //when
    firstThreePeople = people.stream() //
      .limit(3) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(Arrays.asList(hans, heinrich, ivan), firstThreePeople);
  }

  @Test
  public void distinct_getUniquePeople() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person hans2 = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    Person ivan2 = new Person("Ivan", "Russia", 65);
    Person hans3 = new Person("Hans", "Germany", 28);
    List<Person> people = Arrays.asList(hans, hans2, heinrich, ivan, vladimir, ivan2, hans3);
    List<Person> distinctPeople = new ArrayList<>();

    //when
    distinctPeople = people.stream() //
      .distinct() //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(Arrays.asList(hans, heinrich, ivan, vladimir), distinctPeople);
  }

  //endregion

  // --------------------------------------------
  //region ALL_MATCH, NONE_MATCH, ANY_MATCH, FIRST_MATCH

  @Test
  public void allMatch_checkIfAllPeopleAreGerman() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    List<Person> people = Arrays.asList(hans, heinrich);
    boolean areAllPeopleGerman = false;

    //when
    areAllPeopleGerman = people.stream() //
      .allMatch(person -> person.getCountry().equals("Germany"));

    //then
    Assertions.assertTrue(areAllPeopleGerman);
  }

  @Test
  public void noneMatch_checkIfNobodyIsRussian() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    List<Person> people = Arrays.asList(hans, heinrich);
    boolean isNobodyRussian = false;

    //when
    isNobodyRussian = people.stream() //
      .noneMatch(person -> person.getCountry().equals("Russia"));

    //then
    Assertions.assertTrue(isNobodyRussian);
  }

  @Test
  public void anyMatch_checkIfSomebodyIsRussian() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, heinrich, ivan);
    boolean isSomebodyRussian = false;

    //when
    isSomebodyRussian = people.stream() //
      .anyMatch(person -> person.getCountry().equals("Russia"));

    //then
    Assertions.assertTrue(isSomebodyRussian);
  }

  @Test
  public void findFirst_checkIfSomebodyIsRussian() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);
    Optional<Person> firstRussian = Optional.empty();

    //when
    firstRussian = people.stream() //
      .filter(person -> person.getCountry().equals("Russia"))
      .findFirst();

    //then
    Assertions.assertEquals(Optional.of(ivan), firstRussian);
  }

  //endregion

  // --------------------------------------------
  //region SORTED

  @Test
  public void sorted_sortPeopleByName() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> unsortedPeople = Arrays.asList(vladimir, hans, ivan, heinrich);
    List<Person> peopleSortedByName = new ArrayList<>();

    //when
    peopleSortedByName = unsortedPeople.stream() //
      .sorted(Comparator.comparing(person -> person.getName()))
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(Arrays.asList(hans, heinrich, ivan, vladimir), peopleSortedByName);
  }

  @Test
  public void sorted_sortPeopleByNameNullNamesFirst() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    Person nullNamedPerson = new Person(null, "France", 1000);
    List<Person> unsortedPeople = Arrays.asList(vladimir, hans, ivan, heinrich, nullNamedPerson);
    List<Person> peopleSortedByName = new ArrayList<>();

    //when
    peopleSortedByName = unsortedPeople.stream() //
      .sorted(Comparator.comparing(person -> person.getName(), //
        Comparator.nullsFirst(Comparator.naturalOrder()))) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(Arrays.asList(nullNamedPerson, hans, heinrich, ivan, vladimir),
      peopleSortedByName);
  }

  //endregion


}
