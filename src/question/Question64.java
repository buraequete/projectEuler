package question;

import helper.ResultHelper;
import helper.TimeHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question64 {
    public static void main(String[] args) {
        TimeHelper.start();
        ResultHelper.printOut(getMaxOfMins());
        TimeHelper.stop();
    }

    static int getMaxOfMins() {
        int odds = 0;
        for (int d : IntStream.rangeClosed(2, 10000).filter(d -> !isSquare(d)).boxed()
                .collect(Collectors.toList())) {
            if (isCycleSizeOdd(d)) {
                odds++;
            }
        }
        return odds;
    }

    static boolean isCycleSizeOdd(int d) {
        List<Integer> fractions = new ArrayList<>();
        List<BigDecimal> realFractions = new ArrayList<>();
        fractions.add(floor(Math.sqrt(d)));
        realFractions.add(bigSqrt(BigDecimal.valueOf(d), new BigDecimal(1),
                new BigDecimal(1).divide(new BigDecimal(10).pow(200))));
        while (true) {
            int size = realFractions.size(), i = size - 1;
            if (fractions.get(0).compareTo(fractions.get(i) / 2) == 0) {
                return fractions.size() % 2 == 0;
            }
            BigDecimal realFraction = getNextFraction(realFractions.get(i), fractions.get(i));
            Integer fraction = realFraction.intValue();
            realFractions.add(realFraction);
            fractions.add(fraction);
        }
    }

    static BigDecimal getNextFraction(BigDecimal rf, Integer f) {
        return BigDecimal.valueOf(1).divide(rf.subtract(new BigDecimal(f)), 500,
                RoundingMode.HALF_EVEN);
    }

    static boolean isSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return n == sqrt * sqrt;
    }

    static Integer floor(double n) {
        return (int) Math.floor(n);
    }

    private static BigDecimal bigSqrt(BigDecimal c, BigDecimal xn, BigDecimal precision) {
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx, 250, RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());

        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1) {
            return xn1;
        }
        return bigSqrt(c, xn1, precision);
    }
}
