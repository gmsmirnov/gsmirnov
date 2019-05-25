package string;

public class NumberReplace {
    public static String replaceNumbers(String input) {
        StringBuilder temp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        char next;
        char current;
        for (int index = 0; index < input.length() - 1; index++) {
            current = input.charAt(index);
            next = input.charAt(index + 1);
            if (NumberReplace.isNumber(current) && !NumberReplace.isNumber(next)) {
                if (flag) {
                    temp.append(current);
                    flag = false;
                    int number = Integer.parseInt(temp.toString());
                    result.append(NumberReplace.fillNChars(number, next));
                } else {
                    int number = Character.getNumericValue(current);
                    result.append(NumberReplace.fillNChars(number, next));
                }
                index++;
            } else if (NumberReplace.isNumber(current) && NumberReplace.isNumber(next)) {
                flag = true;
                temp.append(current);
            } else {
                result.append(input.charAt(index));
            }
            if (index == input.length() - 2 && !NumberReplace.isNumber(input.charAt(index + 1))) {
                result.append(input.charAt(index + 1));
            }
        }
        return result.toString();
    }

    private static boolean isNumber(char ch) {
        boolean result = false;
        if (ch == '0' || ch == '1' || ch == '2' || ch == '3'
                || ch == '4' || ch == '5' || ch == '6'
                || ch == '7' || ch == '8' || ch == '9') {
            result = true;
        }
        return result;
    }

    private static String fillNChars(int qnt, char letter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < qnt; i++) {
            result.append(letter);
        }
        return result.toString();
    }
}