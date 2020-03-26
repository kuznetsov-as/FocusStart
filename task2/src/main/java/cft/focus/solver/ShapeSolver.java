package cft.focus.solver;

import cft.focus.display.ConsoleShapeDisplay;
import cft.focus.display.FileShapeDisplay;
import cft.focus.display.ShapeDisplay;
import cft.focus.exceptions.ModelException;
import cft.focus.exceptions.ParseException;
import cft.focus.io.InputFileReader;
import cft.focus.model.Shape;
import cft.focus.model.ShapeCreator;
import cft.focus.parser.CommandLineArguments;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShapeSolver {

    public static void main(String[] args) {

        CommandLineArguments commandLineArguments = new CommandLineArguments();

        try {
            commandLineArguments.parsingArguments(args);
        } catch (ParseException exception) {
            log.error(exception.getMessage(), exception);
            exception.printStackTrace();
            System.exit(1);
        }

        new ShapeSolver().solve(commandLineArguments.getInputFileName(),
                commandLineArguments.getOutputFileName(),
                commandLineArguments.isOutputInFile());
    }

    public void solve(String inputFileName, String outputFileName, boolean isOutputInFile) {

        InputFileReader inputFileReader = new InputFileReader();
        inputFileReader.readFile(inputFileName);

        ShapeCreator shapeCreator = new ShapeCreator();
        Shape resultShape = null;
        try {
            resultShape = shapeCreator.createShape(inputFileReader.getShapeType(), inputFileReader.getParameters());
        } catch (ModelException exception) {
            log.error(exception.getMessage(), exception);
            exception.printStackTrace();
            System.exit(1);
        }

        ShapeDisplay shapeDisplay;
        if (isOutputInFile) {
            shapeDisplay = new FileShapeDisplay(outputFileName);
        } else {
            shapeDisplay = new ConsoleShapeDisplay();
        }

        shapeDisplay.print(resultShape);
    }
}
