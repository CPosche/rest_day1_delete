package dtos;

import entities.Employee;
import entities.Person;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data public class EmployeeDTO {

    private Integer id;
    private String name;
    private String address;

    public EmployeeDTO(Employee emp) {
        if(emp.getId() != null)
            this.id = emp.getId();
        this.name = emp.getName();
        this.address = emp.getAddress();
    }

    public static List<EmployeeDTO> getDtos(List<Employee> employees){
        List<EmployeeDTO> employeesDto = new ArrayList();
        employees.forEach(rm->employeesDto.add(new EmployeeDTO(rm)));
        return employeesDto;
    }
}
