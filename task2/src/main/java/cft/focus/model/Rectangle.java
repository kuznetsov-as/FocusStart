package cft.focus.model;

import cft.focus.exceptions.ModelException;

public class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(double length, double width) throws ModelException {
        if (IsRectangleConstructionPossible(length, width)) {
            this.length = length;
            this.width = width;
        } else {
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
                "Длина диагонали: " + Shape.shapeInformationFormatter.format(calculateDiagonal()) + " мм" +
                System.lineSeparator() +
                "Длина: " + Shape.shapeInformationFormatter.format(getLength()) + " мм" +
                System.lineSeparator() +
                "Ширина: " + Shape.shapeInformationFormatter.format(getWidth()) + " мм";
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
