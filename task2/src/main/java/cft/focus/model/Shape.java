package cft.focus.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Shape {

    public static DecimalFormat informationFormatter = new DecimalFormat("#.##",
            new DecimalFormatSymbols(Locale.ENGLISH));

    public abstract double calculatePerimeter();

    public abstract double calculateArea();

    public abstract String generateInformation();
}
