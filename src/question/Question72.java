package question;

import helper.ResultHelper;
import helper.TimeHelper;

public class Question72 extends Question69 {
	public static void main(String[] args) {
		TimeHelper.start();
		int input = (int) 1e6;
		phi = new int[input + 1];
		totientiate(input);
		long result = go();
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	static long go() {
		long sum = 0;
		for(int i : phi) {
			sum += i;
		}
		return sum;
	}
}