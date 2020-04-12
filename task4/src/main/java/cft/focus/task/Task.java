package cft.focus.task;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public class Task implements Runnable {

    int firstNumberInTheRange;
    int lastNumberInTheRange;
    BigInteger result = new BigInteger("0");

    public Task(int firstNumberInTheRange, int lastNumberInTheRange) {
        this.firstNumberInTheRange = firstNumberInTheRange;
        this.lastNumberInTheRange = lastNumberInTheRange;
    }

    public BigInteger getResult() {
        return result;
    }

    @Override
    public void run() {
        log.info("Поток который вычислял значения с " + firstNumberInTheRange + " до " + lastNumberInTheRange + " начал работу.");
        for (int i = firstNumberInTheRange; i <= lastNumberInTheRange; i++) {
            result = result.add(BigInteger.valueOf((long) Math.pow(i, 2)));
        }
        log.info("Поток который вычислял значения с " + firstNumberInTheRange + " до " + lastNumberInTheRange + " завершил работу.");
    }
}
