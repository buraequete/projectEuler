package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.math.BigDecimal;

public class Question071 {
	static BigDecimal leftNumerator = new BigDecimal(2);
	static BigDecimal leftDenumerator = new BigDecimal(5);
	static final BigDecimal rightNumerator = new BigDecimal(3);
	static final BigDecimal rightDenumerator = new BigDecimal(7);

	public static void main(String[] args) {
		TimeHelper.start();
		go(1000000);
		ResultHelper.printOut("Result is " + leftNumerator + "/" + leftDenumerator);
		TimeHelper.stop();
	}

	static void go(int n) {
		BigDecimal denumerator, min, max, localMin, localMax, bigI, bigTarget;
		for (int i = 8; i <= n; i++) {
			bigI = new BigDecimal(i);
			denumerator = rightDenumerator.multiply(leftDenumerator);
			min = leftNumerator.multiply(rightDenumerator).multiply(bigI);
			max = rightNumerator.multiply(leftDenumerator).multiply(bigI);
			localMin = denumerator;
			localMax = denumerator.multiply(bigI.subtract(BigDecimal.ONE));
			if (localMax.compareTo(min) == 1 && localMin.compareTo(max) == -1) {
				int target = (int) (i * (double) 3 / 7);
				bigTarget = new BigDecimal(target).multiply(denumerator);
				if (bigTarget.compareTo(min) == 1 && bigTarget.compareTo(max) == -1) {
					leftNumerator = new BigDecimal(target);
					leftDenumerator = bigI;
				}
			}
		}
	}
}