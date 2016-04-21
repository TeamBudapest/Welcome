package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hand {
    private List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    //populate the cards in the hand
    public List<Card> populateHand(Deck d, List<Card> deck) {
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) {
            Card card = deck.get(rnd.nextInt(deck.size()));
            hand.add(card);
            d.removeCardFromDeck(deck, card);
        }

        return hand;
    }

    //return the current cards in the hand
    public List<Card> getHand() {
        return this.hand;
    }

    //remove the card that you don't want to hold and replace it with a new one
    public void swapCardInHand(List<Card> hand, Deck d, int[] indexes) {
        Random rnd = new Random();

        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] > 0) {
                //generate a random card from the deck
                Card card = d.getDeck().get(rnd.nextInt(d.getDeck().size()));
                hand.remove(indexes[i] - 1);
                hand.add(indexes[i] - 1, card);
                d.removeCardFromDeck(d.getDeck(), card);
            }
        }
    }
}
