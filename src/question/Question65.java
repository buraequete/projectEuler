package question;

import helper.ResultHelper;
import helper.TimeHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Question65 {
    public static void main(String[] args) {
        TimeHelper.start();
        ResultHelper.printOut(getDigitSumOfFraction());
        TimeHelper.stop();
    }

    static int getDigitSumOfFraction() {
        List<Integer> fractions = new ArrayList<>();
        BigDecimal realFraction = getE();
        fractions.add(2);
        for (int i = 1; i <= 100; i++) {
            realFraction = getNextFraction(realFraction, fractions.get(i - 1));
            fractions.add(realFraction.intValue());
        }
        int digiSum = 0;
        String resultFraction = String.valueOf(fractions.get(100));
        for (int i = 0; i < resultFraction.length(); i++) {
            digiSum += resultFraction.charAt(i) - 48;
        }
        return digiSum;
    }

    static BigDecimal getNextFraction(BigDecimal rf, Integer f) {
        return BigDecimal.valueOf(1).divide(rf.subtract(new BigDecimal(f)), 350,
                RoundingMode.HALF_EVEN);
    }

    static BigDecimal getE() {
        BigDecimal E = BigDecimal.ZERO;
        for (int i = 0; i < 1000; i++) {
            E = E.add(BigDecimal.ONE.divide(factorial(i), 500, RoundingMode.HALF_EVEN));
        }
        return E;
    }

    static BigDecimal factorial(int n) {
        BigInteger F = BigInteger.ONE;
        if (n == 0 || n == 1) {
            return new BigDecimal(F);
        }
        for (int i = 2; i <= n; i++) {
            F = F.multiply(BigInteger.valueOf(i));
        }
        return new BigDecimal(F);
    }
}
