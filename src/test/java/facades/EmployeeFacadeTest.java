package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeFacadeTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    @BeforeAll
    static void beforeAll() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EmployeeFacade.getEmployeeFacade(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Employee").executeUpdate();
            em.createNativeQuery("ALTER TABLE employee AUTO_INCREMENT = 1").executeUpdate();
            em.persist(new Employee("test1", "testvej 1", 10.5f));
            em.persist(new Employee("test2", "testvej 2", 10.5f));
            em.persist(new Employee("test3", "testvej 3", 12.5f));
            em.persist(new Employee("test4", "testvej 4", 10.5f));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void createEmpTest() {
        EntityManager em = emf.createEntityManager();
        Employee emp = new Employee("test5", "testvej 5", 10.5f);
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        int excepted = 5;
        assertEquals(excepted, facade.getAll().size());
    }

    @Test
    void getEmpByIdTest() {
        int id = 3;
        EmployeeDTO emp = facade.getByID(3);
        assertEquals(id, emp.getId());
    }

    @Test
    void getEmpByNameTest() {
        String name = "test4";
        List<EmployeeDTO> empdto = facade.getEmployeesByName(name);
        assertTrue(empdto.stream().anyMatch(e -> e.getName().equals(name)));
    }

    @Test
    void getAllEmpTest() {
        List<EmployeeDTO> empdto = facade.getAll();
        List<String> expected = new ArrayList<String>(){{
            add("test1");
            add("test2");
            add("test3");
            add("test4");
        }};
        List<String> actual = new ArrayList<>();
        empdto.forEach(e -> actual.add(e.getName()));
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void getHighestSaleryTest() {
        String expected = "test3";
        assertEquals(expected, facade.getHighestSaleryEmp().getName());
    }
}
