package cft.focus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public static void main(String[] args) {

        System.out.println("Пожалуйста, введите размер таблицы:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                int tableSize = Integer.parseInt(reader.readLine());
                if (tableSize > 32) {
                    System.out.println("Введенное число не должно быть больше 32");
                } else if (tableSize < 1) {
                    System.out.println("Введенное число не должно быть меньше 1");
                } else {
                    TableFormatter tableFormatter = new TableFormatter();
                    MultiplicationTable multiplicationTable = new MultiplicationTable();
                    TableDisplay tableDisplay = new TableDisplay();

                    String result = tableFormatter.formatForMultiplicationTable(multiplicationTable.calculateMultiplication(tableSize), tableSize);
                    tableDisplay.printTable(result);
                    break;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Неверный формат входных данных. Пожалуйста, введите целое число от 1 до 32");
            }
        }
    }
}
