package cft.focus.model;

public class Rectangle implements GeneralCharacteristicsShape {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        if (length > 0 && width > 0) {
            this.length = length;
            this.width = width;
        } else {
            System.out.println("Длина и ширина прямоугольника должны быть больше нуля");
            System.exit(1);
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

    public double calculateDiagonal() {
        return Math.sqrt((length * length) + (width * width));
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }
}
