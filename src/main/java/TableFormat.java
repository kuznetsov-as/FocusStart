public class TableFormat {

    public static void printMultiplicationTable(String[][] tableValues, int tableSize) {
        int maxNumInTable = tableSize * tableSize;
        int separatorLength = String.valueOf(maxNumInTable).length();
        StringBuilder stringBuilder = new StringBuilder();

        // По условию задачи первый элемент в таблице (ноль) отображаться не должен
        tableValues[0][0] = " ";

        for (String[] values : tableValues) {

            for (int j = 0; j < tableValues.length; j++) {

                for (int k = 0; k < separatorLength - values[j].length(); k++) {
                    stringBuilder.append(" ");
                }

                stringBuilder.append(values[j]);
                stringBuilder.append("|");
            }

            // Удаляем лишнюю перегородку "|".
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            stringBuilder.append("\n");

            for (int i = 0; i < tableValues.length; i++) {
                for (int k = 0; k < separatorLength; k++) {
                    stringBuilder.append("-");
                }
                stringBuilder.append("+");
            }

            // Удаляем лишний "+".
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            stringBuilder.append("\n");
        }

        // Удаляем лишний переход на новую строку.
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        System.out.println(stringBuilder.toString());
    }

}
