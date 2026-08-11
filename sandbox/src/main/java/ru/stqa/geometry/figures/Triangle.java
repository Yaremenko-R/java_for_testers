package ru.stqa.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
    }

    public static void printTriangleArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", t.side1, t.side2, t.side3, t.area());
        System.out.println(text);
    }

    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", t.side1, t.side2, t.side3, t.perimeter());
        System.out.println(text);
    }

    public double area() {
        var p = ((this.side1 + this.side2 + this.side3) / 2);
        return sqrt((p * (p - this.side1) * (p - this.side2) * (p - this.side3)));
    }

    public double perimeter() {
        return this.side1 + this.side2 + this.side3;
    }
}
