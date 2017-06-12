package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Question79 {
	static int[] passArr = {129, 160, 162, 168, 180, 289, 290, 316, 318, 319, 362, 368, 380, 389, 620, 629, 680, 689, 690, 710, 716, 718, 719, 720, 728, 729, 731, 736, 760, 762, 769, 790, 890};
	static Map<Integer, Set<Integer>> beforeSet = new HashMap<>();

	public static void main(String[] args) {
		TimeHelper.start();
		go();
		ResultHelper.printOut("Result is " + beforeSet.entrySet().stream()
				.sorted(Comparator.comparingInt(e -> e.getValue().size()))
				.map(e -> e.getKey() + "")
				.collect(Collectors.joining()));
		TimeHelper.stop();
	}

	static void go() {
		Set<Integer> beforeM, beforeL, beforeF;
		int f, m, l;
		for (int i : passArr) {
			f = i / 100;
			m = (i % 100) / 10;
			l = i % 10;
			beforeM = beforeSet.containsKey(m) ? beforeSet.get(m) : new HashSet<>();
			beforeL = beforeSet.containsKey(l) ? beforeSet.get(l) : new HashSet<>();
			beforeF = beforeSet.containsKey(f) ? beforeSet.get(f) : new HashSet<>();
			beforeM.add(f);
			beforeL.add(m);
			beforeL.add(f);
			beforeSet.put(m, beforeM);
			beforeSet.put(l, beforeL);
			beforeSet.put(f, beforeF);
		}
	}
}