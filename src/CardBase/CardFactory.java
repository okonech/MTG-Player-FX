package CardBase;

import java.io.IOException;

import CardBase.JSONDatabaseParser.CardJSON;
import Game.Game;
import Player.Player;

// given a set of arguments for a card, reads the first and creates the proper class type object
// then passes the rest of the arguments to that object's constructor
public class CardFactory {

    CardDatabase database;
    Game game;

    public CardFactory(Game game) {
        try {
            this.database = new CardDatabase("fake path");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.game = game;
    }

    public Card makeCard(Player player, CardJSON cardJson)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String arg1 = "Creature";
        // qualify class path
        arg1 = qualifiedClassPath(arg1);
        // just create new blank instance here
        Card newCard = (Card) Class.forName(arg1).newInstance();
        // handle all further initialization and pass args
        newCard.initializeCard(player, cardJson);
        return newCard;
    }

    public Card makeCard(Player player, String cardName, String set)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String[] args;
        CardJSON cardJson;
        if (set.length() > 0) {
            // edition exists, query set DB
            cardJson = database.searchCard(cardName, set);
        } else {
            // query with just name and use first found set
            cardJson = database.searchCard(cardName);
        }
        return makeCard(player, cardJson);
    }
    

    private String qualifiedClassPath(String cardClass)
            throws ClassNotFoundException {
        switch (cardClass.toUpperCase()) {
        case "CREATURE":
            return "CardBase.CardTypes.Creature";
        case "SORCERY":
            return "CardBase.CardTypes.Sorcery";
        default:
            ClassNotFoundException e = new ClassNotFoundException(
                    "Invalid class: " + cardClass);
            throw e;
        }
    }
}
