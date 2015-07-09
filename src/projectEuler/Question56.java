package projectEuler;

import java.math.BigDecimal;

public class Question56 {
    public static void main(String[] args) {
        int max = 0;

        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                int now = getDigitSum(Bigpower(i, j));
                if (now > max) {
                    max = now;
                }
            }
        }

        System.out.println(max);
    }

    private static BigDecimal Bigpower(int x, int y) {
        BigDecimal sum = BigDecimal.ONE, a = new BigDecimal(x), b = new BigDecimal(y);
        for (BigDecimal i = BigDecimal.ZERO; i.compareTo(b) == -1; i = i.add(BigDecimal.ONE)) {
            sum = sum.multiply(a);
        }

        return sum;
    }

    private static int getDigitSum(BigDecimal num) {
        String numStr = num.toString();
        int sum = 0;
        while (numStr.length() > 0) {
            sum += numStr.charAt(numStr.length() - 1) - 48;
            numStr = numStr.substring(0, numStr.length() - 1);
        }
        return sum;
    }
}
