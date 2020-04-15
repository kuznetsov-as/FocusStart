package cft.focus.parser;

import cft.focus.exceptions.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineArgumentsTest {

    private CommandLineArguments commandLineArguments = new CommandLineArguments();

    @Test
    public void getNumber_returnsNumber_ifCorrectNumbersWerePassed() throws ParseException {
        String[] args = {"100000000", "10"};
        commandLineArguments.parseArguments(args);
        assertEquals(100_000_000, commandLineArguments.getNumber());
    }

    @Test
    public void getNumberOfThreads_returnsNumberOfThreads_ifCorrectNumbersWerePassed() throws ParseException {
        String[] args = {"100000000", "10"};
        commandLineArguments.parseArguments(args);
        assertEquals(10, commandLineArguments.getNumberOfThreads());
    }

    @Test(expected = ParseException.class)
    public void parsingArgument_throwsParseException_IfPassNoDigitStrings() throws ParseException {
        String[] args = {"asd", "asd"};
        commandLineArguments.parseArguments(args);
    }

}