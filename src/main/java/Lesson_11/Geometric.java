interface Shape {
    double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();
}
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
class Rectangle implements Shape {
    private double width, height;
    private String fillColor, borderColor;
    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    public double getPerimeter() {
        return 2 * (width + height);
    }
    public double getArea() {
        return width * height;
    }
    public String getFillColor() {
        return fillColor;
    }
    public String getBorderColor() {
        return borderColor;
    }
}
class Triangle implements Shape {
    private double a, b, c;
    private String fillColor, borderColor;
    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    public double getPerimeter() {
        return a + b + c;
    }
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
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