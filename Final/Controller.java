package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView cardOne;

    @FXML
    private ImageView cardTwo;

    @FXML
    private ImageView cardThree;

    @FXML
    private ImageView cardFour;

    @FXML
    private ImageView cardFive;

    @FXML
    private Button holdOne;

    @FXML
    private Button holdTwo;

    @FXML
    private Button holdThree;

    @FXML
    private Button holdFour;

    @FXML
    private Button holdFive;

    @FXML
    private Button btnDeal;

    @FXML
    private ImageView minus;

    @FXML
    private ImageView plus;

    @FXML
    private Text txtReward;

    @FXML
    private Text txtCredit;

    @FXML
    private Text txtBet;

    @FXML
    private Text royalFlush;

    @FXML
    private Text streetFlush;

    @FXML
    private Text poker;

    @FXML
    private Text fullHouse;

    @FXML
    private Text street;

    @FXML
    private Text threeOfAKind;

    @FXML
    private Text twoPairs;

    @FXML
    private Text onePair;

    @FXML
    private Text royalFlushMultiplier;

    @FXML
    private Text streetFlushMultiplier;

    @FXML
    private Text pokerMultiplier;

    @FXML
    private Text fullHouseMultiplier;

    @FXML
    private Text streetMultiplier;

    @FXML
    private Text threeOfAKindMultiplier;

    @FXML
    private Text twoPairsMultiplier;

    @FXML
    private Text onePairMultiplier;

    @FXML
    private Text gameOver;

    //detrmines when a hold buttons is clicked
    private boolean isHoldOneClicked = false;
    private boolean isHoldTwoClicked = false;
    private boolean isHoldThreeClicked = false;
    private boolean isHoldFourClicked = false;
    private boolean isHoldFiveClicked = false;

    private int round = 1;
    private int bet = 5;

    private Deck d;
    private Hand h;
    private Prize p;
    private Round r;

    //stores cards in deck
    private List<Card> deck;
    //stores cards in hand
    private List<Card> hand;

    private Image imageMinus;
    private Image imagePlus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DropShadow shadow = new DropShadow();

        //show + and - buttons
        enableIncreaseDecreaseButtons();
        //disable hold buttons at startUp
        disableHoldButton();

        //load image for cards starting view
        Image cardBack = new Image("file:resources/back.jpg");
        cardOne.setImage(cardBack);
        cardTwo.setImage(cardBack);
        cardThree.setImage(cardBack);
        cardFour.setImage(cardBack);
        cardFive.setImage(cardBack);

        btnDeal.setText("Start Game");

        d = new Deck();
        h = new Hand();
        p = new Prize();
        r = new Round(bet);

        //loads cards into the deck
        deck = d.populateDeck();
        //loads cards into the hand
        hand = h.populateHand(d, deck);

        //when Deal button is clicked
        btnDeal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hide + and - buttons
                disableIncreaseDecreaseButtons();
                //enable hold buttons
                enableHoldButton();

                //during first part of the round
                if (round == 1) {
                    bet = Integer.parseInt(txtBet.getText());
                    
                    //prevents credit to fall beneath 0
                    int currentCredit = Integer.parseInt(txtCredit.getText());
                    currentCredit -= bet;
                    if (currentCredit < 0) {
                        currentCredit = 0;
                    }
                    txtCredit.setText(Integer.toString(currentCredit));

                    btnDeal.setText("Deal");

                    enableHoldButton();
                    
                    //creates paths for each card image
                    String[] paths = createPath(hand);
                    List<Image> images = createImage(paths);

                    //load card images
                    cardOne.setImage(images.get(0));
                    cardTwo.setImage(images.get(1));
                    cardThree.setImage(images.get(2));
                    cardFour.setImage(images.get(3));
                    cardFive.setImage(images.get(4));

                    //increment to second part of round
                    round++;

                    changeBackColorOfPrize();
                    
                //when second part of round
                } else if (round == 2) {
                    //determines which cards to be replaces
                    int[] swapIndexes = calculateSwapIndexes();
                    //replace cards from hand
                    h.swapCardInHand(hand, d, swapIndexes);

                    btnDeal.setText("New Round");

                    removeButtonEffect();

                    //creates paths for each card image
                    String[] paths2 = createPath(hand);
                    List<Image> images2 = createImage(paths2);

                    //loads images
                    cardOne.setImage(images2.get(0));
                    cardTwo.setImage(images2.get(1));
                    cardThree.setImage(images2.get(2));
                    cardFour.setImage(images2.get(3));
                    cardFive.setImage(images2.get(4));

                    //calculates prize
                    int prize = r.determinePrize(hand, p);
                    String prizeAsText = r.getPrice();
                    txtReward.setText(Integer.toString(prize));

                    changeColorOfPrize(prizeAsText);

                    int newCredit = Integer.parseInt(txtCredit.getText()) + prize;
                    txtCredit.setText(Integer.toString(newCredit));

                    txtBet.setText("5");
                    r.setBet(5);

                    resetHoldBooleans();
                    resetHoldButtons();

                    //determines if game over
                    if (Integer.parseInt(txtCredit.getText()) > 0) {
                        enableIncreaseDecreaseButtons();
                        disableHoldButton();
                    } else {
                        disableHoldButton();
                        disableIncreaseDecreaseButtons();
                        btnDeal.setDisable(true);
                        gameOver.setText("Game Over");
                        gameOver.toFront();
                    }

                    //clear hand and deck and load new cards
                    deck.clear();
                    hand.clear();
                    deck = d.populateDeck();
                    hand = h.populateHand(d, deck);

                    round = 1;
                }
            }
        });

        //lower bet
        minus.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                int bet = r.getBet();
                r.setBet(bet - 5);
                if (r.getBet() <= 0) {
                    r.setBet(1);
                }

                txtBet.setText(Integer.toString(bet));
            }
        });

        //raise bet
        plus.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                int bet = r.getBet();
                int currentCredit = Integer.parseInt(txtCredit.getText());
                r.setBet(bet + 5);
                if (r.getBet() > currentCredit) {
                    r.setBet(currentCredit);
                }

                if (bet == 1) {
                    r.setBet(5);
                }

                txtBet.setText(Integer.toString(r.getBet()));
            }
        });

        //first hold button
        holdOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (holdOne.getId().equals("holdOne")) {
                    holdOne.setId("holdButtonClicked");
                    isHoldOneClicked = true;
                } else {
                    holdOne.setId("holdOne");
                    isHoldOneClicked = false;
                }
            }
        });

        //second hold button
        holdTwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (holdTwo.getId().equals("holdTwo")) {
                    holdTwo.setId("holdButtonClicked");
                    isHoldTwoClicked = true;
                } else {
                    holdTwo.setId("holdTwo");
                    isHoldTwoClicked = false;
                }
            }
        });

        //third hold button
        holdThree.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (holdThree.getId().equals("holdThree")) {
                    holdThree.setId("holdButtonClicked");
                    isHoldThreeClicked = true;
                } else {
                    holdThree.setId("holdThree");
                    isHoldThreeClicked = false;
                }
            }
        });

        //fourth hold button
        holdFour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (holdFour.getId().equals("holdFour")) {
                    holdFour.setId("holdButtonClicked");
                    isHoldFourClicked = true;
                } else {
                    holdFour.setId("holdFour");
                    isHoldFourClicked = false;
                }
            }
        });

        //fifth hold button
        holdFive.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (holdFive.getId().equals("holdFive")) {
                    holdFive.setId("holdButtonClicked");
                    isHoldFiveClicked = true;
                } else {
                    holdFive.setId("holdFive");
                    isHoldFourClicked = false;
                }
            }
        });
    }

    //creates paths for the card images
    private String[] createPath(List<Card> hand) {
        String[] paths = new String[hand.size()];

        for (int i = 0; i < hand.size(); i++) {
            paths[i] = "file:resources/" + hand.get(i).toString() + ".jpg";
        }

        return paths;
    }

    //create the card images
    private List<Image> createImage(String[] paths) {
        List<Image> images = new ArrayList<>();

        for (String path : paths) {
            images.add(new Image(path));
        }

        return images;
    }

    //determine with cards to be replaced
    private int[] calculateSwapIndexes() {
        int[] swapIndexes = new int[5];

        swapIndexes[0] = isHoldOneClicked ? 0 : 1;
        swapIndexes[1] = isHoldTwoClicked ? 0 : 2;
        swapIndexes[2] = isHoldThreeClicked ? 0 : 3;
        swapIndexes[3] = isHoldFourClicked ? 0 : 4;
        swapIndexes[4] = isHoldFiveClicked ? 0 : 5;

        return swapIndexes;
    }

    //return prize text back to black
    private void changeBackColorOfPrize() {
        royalFlush.setFill(Color.BLACK);
        royalFlushMultiplier.setFill(Color.BLACK);

        streetFlush.setFill(Color.BLACK);
        streetFlushMultiplier.setFill(Color.BLACK);

        poker.setFill(Color.BLACK);
        pokerMultiplier.setFill(Color.BLACK);

        fullHouse.setFill(Color.BLACK);
        fullHouseMultiplier.setFill(Color.BLACK);

        street.setFill(Color.BLACK);
        streetMultiplier.setFill(Color.BLACK);

        threeOfAKind.setFill(Color.BLACK);
        threeOfAKindMultiplier.setFill(Color.BLACK);

        twoPairs.setFill(Color.BLACK);
        twoPairsMultiplier.setFill(Color.BLACK);

        onePair.setFill(Color.BLACK);
        onePairMultiplier.setFill(Color.BLACK);
    }

    //sets prize text to red
    private void changeColorOfPrize(String prizeAsText) {
        switch (prizeAsText) {
            case "Royal Flush":
                royalFlush.setFill(Color.RED);
                royalFlushMultiplier.setFill(Color.RED);
                break;
            case "Street Flush":
                streetFlush.setFill(Color.RED);
                streetFlushMultiplier.setFill(Color.RED);
                break;
            case "Poker":
                poker.setFill(Color.RED);
                pokerMultiplier.setFill(Color.RED);
                break;
            case "Full House":
                fullHouse.setFill(Color.RED);
                fullHouseMultiplier.setFill(Color.RED);
                break;
            case "Street":
                street.setFill(Color.RED);
                streetMultiplier.setFill(Color.RED);
                break;
            case "Three of a kind":
                threeOfAKind.setFill(Color.RED);
                threeOfAKindMultiplier.setFill(Color.RED);
                break;
            case "Two Pairs":
                twoPairs.setFill(Color.RED);
                twoPairsMultiplier.setFill(Color.RED);
                break;
            case "One Pair":
                onePair.setFill(Color.RED);
                onePairMultiplier.setFill(Color.RED);
                break;
        }
    }

    //change hold buttons to start view
    private void removeButtonEffect() {
        holdOne.setEffect(null);
        holdTwo.setEffect(null);
        holdThree.setEffect(null);
        holdFour.setEffect(null);
        holdFive.setEffect(null);
    }

    //disable hold buttons
    private void disableHoldButton() {
        holdOne.setDisable(true);
        holdTwo.setDisable(true);
        holdThree.setDisable(true);
        holdFour.setDisable(true);
        holdFive.setDisable(true);
    }

    //enable hold buttons
    private void enableHoldButton() {
        holdOne.setDisable(false);
        holdTwo.setDisable(false);
        holdThree.setDisable(false);
        holdFour.setDisable(false);
        holdFive.setDisable(false);
    }

    //reset hold buttons state
    private void resetHoldBooleans() {
        isHoldOneClicked = false;
        isHoldTwoClicked = false;
        isHoldThreeClicked = false;
        isHoldFourClicked = false;
        isHoldFiveClicked = false;
    }

    //disable increase bet and decrease bet buttons
    private void disableIncreaseDecreaseButtons() {
        imageMinus = null;
        imagePlus = null;

        plus.setImage(imagePlus);
        minus.setImage(imageMinus);
    }

    //enable increase bet and decrease bet buttons
    private void enableIncreaseDecreaseButtons() {
        imagePlus = new Image("file:resources/plus.jpg");
        plus.setImage(imagePlus);

        imageMinus = new Image("file:resources/minus.jpg");
        minus.setImage(imageMinus);
    }

    //change hold buttons id to start id
    private void resetHoldButtons() {
        holdOne.setId("holdOne");
        holdTwo.setId("holdTwo");
        holdThree.setId("holdThree");
        holdFour.setId("holdFour");
        holdFive.setId("holdFive");
    }
}
