package question.Question054;

public enum Rank {
	HIGH_CARD(1),
	ONE_PAIR(2),
	TWO_PAIRS(3),
	THREE_KIND(4),
	STRAIGHT(5),
	FLUSH(6),
	FULL_HOUSE(7),
	FOUR_KIND(8),
	STRAIGHT_FLUSH(9),
	ROYAL_FLUSH(10);

	int value;

	Rank(int value) {
		this.value = value;
	}
}