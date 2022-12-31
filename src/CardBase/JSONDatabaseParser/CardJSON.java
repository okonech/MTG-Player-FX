package CardBase.JSONDatabaseParser;

import java.util.ArrayList;

public class CardJSON {
    public String artist;
    public float cmc;
    public ArrayList<String> colorIdentity;
    public ArrayList<String> colors;
    // uuid
    public String id;
    // normal, flip, split
    public String layout;
    public String manaCost;
    public String mciNumber;
    public String multiverseid;
    public String name;
    // printing number in set
    public String number;
    public String power;
    public ArrayList<String> subtypes;
    public ArrayList<String> supertypes;
    public String toughness;
    public String text;
    // printable type
    public String type;
    public ArrayList<String> types;
    // not initialized in sets load
    public SetJSON set;
    
    public CardJSON(){
        this.colorIdentity = new ArrayList<String>();
        this.colors = new ArrayList<String>();
        this.subtypes = new ArrayList<String>();
        this.supertypes = new ArrayList<String>();
        this.types = new ArrayList<String>();
        
    }
    
    public void addSet(SetJSON set){
        this.set=set;
    }


}
