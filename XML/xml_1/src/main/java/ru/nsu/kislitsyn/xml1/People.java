package ru.nsu.kislitsyn.xml1;

import lombok.Getter;

import java.util.*;


public class People {
    @Getter
    private final Map<String, Person> people = new HashMap<>();

    public void processPerson(Person person) {
        if (people.containsValue(person)) {
            Person duplicate = people.values().stream().filter(p -> p.equals(person)).findFirst().orElse(null);
            assert duplicate != null;
            duplicate.merge(person);
        } else {
            people.put(person.getId(), person);
        }
    }

    public void addPerson(Person person) {
//        people.add(person);
        people.put(person.getId(), person);
    }

    public Person addEmptyPerson(String id) {
        Person person = new Person();
        person.setId(id);
        people.put(id, person);
        return person;
    }

    public Optional<Person> getById(String id) {
        Person person = people.get(id);
        return person == null ?
                Optional.empty() :
                Optional.of(person);
    }

    public List<Person> getByFullname(String firstname, String surname) {
        List<Person> result = new ArrayList<>();
        for (Person person : people.values()) {
            if (firstname.equals(person.getFirstname())
                    && surname.equals(person.getSurname())) {
                result.add(person);
            }
        }
        return result;
    }

    public void print() {
        for (Person person : people.values()) {
            System.out.println(person);
        }
        System.out.println("Amount is " + people.size());
    }
}
