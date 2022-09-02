package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "six");
        assertThat(list).hasSize(6)
                .contains("six")
                .contains("three", Index.atIndex(2))
                .containsAnyOf("three", "nine")
                .doesNotContain("second", Index.atIndex(6))
                .startsWith("first")
                .endsWith("five", "six");
        assertThat(list).filteredOn(e -> e.startsWith("s")).hasSize(2);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "six", "eight");
        assertThat(set).isNotNull()
                .contains("six")
                .containsAnyOf("three", "nine")
                .hasSize(7);
        assertThat(set).filteredOn(e -> e.startsWith("se")).first().isEqualTo("second");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "second", "three")
                .containsValues(1, 2, 3)
                .doesNotContainKey("zero")
                .doesNotContainValue(6)
                .containsEntry("first", 0);
    }
}