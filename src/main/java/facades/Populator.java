/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import dtos.PersonDTO;
import entities.Employee;
import entities.Person;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(emf);
//        PersonFacade pf = PersonFacade.getPersonFacade(emf);
//        pf.create(new PersonDTO(new Person("Casper", 27)));
//        pf.create(new PersonDTO(new Person("Mia", 30)));
//        pf.create(new PersonDTO(new Person("Frederik", 5)));
//        pf.create(new PersonDTO(new Person("Dunner", 2)));
        ef.create(new EmployeeDTO(new Employee("Casper", "fluffavej 27", 50.5f)), 50.5f);
        ef.create(new EmployeeDTO(new Employee("Mia", "larslarsvej 22", 32.52f)), 32.52f);
        ef.create(new EmployeeDTO(new Employee("Frederik", "Scrubvej 7", 12.1f)), 12.1f);
        ef.create(new EmployeeDTO(new Employee("Hartmann", "gitgutvej 1337", 20.76f)), 20.76f);
    }
    
    public static void main(String[] args) {
        populate();
    }
}
