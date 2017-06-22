package question;

import helper.ResultHelper;
import helper.TimeHelper;
import java.io.File;
import java.util.Scanner;

public class Question082 {
	static int result = Integer.MAX_VALUE, size = 80, border = size - 1, matrix[][];

	public static void main(String[] args) {
		TimeHelper.start();
		matrix = new Question082().parseFile();
		go();
		ResultHelper.printOut("Result is " + result);
		TimeHelper.stop();
	}

	int[][] parseFile() {
		int matrix[][] = new int[size][size], i = 0, j;
		File file = new File(getClass().getClassLoader().getResource("p082_matrix.txt").getFile());
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

	static void go() {
		for (int k = 0; k < size; k++) {
			for (int i = border; i >= 0; i--) {
				int ik = (i - k) % size;
				for (int j = border; j >= 0; j--) {
					matrix[ik][j] += Math.min(Math.min(getMatrixValue(ik, j + 1), getMatrixValue(i + 1, j)), getMatrixValue(i - 1, j));
				}
			}
			result = Math.min(result, getMinFirstColumn());
		}
	}

	static int getMatrixValue(int i, int j) {
		try {
			return matrix[i][j];
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}

	static int getMinFirstColumn() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < size; i++) {
			result = Math.min(result, matrix[i][0]);
		}
		return result;
	}
}