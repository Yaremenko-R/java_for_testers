package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateArea() {
        var t = new Triangle(6.0, 8.0, 10.0);
        double result = t.area();
        Assertions.assertEquals(24.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(6.0, 8.0, 10.0);
        double result = t.perimeter();
        Assertions.assertEquals(24.0, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(6.0, -8.0, 10.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //Ok
        }
    }

    @Test
    void cannotCreateTriangleWithWrongInequality() {
        try {
            new Triangle(3.0, 4.0, 8.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //Ok
        }
    }
}
