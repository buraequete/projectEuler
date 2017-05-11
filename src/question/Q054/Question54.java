package question.Q054;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Question54 {
	public static void main(String[] args) {
		int score1 = 0, score2 = 0;
		for (Turn turn : new Question54().parseFile()) {
			int result = turn.decideTurn();
			System.out.println(turn.hand1 + ", " + turn.hand2 + " -> " + result);
			switch (result) {
				case 1:
					score1++;
					break;
				case 2:
					score2++;
					break;
				default:
					break;
			}
		}
		System.out.println("P1: " + score1 + ", P2: " + score2);
	}

	List<Turn> parseFile() {
		List<Turn> turns = new ArrayList<>();
		File file = new File(getClass().getClassLoader().getResource("p054_poker.txt").getFile());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				List<Card> cardLine = Arrays.asList(line.split(" ")).stream()
						.map(Card::new).collect(Collectors.toList());

				Hand hand1 = new Hand(cardLine.subList(0, 5));
				Hand hand2 = new Hand(cardLine.subList(5, 10));
				turns.add(new Turn(hand1, hand2));
			}
			scanner.close();
		} catch (Exception e) {
			//
		}

		return turns;
	}
}
