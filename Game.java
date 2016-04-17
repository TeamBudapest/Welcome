/**
 * Created by asIo on 17.4.2016 г..
 */
//import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

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
        winning.SetBet(0);
    }

    public void OnEvent(events event) {

        switch (event) {
            case hold1:
            case hold2:
            case hold3:
            case hold4:
            case hold5:
                OnHold(event);
                break;
            case deal:
                OnDeal();
                break;
            case betUp:
                OnBetUp();
                break;
            case betDown:
                OnBetDown();
                break;
        }
    }

    public Hand GetHand() {
        return hand;
    }

    public String GetWinning() {
        return winning.GetWinning();
    }

    private void OnDeal() {
        if (isHold1Pressed == false
                && isHold2Pressed == false
                && isHold3Pressed == false
                && isHold4Pressed == false
                && isHold5Pressed == false) {
            round = 0;
        }

        if (round == 0) {
            //раздава първа ръка
            Reset();
            round = 1;
        } else if (round == 1) {
            //заменя нехолднатите карти
            hand.swapCardInHand(hand.getHand(), deck, getSwapIndex());
            round = 0;
        }

        OnCheckWinning(hand);
    }

    private void OnCheckWinning(Hand hand) {
        winning.SetHand(hand);
    }

    private void Reset()
    {
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
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = swaps.get(i);
        }
        return ret;
    }

    private void OnBetUp() {
        if(winning.GetBet() == 1)
        {
            winning.AddBet(4);
        }
        else
        {
            winning.AddBet(5);
        }
    }

    private void OnBetDown() {
        winning.RemoveBet(5);
    }

    private void OnHold(events event) {
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


