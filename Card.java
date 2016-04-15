public class Card {

    private static String[] ranks =
            { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
    private static String[] suits = { "clubs", "spades", "diamonds", "hearts" };
    private short rank;
    private short suit;

    public Card (short rank, short suit){
        this.rank = rank;
        this.suit = suit;
    }

    public short getRank(){
        return rank;
    }
    public short getSuit(){
        return suit;
    }

    public String toString(){
        return ranks[rank] + " " + suits[suit];
    }
}
