package questions;

import helper.ResultHelper;
import helper.TimeHelper;

import java.math.BigDecimal;

public class Question57 {
    static BigDecimal denominator;
    static BigDecimal numerator;
    static int result = 0;

    public static void main(String[] args) {
        TimeHelper.start();
        for (int i = 1; i <= 1000; i++) {
            reset();
            getExpansion(i);
        }
        TimeHelper.stop();
        ResultHelper.printOut(result);
    }

    private static void getExpansion(int expNo) {
        for (int i = 1; i < expNo - 1; i++) {
            divideOneByMe();
            plusTwo();
        }

        divideOneByMe();
        plusOne();
        isNumeratorBigger();
    }

    private static void reset() {
        denominator = new BigDecimal(2);
        numerator = new BigDecimal(5);
    }

    private static void divideOneByMe() {
        BigDecimal temp = denominator;
        denominator = numerator;
        numerator = temp;
    }

    private static void plusOne() {
        numerator = numerator.add(denominator);
    }

    private static void plusTwo() {
        numerator = numerator.add(denominator).add(denominator);
    }

    private static void isNumeratorBigger() {
        if (numerator.toString().length() > denominator.toString().length()) {
            result++;
        }
    }
}
