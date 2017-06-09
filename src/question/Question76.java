package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.HashMap;
import java.util.Map;

public class Question76 {
	static int result, target = 100;
	static Map<Integer, Integer> pentMap = new HashMap<>();

	public static void main(String[] args) {
		TimeHelper.start();
		result = pentagonize(target) - 1;
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static int pentagonize(int n) {
		int gk, gkk, sum = 0, localSum;
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			if (pentMap.containsKey(n)) {
				return pentMap.get(n);
			}
			for (int k = 1; true; k++) {
				gk = miniFnc(k);
				if (gk > n) {
					break;
				}
				gkk = miniFnc(-1 * k);
				localSum = pentagonize(n - gk) + pentagonize(n - gkk);
				if (k % 2 == 0) {
					localSum *= -1;
				}
				sum += localSum;
			}
			pentMap.put(n, sum);
			return sum;
		}
	}

	static int miniFnc(int k) {
		return k * (3 * k - 1) / 2;
	}
}