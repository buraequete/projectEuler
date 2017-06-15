package question;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import helper.ResultHelper;
import helper.TimeHelper;

public class Question065 {
  public static void main(String[] args) {
    TimeHelper.start();
    ResultHelper.printOut(getDigitSumOfFraction());
    TimeHelper.stop();
  }

  static int getDigitSumOfFraction() {
    List<BigInteger> fractions = new ArrayList<>();
    BigDecimal realFraction = getE();
    fractions.add(BigInteger.valueOf(2));
    for (int i = 1; i < 100; i++) {
      realFraction = getNextFraction(realFraction, fractions.get(i - 1));
      fractions.add(realFraction.toBigInteger());
    }
    int digiSum = 0;
    String resultFraction = String.valueOf(getFinalFraction(fractions).getNumerator());
    for (int i = 0; i < resultFraction.length(); i++) {
      digiSum += resultFraction.charAt(i) - 48;
    }
    return digiSum;
  }

  static BigDecimal getNextFraction(BigDecimal rf, BigInteger f) {
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
      gcd = f_d.gcd(f_n);
      f_d = f_d.divide(gcd);
      f_n = f_n.divide(gcd);
    } while (!gcd.equals(BigInteger.ONE));

    return new Fraction(f_n, f_d);
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
}
