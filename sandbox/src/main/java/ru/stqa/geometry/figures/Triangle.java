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
        if (!(side1 + side2 > side3 && side3 + side2 > side1 && side1 + side3 > side2)) {
            throw new IllegalArgumentException("Triangle with such sides can not be created");
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(side1, this.side1) == 0 && Double.compare(side2, this.side2) == 0 && Double.compare(side3, this.side3) == 0)
                || (Double.compare(side1, this.side2) == 0 && Double.compare(side2, this.side1) == 0 && Double.compare(side3, this.side1) == 0)
                || (Double.compare(side1, this.side3) == 0 && Double.compare(side2, this.side3) == 0 && Double.compare(side3, this.side2) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
