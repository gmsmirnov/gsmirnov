package nok;

public class Nok {
    public static int countNok(int a, int b) {
        return a * b / Nod.countNod(a, b);
    }

    public static int countNok(int[] array) {
        int result = 0;
        int a = array[0];
        int b = 0;
        for (int index = 1; index < array.length; index++) {
            b = array[index];
            result = Nok.countNok(a, b);
            a = result;
        }
        return result;
    }
}