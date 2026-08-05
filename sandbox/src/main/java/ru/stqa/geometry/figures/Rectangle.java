package ru.stqa.geometry.figures;

public class Rectangle {

    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public static void printRectangleArea(Rectangle r) {
        String text = String.format("Площадь прямоуголника со сторонами %f и %f = %f", r.side1, r.side2, r.area());
        System.out.println(text);
    }

    public static void printRectanglePerimeter(Rectangle r) {
        String text = String.format("Периметр прямоуголника со сторонами %f и %f = %f", r.side1, r.side2, r.perimeter());
        System.out.println(text);
    }

    public double area() {
        return this.side1 * this.side2;
    }

    public double perimeter() {
        return 2 * (this.side1 + this.side2);
    }
}
