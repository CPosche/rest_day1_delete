/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private long id;
    private String name;
    private int age;

    public PersonDTO(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static List<PersonDTO> getDtos(List<Person> persons){
        List<PersonDTO> personsDto = new ArrayList();
        persons.forEach(rm->personsDto.add(new PersonDTO(rm)));
        return personsDto;
    }


    public PersonDTO(Person rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.name = rm.getName();
        this.age = rm.getAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
