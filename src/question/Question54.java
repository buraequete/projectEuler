package question;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question54 {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    int score1 = 0, score2 = 0;
    for (Turn turn : parseFile()) {
      switch (turn.executeTurn()) {
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
  }

  static enum Suit {
    DIAMOND('D'), SPADE('S'), HEART('H'), CLUB('C');

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

  static enum Value {
    ACE('A', 1), TWO('2', 2), THREE('3', 3), FOUR('4', 4), FIVE('5', 5), SIX('6', 6), SEVEN('7',
        7), EIGHT('8', 8), NINE('9', 9), QUEEN('Q', 10), JACK('J', 11), KING('K', 12);

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

  static class Card {
    Value value;
    Suit suit;

    Card(String cardCode) {
      this.value = Value.getValueFromCode(cardCode.charAt(0));
      this.suit = Suit.getSuitFromCode(cardCode.charAt(1));
    }

    public Value getValue() {
      return value;
    }

    public Suit getSuit() {
      return suit;
    }
  }

  static class Hand extends CardControls {
    Hand(List<Card> cards) {
      this.cards =
          cards.stream().sorted((c1, c2) -> c1.getValue().value.compareTo(c2.getValue().value))
              .collect(Collectors.toList());
    }

    public void addCard(Card card) {
      this.cards.add(card);
    }
  }

  static class Turn {
    Hand hand1;
    Hand hand2;

    Turn(Hand hand1, Hand hand2) {
      this.hand1 = hand1;
      this.hand2 = hand2;
    }

    public int executeTurn() {
      return decideTurn(hand1, hand2);
    }
  }

  static List<Turn> parseFile() throws FileNotFoundException, IOException {
    List<Turn> turns = new ArrayList<>();
    InputStream in = Object.class.getResourceAsStream("/resource/p054_poker.txt");
    try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] cardGroups = line.split(" ");

        List<Card> cards1 = new ArrayList<>(), cards2 = new ArrayList<>();
        for (String card : cardGroups) {
          Card thisCard = new Card(card);
          if (cards1.size() < 5) {
            cards1.add(thisCard);
          } else {
            cards2.add(thisCard);
          }
        }

        Hand hand1 = new Hand(cards1), hand2 = new Hand(cards2);
        turns.add(new Turn(hand1, hand2));
      }
    }

    return turns;
  }

  static class CardControls {
    List<Card> cards;
    static List<Integer> tempHand;

    public boolean isFlush() {
      return cards.stream().map(c -> c.getSuit()).distinct().count() == 1;
    }

    public boolean isStraight() {
      return isHighStraight() || IntStream.range(0, cards.size() - 1)
          .map(i -> cards.get(i + 1).getValue().value - cards.get(i).getValue().value).distinct()
          .count() == 1;
    }

    public boolean isRoyalFlush() {
      return isHighStraight() && isFlush();
    }

    public boolean isStraightFlush() {
      return isStraight() && isFlush();
    }

    public boolean isHighStraight() {
      boolean isHighStraight = cards.stream().map(c -> c.getValue().code + "")
          .collect(Collectors.joining()).equals("A9QJK");
      if (isHighStraight) {
        cards.add(cards.get(0));
        cards.remove(0);
      }
      return isHighStraight;
    }

    public boolean isFourOfA_kind() {
      fillHandList();
      if (tempHand.contains(4)) {
      }
      return false;
    }

    private void fillHandList() {
      tempHand = IntStream.rangeClosed(1, 12).map(i -> 0).boxed().collect(Collectors.toList());
      cards.forEach(c -> tempHand.set(c.getValue().value, tempHand.get(c.getValue().value) + 1));
    }
  }

  static int decideTurn(Hand hand1, Hand hand2) {
    return 0;

  }
}
