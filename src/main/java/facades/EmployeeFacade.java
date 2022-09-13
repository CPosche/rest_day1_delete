package facades;

import dtos.EmployeeDTO;
import dtos.PersonDTO;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO create(EmployeeDTO employeeDTO, float salery){
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getAddress(), salery);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return employeeDTO;
    }

    public EmployeeDTO getByID(int empID){
        EntityManager em = getEntityManager();
        Employee emp = em.find(Employee.class, empID);
        if (emp == null){
            throw new EntityNotFoundException("Employee with id " + empID + " not found");
        }
        return new EmployeeDTO(emp);
    }

    public List<EmployeeDTO> getEmployeesByName(String name){
        EntityManager em = getEntityManager();
        TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.name = :name", Employee.class);
        query.setParameter("name", name);
        return EmployeeDTO.getDtos(query.getResultList());
    }

    public List<EmployeeDTO> getAll(){
        EntityManager em = getEntityManager();
        TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
        return EmployeeDTO.getDtos(query.getResultList());
    }

    public EmployeeDTO getHighestSaleryEmp(){
        EntityManager em = getEntityManager();
        TypedQuery<Employee> query = em.createQuery("select e from Employee e order by e.salery desc", Employee.class);
        query.setMaxResults(1);
        return new EmployeeDTO(query.getSingleResult());
    }

    public EmployeeDTO update(EmployeeDTO employeeDTO, float salery){
        EntityManager em = getEntityManager();
        Employee fromDB = em.find(Employee.class, employeeDTO.getId());
        if (fromDB == null)
            throw new EntityNotFoundException("no such person with id " + employeeDTO.getId() + " exists");
        fromDB.setName(employeeDTO.getName());
        fromDB.setAddress(employeeDTO.getAddress());
        fromDB.setSalery(salery);
        try {
            em.getTransaction().begin();
            em.persist(fromDB);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return employeeDTO;
    }

}
