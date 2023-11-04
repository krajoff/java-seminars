package third.coverage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import seminars.third.coverage.SomeService;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SomeServiceTest {
    SomeService someService;

    @BeforeEach
    void testInit() {
        someService = new SomeService();
    }

    @Test
    void testFizzBuzz() {
        assertEquals("Fizz", someService.fizzBuzz(3));
        assertEquals("Buzz", someService.fizzBuzz(5));
        assertEquals("FizzBuzz", someService.fizzBuzz(15));
        assertNull(someService.fizzBuzz(22));
    }

    @Test
    void testEvenOddNumber() {
        assertFalse(someService.evenOddNumber(Integer.MAX_VALUE));
        assertTrue(someService.evenOddNumber(Integer.MAX_VALUE - 1));
    }

    @Test
    void testNumberInInterval(){
        assertFalse(someService.numberInInterval(Integer.MAX_VALUE));
        assertFalse(someService.numberInInterval(23));
        assertTrue(someService.numberInInterval(50));
        assertFalse(someService.numberInInterval(100));
        assertFalse(someService.numberInInterval(101));
    }

}