/**
 * Created by asIo on 17.4.2016 г..
 */
//import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

public class Game {
    public enum events{
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

    Hand hand;
    Deck deck;

    public void OnEvent(events event) {

        switch(event)
        {
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

    public Hand GetHand()
    {
        return hand;
    }

    private void OnDeal()
    {
        if(isHold1Pressed == false
                && isHold2Pressed == false
                && isHold3Pressed == false
                && isHold4Pressed == false
                && isHold5Pressed == false)
        {
            round = 0;
        }

        if(round == 0)
        {
            //раздаваш първа ръка - hand.populateHand(deck);
            round = 1;
        }
        else if(round == 1)
        {
            //заменяш нехолднатите карти
            //hand.swapCardInHand(deck, getSwapIndex());
            round = 0;
        }
    }

    private int[] getSwapIndex()
    {
        int[] swaps;

        if(!isHold1Pressed)
        {
            swaps.push(0);
        }
        if(!isHold2Pressed)
        {
            swaps.push(1);
        }
        if(!isHold3Pressed)
        {
            swaps.push(2);
        }
        if(!isHold4Pressed)
        {
            swaps.push(3);
        }
        if(!isHold5Pressed)
        {
            swaps.push(4);
        }

        return swaps;
    }

    private void OnBetUp()
    {
        //logic
    }

    private void OnBetDown()
    {
        //logic
    }

    private void OnHold(events event)
    {
        if(round == 0)
        {
            switch(event)
            {
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

    public Game() {

    }


//    public void swapCardInHand(List<Card> hand, Deck d, int[] indexes) {
//        Random rnd = new Random();
//
//        for (int i = 0; i < indexes.length; i++) {
//            Card card = d.getDeck().get(rnd.nextInt(d.getDeck().size()));
//            hand.remove(indexes[i]);
//            hand.add(indexes[i], card);
//            d.removeCardFromDeck(d.getDeck(), card);
//        }
//    }
}


