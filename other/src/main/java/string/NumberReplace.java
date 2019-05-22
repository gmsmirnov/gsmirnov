package string;

public class NumberReplace {
    public static String replaceNumbers(String input) {
        StringBuilder temp = new StringBuilder();
        String result = "";
        for (int index = 0; index < input.length(); index++) {
            if (input.charAt(index) == '0' || input.charAt(index) == '1' || input.charAt(index) == '2' || input.charAt(index) == '3' ||
                    input.charAt(index) == '4' || input.charAt(index) == '5' || input.charAt(index) == '6' ||
                    input.charAt(index) == '7' || input.charAt(index) == '8' || input.charAt(index) == '9') {
                temp.append(input.charAt(index));
            }
        }
        return result;
    }
}