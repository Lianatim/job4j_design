package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("pher")
                .contains("Sphere")
                .doesNotContain("Tetrahedron")
                .startsWith("Sp")
                .startsWithIgnoringCase("s")
                .endsWith("re")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 5);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("TETRAH")
                .contains("rahe")
                .doesNotContain("Unknown")
                .startsWith("Tetr")
                .startsWithIgnoringCase("t")
                .endsWith("ron")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isNumberOfVerticesSphere() {
        Box box = new Box(0, 10);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isEven()
                .isGreaterThan(-1)
                .isLessThan(5)
                .isEqualTo(0);
    }

    @Test
    void isNumberOfVerticesTetrahedron() {
        Box box = new Box(4, 5);
        int result = box.getNumberOfVertices();
        assertThat(result).isPositive()
                .isEven()
                .isGreaterThan(3)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void isNotExist() {
        Box box = new Box(1, 5);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void isExistCube() {
        Box box = new Box(8, 5);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenGetAreaSphere() {
        Box box = new Box(0, 5);
        double result = box.getArea();
        assertThat(result).isEqualTo(314.15d, withPrecision(0.01d))
                .isCloseTo(314.15d, withPrecision(0.01d))
                .isCloseTo(314.25d, Percentage.withPercentage(1.0d))
                .isGreaterThan(313.15d)
                .isLessThan(316.15d);
    }

    @Test
    void whenGetAreaCube() {
        Box box = new Box(8, 5);
        double result = box.getArea();
        assertThat(result).isEqualTo(150.0d, withPrecision(0.001d))
                .isCloseTo(150.0d, withPrecision(0.01d))
                .isCloseTo(150.0d, Percentage.withPercentage(1.0d))
                .isGreaterThan(120.0d)
                .isLessThan(151.0d);
    }
}