package cft.focus.solver;

import cft.focus.exceptions.ModelException;
import cft.focus.exceptions.ParseException;
import cft.focus.io.ConsoleShapeDisplay;
import cft.focus.io.FileShapeDisplay;
import cft.focus.io.InputFileReader;
import cft.focus.io.ShapeDisplay;
import cft.focus.model.Shape;
import cft.focus.model.ShapeCreator;
import cft.focus.parser.CommandLineArguments;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ShapeSolver {

    public static void main(String[] args) {
        log.info("Программа запущена");

        CommandLineArguments commandLineArguments = new CommandLineArguments();

        try {
            commandLineArguments.parsingArguments(args);
            new ShapeSolver().solve(commandLineArguments.getInputFileName(),
                    commandLineArguments.getOutputFileName(),
                    commandLineArguments.isOutputInFile());

            log.info("Выполнение программы завершено успешно");

        } catch (ParseException | ModelException exception) {
            System.err.println(exception.getMessage());
            log.error(exception.getMessage(), exception);
            log.info("Выполнение программы завершено неудачно");
        } catch (NumberFormatException | IOException exception) {
            System.err.println(exception.getMessage());
            log.info("Выполнение программы завершено неудачно");
        }
    }

    private void solve(String inputFileName, String outputFileName, boolean isOutputInFile) throws ModelException, IOException {

        InputFileReader inputFileReader = new InputFileReader();
        inputFileReader.readFile(inputFileName);

        ShapeCreator shapeCreator = new ShapeCreator();
        Shape resultShape = shapeCreator.createShape(inputFileReader.getShapeType(), inputFileReader.getParameters());

        ShapeDisplay shapeDisplay = isOutputInFile
                ? new FileShapeDisplay(outputFileName)
                : new ConsoleShapeDisplay();

        shapeDisplay.print(resultShape);
    }
}
