package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question68 {
	static Set<String> resultSet = new HashSet<>();

	public static void main(String[] args) {
		TimeHelper.start();
		List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
		permutate(list).forEach(l -> doMagic(l));

		ResultHelper.printOut(resultSet.stream().max(String::compareTo).get());
		TimeHelper.stop();
	}

	static void doMagic(List<Integer> list) {
		int size = list.size() / 2;
		list.add(list.get(size));
		long distinctCount = IntStream.range(0, size)
				.map(i -> list.get(i) + list.get(i + size) + list.get(i + size + 1))
				.distinct().count();
		if (distinctCount == 1) {
			List<String> strList = IntStream.range(0, size).boxed()
					.map(i -> list.get(i) + "" + list.get(i + size) + "" + list.get(i + size + 1))
					.collect(Collectors.toList());
			String result = strList.stream().collect(Collectors.joining());
			if (result.length() == 16) {
				if (strList.stream().sorted(Comparator.comparing(s -> Integer.parseInt(s))).findFirst().get().equals(strList.get(0))) {
					resultSet.add(result);
				}
			}
		}
	}

	static List<List<Integer>> permutate(List<Integer> list) {
		if (list.size() == 0) {
			List<List<Integer>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
		}
		Integer firstElement = list.remove(0);
		List<List<Integer>> result = new ArrayList<>();
		for (List<Integer> innerPermutations : permutate(list)) {
			for (int index = 0; index <= innerPermutations.size(); index++) {
				List<Integer> temp = new ArrayList<>(innerPermutations);
				temp.add(index, firstElement);
				result.add(temp);
			}
		}
		return result;
	}
}
