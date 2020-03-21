package cft.focus.display;

import cft.focus.model.ShapeType;
import cft.focus.model.Triangle;
import cft.focus.model.Circle;
import cft.focus.model.Rectangle;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FileShapeDisplay implements ShapeDisplay {

    private DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
    private DecimalFormat df = new DecimalFormat("#.##", symbols);

    @Override
    public void print(Circle circle) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("./task2/result.txt"))) {
            printWriter.println("Тип фигуры: " + ShapeType.CIRCLE.getShapeType());
            printWriter.println("Площадь: " + df.format(circle.calculateArea()) + " кв. мм");
            printWriter.println("Периметр: " + df.format(circle.calculatePerimeter()) + " мм");
            printWriter.println("Радиус: " + df.format(circle.getRadius()) + " мм");
            printWriter.println("Диаметр: " + df.format(circle.calculateDiameter()) + " мм");
        } catch (IOException e) {
            System.out.println("Невозможно произвести запись результата в файл");
            System.exit(1);
        }
    }

    @Override
    public void print(Rectangle rectangle) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("./task2/result.txt"))) {
            printWriter.println("Тип фигуры: " + ShapeType.RECTANGLE.getShapeType());
            printWriter.println("Площадь: " + df.format(rectangle.calculateArea()) + " кв. мм");
            printWriter.println("Периметр: " + df.format(rectangle.calculatePerimeter()) + " мм");
            printWriter.println("Длина диагонали: " + df.format(rectangle.calculateDiagonal()) + " мм");
            printWriter.println("Длина: " + df.format(rectangle.getLength()) + " мм");
            printWriter.println("Ширина: " + df.format(rectangle.getWidth()) + " мм");
        } catch (IOException e) {
            System.out.println("Невозможно произвести запись результата в файл");
            System.exit(1);
        }
    }

    @Override
    public void print(Triangle triangle) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("./task2/result.txt"))) {
            printWriter.println("Тип фигуры: " + ShapeType.TRIANGLE.getShapeType());
            printWriter.println("Площадь: " + df.format(triangle.calculateArea()) + " кв. мм");
            printWriter.println("Периметр: " + df.format(triangle.calculatePerimeter()) + " мм");
            printWriter.println("Длина стороны A: " + df.format(triangle.getLengthA()) + " мм");
            printWriter.println("Угол противолежащий стороне A: " + df.format(triangle.calculateAngleOppositeTheSideA()) + " градусов");
            printWriter.println("Длина стороны B: " + df.format(triangle.getLengthB()) + " мм");
            printWriter.println("Угол противолежащий стороне B: " + df.format(triangle.calculateAngleOppositeTheSideB()) + " градусов");
            printWriter.println("Длина стороны C: " + df.format(triangle.getLengthC()) + " мм");
            printWriter.println("Угол противолежащий стороне C: " + df.format(triangle.calculateAngleOppositeTheSideC()) + " градусов");
        } catch (IOException e) {
            System.out.println("Невозможно произвести запись результата в файл");
            System.exit(1);
        }
    }
}
