/**
 * Created by asIo on 17.4.2016 г..
 */
public class Winning {
    int bet_ = 1;

    private String winningText = new String();

    public Winning() {

    }

    private void setHandCalc(Hand hand) {
        winningText = null;//winningText = ?

        //От ръката се взимат текущите карти
        //и на базата на тях се определя печалбата
        //Зад всяка печалба стои текст който трябва
        //да се присвои на winningText (отгоре)
        //Ако няма печалба - winningText = null;
        //Текущия залог е целоцифрената стойност
        //в bet_ . Тя е винаги по-голяма от 0
        //и по-малка или равна на 100;
    }

    public void setHand(Hand hand) {
        winningText = null;
        setHandCalc(hand);
    }

    public String toString() {
        return winningText;
    }

    public int getBet() {
        return bet_;
    }

    public void setBet(int bet) {
        if(bet > 100) {
            bet_ = 100;
        }
        else if(bet < 1) {
            bet_ = 1;
        }
        else {
            bet_ = bet;
        }
    }

    public void addBet(int bet) {
        bet_ += bet;
    }

    public void removeBet(int bet) {
        bet_ -= bet;
        if(bet_ < 1) {
            bet_ = 1;
        }
    }
}
