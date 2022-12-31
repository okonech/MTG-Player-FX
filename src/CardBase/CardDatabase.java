package CardBase;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import CardBase.JSONDatabaseParser.CardJSON;
import CardBase.JSONDatabaseParser.SetJSON;

public class CardDatabase {
    
    private String cardDbPath = "F:/Games/PlayerFx/Database/AllSets.json";
    private Map<String, SetJSON> setDb;
    private HashMap<String,HashMap<String,CardJSON>> cardDb;
    
    // load serialized version if available, if not load raw json
    
    public CardDatabase(String path) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(cardDbPath));
        Gson gson = new Gson();
        Type mapOfMapsType = new TypeToken<Map<String, SetJSON>>() {}.getType();
        setDb = gson.fromJson(reader, mapOfMapsType);
        
        // put data into hashmap
        cardDb = new HashMap<String,HashMap<String,CardJSON>>();
        
        for(String set: setDb.keySet()){
            ArrayList<CardJSON> cards = setDb.get(set).cards;
            for(CardJSON card:cards){
                card.addSet(setDb.get(set));
                if(cardDb.get(card.name)==null){
                    // new set list
                    HashMap<String,CardJSON> cardSets = new HashMap<String,CardJSON>();
                    cardSets.put(set, card);
                    cardDb.put(card.name, cardSets);
                }else {
                    // existing set list
                    cardDb.get(card.name).put(set, card);
                }
                
                // decide how to store for both card and card+Set searches
                // while leaving card uniqueness by set
                // how to get first set found for card?
            }
        }
         
    }
    

//    public List<Message> readJsonStream(InputStream in) throws IOException {
//        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
//        List<Message> messages = new ArrayList<Message>();
//        reader.beginArray();
//        while (reader.hasNext()) {
//            Message message = gson.fromJson(reader, Message.class);
//            messages.add(message);
//        }
//        reader.endArray();
//        reader.close();
//        return messages;
//    }


    public CardJSON searchCard(String cardName, String set) {
        CardJSON card = cardDb.get(cardName).get(set);
        if(card==null){
            return searchCard(cardName);
        } else{
            return card;
        }
    }

    public CardJSON searchCard(String cardName) {
        return cardDb.get(cardName).values().iterator().next();
    }
    
}
