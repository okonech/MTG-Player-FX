package CardBase;

import java.io.IOException;
import java.util.Set;

import CardBase.CardTypes.SubType;
import CardBase.CardTypes.SuperType;
import Game.Player;
import Game.Zone;
import application.CardScreen.CardController;
import javafx.fxml.FXMLLoader;

public class Card {
    private String name;
    private String set;
    // id to scrape image from Gatherer
    private String multiverseID;
    private Player owner;
    private Player controller;
    // supertypes and overrides
    private Set<SuperType> superTypes;
    private Set<SuperType> superTypesDefault;
    // subtypes and overrides
    private Set<SubType> subTypes;
    private Set<SubType> subTypesDefault;
    // list of abilities
    private Set<Ability> abilities;
    private Set<Ability> abilitiesDefault;
    // incremented when changing zones, used to keep track of blink, etc
    private int zoneChangeCount;
    // enumerated zone
    private Zone zone;
    // controller for this card
    private CardController cardController;

    public Card() {
        // only always true declarations go here
        this.zoneChangeCount = 0;
        // default to library zone
        if (this.zone == null) {
            this.zone = Zone.LIBRARY;
        }
        
    }
    
    public void initializeCard(Player player, String[] args){
        // probably take in list of attributes here
        this.owner=player;
        this.controller=player;
        this.multiverseID = Integer.toString(79217);
        //this.name = "Isamaru, Hound of Konda";
        //this.set="CHK";
        this.name=args[0];
        this.set=args[1];
        // load controller and UI for this Card
        loadController();
    }

    // return name + zone change counter
    // used to handle target, blink, target fizzle, etc
    public String getCardId() {
        return Integer.toString(this.hashCode()) + this.zoneChangeCount;
    }

    public String testOut() {
        return "Card";
    }

    public String getName() {
        return name;
    }

    public String getZone() {
        return zone.toString();
    }

    public String getMultiverseID() {
        return multiverseID;
    }

    public void setController(CardController cardController) {
        this.cardController = cardController;
    }

    public CardController getCardController() {
        return cardController;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    public Player getController() {
        return controller;
    }

    public void loadController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("../../application/CardScreen/CardScreen.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // set references between Card and CardController
        this.cardController = fxmlLoader.<CardController> getController();
        this.cardController.setCard(this);
    }
    
    public String getSet(){
        return set;
    }

}