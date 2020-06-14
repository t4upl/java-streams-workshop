package t4upl.model;

import java.util.Objects;

public class Person {

  private String name;
  private String countryName;
  private Integer age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Person(String name, String countryName, Integer age) {
    this.name = name;
    this.countryName = countryName;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public String getCountryName() {
    return countryName;
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
      Objects.equals(countryName, person.countryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, countryName, age);
  }

  @Override
  public String toString() {
    return "Person{" +
      "name='" + name + '\'' +
      ", countryName='" + countryName + '\'' +
      ", age=" + age +
      '}';
  }


}
