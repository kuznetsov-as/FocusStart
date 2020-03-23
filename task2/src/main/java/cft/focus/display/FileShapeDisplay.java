package cft.focus.display;

import cft.focus.model.Shape;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileShapeDisplay implements ShapeDisplay {

    @Override
    public void print(Shape shape) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("./task2/result.txt"))) {
            printWriter.println(shape.generateInformation());
        } catch (IOException e) {
            System.out.println("Невозможно произвести запись результата в файл");
            System.exit(1);
        }
    }
}
