package CardBase;

import java.util.Set;

import CardBase.CardFunctions.Ability;
import CardBase.CardFunctions.Action;
import CardBase.CardTypes.SubType;
import CardBase.CardTypes.SuperType;
import CardBase.CardTypes.Type;
import CardBase.JSONDatabaseParser.CardJSON;
import Player.Player;
import Player.PlayerZone;
import userInterface.CardScreen.CardController;

public class Card {
    private CardJSON cardJson;
    private Player owner;
    private Player controller;
    // type overrides
    private Set<Type> types;
    // subtype overrides
    private Set<SubType> subTypes;
    // supertype overrides
    private Set<SuperType> superTypes;
    // list of abilities
    private Set<Ability> abilities;
    private Set<Ability> abilitiesDefault;
    
    // list of actions
    private Set<Action> actions;
    
    // incremented when changing zones, used to keep track of blink, etc
    private int zoneChangeCount;
    // enumerated zone and cards list in the zone
    private PlayerZone zone;
    // controller for this card
    private CardController cardController;

    public Card() {
        // only always true declarations go here
        this.zoneChangeCount = 0;
    }
    
    public void initializeCard(Player player, CardJSON cardJson){
        this.cardJson=cardJson;
        // probably take in list of attributes here
        this.owner=player;
        this.controller=player;
        
        // default to library zone
        zone=player.getLibrary();
        
        // load controller and UI for this Card
        //loadController();
        this.cardController= CardController.makeController(this);
    }

    // return name + zone change counter
    // used to handle target, blink, target fizzle, etc
    public String getCardId() {
        return Integer.toString(this.hashCode()) + this.zoneChangeCount;
    }

    public String testOut() {
        return cardJson.name;
    }

    public String getName() {
        return cardJson.name;
    }

    public PlayerZone getZone() {
        return zone;
    }

    public String getMultiverseID() {
        return cardJson.multiverseid;
    }

    public void setController(CardController cardController) {
        this.cardController = cardController;
    }

    public CardController getCardController() {
        return cardController;
    }
    
    public Player getPlayerOwner() {
        return owner;
    }
    
    public Player getPlayerController() {
        return controller;
    }
    
    public String getSetCode(){
        return cardJson.set.code;
    }
    
    public String getSetGatherer(){
        return cardJson.set.gathererCode;
    }
    
    public String getSetMagicCardsInfo(){
        return cardJson.set.magicCardsInfoCode;
    }
    
    public String getSetNumber(){
        return cardJson.number;
    }
    
    public String getMciNumber(){
        return cardJson.mciNumber;
    }
    
    public String getCardUUID(){
        return cardJson.id;
    }
    
    public Set<SuperType> getSuperTypes(){
        return superTypes;
    }
    
    public Set<SuperType> getSuperTypesDefault(){
        return superTypes;
    }
    
    public String getOracleText(){
        return cardJson.text;
    }
    
    public Set<Action> getActions(){
        return actions;
    }

    public void updateZone(PlayerZone zone) {
        this.zone=zone;
    }

}