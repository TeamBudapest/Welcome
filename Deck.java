import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> deck;
    private String[] suits = {"♥", "♣", "♦", "♠"};
    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public List<Card> populateDeck() {
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Card card = new Card(ranks[i], suits[j]);
                deck.add(card);
            }
        }

        return deck;
    }

    public void removeCardFromDeck(List<Card> deck, Card card) {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).toString().equals(card.toString())) {
                deck.remove(i);
            }
        }
    }
}
