package cft.focus;

public class MultiplicationTable {

    /*
    *   Метод рассчитывает и возвращает двумерный массив со всеми табличными значениями.
    *   Тип данных String выбран в связи с удобством дальнейшего форматирования.
    * */

    public static String[][] calculateMultiplication(int tableSize) {

        String[][] tableValues = new String[tableSize + 1][tableSize + 1];

        // Формируем вертикальную ось таблицы
        for (int i = 0; i < tableValues.length; i++) {
            for (int j = 0; j < 1; j++) {
                tableValues[i][j] = String.valueOf(i);
            }
        }

        // Формируем горизонтальную ось таблицы
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < tableValues[i].length; j++) {
                tableValues[i][j] = String.valueOf(j);
            }
        }

        // Заполняем таблицу значениями
        for (int i = 1; i < tableValues.length; i++) {
            for (int j = 1; j < tableValues[i].length; j++) {
                tableValues[i][j] = String.valueOf(i * j);
            }
        }

        // Для отладки
        /*for (String[] tableValue : tableValues) {
            for (String s : tableValue) {
                System.out.print(s + "\t");
            }
            System.out.println();
        }*/

        return tableValues;
    }
}
