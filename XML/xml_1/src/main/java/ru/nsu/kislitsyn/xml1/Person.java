package ru.nsu.kislitsyn.xml1;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String id;
//    private List<Person> parents = new ArrayList<>();
//    private List<Person> children = new ArrayList<>();
//    private List<Person> siblings = new ArrayList<>();
    private Set<Person> parents = new HashSet<>();
    private Set<Person> children = new HashSet<>();
    private Set<Person> siblings = new HashSet<>();
    private String firstname;
    private String surname;
    private Person spouse;
    private Gender gender;
    private int siblingsNumber = -1;
    private int childrenNumber = -1;

    public void merge(Person person) {
        this.id = id == null ? person.getId() : id;

//        this.father = father == null ? person.getFather() : father;
//
//        this.mother = mother == null ? person.getMother() : mother;


        this.firstname = firstname == null ? person.getFirstname() : firstname;

        this.surname = surname == null ? person.getSurname() : surname;

        this.spouse = spouse == null ? person.getSpouse() : spouse;

        this.gender = gender == null ? person.getGender() : gender;

        this.siblingsNumber = siblingsNumber == -1 ? person.getSiblingsNumber() : siblingsNumber;

        this.childrenNumber = childrenNumber == -1 ? person.getChildrenNumber() : childrenNumber;

        this.children.addAll(person.children);

        this.siblings.addAll(person.siblings);
//        this.brothers.addAll(person.getBrothers());
//
//        this.sisters.addAll(person.getSisters());
    }

    public Person addChild(Person child) {
        this.children.add(child);
        return child;
    }

    public Person addParent(Person parent) {
        this.parents.add(parent);
        return parent;
    }

    public boolean doesNotConflictWith(Person otherPerson) {
        if (this.siblingsNumber == otherPerson.getSiblingsNumber()
            || this.siblingsNumber == -1
            || otherPerson.siblingsNumber == -1) {

            if (this.childrenNumber == otherPerson.getChildrenNumber()
                || this.childrenNumber == -1
                || otherPerson.childrenNumber == -1) {
                return true;
            }
        }
        return false;
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


    @Override
    public int hashCode() {
        int result = 17; // Начальное значение для хэш-кода
        if (id != null) {
            result = 31 * result + id.hashCode();
        } else {
            result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
            result = 31 * result + (surname != null ? surname.hashCode() : 0);
            result = 31 * result + Integer.hashCode(childrenNumber);
            result = 31 * result + Integer.hashCode(siblingsNumber);
        }
        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{");
        sb.append("id='").append(id).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", gender=").append(gender);
        if (spouse != null) {
            sb.append(", ").append(spouse.gender == Gender.MALE ? "husband=" : "wife=");
            sb.append(spouse.getFirstname()).append(" ").append(spouse.getSurname());
        } else {
            sb.append(", spouse=None");
        }
        sb.append(", siblingsNumber=").append(siblingsNumber);
        sb.append(", childrenNumber=").append(childrenNumber);

        sb.append(", parents=[");
        for (Person parent : parents) {
            if (parent.getFirstname() == null && parent.getSurname() == null) {
                continue;
            }
            String relation = parent.getGender() == Gender.MALE ? "father" : "mother";
            sb.append(relation).append(": ").append(parent.getFirstname()).append(" ").append(parent.getSurname());
            sb.append(", ");
        }
        sb.append(']');

        sb.append(", children=[");
        for (Person child : children) {
            if (child.getFirstname() == null && child.getSurname() == null) {
                continue;
            }
            String relation = child.getGender() == Gender.MALE ? "son" : "daughter";
            sb.append(relation).append(": ").append(child.getFirstname()).append(" ").append(child.getSurname());
            sb.append(", ");
        }
        sb.append(']');

        sb.append(", siblings=[");
        for (Person sibling : siblings) {
            if (sibling.getFirstname() == null && sibling.getSurname() == null) {
                continue;
            }
            String relation = sibling.getGender() == Gender.MALE ? "brother" : "sister";
            sb.append(relation).append(": ").append(sibling.getFirstname()).append(" ").append(sibling.getSurname());
            sb.append(", ");
        }
        sb.append(']');

        sb.append('}');
        return sb.toString();
    }


}
