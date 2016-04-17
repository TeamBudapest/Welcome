import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hand {
    private List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public List<Card> populateHand(Deck d, List<Card> deck) {
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) {
            Card card = deck.get(rnd.nextInt(deck.size()));
            hand.add(card);
            d.removeCardFromDeck(deck, card);
        }

        return hand;
    }

    public void swapCardInHand(List<Card> hand, Deck d, int[] indexes) {
        Random rnd = new Random();

        for (int i = 0; i < indexes.length; i++) {
            Card card = d.getDeck().get(rnd.nextInt(d.getDeck().size()));
            hand.remove(indexes[i]);
            hand.add(indexes[i], card);
            d.removeCardFromDeck(d.getDeck(), card);
        }
    }
}
