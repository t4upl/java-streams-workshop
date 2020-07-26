package t4upl.model;

import java.util.Objects;

public class Person {

  private String name;
  private String country;
  private Integer age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Person(String name, String country, Integer age) {
    this.name = name;
    this.country = country;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public Integer getAge() {
    return age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return age == person.age &&
      Objects.equals(name, person.name) &&
      Objects.equals(country, person.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, country, age);
  }

  @Override
  public String toString() {
    return "Person{" +
      "name='" + name + '\'' +
      ", country='" + country + '\'' +
      ", age=" + age +
      '}';
  }


}
