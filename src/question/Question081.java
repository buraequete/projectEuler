package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Question081 {
	static int sum = 0, size = 80, border = size - 1, matrix[][];
	static double avg[][] = new double[2][size * 2];

	public static void main(String[] args) {
		TimeHelper.start();
		Arrays.fill(avg[0], Double.MAX_VALUE);
		matrix = new Question081().parseFile();
		sum = go(0, 0, 0);
		ResultHelper.printOut("Result is " + sum);
		TimeHelper.stop();
	}

	int[][] parseFile() {
		int matrix[][] = new int[size][size], i = 0, j;
		File file = new File(getClass().getClassLoader().getResource("p081_matrix.txt").getFile());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				j = 0;
				for (String val : line.split(",")) {
					matrix[i][j++] = Integer.parseInt(val);
				}
				i++;
			}
			scanner.close();
		} catch (Exception e) {
			//
		}
		return matrix;
	}

	static int go(int i, int j, int sum) {
		if (i == size || j == size) {
			return Integer.MAX_VALUE;
		}
		sum += matrix[i][j];
		int ij = i + j;
		if (sum > avg[0][ij] + ((size - ij) * 500)) {
			return Integer.MAX_VALUE;
		}
		avg[1][ij]++;
		avg[0][ij] = (double) ((avg[0][ij] + sum) / avg[1][ij]);
		if (i == border && j == border) {
			return sum;
		} else {
			int down = go(i + 1, j, sum), right = go(i, j + 1, sum);
			return down < right ? down : right;
		}
	}
}