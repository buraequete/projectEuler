package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.HashMap;
import java.util.Map;

public class Question75 {
	static Map<Long, Long> triangleMap = new HashMap<>();
	static int limit = (int) 1e6 + 5 * (int) 1e5;

	public static void main(String[] args) {
		TimeHelper.start();
		go();
		ResultHelper.printOut("Result is " + triangleMap.values().stream().filter(v -> v != 0l).count());
		TimeHelper.stop();
	}

	static void go() {
		long p, a, b, c, m2, n2, kp, kc;
		for (int m = 1; m < Math.sqrt(limit); m++) {
			for (int n = 1; n < m; n++) {
				m2 = m * m;
				n2 = n * n;
				a = m2 - n2;
				b = 2 * m * n;
				c = m2 + n2;
				p = a + b + c;
				if (p <= limit) {
					put(p, c);
					if (m % 2 != 1 || n % 2 != 1) {
						for (int k = 2; true; k++) {
							kc = k * c;
							kp = k * p;
							if (kp < limit) {
								put(kp, kc);
							} else {
								break;
							}
						}
					}
				}
			}
		}
	}

	static void put(long p, long c) {
		if (triangleMap.containsKey(p)) {
			long oc = triangleMap.get(p);
			if (oc != 0l && oc != c) {
				triangleMap.put(p, 0l);
			}
		} else {
			triangleMap.put(p, c);
		}
	}
}