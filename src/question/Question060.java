package question;

import helper.PrimeHelper;
import helper.ResultHelper;
import helper.TimeHelper;
import java.util.Arrays;
import java.util.List;

public class Question060 {
	static int size = 10000;

	public static void main(String[] args) {
		TimeHelper.start();
		PrimeHelper.fillPrimes(size * size);
		TimeHelper.stop();
		TimeHelper.start();
		ResultHelper.printOut(mainLoop());
		TimeHelper.stop();
	}

	private static int mainLoop() {
		int min = Integer.MAX_VALUE;
		for (int i = 2; i < size; i++) {
			if (!PrimeHelper.isPrime(i)) {
				continue;
			}
			for (int j = 2; j < size; j++) {
				if (isNotValid(Arrays.asList(i, j))) {
					continue;
				}
				for (int k = 2; k < size; k++) {
					if (isNotValid(Arrays.asList(i, j, k))) {
						continue;
					}
					for (int l = 2; l < size; l++) {
						if (isNotValid(Arrays.asList(i, j, k, l))) {
							continue;
						}
						for (int m = 2; m < size; m++) {
							if (isNotValid(Arrays.asList(i, j, k, l, m))) {
								continue;
							}
							int sum = i + j + k + l + m;
							if (sum < min) {
								return sum;
							}
						}
					}
				}
			}
		}
		return min;
	}

	private static boolean isNotValid(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int list_i = list.get(i);
			if (!PrimeHelper.isPrime(list_i)) {
				return true;
			}
			for (int j = 0; j < list.size(); j++) {
				int list_j = list.get(j);
				if (i == j) {
					continue;
				} else if (list_i == list_j) {
					return true;
				}
				if (!isPrimePairSet(list_i, list_j)) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean isPrimePairSet(int a, int b) {
		if (PrimeHelper.isPrime(joinPrimes(a, b)) && PrimeHelper.isPrime(joinPrimes(b, a))) {
			return true;
		}
		return false;
	}

	private static int joinPrimes(int a, int b) {
		int temp_b = b;
		while (temp_b > 0) {
			temp_b /= 10;
			a *= 10;
		}
		return a + b;
	}
}
