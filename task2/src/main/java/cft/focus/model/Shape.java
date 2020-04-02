package cft.focus.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Shape {

    final static DecimalFormat shapeInformationFormatter = new DecimalFormat("#.##",
            new DecimalFormatSymbols(Locale.ENGLISH));

    final static String generalUnitOfMeasurement = "мм";

    public abstract double calculatePerimeter();

    public abstract double calculateArea();

    public abstract String generateDetailedInformation();

    String generateGeneralInformation() {

        return "Площадь: " + shapeInformationFormatter.format(calculateArea()) + " кв. " + generalUnitOfMeasurement +
                System.lineSeparator() +
                "Периметр: " + shapeInformationFormatter.format(calculatePerimeter()) + " " + generalUnitOfMeasurement;
    }
}
