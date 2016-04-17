import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public List<Card> populateDeck() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(i, j);
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
