package Player;

import CardBase.Card;
import Game.Game;
import javafx.collections.ObservableList;

// called by game to take all possible player actions
public class PlayerActions {
    Game game;

    public PlayerActions(Game game) {
        this.game = game;
    }

    public void drawCard(Player player) {
        ObservableList<Card> library = player.getLibrary().getCardsList();
        ObservableList<Card> hand = player.getHand().getCardsList();
        if (library.isEmpty()) {
            // add loss code here
        } else {
            Card card = library.remove(0);
            hand.add(card);
            card.updateZone(player.hand);
        }
    }

    public void discardCard(Player player, Card card) {
        moveCardToZone(player.getHand(),player.getGraveyard(),card);
    }
    
    public void playCard(Player player, Card card) {
        moveCardToZone(player.getHand(),player.getBattlefield(),card);
    }
    
    public void returnCardToHand(Player player, Card card) {
        moveCardToZone(player.getBattlefield(),player.getHand(),card);
    }
    
    private void moveCardToZone(PlayerZone fromZone, PlayerZone toZone, Card card){
        if (fromZone.getCardsList().contains(card)) {
            fromZone.getCardsList().remove(card);
            toZone.getCardsList().add(card);
            card.updateZone(toZone);
        }
    }

}
