public class Rectangle {
    static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоуголника со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
