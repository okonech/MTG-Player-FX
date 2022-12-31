package CardBase.OracleTextParser;

import java.util.Set;

import CardBase.Card;
import CardBase.CardTypes.SuperType;

public class CardParser {
    // all cards go through here and go to appropriate parsers
    
    public static void ParseOracleText(Card card){
        // put cards through to appropriate parsers based on types
        Set<SuperType> superTypes = card.getSuperTypes();
        // handle instants and sorceries in nonpermanent parser
        
        if(superTypes.contains(SuperType.SORCERY)){
            NonPermanentParser.ParseNonPermanentOracleText(card);
            
        } else if(superTypes.contains(SuperType.INSTANT)){
            NonPermanentParser.ParseNonPermanentOracleText(card);
            
        } else {
            PermanentParser.ParsePermanentOracleText(card);
        }
    }

}
