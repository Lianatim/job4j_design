package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;


import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> sortedEmployee = store.findBy(filter);
        sortedEmployee.sort(((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary())));
        for (Employee employee : sortedEmployee) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
