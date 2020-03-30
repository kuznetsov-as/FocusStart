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

            if (shapeType == null || notSplitParameters == null) {
                throw new IOException();
            }

            String[] tempParameters = notSplitParameters.split(" ");
            parameters = new double[tempParameters.length];

            for (int i = 0; i < tempParameters.length; i++) {
                parameters[i] = Double.parseDouble(tempParameters[i]);
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
}
