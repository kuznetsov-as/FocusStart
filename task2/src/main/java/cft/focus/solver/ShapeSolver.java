package cft.focus.solver;

import cft.focus.display.FileShapeDisplay;
import cft.focus.display.ShapeDisplay;
import cft.focus.model.ShapeType;
import cft.focus.display.ConsoleShapeDisplay;
import cft.focus.model.Circle;
import cft.focus.model.Rectangle;
import cft.focus.model.Triangle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShapeSolver {
    public static void main(String[] args) {

        String fileName = "./task2/test.txt";
        ShapeDisplay shapeDisplay;

        if (args[0].equals("-c")) {
            shapeDisplay = new ConsoleShapeDisplay();
        } else {
            shapeDisplay = new FileShapeDisplay();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String shapeType = reader.readLine();
            String[] parameters = reader.readLine().split(" ");

            if (shapeType.equals(ShapeType.CIRCLE.name()) && parameters.length == 1) {
                shapeDisplay.print(new Circle(Double.parseDouble(parameters[0])));
            } else if (shapeType.equals(ShapeType.RECTANGLE.name()) && parameters.length == 2) {
                shapeDisplay.print(new Rectangle(
                        Double.parseDouble(parameters[0]),
                        Double.parseDouble(parameters[1])));
            } else if (shapeType.equals(ShapeType.TRIANGLE.name()) && parameters.length == 3) {
                shapeDisplay.print(new Triangle(
                        Double.parseDouble(parameters[0]),
                        Double.parseDouble(parameters[1]),
                        Double.parseDouble(parameters[2])));
            } else {
                System.out.println("Такой фигуры не существует");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла не существует");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Невозможно прочитать данные из файла");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат параметров");
            System.exit(1);
        }
    }
}
