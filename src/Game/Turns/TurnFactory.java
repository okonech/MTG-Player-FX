package Game.Turns;

import Game.GameState;
import Game.Player;

public class TurnFactory {
    
    private GameState gameState;

    public TurnFactory(GameState gameState){
        this.gameState=gameState;
    }
    
    public Turn newTurn(Player player){
        return new Turn(player);
    }

}
