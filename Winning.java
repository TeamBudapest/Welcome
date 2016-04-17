/**
 * Created by asIo on 17.4.2016 г..
 */
public class Winning {

    private String winningText = new String();

    public Winning()
    {

    }

    private void SetHandCalc(Hand hand)
    {
        winningText = "";//winningText = ?
    }

    public void SetHand(Hand hand)
    {
        winningText = "";
        SetHandCalc(hand);
    }

    public String GetWinning()
    {
        return winningText;
    }


}
