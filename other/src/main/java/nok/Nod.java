package nok;

public class Nod {
    public static int countNod(int a, int b) {
        int result = 0;
        int ost = -1;
        while (ost != 0) {
            ost = a % b;
            a = b;
            b = ost;
            result = a;
        }
        return result;
    }
}