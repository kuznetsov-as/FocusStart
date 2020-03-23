package cft.focus.model;

public class Triangle extends Shape {

    private double lengthA;
    private double lengthB;
    private double lengthC;

    public Triangle(double lengthA, double lengthB, double lengthC) {

        if (lengthA < lengthB + lengthC && lengthB < lengthA + lengthC && lengthC < lengthA + lengthB) {
            if (lengthA > 0 || lengthB > 0 || lengthC > 0) {
                this.lengthA = lengthA;
                this.lengthB = lengthB;
                this.lengthC = lengthC;
            } else {
                System.out.println("Каждая из сторон треугольника должна быть больше нуля");
                System.exit(1);
            }
        } else {
            System.out.println("Невозможно построить треугольник с такими стронами");
            System.exit(1);
        }
    }

    @Override
    public double calculatePerimeter() {
        return lengthA + lengthB + lengthC;
    }

    @Override
    public double calculateArea() {
        double semiperimeter = calculatePerimeter() / 2;
        return Math.sqrt(semiperimeter * (semiperimeter - lengthA) * (semiperimeter - lengthB) * (semiperimeter - lengthC));
    }

    @Override
    public String generateInformation() {
        return "Тип фигуры: " + ShapeType.TRIANGLE.getShapeType() +
                System.lineSeparator() +
                "Площадь: " + Shape.informationFormatter.format(calculateArea()) + " кв. мм" +
                System.lineSeparator() +
                "Периметр: " + Shape.informationFormatter.format(calculatePerimeter()) + " мм" +
                System.lineSeparator() +
                "Длина стороны A: " + Shape.informationFormatter.format(getLengthA()) + " мм" +
                System.lineSeparator() +
                "Угол противолежащий стороне A: " + Shape.informationFormatter.format(calculateAngleOppositeTheSideA()) + " градусов" +
                "Длина стороны B: " + Shape.informationFormatter.format(getLengthB()) + " мм" +
                System.lineSeparator() +
                "Угол противолежащий стороне B: " + Shape.informationFormatter.format(calculateAngleOppositeTheSideB()) + " градусов" +
                System.lineSeparator() +
                "Длина стороны C: " + Shape.informationFormatter.format(getLengthC()) + " мм" +
                System.lineSeparator() +
                "Угол противолежащий стороне C: " + Shape.informationFormatter.format(calculateAngleOppositeTheSideC()) + " градусов";
    }

    public double calculateAngleOppositeTheSideA() {
        double angle = Math.acos((lengthB * lengthB + lengthC * lengthC - lengthA * lengthA) / (2 * lengthB * lengthC));
        return angle * 180 / Math.PI;
    }

    public double calculateAngleOppositeTheSideB() {
        double angle = Math.acos((lengthA * lengthA + lengthC * lengthC - lengthB * lengthB) / (2 * lengthA * lengthC));
        return angle * 180 / Math.PI;
    }

    public double calculateAngleOppositeTheSideC() {
        double angle = Math.acos((lengthA * lengthA + lengthB * lengthB - lengthC * lengthC) / (2 * lengthA * lengthB));
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
}
