package cft.focus.parser;

import cft.focus.exceptions.ParseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandLineArguments {

    private String inputFileName;
    private String outputFileName;
    private boolean outputInFile;

    public void parsingArguments(String[] args) throws ParseException {

        if (args.length == 2) {

            if (args[0].equals("-c")) {
                outputInFile = false;
            } else {
                throw new ParseException("Неверный формат параметров.");
            }

            inputFileName = args[1];

        } else if (args.length == 3) {

            if (args[0].equals("-f")) {
                outputInFile = true;
            } else {
                throw new ParseException("Неверный формат параметров.");
            }

            inputFileName = args[1];
            outputFileName = args[2];

        } else {
            throw new ParseException("Неверный формат параметров.");
        }
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public boolean isOutputInFile() {
        return outputInFile;
    }
}
