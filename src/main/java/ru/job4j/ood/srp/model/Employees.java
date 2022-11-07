package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public Employees() {
    }

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

}