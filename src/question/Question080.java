package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.math.BigDecimal;

public class Question080 {
	static int sum = 0;

	public static void main(String[] args) {
		TimeHelper.start();
		go();
		ResultHelper.printOut("Result is " + sum);
		TimeHelper.stop();
	}

	static void go() {
		for (int i = 1; i <= 100; i++) {
			if (Math.pow(Math.floor(Math.sqrt(i)), 2) != i) {
				sum += sqrtDecimalSum(i);
			}
		}
	}

	static int sqrtDecimalSum(int s) {
		BigDecimal bigTwo = new BigDecimal(2), bigS = new BigDecimal(s), temp = new BigDecimal(Math.sqrt(s));
		for (int i = 0; i < 3; i++) {
			temp = (temp.add(bigS.divide(temp, 120, BigDecimal.ROUND_HALF_UP))).divide(bigTwo);
		}
		return temp.toString().replace(".", "").chars().limit(100).map(Character::getNumericValue).sum();
	}
}