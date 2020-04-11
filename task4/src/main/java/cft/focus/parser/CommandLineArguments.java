package cft.focus.parser;

import cft.focus.exceptions.ParseException;

public class CommandLineArguments {

    int number;
    int numberOfThreads;

    public void parsingArgument(String[] args) throws NumberFormatException, ParseException {

        if (args.length != 2) {
            throw new ParseException("Запуск программы невозможен в связи с несоотвествием количества исходных " +
                    "параметров ожидаемым значениям.");
        }

        number = Integer.parseInt(args[0]);
        numberOfThreads = Integer.parseInt(args[1]);

        if (numberOfThreads > number) {
            throw new ParseException("Количество потоков обрабатывающих значения " +
                    "не может превышать количество самих значений");

        }

        if (numberOfThreads < 1) {
            throw new ParseException("Количество потоков обрабатывающих значения " +
                    "не может быть меньше 1");
        }

        if (number < 100) {
            throw new ParseException("Входящее число не может быть меньше 100");
        }
    }

    public int getNumber() {
        return number;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }
}
