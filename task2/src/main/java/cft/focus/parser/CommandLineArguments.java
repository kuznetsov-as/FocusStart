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
                throw new ParseException("При наличии только двух параметров работа программы возможна " +
                        "только если вывод результата осуществляется в консоль." +
                        System.lineSeparator() +
                        "(Отсутствует обязательный параметр \"-c\")" +
                        System.lineSeparator() +
                        "Все возможные вариации параметров можно увидеть в файле README.txt");
            }

            inputFileName = args[1];

        } else if (args.length == 3) {

            if (args[0].equals("-f")) {
                outputInFile = true;
            } else {
                throw new ParseException("При наличии трех параметров работа программы возможна " +
                        "только если вывод результата осуществляется в файл." +
                        System.lineSeparator() +
                        "(Отсутствует обязательный параметр \"-f\")" +
                        System.lineSeparator() +
                        "Все возможные вариации параметров можно увидеть в файле README.txt");
            }

            inputFileName = args[1];
            outputFileName = args[2];

        } else {
            throw new ParseException("Запуск программы невозможен в связи с несоотвествием количества исходных " +
                    "параметров ожидаемым значениям." +
                    System.lineSeparator() +
                    "Все возможные вариации параметров можно увидеть в файле README.txt");
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
