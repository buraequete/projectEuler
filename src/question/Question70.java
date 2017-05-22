package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.util.Arrays;

public class Question70 extends Question69 {
	static double min = 10;

	public static void main(String[] args) {
		TimeHelper.start();
		int input = (int) 1e7;
		phi = new int[input + 1];
		totientiate(input);
		findMinimum(input);
		ResultHelper.printOut("Result is " + result + ", with fraction " + min);
		TimeHelper.stop();
	}

	static void findMinimum(int limit) {
		for (int i = 2; i <= limit; i++) {
			double localResult = (double) i / phi[i];
			if (localResult < min && isPermutable(i, phi[i])) {
				min = localResult;
				result = i;
			}
		}
	}

	static boolean isPermutable(int i, int phi) {
		char[] iStr = ("" + i).toCharArray(), phiStr = ("" + phi).toCharArray();
		Arrays.sort(iStr);
		Arrays.sort(phiStr);
		return Arrays.equals(iStr, phiStr);
	}
}
