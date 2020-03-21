package cft.focus.display;

import cft.focus.model.Circle;
import cft.focus.model.Rectangle;
import cft.focus.model.ShapeType;
import cft.focus.model.Triangle;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ConsoleShapeDisplay implements ShapeDisplay {

    private DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
    private DecimalFormat df = new DecimalFormat("#.##", symbols);

    @Override
    public void print(Circle circle) {
        System.out.println("Тип фигуры: " + ShapeType.CIRCLE.getShapeType());
        System.out.println("Площадь: " + df.format(circle.calculateArea()) + " кв. мм");
        System.out.println("Периметр: " + df.format(circle.calculatePerimeter()) + " мм");
        System.out.println("Радиус: " + df.format(circle.getRadius()) + " мм");
        System.out.println("Диаметр: " + df.format(circle.calculateDiameter()) + " мм");
    }

    @Override
    public void print(Rectangle rectangle) {
        System.out.println("Тип фигуры: " + ShapeType.RECTANGLE.getShapeType());
        System.out.println("Площадь: " + df.format(rectangle.calculateArea()) + " кв. мм");
        System.out.println("Периметр: " + df.format(rectangle.calculatePerimeter()) + " мм");
        System.out.println("Длина диагонали: " + df.format(rectangle.calculateDiagonal()) + " мм");
        System.out.println("Длина: " + df.format(rectangle.getLength()) + " мм");
        System.out.println("Ширина: " + df.format(rectangle.getWidth()) + " мм");
    }

    @Override
    public void print(Triangle triangle) {
        System.out.println("Тип фигуры: " + ShapeType.TRIANGLE.getShapeType());
        System.out.println("Площадь: " + df.format(triangle.calculateArea()) + " кв. мм");
        System.out.println("Периметр: " + df.format(triangle.calculatePerimeter()) + " мм");
        System.out.println("Длина стороны A: " + df.format(triangle.getLengthA()) + " мм");
        System.out.println("Угол противолежащий стороне A: " + df.format(triangle.calculateAngleOppositeTheSideA()) + " градусов");
        System.out.println("Длина стороны B: " + df.format(triangle.getLengthB()) + " мм");
        System.out.println("Угол противолежащий стороне B: " + df.format(triangle.calculateAngleOppositeTheSideB()) + " градусов");
        System.out.println("Длина стороны C: " + df.format(triangle.getLengthC()) + " мм");
        System.out.println("Угол противолежащий стороне C: " + df.format(triangle.calculateAngleOppositeTheSideC()) + " градусов");
    }
}
