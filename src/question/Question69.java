package question;

import helper.ResultHelper;
import helper.TimeHelper;

public class Question69 {
	static int result = -1;
	static double max = -1;
	static int phi[];

	public static void main(String[] args) {
		TimeHelper.start();
		int input = (int) 1e6;
		phi = new int[input + 1];
		totientiate(input);
		findMaximum(input);
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static void findMaximum(int limit) {
		for (int i = 2; i <= limit; i++) {
			double localResult = (double) i / phi[i];
			if (localResult > max) {
				max = localResult;
				result = i;
			}
		}
	}

	static void totientiate(int limit) {
		int sqrt = (int) Math.sqrt(limit);
		for (int i = 3; i < limit; i += 2) {
			if (phi[i] == 0) {
				phi[i] = calculatePhi(i);
				if (i < sqrt) {
					int ex = i;
					for (int e = ex * i; e < limit && e > 0; e *= i) {
						phi[e] = phi[i] * ex;
						ex = e;
					}
				}
			}
		}

		phi[2] = 1;
		for (int i = 4, j = 2; i <= limit; i += 2, j++) {
			phi[i] = j % 2 == 0 ? 2 * phi[j] : phi[j];
		}
	}

	static int calculatePhi(int n) {
		int phi = n;
		for (int p = 3; p <= n / p + 1; p += 2) {
			if (n % p == 0) {
				phi -= phi / p;
				do {
					n /= p;
				} while (n % p == 0);
			}
		}
		return phi == n ? phi - 1 : n == 1 ? phi : phi - (phi / n);
	}
}
