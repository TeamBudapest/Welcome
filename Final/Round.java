package sample;

import java.util.List;

public class Round {
    private int bet;
    private int multiplier;
    private String prizeAsText;

    public Round(int bet) {
        this.bet = bet;
    }

    public int calculateEarnings() {
        return this.bet * this.multiplier;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getBet() {
        return this.bet;
    }

    public String getPrice() {
        return this.prizeAsText;
    }

    //check the combination of cards in the hand and determine the prize, starting from the biggest one
    public int determinePrize(List<Card> hand, Prize p) {
        if (p.isRoyalFlush(hand)) {
            this.multiplier = 500;
            prizeAsText = "Royal Flush";
        } else if (p.isStreetFlush(hand)) {
            this.multiplier = 100;
            prizeAsText = "Street Flush";
        } else if (p.isThreeOfAKindOrIsPoker(hand).equals("Poker")) {
            this.multiplier = 40;
            prizeAsText = "Poker";
        } else if (p.isFullHouse(hand)) {
            this.multiplier = 10;
            prizeAsText = "Full House";
        } else if (p.isStreet(hand)) {
            this.multiplier = 5;
            prizeAsText = "Street";
        } else if (p.isThreeOfAKindOrIsPoker(hand).equals("Three of a kind")) {
            this.multiplier = 3;
            prizeAsText = "Three of a kind";
        } else if (p.calculatePairs(hand) == 2) {
            this.multiplier = 2;
            prizeAsText = "Two Pairs";
        } else if (p.calculatePairs(hand) == 1) {
            this.multiplier = 1;
            prizeAsText = "One Pair";
        } else {
            this.multiplier = 0;
            prizeAsText = "Nothing";
        }

        //calculate prize
        int prize = this.calculateEarnings();

        return prize;
    }
}
