import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hand {
    private List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public void populateHand(Deck d, List<Card> deck) {
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) {
            Card card = d.getDeck().get(rnd.nextInt(deck.size()));
            
            hand.add(card);
            d.removeCardFromDeck(deck, card);
        }
    }

    public void swapCards(List<Card> hand, Deck d, int index) {
        Random rnd = new Random();
        Card c = d.getDeck().get(rnd.nextInt(d.getDeck().size()));

        hand.remove(index);
        
        hand.add(index, c);
        d.removeCardFromDeck(d.getDeck(), c);
    }
}
