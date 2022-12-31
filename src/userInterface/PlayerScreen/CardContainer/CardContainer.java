package userInterface.PlayerScreen.CardContainer;

import CardBase.Card;
import javafx.collections.ObservableList;

public interface CardContainer {
    
    public Card getCard();
    
    public ObservableList<Card> getCardList();

}
