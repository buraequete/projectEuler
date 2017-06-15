package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.HashMap;

public class Question074 {
	static HashMap<Integer, Integer> factorialSumCache, chainCache, tempCache;
	static int limit = (int) 1e6, sum = 0;
	static int[] factorials;

	public static void main(String[] args) {
		TimeHelper.start();
		fillFactorials();
		addChainCache();
		go();
		ResultHelper.printOut("Result is " + sum);
		TimeHelper.stop();
	}

	static void go() {
		int temp, count;
		for (int i = 1; i < limit; i++) {
			if (chainCache.containsKey(i)) {
				continue;
			}
			count = 1;
			temp = i;
			tempCache.clear();
			tempCache.put(temp, 0);
			while (count < 60) {
				temp = getDigitFactorialSum(temp);
				if (chainCache.containsKey(temp)) {
					count += chainCache.get(temp);
					break;
				} else if (tempCache.containsKey(temp)) {
					break;
				}
				tempCache.put(temp, -1 * count);
				count++;
			}
			if (count == 60) {
				sum++;
			}
			for (int key : tempCache.keySet()) {
				chainCache.put(key, tempCache.get(key) + count);
			}
		}
	}

	static void addChainCache() {
		factorialSumCache = new HashMap<>();
		chainCache = new HashMap<>();
		tempCache = new HashMap<>();
		chainCache.put(1, 1);
		chainCache.put(2, 1);
		chainCache.put(145, 1);
		chainCache.put(40585, 1);
		chainCache.put(871, 2);
		chainCache.put(45361, 2);
		chainCache.put(872, 2);
		chainCache.put(45362, 2);
		chainCache.put(169, 3);
		chainCache.put(363601, 3);
		chainCache.put(1454, 3);
	}

	static int getDigitFactorialSum(int n) {
		if (factorialSumCache.containsKey(n)) {
			return factorialSumCache.get(n);
		} else {
			int sum = 0;
			for (char ch : (n + "").toCharArray()) {
				sum += factorials[Character.getNumericValue(ch)];
			}
			return sum;
		}
	}

	static void fillFactorials() {
		factorials = new int[10];
		factorials[0] = 1;
		for (int i = 1; i < 10; i++) {
			factorials[i] = factorials[i - 1] * i;
		}
	}
}