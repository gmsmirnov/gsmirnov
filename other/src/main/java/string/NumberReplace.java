package string;

public class NumberReplace {
    public static void main(String[] args) {
        System.out.println(NumberReplace.replaceNumbers("abc5x"));
    }

    public static String replaceNumbers(String input) {
        StringBuilder temp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        char next;
        char current;
        for (int index = 0; index < input.length() - 1; index++) {
            current = input.charAt(index);
            next = input.charAt(index + 1);
            if (NumberReplace.isNumber(current)) {
                temp.append(current);
                flag = true;
            }



                temp.append(current);
                current = input.charAt(index + 1);

            if (temp.length() > 0) {
                System.out.println(Integer.parseInt(temp.toString()));
            }
            result.append(input.charAt(index));
        }
        return result.toString();
    }

    public static boolean isNumber(char ch) {
        boolean result = false;
        if (ch == '0' || ch == '1' || ch == '2' || ch == '3'
                || ch == '4' || ch == '5' || ch == '6'
                || ch == '7' || ch == '8' || ch == '9') {
            result = true;
        }
        return result;
    }
}