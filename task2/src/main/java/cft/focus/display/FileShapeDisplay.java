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
            log.info("Произведена запись результата в файл " + outputFileName);
        } catch (IOException exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}
