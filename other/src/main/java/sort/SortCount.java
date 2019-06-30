package sort;

public class SortCount {

    public static int maxNumber(int[] array) {
        int result = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > result) {
                result = array[i];
            }
        }
        return result;
    }

    public static int[] countedArray(int[] input) {
        int[] result = new int[SortCount.maxNumber(input)];
        for (int i = 0; i < input.length; i++) {
            result[input[i] - 1]++;
        }
        return result;
    }

    public static int[] sortCount(int[] input) {
        int[] result = new int[input.length];
        int[] counters = SortCount.countedArray(input);
        int index = 0;
        int qnt = 0;
        for (int i = 0; i < counters.length; i++) {
            qnt = counters[i];
            for (int j = 0; j < qnt; j++) {
                result[index] = i + 1;
                index++;
            }
        }
        return result;
    }
}