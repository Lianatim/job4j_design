package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;


import java.util.*;

class MaxMinTest {

    private Comparator<Integer> comparator;

    @BeforeEach
    void test() {
        comparator = Comparator.naturalOrder();
    }


    @Test
    void whenFindMax() {
        MaxMin max = new MaxMin();
        List<Integer> search = Arrays.asList(4, 6, 3, 2, 9, 1);
        assertThat(max.max(search, comparator)).isEqualTo(9);
    }

    @Test
    void whenFindMin() {
        MaxMin min = new MaxMin();
        List<Integer> search = Arrays.asList(4, 6, 3, 2, 9, 1);
        assertThat(min.min(search, comparator)).isEqualTo(1);
    }

    @Test
    void whenListIsNull() {
        MaxMin min = new MaxMin();
        List<Integer> search = Collections.emptyList();
        assertThat(min.findValue(search, comparator)).isNull();
    }
}