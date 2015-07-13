package question;

import helper.ResultHelper;
import helper.TimeHelper;

import java.math.BigDecimal;

public class Question56 {
    public static void main(String[] args) {
        TimeHelper.start();
        int max = 0;
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                int now = getDigitSum(new BigDecimal(i).pow(j));
                if (now > max) {
                    max = now;
                }
            }
        }
        TimeHelper.stop();
        ResultHelper.printOut(max);
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
