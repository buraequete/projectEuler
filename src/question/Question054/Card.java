package question.Question054;

public class Card {
	Value value;
	Suit suit;

	Card(String cardCode) {
		this.value = Value.getValueFromCode(cardCode.charAt(0));
		this.suit = Suit.getSuitFromCode(cardCode.charAt(1));
	}

	public Integer getValue() {
		return value.value;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		return suit.code + "" + value.code;
	}
}