package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void canCalculateArea() {
        var r = new Rectangle(5.0, 7.0);
        double result = r.area();
        Assertions.assertEquals(35.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var r = new Rectangle(5.0, 7.0);
        double result = r.perimeter();
        Assertions.assertEquals(24.0, result);
    }

    @Test
    void cannotCreateRectangleWithNegativeSide() {
        try {
            new Rectangle(-5.0, 7.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //Ok
        }
    }

    @Test
    void testEquality() {
        var r1 = new Rectangle(5.0, 7.0);
        var r2 = new Rectangle(5.0, 7.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality2() {
        var r1 = new Rectangle(5.0, 7.0);
        var r2 = new Rectangle(7.0, 5.0);
        Assertions.assertEquals(r1, r2);
    }
}
