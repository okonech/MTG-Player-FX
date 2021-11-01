package Game;

import java.util.ArrayList;
import java.util.Stack;

import CardBase.CardFactory;
import Game.Turns.Turn;
import Game.Turns.TurnFactory;
import Images.ImageCache;

// keeps track of battlefield
// keeps reference to players for their hands/graveyards/libraries etc
// loops through players for their etb effects
// loops through players for constant effects

public class GameState {
    ArrayList<Player> players;
    // on every player's turn add the next player to the stack
    // add extra turns to the stack as needed
    // this handles all extra turn taking
    Stack<Player> turns;
    Stack stack;
    int startLife;
    // have central CardFactory to create cards for decks and to make tokens
    CardFactory cardFactory;
    TurnFactory turnFactory;
    ImageCache imageCache;
    
    public GameState(){
        // see if importing things here like starting life is a good idea
        // maybe have a prefs object loaded to handle setup
        startLife=40;
        // card creator
        cardFactory=new CardFactory(this);
        // turn creator
        turnFactory = new TurnFactory(this);
        // cache for loaded images
        imageCache = new ImageCache();
        players= new ArrayList<Player>();
        stack= new Stack();
        turns = new Stack<Player>();
    }
    
    public void addPlayer() {
        String playerName="Player "+(players.size()+1);
        Player player = new Player(this,playerName,startLife);
        players.add(player);
    }
    
    public ArrayList<Player> getPlayers(){
        return players;
    }
    
    public ImageCache getImageCache(){
        return imageCache;
    }
    
    public TurnFactory getTurnFactory(){
        return turnFactory;
    }
    
}
