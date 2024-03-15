package lv.course.testing.frameworks.service.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParametrizedTestExample {


    @ParameterizedTest
    @ValueSource(ints = {1, 5, 97, 237941, -7})
    void testIsOdd(int number) {
        assertTrue(isOdd(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 98, 2379416, -70})
    void testIsNotOdd(int number) {
        assertFalse(isOdd(number));
    }

    boolean isOdd(int number) {
        return number % 2 == 1;
    }

    @ParameterizedTest
    @MethodSource("divisionSources")
    void testDivision(double num1, double num2, double expected) {
        assertEquals(expected, getDivision(num1, num2));
    }

    private static Stream<Arguments> divisionSources() {
        return Stream.of(
                Arguments.of(1.0, 2.0, 0.5),
                Arguments.of(3.0, -2.0, -1.5),
                Arguments.of(10.0, 5.0, 2.0),
                Arguments.of(4, 3.0, 1.33333),
                Arguments.of(-6.6, 3.0, -2.2),
                Arguments.of(2, 0, Double.MAX_VALUE)
        );
    }

    double getDivision(double num1, double num2) {
        return num1 / num2;
    }

}
