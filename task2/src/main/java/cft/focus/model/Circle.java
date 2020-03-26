package cft.focus.model;

import cft.focus.exceptions.ModelException;

public class Circle extends Shape {

    private double radius;

    public Circle(double radius) throws ModelException {
        if (IsCircleConstructionPossible(radius)) {
            this.radius = radius;
        } else {
            throw new ModelException("Радиус круга должен быть больше нуля");
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
    public String generateDetailedInformation() {

        return "Тип фигуры: " + ShapeType.CIRCLE.getShapeType() +
                System.lineSeparator() +
                this.generateGeneralInformation() +
                System.lineSeparator() +
                "Радиус: " + Shape.shapeInformationFormatter.format(getRadius()) + " мм" +
                System.lineSeparator() +
                "Диаметр: " + Shape.shapeInformationFormatter.format(calculateDiameter()) + " мм";
    }

    public double calculateDiameter() {
        return radius + radius;
    }

    public double getRadius() {
        return radius;
    }

    private boolean IsCircleConstructionPossible(double radius) {
        return radius > 0;
    }
}
