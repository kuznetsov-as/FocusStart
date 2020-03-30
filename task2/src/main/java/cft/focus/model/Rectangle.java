package cft.focus.model;

import cft.focus.exceptions.ModelException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rectangle extends Shape {

    private final double length;
    private final double width;

    Rectangle(double length, double width) throws ModelException {
        if (IsRectangleConstructionPossible(length, width)) {
            this.length = length;
            this.width = width;
            log.info("Прямоугольник создан");
        } else {
            log.info("Не удалось создать прямоугольник");
            throw new ModelException("Длина и ширина прямоугольника должны быть больше нуля");
        }
    }

    @Override
    public double calculatePerimeter() {
        return (length + width) * 2;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public String generateDetailedInformation() {
        return "Тип фигуры: " + ShapeType.RECTANGLE.getShapeType() +
                System.lineSeparator() +
                this.generateGeneralInformation() +
                System.lineSeparator() +
                "Длина диагонали: " + Shape.shapeInformationFormatter.format(calculateDiagonal()) + " " +
                Shape.generalUnitOfMeasurement +
                System.lineSeparator() +
                "Длина: " + Shape.shapeInformationFormatter.format(getLength()) + " " +
                Shape.generalUnitOfMeasurement +
                System.lineSeparator() +
                "Ширина: " + Shape.shapeInformationFormatter.format(getWidth()) + " " +
                Shape.generalUnitOfMeasurement;
    }

    public double calculateDiagonal() {
        return Math.sqrt((length * length) + (width * width));
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    private boolean IsRectangleConstructionPossible(double length, double width) {
        return length > 0 && width > 0;
    }
}
