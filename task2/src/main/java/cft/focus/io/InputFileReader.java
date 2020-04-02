package cft.focus.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class InputFileReader {

    private String shapeType;
    private double[] parameters;

    public void readFile(String inputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            shapeType = reader.readLine();
            String notSplitParameters = reader.readLine();

            if (shapeType == null) {
                throw new IOException("Во входном файле отсутствует строка с названием фигуры");
            }
            if (notSplitParameters == null) {
                throw new IOException("Во входном файле отсутствует строка с параметрами фигуры");
            }

            String[] tempParameters = notSplitParameters.split(" ");
            parameters = new double[tempParameters.length];

            for (int i = 0; i < tempParameters.length; i++) {
                parameters[i] = parseParameter(tempParameters[i]);
            }

        } catch (FileNotFoundException exception) {
            log.error("Не удается найти указанный файл " + exception.getMessage(), exception);
            throw exception;
        } catch (IOException | NumberFormatException exception) {
            log.error("В файле неверно заданы параметры фигуры ", exception);
            throw exception;
        }
    }

    public String getShapeType() {
        return shapeType;
    }

    public double[] getParameters() {
        return parameters;
    }

    private double parseParameter(String stringParameter) {
        try {
            return Double.parseDouble(stringParameter);
        } catch (NumberFormatException e) {
            log.error("Не удалось получить значение параметра '{}'", stringParameter, e);
            throw e;
        }
    }
}
