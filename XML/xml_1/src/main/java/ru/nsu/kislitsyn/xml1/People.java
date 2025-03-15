package ru.nsu.kislitsyn.xml1;

import lombok.Getter;

import java.util.*;

@Getter
public class People {
    private final Map<String, Person> people = new HashMap<>();

    public void processPerson(Person person) {
        if (!Utils.isUUID(person.getId())) {
            if (people.containsKey(person.getId())) {
                Person toMerge = people.get(person.getId());
                people.remove(person.getId());
                if (toMerge != person) {
                    toMerge.merge(person);
                }
                addPerson(toMerge);
            } else {
                addPerson(person);
            }
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
                addPerson(person);
                return;
            }

            for (Person mergePerson : mergeCandidates) {
                if (person.doesNotConflictWith(mergePerson)) {
                    if (person != mergePerson) {
                        people.remove(mergePerson.getId());
                        addPerson(mergePerson.merge(person));
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
        System.out.println("Amount is " + people.size());
    }

    public void removeWrongKeys() {
        Iterator<Map.Entry<String, Person>> iterator = people.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Person> entry = iterator.next();
            String key = entry.getKey();
            Person person = entry.getValue();
            if (Utils.isUUID(key)) {
                List<Person> candidates = getByFullname(
                        List.of(person.getFirstname(), person.getSurname()));
                for (Person candidate : candidates) {
                    if (candidate.doesNotConflictWith(person)
                            && !Utils.isUUID(candidate.getId())) {
                        if (candidate != person) {
                            candidate.merge(person);
                        }
                        break;
                    }
                }
            }
        }

        people.keySet().removeIf(Utils::isUUID);
    }

    public void removeDuplicatedPersons() {
        for (Person person : people.values()) {
            person.getSiblings().removeIf(this::isPartialPerson);
            person.getParents().removeIf(this::isPartialPerson);
            person.getChildren().removeIf(this::isPartialPerson);

            person.setSiblings(new HashSet<>(person.getSiblings()));
            person.setParents(new HashSet<>(person.getParents()));
            person.setChildren(new HashSet<>(person.getChildren()));
        }
    }

    private boolean isPartialPerson(Person person) {
        return Utils.isUUID(person.getId()) ||
                person.getSurname() == null ||
                person.getFirstname() == null;
    }

    public void remove(Person person) {
        people.remove(person.getId());
    }
}
