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
    void canCalculatePerimeter(){
        var r = new Rectangle(5.0, 7.0);
        double result = r.perimeter();
        Assertions.assertEquals(24.0, result);
    }
}
