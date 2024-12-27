package ru.nsu.kislitsyn.xml1;

import lombok.Getter;

import java.util.*;


public class People {
    @Getter
    private final Map<String, Person> people = new HashMap<>();

    public void processPerson(Person person) {
        if (person.getId() != null) {
            people.put(person.getId(), person);
        } else {
            List<Person> mergeCandidates = getByFullname(
                    List.of(person.getFirstname(),
                            person.getSurname())
            );

            if (mergeCandidates.isEmpty()) {
                people.put(UUID.randomUUID().toString(), person);
                return;
            }

            for (Person mergePerson : mergeCandidates) {
                if (person.doesNotConflictWith(mergePerson)) {
                    mergePerson.merge(person);
                    if (mergePerson.getId() != null) {
                        people.put(mergePerson.getId(), mergePerson);
                    }
                    break;
                }
            }
        }
    }

    public void addPerson(Person person) {
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
        Set<String> keysToRemove = new HashSet<>();
        for (String key : people.keySet()) {
            if (Utils.isUUID(key)) {
                keysToRemove.add(key);
            }
        }

        for (String key : keysToRemove) {
            people.remove(key);
        }
    }
}
