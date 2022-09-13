package entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
@Entity
@Table(name = "employee")
@Data public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false)
    private Integer id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @Column(name = "emp_address", nullable = false)
    private String address;

    @Column(name = "emp_salery", nullable = false)
    private float salery;

    public Employee() {
    }

    public Employee(String name, String address, float salery) {
        this.name = name;
        this.address = address;
        this.salery = salery;
    }

    public Employee(Integer id, String name, String address, float salery) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salery = salery;
    }
}