package cft.focus.model;

public class Circle implements GeneralCharacteristicsShape {

    private double radius;

    public Circle(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            System.out.println("Радиус круга должен быть больше нуля");
            System.exit(1);
        }
    }

    @Override
    public double calculatePerimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * (radius * radius);
    }

    public double calculateDiameter() {
        return radius + radius;
    }

    public double getRadius() {
        return radius;
    }
}
