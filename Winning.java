/**
 * Created by asIo on 17.4.2016 г..
 */
public class Winning {
    private winningText;

    public void SetHand(Hand hand)
    {
        winningText = null;
        SetHandCalc(hand);
    }

    private SetHandCalc(Hand hand)
    {
        winningText = null;//winningText = ?
    }

    public String GetWinning()
    {
        return winningText;
    }
}
