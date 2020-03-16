package cft.focus;

public class MultiplicationTable {

    /**
     * Метод рассчитывает и возвращает двумерный массив со всеми табличными значениями.
     *
     * @param tableSize размер таблицы
     * @return двумерный массив с таблицей умножения
     */

    public int[][] calculateMultiplication(int tableSize) {

        int[][] tableValues = new int[tableSize + 1][tableSize + 1];

        // Формируем вертикальную ось таблицы
        for (int i = 0; i < tableValues.length; i++) {
            tableValues[i][0] = i;
        }

        // Формируем горизонтальную ось таблицы
        for (int j = 0; j < tableValues[0].length; j++) {
            tableValues[0][j] = j;
        }

        // Заполняем таблицу значениями
        for (int i = 1; i < tableValues.length; i++) {
            for (int j = 1; j < tableValues[i].length; j++) {
                tableValues[i][j] = i * j;
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
