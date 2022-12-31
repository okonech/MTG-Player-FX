package Player;

import CardBase.Card;
import Game.Game;
import Game.Zone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import userInterface.PlayerScreen.PlayerController;

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
    PlayerZone library;
    // track player's other cards with hashmaps
    // cards are id'd by (id,zone int) so failures to find can happen by
    // blinking, etc
    // hashmaps will be searched very often
    PlayerZone hand;
    PlayerZone battlefield;
    // arraylist to preserve order of graveyard
    PlayerZone graveyard;
    PlayerZone exile;
    PlayerZone exileFaceDown;
    Card commander;
    int lifeTotal;
    // reference to main game state to perform actions and checks
    Game game;
    // reference to player's controller to update UI data
    PlayerController controller;

    public Player(Game game, String name, int startingLife) {
        this.game = game;
        this.name = name;
        this.lifeTotal = startingLife;
        this.library = new PlayerZone(Zone.LIBRARY);
        this.graveyard = new PlayerZone(Zone.GRAVEYARD);
        this.exile = new PlayerZone(Zone.EXILE);
        this.exileFaceDown = new PlayerZone(Zone.EXILEFACEDOWN);
        this.hand = new PlayerZone(Zone.HAND);
        this.battlefield = new PlayerZone(Zone.BATTLEFIELD);
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
                    this.library.getCardsList().add(game.getCardFactory().makeCard(this,
                            cardArgs[0], cardArgs[1]));
                }
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public PlayerZone getLibrary() {
        return library;
    }

    public PlayerZone getHand() {
        return hand;
    }
    
    public PlayerZone getBattlefield() {
        return battlefield;
    }
    
    public PlayerZone getGraveyard() {
        return graveyard;
    }
    
    public PlayerZone getExile() {
        return exile;
    }
    
    public PlayerZone getExileFaceDown() {
        return exileFaceDown;
    }

    public ObservableList<ScrollPane> getHandUI() {
        ObservableList<ScrollPane> handUI = FXCollections.observableArrayList();
        for (Card card : hand.getCardsList()) {
            handUI.add(card.getCardController().getCardUI());
        }
        return handUI;
    }
    

    public Game getGame() {
        return game;
    }
    

}
