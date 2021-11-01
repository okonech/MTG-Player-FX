package CardBase;

import Game.GameState;
import Game.Player;

// given a set of arguments for a card, reads the first and creates the proper class type object
// then passes the rest of the arguments to that object's constructor
public class CardFactory {

    CardDatabase database;
    GameState gameState;

    public CardFactory(GameState gameState) {
        this.database = new CardDatabase();
        this.gameState = gameState;
    }

    public Card makeCard(Player player, String[] args)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String arg1 = "Creature";
        // qualify class path
        arg1 = qualifiedClassPath(arg1);
        // just create new blank instance here
        Card newCard = (Card) Class.forName(arg1).newInstance();
        // handle all further initialization and pass args
        newCard.initializeCard(player, args);
        return newCard;
    }

    public Card makeCard(Player player, String cardName, String set)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String[] args;
        if (set.length() > 0) {
            // edition exists, query set DB
            args = database.searchCard(cardName, set);
        } else {
            // query with just name (requires 2 searches)
            args = database.searchCard(cardName);
        }
        args = new String[] { cardName, set };
        return makeCard(player, args);
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
