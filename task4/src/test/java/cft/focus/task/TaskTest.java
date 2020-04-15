package cft.focus.task;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void getResult_returnsResult_IfPass1and10_000_000() {
        Task task = new Task(1, 10_000_000);
        task.run();
        assertEquals(new  BigInteger("333333383333335000000"), task.getResult());
    }
}