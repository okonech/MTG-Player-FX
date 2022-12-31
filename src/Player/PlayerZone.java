package Player;

import CardBase.Card;
import Game.Zone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerZone {
   private Zone zone;
   private ObservableList<Card> cardsList;
    
    public PlayerZone(Zone zone){
        this.zone=zone;
        this.cardsList = FXCollections.observableArrayList();
    }
    
    public Zone getZone(){
        return zone;
    }
    
    public ObservableList<Card> getCardsList(){
        return cardsList;
    }
   
}
