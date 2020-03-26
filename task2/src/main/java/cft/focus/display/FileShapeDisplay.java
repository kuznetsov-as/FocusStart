package cft.focus.display;

import cft.focus.model.Shape;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class FileShapeDisplay implements ShapeDisplay {

    private String outputFileName;

    public FileShapeDisplay(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    @Override
    public void print(Shape shape) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(outputFileName))) {
            printWriter.println(shape.generateDetailedInformation());
        } catch (IOException exception) {
            log.error(exception.getMessage(), exception);
            System.err.println("Невозможно произвести запись результата в файл");
            exception.printStackTrace();
            System.exit(1);
        }
    }
}
