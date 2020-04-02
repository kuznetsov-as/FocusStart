package cft.focus.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Shape {

    static final DecimalFormat SHAPE_INFORMATION_FORMATTER = new DecimalFormat("#.##",
            new DecimalFormatSymbols(Locale.ENGLISH));

    static final String GENERAL_UNIT_OF_MEASUREMENT = "мм";

    public abstract double calculatePerimeter();

    public abstract double calculateArea();

    public abstract String generateDetailedInformation();

    String generateGeneralInformation() {

        return "Площадь: " + SHAPE_INFORMATION_FORMATTER.format(calculateArea()) + " кв. " +
                GENERAL_UNIT_OF_MEASUREMENT +
                System.lineSeparator() +
                "Периметр: " + SHAPE_INFORMATION_FORMATTER.format(calculatePerimeter()) + " " +
                GENERAL_UNIT_OF_MEASUREMENT;
    }
}
