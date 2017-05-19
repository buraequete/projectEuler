package question;

import helper.ResultHelper;
import helper.TimeHelper;

public class Question69 {
	static int input = (int) 1e6;
	static int result = -1;
	static double max = -1;
	static int phi[] = new int[input + 1];

	public static void main(String[] args) {
		TimeHelper.start();
		totientiate(input);
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static void totientiate(int target) {
		int sqrt = (int) Math.sqrt(target);
		for (int i = 3; i < target; i += 2) {
			if (phi[i] == 0) {
				phi[i] = calculatePhi(i);
				if (i < sqrt) {
					int ex = i;
					for (int e = ex * i; e < target && e > 0; e *= i) {
						phi[e] = phi[i] * ex;
						ex = e;
					}
				}
			}
		}

		phi[2] = 1;
		for (int i = 4, j = 2; i <= target; i += 2, j++) {
			phi[i] = j % 2 == 0 ? 2 * phi[j] : phi[j];
		}

		for (int i = 2; i <= target; i++) {
			double localResult = (double) i / phi[i];
			if (localResult > max) {
				max = localResult;
				result = i;
			}
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
