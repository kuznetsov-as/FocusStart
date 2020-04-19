package cft.focus;

import cft.focus.exceptions.ParseException;
import cft.focus.parser.CommandLineArguments;
import cft.focus.task.Task;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MultithreadedComputing {
    public static void main(String[] args) {
        try {
            log.info("Программа запущена");

            CommandLineArguments commandLineArguments = new CommandLineArguments();
            commandLineArguments.parseArguments(args);

            //Засекаем время
            long time = System.nanoTime();

            new MultithreadedComputing().solve(commandLineArguments);
            log.info("Выполнение программы завершено успешно");

            time = System.nanoTime() - time;
            log.info("Время, затраченное на работу программы: " +
                    TimeUnit.NANOSECONDS.toMillis(time) + " мс.");

        } catch (ParseException | InterruptedException e) {
            System.err.println(e.getMessage());
            log.error(e.getMessage(), e);
            log.info("Выполнение программы завершено неудачно");
        }
    }

    private void solve(CommandLineArguments commandLineArguments) throws InterruptedException {
        Thread[] threads = new Thread[commandLineArguments.getNumberOfThreads()];
        Task[] tasks = new Task[threads.length];

        int firstNumberInTheRange = 1;
        int lastNumberInTheRange = commandLineArguments.getNumber() / commandLineArguments.getNumberOfThreads();
        int gap = commandLineArguments.getNumber() / commandLineArguments.getNumberOfThreads();
        //Разбиваем на кусочки
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(firstNumberInTheRange, lastNumberInTheRange);
            firstNumberInTheRange = lastNumberInTheRange + 1;
            //Когда доходим до предпоследнего элемента (task.length - 2) устанавливаем
            //lastNumberInTheRange в максимальное значение, чтобы присвоить его последнему элементу
            //независимо от размера предыдущих интервалов
            if (i == tasks.length - 2) {
                lastNumberInTheRange = commandLineArguments.getNumber();
            } else {
                lastNumberInTheRange = firstNumberInTheRange + gap;
            }
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        BigInteger result = BigInteger.ZERO;
        for (Task task : tasks) {
            result = result.add(task.getResult());
        }
        System.out.println(result.toString());
    }
}
