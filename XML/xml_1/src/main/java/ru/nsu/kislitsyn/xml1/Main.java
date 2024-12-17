package ru.nsu.kislitsyn.xml1;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public static void main(String[] args) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        PeopleHandler handler = new PeopleHandler();
        parser.parse("people.xml", handler);

        handler.getPeople().print();
//        for (String id : handler.getIds()) {
//            System.out.println(id);
//        }
//        System.out.println(handler.getIds().size());
    }
}