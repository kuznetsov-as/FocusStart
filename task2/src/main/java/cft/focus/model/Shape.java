package cft.focus.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Shape {

    public abstract double calculatePerimeter();

    public abstract double calculateArea();

    public abstract String generateDetailedInformation();

    public String generateGeneralInformation() {

        return "Площадь: " + shapeInformationFormatter.format(calculateArea()) + " кв. " + generalUnitOfMeasurement +
                System.lineSeparator() +
                "Периметр: " + shapeInformationFormatter.format(calculatePerimeter()) + " " + generalUnitOfMeasurement;
    }

    static DecimalFormat shapeInformationFormatter = new DecimalFormat("#.##",
            new DecimalFormatSymbols(Locale.ENGLISH));

    static String generalUnitOfMeasurement = "мм";
}
