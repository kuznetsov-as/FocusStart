package cft.focus.model;

import cft.focus.exceptions.ModelException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Triangle extends Shape {

    private final double lengthA;
    private final double lengthB;
    private final double lengthC;

    Triangle(double lengthA, double lengthB, double lengthC) throws ModelException {

        if (isTriangleConstructionPossible(lengthA, lengthB, lengthC)) {
            this.lengthA = lengthA;
            this.lengthB = lengthB;
            this.lengthC = lengthC;
            log.info("Треугольник создан");
        } else {
            log.info("Не удалось создать треугольник");
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
                "Длина стороны A: " + Shape.shapeInformationFormatter.format(getLengthA()) + " " +
                Shape.generalUnitOfMeasurement +
                System.lineSeparator() +
                "Угол противолежащий стороне A: " +
                Shape.shapeInformationFormatter.format(calculateAngleOppositeTheSideA()) + " градусов" +
                System.lineSeparator() +
                "Длина стороны B: " +
                Shape.shapeInformationFormatter.format(getLengthB()) + " " +
                Shape.generalUnitOfMeasurement +
                System.lineSeparator() +
                "Угол противолежащий стороне B: " +
                Shape.shapeInformationFormatter.format(calculateAngleOppositeTheSideB()) + " градусов" +
                System.lineSeparator() +
                "Длина стороны C: " + Shape.shapeInformationFormatter.format(getLengthC()) + " " +
                Shape.generalUnitOfMeasurement +
                System.lineSeparator() +
                "Угол противолежащий стороне C: " +
                Shape.shapeInformationFormatter.format(calculateAngleOppositeTheSideC()) + " градусов";
    }

    private double calculateAngleOppositeTheSideA() {
        double angle = Math.acos((lengthB * lengthB + lengthC * lengthC - lengthA * lengthA) /
                (2 * lengthB * lengthC));
        return angle * 180 / Math.PI;
    }

    private double calculateAngleOppositeTheSideB() {
        double angle = Math.acos((lengthA * lengthA + lengthC * lengthC - lengthB * lengthB) /
                (2 * lengthA * lengthC));
        return angle * 180 / Math.PI;
    }

    private double calculateAngleOppositeTheSideC() {
        double angle = Math.acos((lengthA * lengthA + lengthB * lengthB - lengthC * lengthC) /
                (2 * lengthA * lengthB));
        return angle * 180 / Math.PI;
    }

    private double getLengthA() {
        return lengthA;
    }

    private double getLengthB() {
        return lengthB;
    }

    private double getLengthC() {
        return lengthC;
    }

    private boolean isTriangleConstructionPossible(double lengthA, double lengthB, double lengthC) {
        return (lengthA < lengthB + lengthC && lengthB < lengthA + lengthC && lengthC < lengthA + lengthB) &&
                (lengthA > 0 && lengthB > 0 && lengthC > 0);
    }
}
