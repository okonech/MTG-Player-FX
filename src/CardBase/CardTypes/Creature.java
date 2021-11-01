package CardBase.CardTypes;

import CardBase.Card;

public class Creature extends Card{
    private int power;
    private int powerDefault;
    private int toughness;
    private int toughnessDefault;
    
    public Creature() {
        // base card constructor call
        super();
        this.power=2;
        this.powerDefault=2;
        this.toughness=3;
        this.toughnessDefault=3;
    }
    
    public int getPower(){
        return power;
    }
    
    public int getToughness(){
        return toughness;
    }
    
    public void setPower(int power){
        this.power=power;
    }
    
    public void setToughness(int toughness) {
        this.toughness=toughness;
    }
    
    public void resetPT(){
        power=powerDefault;
        toughness=toughnessDefault;
    }
    
    @Override
    public String testOut() {
        return "Creature";
    }
    

}
