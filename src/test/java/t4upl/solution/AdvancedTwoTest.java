package t4upl.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import t4upl.model.Person;

public class AdvancedTwoTest {

  // --------------------------------------------
  //region INT_STREAM

  @Test
  public void range_generateNumberFromZeroToFour() {
    //given
    List<Integer> zeroToFour = new ArrayList<>();

    //when
    zeroToFour = IntStream.range(0, 5) //
      .boxed() //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 4), zeroToFour);
  }

  @Test
  public void range_getEverySecondPerson() {
    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);
    List<Person> everySecondPerson = new ArrayList<>();

    //when
    everySecondPerson = IntStream.range(0, people.size()) //
      .filter(index -> index % 2 == 1) //
      .mapToObj(index -> people.get(index)) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(Arrays.asList(heinrich, vladimir), everySecondPerson);
  }

  @Test
  public void range_zipListsToCreatePeople() {
    //Note: use getPerson(Person) function provided

    //given
    List<String> names = Arrays.asList("Hans", "Heinrich");
    List<String> countries = Arrays.asList("Germany", "Germany");
    List<Integer> ages = Arrays.asList(28, 43);

    List<Person> people = new ArrayList<>();

    //when
    people = IntStream.range(0, names.size()) //
      .mapToObj(index -> getPerson(names.get(index), countries.get(index), ages.get(index))) //
      .collect(Collectors.toList());

    //then
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Assertions.assertEquals(Arrays.asList(hans, heinrich), people);
  }

  private Person getPerson(String name, String country, int age) {
    return new Person(name, country, age);
  }

  //endregion

  // --------------------------------------------
  //region COUNT, SUM

  @Test
  public void count_countGermans() {
    //Note: use getPerson(Person) function provided

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);

    long germanCount = -1;

    //when
    germanCount = people.stream() //
      .filter(person -> person.getCountry().equals("Germany"))
      .count();

    //then
    Assertions.assertEquals(2, germanCount);
  }


  @Test
  public void sum_sumAgesOfAllPeople() {
    //Note: use getPerson(Person) function provided

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);

    int ageSum = -1;

    //when
    ageSum = people.stream() //
      .mapToInt(person -> person.getAge()) //
      .sum();

    //then
    Assertions.assertEquals(163, ageSum);
  }

  //endregion

  // --------------------------------------------
  //region REDUCE

  @Test
  public void reduce_sumAgesOfAllPeople() {
    //Note: use getPerson(Person) function provided

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    Person vladimir = new Person("Vladimir", "Russia", 27);
    List<Person> people = Arrays.asList(hans, heinrich, ivan, vladimir);

    int ageSum = -1;

    //when
    ageSum = people.stream() //
      .mapToInt(person -> person.getAge()) //
      .reduce(0, (previousValue, newValue) -> previousValue + newValue);

    //then
    Assertions.assertEquals(163, ageSum);
  }

  //endregion

  // --------------------------------------------
  //region CUSTOM COLLECTOR

  @Test
  public void collect_findMostCommonCountry() {
    //Note: implement methods in MostPopularCountryCollector

    //given
    Person hans = new Person("Hans", "Germany", 28);
    Person heinrich = new Person("Heinrich", "Germany", 43);
    Person ivan = new Person("Ivan", "Russia", 65);
    List<Person> people = Arrays.asList(hans, heinrich, ivan);

    MostPopularCountryCollector mostPopularCountryCollector = new MostPopularCountryCollector();

    String mostPopularCountry = "";

    //when
    mostPopularCountry = people.stream().collect(mostPopularCountryCollector);

    //then
    Assertions.assertEquals("Germany", mostPopularCountry);
  }

  private class MostPopularCountryCollector implements
    Collector<Person, PeopleListWrapper, String> {

    @Override
    public Supplier<PeopleListWrapper> supplier() {
      return () -> new PeopleListWrapper();
    }

    @Override
    public BiConsumer<PeopleListWrapper, Person> accumulator() {
      return (PeopleListWrapper countryList, Person person) -> countryList.add(person);
    }

    @Override
    public BinaryOperator<PeopleListWrapper> combiner() {
      return (c1, c2) -> {
        throw new RuntimeException("Parallel processing not implemented");
      };
    }

    @Override
    public Function<PeopleListWrapper, String> finisher() {
      return peopleListWrapper -> peopleListWrapper.getCountry();
    }

    @Override
    public Set<Characteristics> characteristics() {
      return new HashSet<>();
    }
  }

  private class PeopleListWrapper {

    private List<Person> people = new ArrayList<>();

    void add(Person person) {
      people.add(person);
    }

    String getCountry() {
      Map<String, Long> countryCount = people.stream() //
        .collect(Collectors.groupingBy(person -> person.getCountry(), Collectors.counting()));
      Optional<String> mostPopularCountry = countryCount.entrySet().stream() //
        .sorted(Comparator.comparing((Entry<String, Long> entry) -> entry.getValue()).reversed()) //
        .map(entry -> entry.getKey()) //
        .findFirst();

      return mostPopularCountry.orElseThrow(() ->
        new RuntimeException("In this test mostPopularCountry cannot be empty!"));
    }

  }

  //endregion

  // --------------------------------------------
  //region PARALLEL

  @Test
  public void parallelStreams() {
    //given

    //when
    IntStream.range(0, 1000) //
      .parallel() //
      .forEach(i -> System.out.println(
        String.format("i: %s, active threads: %s, thread name: %s", i, Thread.activeCount(),
          Thread.currentThread().getName())));

    //then
    //set to true if you see different thread names in console
    Assertions.assertTrue(true);


  }

  //endregion

}
