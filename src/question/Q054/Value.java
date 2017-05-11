package question.Q054;

public enum Value {
	ACE('A', 14),
	TWO('2', 2),
	THREE('3', 3),
	FOUR('4', 4),
	FIVE('5', 5),
	SIX('6', 6),
	SEVEN('7', 7),
	EIGHT('8', 8),
	NINE('9', 9),
	TEN('T', 10),
	JACK('J', 11),
	QUEEN('Q', 12),
	KING('K', 13);

	char code;
	Integer value;

	Value(char code, int value) {
		this.code = code;
		this.value = value;
	}

	public static Value getValueFromCode(char code) {
		for (Value value : values()) {
			if (value.code == code) {
				return value;
			}
		}
		return null;
	}
}