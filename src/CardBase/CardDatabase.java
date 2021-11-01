package CardBase;

public class CardDatabase {
    
    private String cardDbPath = "C:/Users/Alex/Downloads/CardDB/";
    
    public CardDatabase(){
        
        
    }
    
    /*
    public List<Message> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Message> messages = new ArrayList<Message>();
        reader.beginArray();
        while (reader.hasNext()) {
            Message message = gson.fromJson(reader, Message.class);
            messages.add(message);
        }
        reader.endArray();
        reader.close();
        return messages;
    }
    */

    public String[] searchCard(String cardName, String set) {
        // allsets- {"artist":"Christopher Moeller","cmc":1,"colorIdentity":["W"],"colors":["White"],"flavor":"The hound sniffed the air and let slip a low growl. General Takeno looked down at the faithful Isamaru and calmed him with a touch. \"Alert the men. The kami are coming.\"","id":"6d08cbcae92c9af4b16b2958165d0a77fca8f58c","imageName":"isamaru, hound of konda","layout":"normal","manaCost":"{W}","mciNumber":"19","multiverseid":79217,"name":"Isamaru, Hound of Konda","number":"19","power":"2","rarity":"Rare","subtypes":["Hound"],"supertypes":["Legendary"],"toughness":"2","type":"Legendary Creature — Hound","types":["Creature"]}
        return null;
    }

    public String[] searchCard(String cardName) {
        // sample text:
        //all cards- "Isamaru, Hound of Konda":{"layout":"normal","name":"Isamaru, Hound of Konda","manaCost":"{W}","cmc":1,"colors":["White"],"type":"Legendary Creature — Hound","supertypes":["Legendary"],"types":["Creature"],"subtypes":["Hound"],"mciNumber":"19","power":"2","toughness":"2","imageName":"isamaru, hound of konda","colorIdentity":["W"]}
        //all cards x- "Isamaru, Hound of Konda":{"layout":"normal","name":"Isamaru, Hound of Konda","manaCost":"{W}","cmc":1,"colors":["White"],"type":"Legendary Creature — Hound","supertypes":["Legendary"],"types":["Creature"],"subtypes":["Hound"],"mciNumber":"19","power":"2","toughness":"2","imageName":"isamaru, hound of konda","printings":["CHK"],"legalities":[{"format":"Commander","legality":"Legal"},{"format":"Kamigawa Block","legality":"Legal"},{"format":"Legacy","legality":"Legal"},{"format":"Modern","legality":"Legal"},{"format":"Vintage","legality":"Legal"}],"colorIdentity":["W"]}
        // TODO Auto-generated method stub
        return null;
    }
    
}
