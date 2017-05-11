package question.Q054;

public enum Suit {
	DIAMOND('D'),
	SPADE('S'),
	HEART('H'),
	CLUB('C');

	char code;

	Suit(char code) {
		this.code = code;
	}

	public static Suit getSuitFromCode(char code) {
		for (Suit suit : values()) {
			if (suit.code == code) {
				return suit;
			}
		}
		return null;
	}
}