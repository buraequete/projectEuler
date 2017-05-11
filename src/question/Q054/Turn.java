package question.Q054;

public class Turn {
	Hand hand1;
	Hand hand2;

	Turn(Hand hand1, Hand hand2) {
		this.hand1 = hand1;
		this.hand2 = hand2;
	}

	public int decideTurn() {
		if(hand1.rank.value > hand2.rank.value) {
			return 1;
		} else if (hand1.rank.value < hand2.rank.value) {
			return 2;
		} else {
			switch (hand1.rank) {
				case HIGH_CARD:
					if (hand1.highCard > hand2.highCard) {
						return 1;
					} else {
						return 2;
					}
				case STRAIGHT:
				case FLUSH:
				case ROYAL_FLUSH:
				case STRAIGHT_FLUSH:
					if (hand1.highGroup > hand2.highGroup) {
						return 1;
					} else {
						return 2;
					}
				case ONE_PAIR:
				case THREE_KIND:
				case FOUR_KIND:
					if(hand1.highGroup > hand2.highGroup) {
						return 1;
					} else if (hand1.highGroup < hand2.highGroup) {
						return 2;
					} else if (hand1.highCard > hand2.highCard) {
						return 1;
					} else {
						return 2;
					}
				case TWO_PAIRS:
				case FULL_HOUSE:
					if(hand1.highGroup > hand2.highGroup) {
						return 1;
					} else if (hand1.highGroup < hand2.highGroup) {
						return 2;
					} else if (hand1.lowGroup > hand2.lowGroup) {
						return 1;
					} else if (hand1.lowGroup < hand2.lowGroup) {
						return 2;
					} else if (hand1.highCard > hand2.highCard) {
						return 1;
					} else {
						return 2;
					}
				default:
					return 0;
			}
		}
	}
}