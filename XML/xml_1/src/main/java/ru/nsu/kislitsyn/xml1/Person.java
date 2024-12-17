package ru.nsu.kislitsyn.xml1;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String id;
    private Person father;
    private Person mother;
    private List<Person> sons = new ArrayList<>();
    private List<Person> daughters = new ArrayList<>();
    private List<Person> brothers = new ArrayList<>();
    private List<Person> sisters = new ArrayList<>();
    private String firstname;
    private String surname;
    private Person spouse;
    private Gender gender;
    private int siblingsNumber;
    private int childrenNumber;

    public void merge(Person person) {
        this.id = id == null ? person.getId() : id;

        this.father = father == null ? person.getFather() : father;

        this.mother = mother == null ? person.getMother() : mother;

        this.firstname = firstname == null ? person.getFirstname() : firstname;

        this.surname = surname == null ? person.getSurname() : surname;

        this.spouse = spouse == null ? person.getSpouse() : spouse;

        this.gender = gender == null ? person.getGender() : gender;

        this.siblingsNumber = siblingsNumber == 0 ? person.getSiblingsNumber() : siblingsNumber;

        this.childrenNumber = childrenNumber == 0 ? person.getChildrenNumber() : childrenNumber;

        this.sons.addAll(person.getSons());

        this.daughters.addAll(person.getDaughters());

        this.brothers.addAll(person.getBrothers());

        this.sisters.addAll(person.getSisters());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        if ((this.id != null
                && this.id.equals(person.id))
                    || (this.firstname != null
                        && (this.firstname.equals(person.firstname))
                        && (this.surname != null)
                        && (this.surname.equals(person.surname)))) {
            return true;
        }
        return false;
    }
}
