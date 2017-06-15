package question.Question054;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {
	List<Card> cards;
	Rank rank = Rank.HIGH_CARD;
	Integer highCard;
	Integer highGroup;
	Integer lowGroup;

	Hand(List<Card> cards) {
		this.cards = cards.stream()
				.sorted(Comparator.comparing(Card::getValue))
				.collect(Collectors.toList());
		highCard = highGroup = this.cards.get(4).getValue();
		lowGroup = this.cards.get(3).getValue();
		isFlushOrStraight();
		isKinds();
	}

	private void isFlushOrStraight() {
		Integer firstVal = cards.get(0).getValue();
		Integer lastVal = cards.get(4).getValue();
		if (firstVal <= Value.TEN.value && lastVal - firstVal == 4) {
			rank = Rank.STRAIGHT;
		} else if (firstVal == Value.TWO.value && lastVal == Value.ACE.value && cards.get(3).getValue() == Value.FIVE.value) {
			rank = Rank.STRAIGHT;
			highGroup = Value.FIVE.value;
		}

		if (cards.stream().map(c -> c.getSuit()).distinct().count() == 1) {
			rank = rank == Rank.STRAIGHT ? firstVal == Value.TEN.value ? Rank.ROYAL_FLUSH : Rank.STRAIGHT_FLUSH : Rank.FLUSH;
		}
	}

	public void isKinds() {
		Map<Integer, Integer> valueFrq = new HashMap<>();
		cards.stream().map(Card::getValue).collect(Collectors.toSet()).stream()
				.forEach(val -> valueFrq.put(val,
						Collections.frequency(cards.stream().map(Card::getValue).collect(Collectors.toList()), val)));
		Collection<Integer> freqList = valueFrq.values();
		if (freqList.contains(4)) {
			rank = Rank.FOUR_KIND;
			valueFrq.keySet().forEach(key -> {
				switch (valueFrq.get(key)) {
					case 4:
						highGroup = key;
						break;
					case 1:
						highCard = key;
						break;
				}
			});
		} else if (freqList.contains(3) && freqList.contains(2)) {
			rank = Rank.FULL_HOUSE;
			valueFrq.keySet().forEach(key -> {
				switch (valueFrq.get(key)) {
					case 3:
						highGroup = key;
						break;
					case 2:
						lowGroup = key;
						break;
				}
			});
		} else if (freqList.contains(3)) {
			rank = Rank.THREE_KIND;
			valueFrq.keySet().forEach(key -> {
				switch (valueFrq.get(key)) {
					case 3:
						highGroup = key;
						break;
					case 1:
						highCard = key > highCard ? key : highCard;
						break;
				}
			});
		} else if (freqList.contains(2)) {
			if (Collections.frequency(freqList, 2) == 2) {
				rank = Rank.TWO_PAIRS;
				valueFrq.keySet().forEach(key -> {
					switch (valueFrq.get(key)) {
						case 2:
							if (key > highGroup) {
								lowGroup = highGroup;
							}
							highGroup = key;
							break;
						case 1:
							highCard = key;
							break;
					}
				});
			} else {
				rank = Rank.ONE_PAIR;
				valueFrq.keySet().forEach(key -> {
					switch (valueFrq.get(key)) {
						case 2:
							highGroup = key;
							break;
						case 1:
							highCard = key > highCard ? key : highCard;
							break;
					}
				});
			}
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(cards.toArray());
	}
}