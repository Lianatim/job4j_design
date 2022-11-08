package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private final Store store;
    private final Gson lib;

    public ReportJSON(Store store) {
        this.store = store;
        this.lib = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> sorted = store.findBy(filter);
        return lib.toJson(sorted);
    }
}

