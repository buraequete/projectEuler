package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.HashMap;
import java.util.Map;

public class Question076 {
	static int result, target = 100;
	static Map<Integer, Integer> pentMap = new HashMap<>();

	public static void main(String[] args) {
		TimeHelper.start();
		pentMap.put(0, 1);
		result = pentagonize(target) - 1;
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static int pentagonize(int n) {
		int gk = 0, sum = 0, localSum;
		if (pentMap.containsKey(n)) {
			return pentMap.get(n);
		}
		for (int k = 1; gk <= n; k++) {
			gk = miniFnc(k);
			localSum = pentagonize(n - gk) + pentagonize(n - gk - k);
			if (k % 2 == 0) {
				sum -= localSum;
			} else {
				sum += localSum;
			}
		}
		pentMap.put(n, sum);
		return sum;
	}

	static int miniFnc(int k) {
		return k * (3 * k - 1) / 2;
	}
}