package ru.nsu.kislitsyn.xml1;

import lombok.Getter;

import java.util.*;


public class People {
    @Getter
    private final Map<String, Person> people = new HashMap<>();

    public void processPerson(Person person) {
        if (!Utils.isUUID(person.getId()) && people.containsKey(person.getId())) {
            putPerson(person); //обновляем значение
        } else {
            if (person.getFirstname() == null || person.getSurname() == null) {
                System.out.println(person);
                return;
            }
            List<Person> mergeCandidates = getByFullname(
                    List.of(person.getFirstname(),
                            person.getSurname())
            );

            if (mergeCandidates.isEmpty()) {
                putPerson(person);
                return;
            }

            for (Person mergePerson : mergeCandidates) {
                if (person.doesNotConflictWith(mergePerson)) {
                    mergePerson.merge(person);
                    putPerson(mergePerson);
                    break;
                }
            }
        }
    }

    public void putPerson(Person person) {
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

    public List<Person> getByFullname(List<String> fullname) {
        List<Person> result = new ArrayList<>();
        for (Person person : people.values()) {
            if (fullname.getFirst().equals(person.getFirstname())
                    && fullname.getLast().equals(person.getSurname())) {
                result.add(person);
            }
        }
        return result;
    }

    public void print() {
        for (Person person : people.values()) {
            System.out.println(person);
        }
//        for (String key : people.keySet()) {
//
//            System.out.println(key + ": " + people.get(key));
//
//        }
        System.out.println("Amount is " + people.size());
    }

    public void removeWrongKeys() {
        people.keySet().removeIf(Utils::isUUID);
    }

    public void removeDuplicatedPersons() {
        for (Person person : people.values()) {
            person.getSiblings().removeIf(sibling -> Utils.isUUID(sibling.getId()));
            person.getParents().removeIf(parent -> Utils.isUUID(parent.getId()));
            person.getChildren().removeIf(child -> Utils.isUUID(child.getId()));

            person.setSiblings(new HashSet<>(person.getSiblings()));
            person.setParents(new HashSet<>(person.getParents()));
            person.setChildren(new HashSet<>(person.getChildren()));
        }
    }
}
