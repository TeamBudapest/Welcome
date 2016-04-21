package sample;

public class Card implements Comparable<Card>{
    private static String[] ranks =
            { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
    private static String[] suits = { "clubs", "spades", "diamonds", "hearts" };
    private int rank;
    private int suit;

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }
    public int getSuit() {
        return suit;
    }

    public String toString() {
        return ranks[rank] + " " + suits[suit];
    }

    @Override
    public int compareTo(Card otherCard) {
        return ranks[this.rank].compareTo(ranks[otherCard.getRank()]);
    }
}
