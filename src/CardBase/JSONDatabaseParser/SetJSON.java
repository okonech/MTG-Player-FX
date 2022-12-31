package CardBase.JSONDatabaseParser;

import java.util.ArrayList;

public class SetJSON {
    public String name;
    public String code;
    public String gathererCode;
    public String magicCardsInfoCode;
    public String border;
    public ArrayList<CardJSON> cards;
    
    public SetJSON(){
        this.cards = new ArrayList<CardJSON>();
        
    }

}
