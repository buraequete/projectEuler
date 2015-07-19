package question;

import helper.ResultHelper;
import helper.TimeHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question66 {

    public static void main(String[] args) {
        TimeHelper.start();
        ResultHelper.printOut(getMaxOfMins());
        TimeHelper.stop();
    }

    static int getMaxOfMins() {
        BigInteger max = BigInteger.ZERO;
        int d_max = 0;
        for (int d : IntStream.rangeClosed(2, 1000).filter(d -> !isSquare(d)).boxed().collect(Collectors.toList())) {
            BigInteger localResult = getLocalMin(d);
            if (localResult.compareTo(max) == 1) {
                max = localResult;
                d_max = d;
            }
        }

        return d_max;
    }

    static BigInteger getLocalMin(int d) {
        List<BigInteger> fractions = new ArrayList<>(), secondaryFractionList;
        List<BigDecimal> realFractions = new ArrayList<>();
        fractions.add(floor(Math.sqrt(d)));
        realFractions.add(bigSqrt(BigDecimal.valueOf(d)));
        while (true) {
            int size = realFractions.size(), i = size - 1;
            if (size > 1 && size % 2 == 1 && isCycled(realFractions, size - 1)) {
                int topLimit = size / 2;
                if (size == 3) {
                    topLimit += 1;
                }
                secondaryFractionList = new ArrayList<>(fractions.subList(0, size - 1));
                fractions = fractions.subList(0, topLimit);
                break;
            }
            BigDecimal realFraction = getNextFraction(realFractions.get(i), fractions.get(i));
            BigInteger fraction = realFraction.toBigInteger();
            realFractions.add(realFraction);
            fractions.add(fraction);
        }

        Fraction f = getFinalFraction(fractions);
        BigInteger x2 = f.getNumerator().multiply(f.getNumerator());
        BigInteger y2 = f.getDenumerator().multiply(f.getDenumerator());
        BigInteger dy2 = BigInteger.valueOf(d).multiply(y2);
        if (x2.subtract(dy2).equals(BigInteger.ONE)) {
            return f.getNumerator();
        } else {
            return getFinalFraction(secondaryFractionList).getNumerator();
        }
    }

    static Fraction getFinalFraction(List<BigInteger> fractions) {
        Fraction firstRatio = new Fraction(fractions.get(0), BigInteger.ONE);
        Collections.reverse(fractions);
        Fraction lastRatio = new Fraction(fractions.get(0), BigInteger.ONE);
        Fraction resultRatio = divideOne(lastRatio);
        for (BigInteger f : fractions.subList(1, fractions.size() - 1)) {
            Fraction thisRatio = new Fraction(f, BigInteger.ONE);
            resultRatio = divideOne(add(resultRatio, thisRatio));
        }
        return add(resultRatio, firstRatio);
    }

    static boolean isCycled(List<BigDecimal> fractions, int size) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(300);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);

        List<String> reducedFractions = fractions.stream().map(f -> {
            f = f.setScale(5, BigDecimal.ROUND_DOWN);
            return df.format(f);
        }).collect(Collectors.toList());
        List<String> firstHalf = reducedFractions.subList(1, size / 2 + 1);
        List<String> secondHalf = reducedFractions.subList(size / 2 + 1, size + 1);
        return firstHalf.equals(secondHalf);
    }

    static BigDecimal getNextFraction(BigDecimal rf, BigInteger f) {
        return BigDecimal.valueOf(1).divide(rf.subtract(new BigDecimal(f)), 200, RoundingMode.HALF_EVEN);
    }

    static boolean isSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return n == sqrt * sqrt;
    }

    static BigInteger floor(double n) {
        return BigInteger.valueOf((int) Math.floor(n));
    }

    static Fraction add(Fraction a, Fraction b) {
        Fraction result;
        BigInteger a_d = a.getDenumerator(), b_d = b.getDenumerator();
        BigInteger a_n = a.getNumerator(), b_n = b.getNumerator();
        if (a_d == b_d) {
            result = new Fraction(a_n.add(b_n), a_d);
        } else {
            BigInteger new_d = a_d.multiply(b_d);
            BigInteger new_n = b_n.multiply(a_d).add(a_n.multiply(b_d));
            result = new Fraction(new_n, new_d);
        }
        return reduce(result);
    }

    static Fraction divideOne(Fraction f) {
        BigInteger temp = f.getDenumerator();
        f.setDenumerator(f.getNumerator());
        f.setNumerator(temp);
        return f;
    }

    static Fraction reduce(Fraction f) {
        BigInteger gcd = BigInteger.ZERO, f_d = f.getDenumerator(), f_n = f.getNumerator();
        do {
            gcd = getGCD(f_d, f_n);
            f_d = f_d.divide(gcd);
            f_n = f_n.divide(gcd);
        } while (!gcd.equals(BigInteger.ONE));

        return new Fraction(f_n, f_d);
    }

    static BigInteger getGCD(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    static class Fraction {
        BigInteger numerator;
        BigInteger denumerator;

        public BigInteger getNumerator() {
            return numerator;
        }

        public void setNumerator(BigInteger numerator) {
            this.numerator = numerator;
        }

        public BigInteger getDenumerator() {
            return denumerator;
        }

        public void setDenumerator(BigInteger denumerator) {
            this.denumerator = denumerator;
        }

        public Fraction(BigInteger numerator, BigInteger denumerator) {
            super();
            this.numerator = numerator;
            this.denumerator = denumerator;
        }
    }

    private static final BigDecimal SQRT_DIG = new BigDecimal(150);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

    private static BigDecimal sqrtNewtonRaphson(BigDecimal c, BigDecimal xn, BigDecimal precision) {
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx, 2 * SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());

        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1) {
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }

    public static BigDecimal bigSqrt(BigDecimal c) {
        return sqrtNewtonRaphson(c, new BigDecimal(1), new BigDecimal(1).divide(SQRT_PRE));
    }

}
