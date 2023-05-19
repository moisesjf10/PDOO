
package deepspace;

public class ShieldBooster implements CombatElement{
    private String name;
    private float boost;
    private int uses;
    private static final float DEFAULT_BOOST = 1.0f;
    private static final int MIN_USES= 0;
    
    ShieldBooster (String name, float boost, int uses){
        this.name = name;
        this.boost = boost;
        this.uses = uses;
    }
    
    ShieldBooster (ShieldBooster s){
        this(s.name,s.boost,s.uses);        
    }
    
    public float getBoost(){
        return boost;
    }
    
    @Override
    public int getUses(){
        return uses;
    }
    @Override
    public float useIt(){
        float aux;
        if (uses > MIN_USES){
            uses += -1;
            aux = boost;
        }
        else {
            aux = DEFAULT_BOOST;
        }
        
        return aux;
    }
    
    @Override
    public String toString(){
        String salida="[ShieldBooster] -> name: " + name + ", boost: " + boost
                + ", uses: " + uses;
        return salida; 
    }
    
    ShieldToUI getUIversion(){
        return (new ShieldToUI(this));
    }
}

