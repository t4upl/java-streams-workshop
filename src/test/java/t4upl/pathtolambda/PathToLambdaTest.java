package t4upl.pathtolambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import t4upl.model.Person;

/**
 * PRESENTATION ONLY
 */
public class PathToLambdaTest {


  @Test
  void getOldPeopleUsingInterfaceImplementation() {
    //We define old people as somebody who is over 75

    //given
    Person[] personArray = {
      new Person("Adam", 80),
      new Person("Bob", 21),
      new Person("Alice", 120),
      new Person("Jake", 36)
    };
    List<Person> people = Arrays.asList(personArray);

    //when
    List<Person> oldPeople = people.stream() //
      .filter(new OldPeoplePredicate()) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(oldPeople.size(), 2);
    Assertions.assertEquals(oldPeople.get(0), new Person("Adam", 80));
    Assertions.assertEquals(oldPeople.get(1), new Person("Alice", 120));
  }

  private class OldPeoplePredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
      return person.getAge() > 70;
    }
  }

  @Test
  void getOldPeopleUsingAnonymousClass() {
    //given
    Person[] personArray = {
      new Person("Adam", 80),
      new Person("Bob", 21),
      new Person("Alice", 120),
      new Person("Jake", 36)
    };
    List<Person> people = Arrays.asList(personArray);

    Predicate<Person> oldPeoplePredicateImpl = new Predicate<Person>() {
      @Override
      public boolean test(Person person) {
        return person.getAge() > 70;
      }
    };

    //when
    List<Person> oldPeople = people.stream() //
      .filter(oldPeoplePredicateImpl) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(oldPeople.size(), 2);
    Assertions.assertEquals(oldPeople.get(0), new Person("Adam", 80));
    Assertions.assertEquals(oldPeople.get(1), new Person("Alice", 120));
  }

  @Test
  void getOldPeopleUsingLambdaExpression() {
    //given
    Person[] personArray = {
      new Person("Adam", 80),
      new Person("Bob", 21),
      new Person("Alice", 120),
      new Person("Jake", 36)
    };
    List<Person> people = Arrays.asList(personArray);

    Predicate<Person> oldPeoplePredicateImpl = (Person person) -> {
      return person.getAge() > 70;
    };

    //when
    List<Person> oldPeople = people.stream() //
      .filter(oldPeoplePredicateImpl) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(oldPeople.size(), 2);
    Assertions.assertEquals(oldPeople.get(0), new Person("Adam", 80));
    Assertions.assertEquals(oldPeople.get(1), new Person("Alice", 120));
  }

  @Test
  void getOldPeopleUsingLambdaExpressionInLined() {
    //given
    Person[] personArray = {
      new Person("Adam", 80),
      new Person("Bob", 21),
      new Person("Alice", 120),
      new Person("Jake", 36)
    };
    List<Person> people = Arrays.asList(personArray);

    //when
    List<Person> oldPeople = people.stream() //
      .filter((Person person) -> { //
        return person.getAge() > 70; //
      }) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(oldPeople.size(), 2);
    Assertions.assertEquals(oldPeople.get(0), new Person("Adam", 80));
    Assertions.assertEquals(oldPeople.get(1), new Person("Alice", 120));
  }

  @Test
  void getOldPeopleUsingLambdaExpressionSimplified() {
    //given
    Person[] personArray = {
      new Person("Adam", 80),
      new Person("Bob", 21),
      new Person("Alice", 120),
      new Person("Jake", 36)
    };
    List<Person> people = Arrays.asList(personArray);

    //when
    List<Person> oldPeople = people.stream() //
      .filter(person -> person.getAge() > 70) //
      .collect(Collectors.toList());

    //then
    Assertions.assertEquals(oldPeople.size(), 2);
    Assertions.assertEquals(oldPeople.get(0), new Person("Adam", 80));
    Assertions.assertEquals(oldPeople.get(1), new Person("Alice", 120));
  }

}
