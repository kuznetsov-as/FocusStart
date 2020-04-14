package cft.focus.parser;

import cft.focus.exceptions.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineArgumentsTest {

    private CommandLineArguments commandLineArguments = new CommandLineArguments();

    @Test
    public void getNumber_returnsNumber_If_Pass_Correct_Numbers() throws ParseException {
        String[] args = {"100000000", "10"};
        commandLineArguments.parsingArgument(args);
        assertEquals(100_000_000, commandLineArguments.getNumber());
    }

    @Test
    public void getNumberOfThreads_returnsNumberOfThreads_If_Pass_Correct_Numbers() throws ParseException {
        String[] args = {"100000000", "10"};
        commandLineArguments.parsingArgument(args);
        assertEquals(10, commandLineArguments.getNumberOfThreads());
    }

    @Test(expected = ParseException.class)
    public void parsingArgument_throwsParseException_IfPassNoDigitStrings() throws ParseException {
        String[] args = {"asd", "asd"};
        commandLineArguments.parsingArgument(args);
    }

}