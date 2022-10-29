package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findValue(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findValue(value, comparator.reversed());
    }

    public <T> T findValue(List<T> value, Comparator<T> comparator) {
        T max = value.get(0);
        for (T t : value) {
            max = (comparator.compare(t, max) > 0) ? t : max;
        }
        return max;
    }
}