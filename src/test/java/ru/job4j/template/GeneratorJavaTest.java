package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class GeneratorJavaTest {

    @Test
    void whenProduceFailed() {
        Generator generator = new GeneratorJava();
        String template = "I am a ${surname}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, args);
        });
    }

    @Test
    void whenProduceContainsExtraKeys() {
        Generator generator = new GeneratorJava();
        String template = "I am a ${name} ${surname}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, args);
        });
    }

    @Test
    void whenProduceContainsKeysIsNull() {
        Generator generator = new GeneratorJava();
        String template = "I am a name surname, Who are subject? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, args);
        });
    }

    @Test
    void whenProduceSuccess() {
        Generator generator = new GeneratorJava();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        String rsl = "I am a Petr Arsentev, Who are you? ";
        assertThat(generator.produce(template, args)).isEqualTo(rsl);
    }
}