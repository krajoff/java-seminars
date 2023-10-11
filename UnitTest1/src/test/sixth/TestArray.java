package sixth;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seminars.sixth.array.ArrayCalculation;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestArray {

    List<Double> array1;
    List<Double> array2;
    List<Double> array3;
    ArrayCalculation arrayCalculation;

    @BeforeEach
    public void testInit() {
        array1 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        array2 = Arrays.asList(-1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        array3 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        arrayCalculation = new ArrayCalculation();
    }

    @Test
    public void simpleTest() {
        ArrayCalculation mockedList = mock(ArrayCalculation.class);
        mockedList.average(array1);
        verify(mockedList).average(array1);
    }

    @Test
    public void averageTest() {
        assertEquals(3.5, arrayCalculation.average(array1));
        assertEquals(3.16, arrayCalculation.average(array2), 0.01);
    }

    @Test
    public void compareTestArrayFirstBigger() {
        String expected = "Первый список имеет большее среднее значение";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        arrayCalculation.compare(array1, array2);
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertEquals(expected, actual);
    }

    @Test
    public void compareTestArrayEquals() {
        String expected = "Средние значения равны";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        arrayCalculation.compare(array3, array1);
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertEquals(expected, actual);
    }

}
