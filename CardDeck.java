import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
	public ArrayList<Card> deck;
	public ArrayList<Card> removedCards;

	public static void main(String[] args) {
		/* Just some basic tests to see if I have broken anything */
		System.out.println("Generating Deck Test");
		/* Generating Deck Test */ 
		CardDeck testDeck = new CardDeck();
		int count = 0;
		while (count < 13) {
			Card next = testDeck.nextCard();
			if (count == 0 || count >= 10) {
				System.out.println(next.suit + " "+ next.face);
			} else {
				System.out.println(next.suit + " " + next.value);
			}
			count = count + 1;
		}

		System.out.println(" ");
		System.out.println("Shuffling Test");

		/* Shuffling Test */
		count = 0;
		CardDeck testDeck2 = new CardDeck();
		testDeck2.shuffle();
		while (count < 13) {
			Card next = testDeck2.nextCard();
			if (!(next.face == null)) {
				System.out.println(next.suit + " "+ next.face);
			} else {
				System.out.println(next.suit + " " + next.value);
			}
			count = count + 1;
		}	

	}

	public CardDeck() {
		this.deck = new ArrayList<Card>();
		this.removedCards = new ArrayList<Card>();
		this.makeDeck();
	}

	/* Instantiates a Deck for our Deck object */
	public void makeDeck() {

		String[] cardSuits = {"Spade", "Heart", "Diamond", "Clubs"};

		for (String suit : cardSuits) {

			Card ace = new Card(suit, "Ace");
			deck.add(ace);

			int value = 2; 
			while (value <= 10) {
				Card toAppend = new Card(suit, value);
				deck.add(toAppend);
				value = value + 1;
			}

			Card jack = new Card(suit, "Jack");
			Card queen = new Card(suit, "Queen");
			Card king = new Card(suit, "King");

			deck.add(jack);
			deck.add(queen);
			deck.add(king);
		}

	}

	/* Randonmizes the Cards within the Deck object. Adds all Cards back into the Deck. */
	public void shuffle() {
		for (Card c : removedCards) {
			deck.add(c);
		}
		removedCards.clear();
		Collections.shuffle(deck);
	}

	/* Returns the next card within our Deck object. */
	public Card nextCard() {
		if (deck.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException("No more cards within the deck!");
		} else {
			Card next = deck.remove(0);
			removedCards.add(next);
			return next;
		}
	}

	/* Nested class which will represent a single card */
	public class Card {
		String suit;
		String face;
		int value;

		public Card(String suit, String face) {
			this.face = face;
			this.suit = suit;
		}

		public Card(String suit, int value) {
			this.suit = suit;
			this.value = value;
		}
	}
}