package cft.focus.model;

import cft.focus.exceptions.ModelException;

public class Triangle extends Shape {

    private double lengthA;
    private double lengthB;
    private double lengthC;

    public Triangle(double lengthA, double lengthB, double lengthC) throws ModelException {

        if (IsTriangleConstructionPossible(lengthA, lengthB, lengthC)) {
            this.lengthA = lengthA;
            this.lengthB = lengthB;
            this.lengthC = lengthC;
        } else {
            throw new ModelException("Невозможно построить треугольник с такими стронами");
        }
    }

    @Override
    public double calculatePerimeter() {
        return lengthA + lengthB + lengthC;
    }

    @Override
    public double calculateArea() {
        double semiperimeter = calculatePerimeter() / 2;
        return Math.sqrt(semiperimeter * (semiperimeter - lengthA) * (semiperimeter - lengthB) *
                (semiperimeter - lengthC));
    }

    @Override
    public String generateDetailedInformation() {
        return "Тип фигуры: " + ShapeType.TRIANGLE.getShapeType() +
                System.lineSeparator() +
                this.generateGeneralInformation() +
                System.lineSeparator() +
                "Длина стороны A: " + Shape.shapeInformationFormatter.format(getLengthA()) + " мм" +
                System.lineSeparator() +
                "Угол противолежащий стороне A: " +
                Shape.shapeInformationFormatter.format(calculateAngleOppositeTheSideA()) + " градусов" +
                System.lineSeparator() +
                "Длина стороны B: " +
                Shape.shapeInformationFormatter.format(getLengthB()) + " мм" +
                System.lineSeparator() +
                "Угол противолежащий стороне B: " +
                Shape.shapeInformationFormatter.format(calculateAngleOppositeTheSideB()) + " градусов" +
                System.lineSeparator() +
                "Длина стороны C: " + Shape.shapeInformationFormatter.format(getLengthC()) + " мм" +
                System.lineSeparator() +
                "Угол противолежащий стороне C: " +
                Shape.shapeInformationFormatter.format(calculateAngleOppositeTheSideC()) + " градусов";
    }

    public double calculateAngleOppositeTheSideA() {
        double angle = Math.acos((lengthB * lengthB + lengthC * lengthC - lengthA * lengthA) /
                (2 * lengthB * lengthC));
        return angle * 180 / Math.PI;
    }

    public double calculateAngleOppositeTheSideB() {
        double angle = Math.acos((lengthA * lengthA + lengthC * lengthC - lengthB * lengthB) /
                (2 * lengthA * lengthC));
        return angle * 180 / Math.PI;
    }

    public double calculateAngleOppositeTheSideC() {
        double angle = Math.acos((lengthA * lengthA + lengthB * lengthB - lengthC * lengthC) /
                (2 * lengthA * lengthB));
        return angle * 180 / Math.PI;
    }

    public double getLengthA() {
        return lengthA;
    }

    public double getLengthB() {
        return lengthB;
    }

    public double getLengthC() {
        return lengthC;
    }

    private boolean IsTriangleConstructionPossible(double lengthA, double lengthB, double lengthC) {
        return (lengthA < lengthB + lengthC && lengthB < lengthA + lengthC && lengthC < lengthA + lengthB) &&
                (lengthA > 0 && lengthB > 0 && lengthC > 0);
    }
}
