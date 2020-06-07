package t4upl.model;

import java.util.List;

public class Nation {

  private final List<Person> people;

  public Nation(List<Person> people) {
    this.people = people;
  }

  public List<Person> getPeople() {
    return people;
  }
}
