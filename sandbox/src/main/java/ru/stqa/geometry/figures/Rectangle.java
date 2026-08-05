package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double a, double b) {
        String text = String.format("Площадь прямоуголника со сторонами %f и %f = %f", a, b, Area(a, b));
        System.out.println(text);
    }

    public static double Area(double a, double b) {
        return a * b;
    }
}
