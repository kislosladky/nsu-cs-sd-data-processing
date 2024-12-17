package ru.nsu.kislitsyn.xml1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class People {
    @Getter
    private final List<Person> people = new ArrayList<>();

    public void processPerson(Person person) {
        if (people.contains(person)) {
            Person duplicate = people.stream().filter(p -> p.equals(person)).findFirst().orElse(null);
            assert duplicate != null;
            duplicate.merge(person);
        } else {
            people.add(person);
        }
    }

//    public Optional<Person> getById(String id) {
//        for (Person person : people) {
//            if (person.getId().equals(id)) {
//                return Optional.of(person);
//            }
//        }
//        return Optional.empty();
//    }

//    public Optional<Person> getByFirstname(String name) {
//        for (Person person : people) {
//            if (person.getFirstname().equals(name)) {
//                return Optional.of(person);
//            }
//        }
//        return Optional.empty();
//    }

//    public Optional<Person> getBySurname(String name) {
//        for (Person person : people) {
//            if (person.getSurname().equals(name)) {
//                return Optional.of(person);
//            }
//        }
//        return Optional.empty();
//    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (Person person : people) {
//            sb.append(person.toString());
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
    public void print() {
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
