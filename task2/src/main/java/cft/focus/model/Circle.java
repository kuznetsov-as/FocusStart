package cft.focus.model;

import cft.focus.exceptions.ModelException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Circle extends Shape {

    private final double radius;

    Circle(double radius) throws ModelException {
        if (isCircleConstructionPossible(radius)) {
            this.radius = radius;
            log.info("Круг создан");
        } else {
            log.info("Не удалось создать круг");
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
                "Радиус: " + Shape.SHAPE_INFORMATION_FORMATTER.format(getRadius()) + " " +
                Shape.GENERAL_UNIT_OF_MEASUREMENT +
                System.lineSeparator() +
                "Диаметр: " + Shape.SHAPE_INFORMATION_FORMATTER.format(calculateDiameter()) + " " +
                Shape.GENERAL_UNIT_OF_MEASUREMENT;
    }

    private double calculateDiameter() {
        return radius + radius;
    }

    private double getRadius() {
        return radius;
    }

    private boolean isCircleConstructionPossible(double radius) {
        return radius > 0;
    }
}
