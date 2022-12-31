package userInterface.PlayerScreen.CardContainer;

import CardBase.Card;
import Player.Player;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import userInterface.CardScreen.CardController;

public class CardCell extends ListCell<Card> implements CardContainer{
    ObservableList<Card> correspondingList;
    

    public CardCell(Player player, ObservableList<Card> correspondingList, ListView<Card> handListView) {
        
        this.correspondingList=correspondingList;
        this.prefHeightProperty().bind(Bindings.subtract(handListView.heightProperty(), 14.0));

        double cardWidthHeightRatio = (CardController.DEF_WIDTH) / CardController.DEF_HEIGHT;

        DoubleBinding calcWidth = Bindings.multiply(this.prefHeightProperty(), cardWidthHeightRatio);
        
        prefWidthProperty().bind(calcWidth);
        
    }

    @Override
    public void updateItem(Card card, boolean empty) {
        super.updateItem(card, empty);
        if (empty || card == null) {
            setGraphic(null);
        } else {
            setGraphic(card.getCardController().getCardUI());

        }

    }
    
    // replace this with a new card container type so drag handler can always call getcard on all pane objects like this
    
    public Card getCard(){
        return this.getItem();
    }
    
    public ObservableList<Card> getCardList(){
        return correspondingList;
    }

}
