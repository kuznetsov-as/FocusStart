package cft.focus;

public class TableFormatter {
    private static final String SPACE = " ";
    private static final String VERTICAL_LINE = "|";
    private static final String PLUS = "+";
    private static final String MINUS = "-";

    public String formatForMultiplicationTable(int[][] tableValues, int tableSize) {

        int maxNumInTable = tableSize * tableSize;
        int separatorLength = String.valueOf(maxNumInTable).length();
        StringBuilder stringBuilder = new StringBuilder();

        for (int[] values : tableValues) {

            for (int j = 0; j < tableValues.length; j++) {

                for (int k = 0; k < separatorLength - String.valueOf(values[j]).length(); k++) {
                    stringBuilder.append(SPACE);
                }

                if (values[j] == 0) {
                    stringBuilder.append(SPACE);
                }
                else {
                    stringBuilder.append(values[j]);
                }

                stringBuilder.append(VERTICAL_LINE);
            }

            // Удаляем лишнюю перегородку "|".
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            stringBuilder.append(System.lineSeparator());

            for (int i = 0; i < tableValues.length; i++) {
                for (int k = 0; k < separatorLength; k++) {
                    stringBuilder.append(MINUS);
                }
                stringBuilder.append(PLUS);
            }

            // Удаляем лишний "+".
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
