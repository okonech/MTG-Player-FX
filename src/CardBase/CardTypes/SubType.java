package CardBase.CardTypes;

public enum SubType {
    
// get list from somewhere later
    DEFAULT("Default type"),
    SLIVER("Sliver"),
    BEAST("Beast");
    
    final String subType;
    
    private SubType(String subType){
        this.subType=subType;
    }
}
