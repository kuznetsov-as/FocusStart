package cft.focus.model;

public class Circle extends Shape {

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

    @Override
    public String generateInformation() {
        return "Тип фигуры: " + ShapeType.CIRCLE.getShapeType() +
                System.lineSeparator() +
                "Площадь: " + Shape.informationFormatter.format(calculateArea()) + " кв. мм" +
                System.lineSeparator() +
                "Периметр: " + Shape.informationFormatter.format(calculatePerimeter()) + " мм" +
                System.lineSeparator() +
                "Радиус: " + Shape.informationFormatter.format(getRadius()) + " мм" +
                System.lineSeparator() +
                "Диаметр: " + Shape.informationFormatter.format(calculateDiameter()) + " мм";
    }

    public double calculateDiameter() {
        return radius + radius;
    }

    public double getRadius() {
        return radius;
    }
}
