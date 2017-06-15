package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question061 {
	static Map<Integer, List<String>> polygonalityMap = new HashMap<>();

	public static void main(String[] args) {
		TimeHelper.start();
		int result = -1;
		fillLists();
		for (String i3 : polygonalityMap.get(3)) {
			for (String i4 : polygonalityMap.get(4)) {
				for (String i5 : polygonalityMap.get(5)) {
					for (String i6 : polygonalityMap.get(6)) {
						if (checkCircularity(Arrays.asList(i3, i4, i5, i6), 1)) {
							for (String i7 : polygonalityMap.get(7)) {
								if (checkCircularity(Arrays.asList(i3, i4, i5, i6, i7), 2)) {
									for (String i8 : polygonalityMap.get(8)) {
										List<String> temp = Arrays.asList(i3, i4, i5, i6, i7, i8);
										if (checkCircularity(temp, 6)) {
											result = temp.stream().map(Integer::parseInt).mapToInt(i -> i.intValue()).sum();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static boolean checkCircularity(List<String> strings, int level) {
		int sum = 0;
		for (int i = 0; i < strings.size(); i++) {
			String current = strings.get(i);
			String first = current.substring(0, 2);
			String last = current.substring(2, 4);
			if (strings.stream().filter(s -> s.matches(last + "\\d{2}") && !s.equals(current)).count() > 0) {
				if (strings.stream().filter(s -> s.matches("\\d{2}" + first) && !s.equals(current)).count() > 0) {
					if (first == last || strings.stream().filter(s -> s.matches(last + first)).count() == 0) {
						sum++;
					}
				}
			}
		}
		return sum >= level ? true : false;
	}

	static void fillLists() {
		for (int i = 15; i < 200; i++) {
			insertIntoMap(3, i * (i + 1) / 2);
			insertIntoMap(4, i * i);
			insertIntoMap(5, i * (3 * i - 1) / 2);
			insertIntoMap(6, i * (2 * i - 1));
			insertIntoMap(7, i * (5 * i - 3) / 2);
			insertIntoMap(8, i * (3 * i - 2));
		}
	}

	static void insertIntoMap(int poly, int val) {
		String valStr = val + "";
		if (valStr.matches("\\d{2}[^0]\\d") && !valStr.matches("\\d{2}00")) {
			if (!polygonalityMap.containsKey(poly)) {
				polygonalityMap.put(poly, new ArrayList<>());
			}
			polygonalityMap.get(poly).add(valStr);
		}
	}
}
