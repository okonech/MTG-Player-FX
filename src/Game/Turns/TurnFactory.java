package Game.Turns;

import Game.Game;
import Player.Player;

public class TurnFactory {
    
    private Game game;

    public TurnFactory(Game game){
        this.game=game;
    }
    
    public Turn newTurn(Player player){
        return new Turn(player);
    }

}
