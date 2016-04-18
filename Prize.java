package javaPoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Prize {
    public int calculatePairs(List<Card> hand) {
        int numberOfPairs = 0;
        Collections.sort(hand);

        //calculate the number of pairs
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                numberOfPairs++;
            }
        }

        return numberOfPairs;
    }

    public boolean isRoyalFlush(List<Card> hand) {
        int suit = hand.get(0).getSuit();
        boolean isRankFound = false;

        //ranks needed for royal flush
        List<Integer> neededRanks = new ArrayList<>();
        neededRanks.add(9);
        neededRanks.add(10);
        neededRanks.add(11);
        neededRanks.add(12);
        neededRanks.add(0);

        //check suits
        for (int i = 1; i < hand.size(); i++) {
             if (!(suit == hand.get(i).getSuit())) {
                 return false;
             }
        }

        //check ranks
        for (Card card : hand) {
            for (int neededRank : neededRanks) {
                if (card.getRank() == neededRank) {
                    isRankFound = true;
                    break;
                }
            }

            if(isRankFound) {
                neededRanks.remove(card.toString());
            } else {
                return false;
            }

            isRankFound = false;
        }

        return true;
    }

    public String isThreeOfAKindOrIsPoker (List<Card> hand) {
        List<Card> currentHand = new ArrayList<>(hand);
        Collections.sort(currentHand);

        int numberOfEquals = 1;
        int biggestNumberOfEquals = 0;

        for (int i = 0; i < currentHand.size() - 1; i++) {
            if (currentHand.get(i).getRank() == currentHand.get(i + 1).getRank()) {
                numberOfEquals++;
                if (numberOfEquals >= biggestNumberOfEquals) {
                    biggestNumberOfEquals = numberOfEquals;
                }
            } else {
                numberOfEquals = 1;
            }
        }

        if (biggestNumberOfEquals == 3) {
            return "Three of a kind";
        }

        if (biggestNumberOfEquals == 4) {
            return "Poker";
        }

        return "None of them";
    }

    public boolean isStreetFlush (List<Card> hand){
        Collections.sort(hand);
        int suit = hand.get(0).getSuit();
        String rank = "";
        boolean isRankFound = false;
        int minIndex = Integer.MAX_VALUE;
        int currentIndex = Integer.MIN_VALUE;

        //check suits
        for (int i = 1; i < hand.size(); i++) {
            if (!(suit == hand.get(i).getSuit())) {
                return false;
            }
        }

        //all the possible ranks
        List<Integer> possibleRanks = new ArrayList<>();
        possibleRanks.add(1);
        possibleRanks.add(2);
        possibleRanks.add(3);
        possibleRanks.add(4);
        possibleRanks.add(5);
        possibleRanks.add(6);
        possibleRanks.add(7);
        possibleRanks.add(8);
        possibleRanks.add(9);
        possibleRanks.add(10);
        possibleRanks.add(11);
        possibleRanks.add(12);

        //get the index of the smallest card
        for (Card card : hand) {
            currentIndex = possibleRanks.indexOf(card.getRank());

            if (currentIndex == -1) {
                return false;
            }

            if (minIndex > currentIndex) {
                minIndex = currentIndex;
            }
        }

        //if index > 7 means that street flush cannot be formed
        if (minIndex > 7) {
            return false;
        }

        //get only the ranks needed from possible ranks
        List<Integer> neededRanks = possibleRanks.subList(minIndex, minIndex + 5);

        //check if rank in hand is available in neededRanks
        for (int i = 0; i < hand.size(); i++) {
             if (neededRanks.contains(hand.get(i).getRank())) {
                 neededRanks.remove(hand.get(i));
             } else {
                 return false;
             }
        }

        return true;
    }

    public boolean isStreet (List<Card> hand) {
        List<Integer> currentHandRanks = new ArrayList<>();
        List<Integer> possibleRanks = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0);
        int startIndex = Integer.MAX_VALUE;
        int currentIndex = Integer.MIN_VALUE;

        for (Card card : hand) {
            currentHandRanks.add(card.getRank());
        }

        //determine the start index
        for (Integer rank : currentHandRanks) {
            currentIndex = possibleRanks.indexOf(rank);

            if (startIndex > currentIndex) {
                startIndex = currentIndex;
            }
        }

        //means no rank less than "J"
        if (startIndex > 8) {
            return false;
        }

        //check if rank in hand is available in possible ranks
        for (int i = 0; i < currentHandRanks.size(); i++) {
             if (!currentHandRanks.contains(possibleRanks.get(startIndex + i))) {
                 return false;
             }
        }

        return true;
    }

    public boolean isFullHouse (List<Card> hand) {
        List<Integer> currentHandRanks = new ArrayList<>();
        String threeOfAKind = isThreeOfAKindOrIsPoker(hand);

        for (Card card : hand) {
            currentHandRanks.add(card.getRank());
        }

        Collections.sort(currentHandRanks);

        //if it is not three of a kind, full house is not possible
        if (!threeOfAKind.equals("Three of a kind")) {
            return false;
        }

        //check the three equal cards are sorted on the first three indexes or on the second three indexes
        //and after that checks if the other two cards are with the same rank
        if (currentHandRanks.get(0).equals(currentHandRanks.get(2))) {
            if (!currentHandRanks.get(3).equals(currentHandRanks.get(4))) {
                return false;
            }
        } else {
            if (!currentHandRanks.get(0).equals(currentHandRanks.get(1))) {
                return false;
            }
        }

        return true;
    }
}
