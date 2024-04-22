package mx.edu.unistmo.ixtepec.li.twi.p2.examples.swmetrics;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void mayorNumTest_Case1_ResultC() {
        // ARRANGE
        int a = 5;
        int b = 3;
        int c = 7;
        App app = new App();
        int expected = c;

        // ACT
        int result = app.majorNum(a, b, c);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    public void mayorNumTest_Case2_ResultA() {
        // ARRANGE
        int a = 5;
        int b = 3;
        int c = 4;
        App app = new App();
        int expected = a;

        // ACT
        int result = app.majorNum(a, b, c);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    public void mayorNumTest_Case3_ResultB() {
        // ARRANGE
        int a = 5;
        int b = 7;
        int c = 6;
        App app = new App();
        int expected = b;

        // ACT
        int result = app.majorNum(a, b, c);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    public void mayorNumTest_Case4_ResultC() {
        // ARRANGE
        int a = 5;
        int b = 7;
        int c = 9;
        App app = new App();
        int expected = c;

        // ACT
        int result = app.majorNum(a, b, c);

        // ASSERT
        assertEquals(expected, result);
    }
}