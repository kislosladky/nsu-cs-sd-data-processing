package ru.nsu.kislitsyn.xml1;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.util.stream.Collectors;

public class PeopleHandler extends DefaultHandler {
    @Getter
    private final People people = new People();

    private Person currentPerson = new Person();

    private Tag currentTag;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "person" -> {
                currentPerson.setId(UUID.randomUUID().toString());
                if (attributes.getLength() > 0) {
                    String id = attributes.getValue("id");
                    String name = attributes.getValue("name");
                    if (id != null) {
                        currentPerson = people.getById(id.trim()).orElse(new Person());
                        currentPerson.setId(id);
                    } else {
                        List<String> fullname = Utils.splitByWhitespaces(name.trim());
                        currentPerson.setFirstname(fullname.getFirst());
                        currentPerson.setSurname(fullname.getLast());
                    }
                }
            }
            case "gender" -> {
                if (attributes.getLength() > 0) {
                    String gender = attributes.getValue("value").trim();
                    switch (gender) {
                        case "male":
                            currentPerson.setGender(Gender.MALE);
                            break;
                        case "female":
                            currentPerson.setGender(Gender.FEMALE);
                            break;
                    }
                } else {
                    currentTag = Tag.GENDER;
                }
            }
            case "id" -> {
                if (attributes.getLength() > 0) {
                    String id = attributes.getValue("value").trim();
                    if (currentPerson.getId() == null || Utils.isUUID(currentPerson.getId())) {
                        currentPerson.setId(id);
                    }
                }
            }

            case "firstname" -> {
                if (attributes.getLength() > 0) {
                    String firstname = attributes.getValue("value").trim();
                    currentPerson.setFirstname(firstname);
                } else {
                    currentTag = Tag.FIRSTNAME;
                }
            }

            case "surname" -> {
                if (attributes.getLength() > 0) {
                    String surname = attributes.getValue("value").trim();
                    currentPerson.setSurname(surname);
                }
            }

            case "family-name" -> {
                currentTag = Tag.FAMILYNAME;

            }
            case "first" -> {
                currentTag = Tag.FIRST;

            }
            case "family" -> {
                currentTag = Tag.FAMILY;

            }
            case "children-number" -> {
                if (attributes.getLength() > 0) {
                    String number = attributes.getValue("value").trim();
                    currentPerson.setChildrenNumber(Integer.parseInt(number));
                }

            }
            case "siblings-number" -> {
                if (attributes.getLength() > 0) {
                    String number = attributes.getValue("value").trim();
                    currentPerson.setSiblingsNumber(Integer.parseInt(number));
                }
            }

            case "siblings" -> {
                if (attributes.getLength() > 0) {
                    String siblingIds = attributes.getValue("val");
                    addSiblingsByIds(siblingIds);
                }
            }

            case "brother" -> {
                currentTag = Tag.BROTHER;
            }

            case "sister" -> {
                currentTag = Tag.SISTER;
            }

            case "parent" -> {
                if (attributes.getLength() > 0) {
                    String parentId = attributes.getValue("value").trim();
                    addParentById(parentId);
                } else {
                    currentTag = Tag.PARENT;
                }
            }

            case "father" -> {
                currentTag = Tag.FATHER;
            }

            case "mother" -> {
                currentTag = Tag.MOTHER;
            }

            case "wife" -> {
                if (attributes.getLength() > 0) {
                    String wifeId = attributes.getValue("value").trim();
                    addSpouseById(wifeId, Gender.FEMALE);
                }
            }

            case "husband" -> {
                if (attributes.getLength() > 0) {
                    String husbandId = attributes.getValue("value").trim();
                    addSpouseById(husbandId, Gender.MALE);
                }
            }
            case "spouce" -> {
                if (attributes.getLength() > 0) {
                    String spouseName = attributes.getValue("value").trim();
                    if (!spouseName.equals("NONE")) {
                        addSpouseByName(spouseName);
                    }
                }
            }

            case "daughter" -> {
                if (attributes.getLength() > 0) {
                    String daughterId = attributes.getValue("id").trim();
                    addChildById(daughterId, Gender.FEMALE);
                }
            }

            case "son" -> {
                if (attributes.getLength() > 0) {
                    String sonId = attributes.getValue("id").trim();
                    addChildById(sonId, Gender.MALE);
                }
            }

            case "child" -> {
                currentTag = Tag.CHILD;
            }

            default -> {}
        }

    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (currentTag == null || start == length) {
            return;
        }
        switch (currentTag) {
            case FIRST, FIRSTNAME -> currentPerson.setFirstname(content);
            case FAMILY, FAMILYNAME -> currentPerson.setSurname(content);
            case BROTHER -> addSiblingByName(content, Gender.MALE);
            case SISTER -> addSiblingByName(content, Gender.FEMALE);
            case PARENT -> addParentByName(content);
            case FATHER -> addParentByName(content, Gender.MALE);
            case MOTHER -> addParentByName(content, Gender.FEMALE);
            case GENDER -> setGender(content);
            case CHILD -> setChildByName(content);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            people.processPerson(currentPerson);
            currentPerson = new Person();
        }
        currentTag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        people.removeWrongKeys();
        people.removeDuplicatedPersons();
    }

    private void addChildById(String childId, Gender childGender) {
        Person child = people.getById(childId).orElse(new Person());
        child.setId(childId);
        child.addParent(currentPerson);
        child.setGender(childGender);
        currentPerson.addChild(child);
    }

    private void setChildByName(String rawChildName) {
        List<String> childFullName = Utils.splitByWhitespaces(rawChildName);

        List<Person> potentialChildren = people.getByFullname(childFullName);

        if (!potentialChildren.isEmpty()) {
            Person child = new Person();
            child.setFirstname(childFullName.getFirst());
            child.setSurname(childFullName.getLast());
            child.addParent(currentPerson);
            child.setId(UUID.randomUUID().toString());
            currentPerson.addChild(child);
            people.addPerson(child);
        }


        for (Person child : potentialChildren) {
            if (currentPersonCanBeParentOf(child)) {
                 currentPerson.addChild(child);
                 child.addParent(currentPerson);
                 break;
            }
        }
    }

    private boolean currentPersonCanBeParentOf(Person child) {
        Set<Person> parents = child.getParents();
        if (parents.size() == 2) {
            return false;
        }

        if (parents.size() == 1) {
            var parent = child.getParents().stream().toList().getFirst();
            if (parent.getGender() == null
                    || parent.getGender() != currentPerson.getGender()) {
                return true;
            }
        }
        return false;
    }

    private void setGender(String gender) {
        switch (gender.trim()) {
            case "M" -> currentPerson.setGender(Gender.MALE);
            case "F" -> currentPerson.setGender(Gender.FEMALE);
        }
    }

    private void addSpouseByName(String rawSpouseName) {
        List<String> fullSpouseName = Utils.splitByWhitespaces(rawSpouseName);
        List<Person> potentialSpouses = people.getByFullname(fullSpouseName);

        for (Person spouse : potentialSpouses) {
            if (currentPersonCanBeSpouseOf(spouse)) {
                spouse.setSpouse(currentPerson);
                currentPerson.setSpouse(spouse);
                if (currentPerson.getGender() == Gender.FEMALE) {
                    spouse.setGender(Gender.MALE);
                } else if (currentPerson.getGender() == Gender.MALE) {
                    spouse.setGender(Gender.FEMALE);
                }
            }
        }
    }

    private boolean currentPersonCanBeSpouseOf(Person person) {
        Person spouse = person.getSpouse();
        if (spouse == null) {
            return true;
        }

        return spouse.doesNotConflictWith(currentPerson) && spouse.equals(currentPerson);
    }

    private void addSpouseById(String id, Gender spouseGender) {
        Person spouse = people.getById(id).orElse(new Person());
        spouse.setId(id);
        spouse.setGender(spouseGender);
        spouse.setSpouse(currentPerson);
        currentPerson.setSpouse(spouse);
        if (spouseGender.equals(Gender.FEMALE)) {
            currentPerson.setGender(Gender.MALE);
        } else if (spouseGender.equals(Gender.MALE)) {
            currentPerson.setGender(Gender.FEMALE);
        }
    }


    private void addSiblingByName(String rawName, Gender siblingGender) {
        List<String> fullName = Utils.splitByWhitespaces(rawName);

        List<Person> potentialSiblings = people.getByFullname(fullName);

        for (Person person : potentialSiblings) {
                person.setGender(siblingGender);
                addCurrentPersonAsSiblingTo(person);
        }
    }

    private void addSiblingsByIds(String siblingIds) {
        List<String> splittedIds  = Utils.splitByWhitespaces(siblingIds);

        for (String id : splittedIds) {
            Optional<Person> sibling = people.getById(id);
            if (sibling.isEmpty()) {
                sibling = Optional.of(people.addEmptyPerson(id));
            }
            addCurrentPersonAsSiblingTo(sibling.get());
        }
    }

    private void addCurrentPersonAsSiblingTo(Person sibling) {
        sibling.getSiblings().add(currentPerson);
        currentPerson.getSiblings().add(sibling);
    }

    private void addParentById(String parentId) {
        if (parentId.equals("UNKNOWN")) {
            return;
        }
        Person parent = people.getById(parentId).orElse(new Person());
        parent.setId(parentId);

        parent.addChild(currentPerson);
        currentPerson.addParent(parent);
    }

    private void addParentByName(String parentName) {
        if (parentName.equals("UNKNOWN")) {
            return;
        }
        throw new RuntimeException("Parent handling not implemented");
    }

    private void addParentByName(String parentName, Gender parentGender) {
        List<String> fullname = Utils.splitByWhitespaces(parentName);

        List<Person> potentialParents = people.getByFullname(fullname);
        for (Person parent : potentialParents) {
            parent.setGender(parentGender);
            parent.addChild(currentPerson);
            currentPerson.addParent(parent);
        }
    }
}
