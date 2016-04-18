import java.util.List;

public class Round {
    private int bet;
    private int multiplier;

    public Round(int bet) {
        this.bet = bet;
    }

    public int calculateEarnings() {
        return this.bet * this.multiplier;
    }

    public int determinePrize(List<Card> hand, Prize p) {
        if (p.isRoyalFlush(hand)) {
            this.multiplier = 500;
            System.out.println("Royal Flush");
        } else if (p.isStreetFlush(hand)) {
            this.multiplier = 100;
            System.out.println("Street Flush");
        } else if (p.isThreeOfAKindOrIsPoker(hand).equals("Poker")) {
            this.multiplier = 40;
            System.out.println("Poker");
        } else if (p.isFullHouse(hand)) {
            this.multiplier = 10;
            System.out.println("Full House");
        } else if (p.isStreet(hand)) {
            this.multiplier = 5;
            System.out.println("Street");
        } else if (p.isThreeOfAKindOrIsPoker(hand).equals("Three of a kind")) {
            this.multiplier = 3;
            System.out.println("Three of a kind");
        } else if (p.calculatePairs(hand) == 2) {
            this.multiplier = 2;
            System.out.println("Two Pairs");
        } else if (p.calculatePairs(hand) == 1) {
            this.multiplier = 1;
            System.out.println("One Pair");
        } else {
            this.multiplier = 0;
            System.out.println("Nothing");
        }

        int prize = this.calculateEarnings();

        return prize;
    }
}
