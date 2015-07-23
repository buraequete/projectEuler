package question;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import helper.ResultHelper;
import helper.TimeHelper;

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
    BigDecimal realFraction = bigSqrt(BigDecimal.valueOf(d), new BigDecimal(1),
        new BigDecimal(1).divide(new BigDecimal(10).pow(170)));
    fractions.add(floor(Math.sqrt(d)));
    while (true) {
      int size = fractions.size(), i = size - 1;
      int firstNum = fractions.get(0), lastNum = fractions.get(i);
      if (lastNum > firstNum && firstNum * 2 == lastNum) {
        return fractions.size() % 2 == 0;
      }
      realFraction = getNextFraction(realFraction, fractions.get(i));
      fractions.add(realFraction.intValue());
    }
  }

  static BigDecimal getNextFraction(BigDecimal rf, Integer f) {
    return BigDecimal.valueOf(1).divide(rf.subtract(new BigDecimal(f)), 350,
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
    BigDecimal xn1 = fx.divide(fpx, 230, RoundingMode.HALF_DOWN);
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
