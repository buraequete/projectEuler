package question;

import helper.PrimeHelper;
import helper.ResultHelper;
import helper.TimeHelper;
import java.util.stream.IntStream;

public class Question69 {
	static double max = -1;
	static int result = -1;

	// After finding that for n < 100, max n/φ(n) was n = 30, and for n < 1000, it was 210.
	// From there I was able to deduce that the values were a series of multiplication of prime numbers,
	// thus the result was 2*3*5*7*11*13*17
	// (!) This program is highly inefficient
	public static void main(String[] args) {
		TimeHelper.start();
		iterate(1000);
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static void iterate(int limit) {
		PrimeHelper.fillPrimes(limit);
		IntStream.rangeClosed(3, limit).boxed()
				.forEach(n -> {
					double tmp = (double) n / phi(n);
					if (tmp > max) {
						max = tmp;
						result = n;
					}
				});
	}

	static long phi(int n) {
		return IntStream.range(2, n).boxed().filter(m -> PrimeHelper.isCoprime(m, n)).count() + 1;
	}
}
