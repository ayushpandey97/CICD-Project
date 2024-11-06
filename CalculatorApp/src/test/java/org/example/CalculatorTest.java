package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    }

    @Test
    void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3), "5 - 3 should equal 2");
    }

    @Test
    void testMultiply() {
        assertEquals(15, calculator.multiply(5, 3), "5 * 3 should equal 15");
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3), "6 / 3 should equal 2");
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
}
