package ru.nsu.kislitsyn.xml1;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class PeopleHandler extends DefaultHandler {
    @Getter
    private final People people = new People();
    @Getter
    private final Set<String> ids = new HashSet<>();

    private Person currentPerson = new Person();

    private Tag currentTag;

//    @Override
//    public void startDocument() throws SAXException {
//        System.out.println("startDocument");
//    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        tags.add(qName);
        if (qName.equals("person")) {
            if (attributes.getLength() > 0) {
                String id = attributes.getValue("id");
                String name = attributes.getValue("name");
                if (id != null) {
                    currentPerson.setId(id);
                } else {
                    List<String> fullname = Arrays.stream(name.split(" ")).filter(x -> !x.equals(" ")).toList();
                    assert (fullname.size() > 1);
                    currentPerson.setFirstname(fullname.getFirst());
                    currentPerson.setSurname(fullname.get(1));
                }
            }
            return;
        }

        if (qName.equals("gender")) {
            if (attributes.getLength() > 0) {
                String gender = attributes.getValue("value");
                switch (gender) {
                    case "male": currentPerson.setGender(Gender.MALE); break;
                    case "female": currentPerson.setGender(Gender.FEMALE); break;
                }
            }
            return;
        }

        if (qName.equals("id")) {
            if (attributes.getLength() > 0) {
                String id = attributes.getValue("value");
                currentPerson.setId(id);
            }
        }

        if (qName.equals("firstname")) {
            if (attributes.getLength() > 0) {
                String firstname = attributes.getValue("value");
                currentPerson.setFirstname(firstname);
            } else {
                currentTag = Tag.FIRSTNAME;
            }
        }

        if (qName.equals("surname")) {
            if (attributes.getLength() > 0) {
                String surname = attributes.getValue("value");
                currentPerson.setSurname(surname);
            }
        }

        if (qName.equals("family-name")) {
            currentTag = Tag.FAMILYNAME;
        }

        if (qName.equals("first")) {
            currentTag = Tag.FIRST;
        }

        if (qName.equals("family")) {
            currentTag = Tag.FAMILY;
        }

        if (qName.equals("children-number")) {
            if (attributes.getLength() > 0) {
                String number = attributes.getValue("value");
                currentPerson.setChildrenNumber(Integer.parseInt(number));
            }
        }

        if (qName.equals("siblings-number")) {
            if (attributes.getLength() > 0) {
                String number = attributes.getValue("value");
                currentPerson.setSiblingsNumber(Integer.parseInt(number));
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length);
        if (currentTag == null || start == length) {
            return;
        }
        switch (currentTag) {
            case FIRST, FIRSTNAME: currentPerson.setFirstname(string); break;
            case FAMILY, FAMILYNAME: currentPerson.setSurname(string); break;
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
}
