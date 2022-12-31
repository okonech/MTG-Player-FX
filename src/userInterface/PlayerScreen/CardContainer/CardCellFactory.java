package userInterface.PlayerScreen.CardContainer;

import CardBase.Card;
import Player.Player;
import Player.PlayerZone;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class CardCellFactory extends Pane implements Callback<ListView<Card>, ListCell<Card>>{
        ListView<Card> handListView;
        Player player;
        PlayerZone correspondingZone;
        DoubleBinding desiredCellHeight;
        boolean bound;
    
        // need to pass the current hand as observable list here, but should this be unique to a player?
        public CardCellFactory(Player player, PlayerZone correspondingZone, ListView<Card> handListView) {
            this.player=player;
            this.correspondingZone = correspondingZone;
            this.handListView=handListView;
            this.bound=false;
        }

        @Override
        public ListCell<Card> call(ListView<Card> listview) {
            ListCell<Card> cell = new CardCell(player,correspondingZone.getCardsList(),handListView);
            return cell;
        }
        
}

