package question;

import helper.ResultHelper;
import helper.TimeHelper;

public class Question078 {
	static int result, target = (int) 1e6;
	static int[] pentArr = new int[target];

	public static void main(String[] args) {
		TimeHelper.start();
		go();
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static void go() {
		pentArr[0] = 1;
		for (int i = 1; true; i++) {
			if (pentagonize(i) == 0) {
				result = i;
				break;
			}
		}
	}

	static int pentagonize(int n) {
		if (n < 0) {
			return 0;
		} else if (pentArr[n] != 0) {
			return pentArr[n];
		}
		int gk = 0, sum = 0, localSum;
		for (int k = 1; gk <= n; k++) {
			gk = miniFnc(k);
			localSum = pentagonize(n - gk) + pentagonize(n - gk - k);
			if (k % 2 == 0) {
				sum -= localSum;
			} else {
				sum += localSum;
			}
		}
		sum %= target;
		pentArr[n] = sum;
		return sum;
	}

	static int miniFnc(int k) {
		return k * (3 * k - 1) / 2;
	}
}