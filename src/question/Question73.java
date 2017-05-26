package question;

import helper.PrimeHelper;
import helper.ResultHelper;
import helper.TimeHelper;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question73 {
	static Set<Integer> factors = new HashSet<>();
	static final int min = 2, max = 3, step = 6;
	static int sum = 0;

	public static void main(String[] args) {
		TimeHelper.start();
		int input = 12000;
		PrimeHelper.fillPrimes(input / 2 + 1);
		go(input);
		ResultHelper.printOut("Result is " + sum);
		TimeHelper.stop();
	}

	static void go(int limit) {
		int count;
		int startPoint = 4;
		int localMin = min * startPoint, localMax = max * startPoint;
		for (int i = 4; i <= limit; i++, localMin += min, localMax += max) {
			count = localMin / step + 1;
			getFactors(i);
			for (int j = count * step; j < localMax; j += step, count++) {
				if (isReduced(count)) {
					sum++;
				}
			}
		}
	}

	static boolean isReduced(int numerator) {
		for (int f : factors) {
			if (numerator % f == 0) {
				return false;
			}
		}
		return true;
	}

	static void getFactors(int denum) {
		factors.clear();
		List<Integer> primes = PrimeHelper.getPrimes(denum / 2 + 1);
		int val = denum;
		for (int prime : primes) {
			while (val % prime == 0) {
				factors.add(prime);
				val /= prime;
			}
			if (val == 1) {
				break;
			}
		}
	}
}