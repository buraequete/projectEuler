package question;

import helper.PrimeHelper;
import helper.ResultHelper;
import helper.TimeHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Question058 {
	static List<Integer> primeDiagonals = new ArrayList<>();
	static List<Integer> diagonals = new ArrayList<>();

	public static void main(String[] args) {
		TimeHelper.start();
		// Due to this part, takes ~40 seconds to get the results.
		// Also had to use -Xmx4096m for VM not to have OutOfMemoryException
		PrimeHelper.fillPrimes(700000000);
		int result;
		int lastValue = 1;
		diagonals.add(1);

		for (int i = 3; ; i += 2) {
			List<Integer> newDiagonals = new ArrayList<>();
			int increment = i - 1;
			for (int j = 0; j < 4; j++) {
				lastValue += increment;
				newDiagonals.add(lastValue);
			}
			if (getPercentage(newDiagonals) < 10) {
				result = i;
				break;
			}
		}
		TimeHelper.stop();
		ResultHelper.printOut(result);
	}

	private static double getPercentage(List<Integer> newDiagonals) {
		diagonals.addAll(newDiagonals);
		primeDiagonals.addAll(newDiagonals.stream().filter(d -> PrimeHelper.isPrime(d)).collect(Collectors.toList()));

		double denumerator = diagonals.size();
		double numerator = primeDiagonals.size();
		double percentage = numerator * 100 / denumerator;
		return percentage;
	}
}
