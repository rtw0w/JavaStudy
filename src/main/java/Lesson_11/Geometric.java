package Lesson_11;

public class Geometric implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Geometric(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
    public String getFillColor() {
        return fillColor;
    }
    public String getBorderColor() {
        return borderColor;
    }
}
class App {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Geometric(43, "Белый", "Черный"),
                new Rectangle(13, 53, "Красный", "Черный"),
                new Triangle(32, 11, 110, "Зеленый", "Черный")
        };
        for (Shape shape : shapes) {
            System.out.println("Фигура: " + shape.getClass().getSimpleName());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Цвет заливки: " + shape.getFillColor());
            System.out.println("Цвет границы: " + shape.getBorderColor());
            System.out.println();
        }
    }
}