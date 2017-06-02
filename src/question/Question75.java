package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Question75 {
	static Map<Long, Set<String>> triangleMap = new HashMap<>();
	static Set<String> trioSet;
	static int limit = (int) 1e6 + 5 * (int) 1e5;
	static long sum;

	public static void main(String[] args) {
		TimeHelper.start();
		go();
		sum = triangleMap.keySet().stream().filter(k -> triangleMap.get(k).size() == 1).count();
		ResultHelper.printOut("Result is " + sum);
		TimeHelper.stop();
	}

	static void go() {
		long p, a, b, c, m2, n2, kp, ka, kb, kc;
		for (int m = 1; m < Math.sqrt(limit); m++) {
			for (int n = 1; n < m; n++) {
				m2 = m * m;
				n2 = n * n;
				a = m2 - n2;
				b = 2 * m * n;
				c = m2 + n2;
				p = a + b + c;
				if (m % 2 != 1 || n % 2 != 1) {
					for (int k = 2; true; k++) {
						ka = k * a;
						kb = k * b;
						kc = k * c;
						kp = k * p;
						if (kp < limit) {
							put(kp, ka, kb, kc);
						} else {
							break;
						}
					}
				}
				if (p > limit) {
					continue;
				}
				put(p, a, b, c);
			}
		}
	}

	static void put(long p, long a, long b, long c) {
		long t;
		if (a > b) {
			t = a;
			a = b;
			b = t;
		}
		trioSet = triangleMap.containsKey(p) ? triangleMap.get(p) : new HashSet<>();
		trioSet.add(a + "," + b + "," + c);
		triangleMap.put(p, trioSet);
	}
}