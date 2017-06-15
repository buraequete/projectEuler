package question;

import helper.PrimeHelper;
import helper.ResultHelper;
import helper.TimeHelper;

public class Question077 {
	static int result, target = 100;

	public static void main(String[] args) {
		TimeHelper.start();
		PrimeHelper.fillPrimes(target);
		go();
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static void go() {
		int[] ways = new int[target + 1];
		ways[0] = 1;

		for (int i : PrimeHelper.getPrimes(target)) {
			for (int j = i; j <= target; j++) {
				ways[j] += ways[j - i];
			}
		}
		for (int i = 1; i <= target; i++) {
			if (ways[i] > 5000) {
				result = i;
				return;
			}
		}
	}
}