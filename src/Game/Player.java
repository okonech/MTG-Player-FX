package Game;

import java.util.ArrayList;
import CardBase.Card;
import application.PlayerScreen.PlayerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;

//keeps track of all cards which care about triggers by type
public class Player {
    String name;

    // trigger lists
    // etb trigger cards
    // spell cast trigger cards
    // upkeep
    //

    // constant effect lists

    // arraylist to preserve order of library
    ArrayList<Card> library;
    // track player's other cards with hashmaps
    // cards are id'd by (id,zone int) so failures to find can happen by
    // blinking, etc
    // hashmaps will be searched very often
    ArrayList<Card> hand;
    // arraylist to preserve order of graveyard
    ArrayList<Card> graveyard;
    ArrayList<Card> exile;
    ArrayList<Card> exileFaceDown;
    Card commander;
    int lifeTotal;
    // reference to main game state to perform actions and checks
    GameState gameState;
    // reference to player's controller to update UI data
    PlayerController controller;

    public Player(GameState gameState, String name, int startingLife) {
        this.gameState = gameState;
        this.name = name;
        this.lifeTotal = startingLife;
        this.library = new ArrayList<Card>();
        this.graveyard = new ArrayList<Card>();
        this.exile = new ArrayList<Card>();
        this.exileFaceDown = new ArrayList<Card>();
        this.hand = new ArrayList<Card>();
        // this.hand= FXCollections.observableArrayList();
    }

    public void setController(PlayerController controller) {
        this.controller = controller;
        // initialize listeners for player lists
        // controller.setListeners();
    }

    public PlayerController getController() {
        return controller;
    }

    public String getName() {
        return name;
    }

    public void loadDeck(String[][] cardLine) {
        if (cardLine == null) {
            // nothing on null
        } else {
            try {
                for (String[] cardArgs : cardLine) {
                    // should be parsed into [Card Name][TAB][Set]
                    this.library.add(gameState.cardFactory.makeCard(this,
                            cardArgs[0], cardArgs[1]));
                }
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Card> getLibrary() {
        return library;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ObservableList<ScrollPane> getHandUI() {
        ObservableList<ScrollPane> handUI = FXCollections.observableArrayList();
        for (Card card : hand) {
            handUI.add(card.getCardController().getCardUI());
        }
        return handUI;
    }

    public void drawCard() {
        if (library.isEmpty()) {
            // add loss code here
        } else {
            hand.add(library.remove(0));
            controller.updateHand();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

}
