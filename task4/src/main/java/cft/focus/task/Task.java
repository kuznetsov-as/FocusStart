package cft.focus.task;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public class Task implements Runnable {

    int begin;
    int end;
    BigInteger result = new BigInteger("0");

    public Task(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public BigInteger getResult() {
        return result;
    }

    @Override
    public void run() {
        log.info("Поток который вычислял значения с " + begin + " до " + end + " начал работу.");
        for (int i = begin; i <= end; i++) {
            BigInteger bigInteger = BigInteger.valueOf((long) Math.pow(i, 2));
            result = result.add(bigInteger);
        }
        log.info("Поток который вычислял значения с " + begin + " до " + end + " завершил работу.");
    }
}
