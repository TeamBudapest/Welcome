/**
 * Created by asIo on 17.4.2016 г.
 *
 * Създава се обект (инстанция) от класа Game.
 * -
 * -   Game game;
 * -
 *  За да се вземе залог се изиква
 *  -
 *  - game.getWinning().getBet()
 *  -
 *  За да се вземе текста на печалбата се извиква
 *  -
 *  - game.getWinning().toString();
 *  -
 *  За да се вземе ръката като обект от типа class Hand се извиква
 *  -
 *  - game.getHand();
 *  -
 *  При натискане на клавиш се извиква някое от следните събития:
 *  OnBetUp, OnBetDown, OnHold1, OnHold2, OnHold3, OnHold4, OnHold5, OnDeal.
 *
 *  1. OnBetUp - При натискане на клавиша за вдигане на залога. Залога се вдига през 5 - 1, 5, 10, 15 ... , 100.
 *  -
 *  - game.onEventName("OnBetUp");
 *  -
 *  2. OnBetDown - При натискане на клавиша за сваляне на залога. Залога се сваля през 5 - 100, 95, ... , 10, 5, 1.
 *  -
 *  - game.onEventName("OnBetDown");
 *  -
 *  3. OnHold1 - При натискане на клавиша за задържане на първа карта.
 *  -
 *  - game.onEventName("OnHold1");
 *  -
 *  4. OnHold2 - При натискане на клавиша за задържане на втора карта.
 *  -
 *  - game.onEventName("OnHold2");
 *  -
 *  5. OnHold3 - При натискане на клавиша за задържане на трета карта.
 *  -
 *  - game.onEventName("OnHold3");
 *  -
 *  6. OnHold4 - При натискане на клавиша за задържане на четвърта карта.
 *  -
 *  - game.onEventName("OnHold4");
 *  -
 *  7. OnHold5 - При натискане на клавиша за задържане на пета карта.
 *  -
 *  - game.onEventName("OnHold5");
 *  -
 *  8. Deal - При натискане на клавиша за ново раздаване.
 *  -
 *  - game.onEventName("Deal");
 *  -
 * .
 */
import java.util.ArrayList;

public class Game {
    public enum events {
        hold1,
        hold2,
        hold3,
        hold4,
        hold5,
        deal,
        betUp,
        betDown
    }

    private int round = 0;
    private boolean isHold1Pressed = false;
    private boolean isHold2Pressed = false;
    private boolean isHold3Pressed = false;
    private boolean isHold4Pressed = false;
    private boolean isHold5Pressed = false;

    private Hand hand;
    private Deck deck;
    private Winning winning = new Winning();

    public Game() {
        winning.setBet(0);
    }

    public void onEventName(String event) {
        switch(event) {
            case "OnBetUp":
                onEvent(events.betUp);
                break;
            case "OnBetDown":
                onEvent(events.betDown);
                break;
            case "OnHold1":
                onEvent(events.hold1);
                break;
            case "OnHold2":
                onEvent(events.hold2);
                break;
            case "OnHold3":
                onEvent(events.hold3);
                break;
            case "OnHold4":
                onEvent(events.hold4);
                break;
            case "OnHold5":
                onEvent(events.hold5);
                break;
            case "OnDeal":
                onEvent(events.deal);
        }
    }

    public Hand getHand() {
        return hand;
    }

    public Winning getWinning() {
        return winning;
    }

    private void onEvent(events event) {

        switch (event) {
            case hold1:
            case hold2:
            case hold3:
            case hold4:
            case hold5:
                onHold(event);
                break;
            case deal:
                onDeal();
                break;
            case betUp:
                onBetUp();
                break;
            case betDown:
                onBetDown();
                break;
        }
    }

    private void onDeal() {
        if (isHold1Pressed == false
                && isHold2Pressed == false
                && isHold3Pressed == false
                && isHold4Pressed == false
                && isHold5Pressed == false) {
            round = 0;
        }

        if (round == 0) {
            //раздава първа ръка
            reset();
            round = 1;
        } else if (round == 1) {
            //заменя нехолднатите карти
            hand.swapCardInHand(hand.getHand(), deck, getSwapIndex());
            round = 0;
        }

        onCheckWinning(hand);
    }

    private void onCheckWinning(Hand hand) {
        winning.setHand(hand);
    }

    private void reset() {
        deck.getDeck().clear();
        deck.populateDeck();
        hand.getHand().clear();
        hand.populateHand(deck, deck.getDeck());
    }

    private int[] getSwapIndex() {
        ArrayList<Integer> swaps = new ArrayList<>();

        if (!isHold1Pressed) {
            swaps.add(1);
        }
        if (!isHold2Pressed) {
            swaps.add(2);
        }
        if (!isHold3Pressed) {
            swaps.add(3);
        }
        if (!isHold4Pressed) {
            swaps.add(4);
        }
        if (!isHold5Pressed) {
            swaps.add(5);
        }

        int[] ret = new int[swaps.size()];
        for (int i=0; i < ret.length; i++) {
            ret[i] = swaps.get(i);
        }
        return ret;
    }

    private void onBetUp() {
        if(winning.getBet() == 1) {
            winning.addBet(4);
        }
        else {
            winning.addBet(5);
        }
    }

    private void onBetDown() {
        winning.removeBet(5);
    }

    private void onHold(events event) {
        if (round == 0) {
            switch (event) {
                case hold1:
                    isHold1Pressed = !isHold1Pressed;
                case hold2:
                    isHold2Pressed = !isHold2Pressed;
                case hold3:
                    isHold3Pressed = !isHold3Pressed;
                case hold4:
                    isHold4Pressed = !isHold4Pressed;
                case hold5:
                    isHold5Pressed = !isHold5Pressed;

            }
        }
    }
}


