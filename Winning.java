/**
 * Created by asIo on 17.4.2016 г..
 */
public class Winning {
    int bet_ = 1;

    private String winningText = new String();

    public Winning() {

    }

    private void SetHandCalc(Hand hand) {
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

    public void SetHand(Hand hand) {
        winningText = null;
        SetHandCalc(hand);
    }

    public String ToString() {
        return winningText;
    }

    public int GetBet() {
        return bet_;
    }

    public void SetBet(int bet) {
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

    public void AddBet(int bet) {
        bet_ += bet;
    }

    public void RemoveBet(int bet) {
        bet_ -= bet;
        if(bet_ < 1) {
            bet_ = 1;
        }
    }
}
