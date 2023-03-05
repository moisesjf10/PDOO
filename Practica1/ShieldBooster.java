/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica1;

/**
 *
 * @author manuel
 */
class ShieldBooster {
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
    
    public String getName(){
        return name;
    }
    
    public float getBoost(){
        return boost;
    }
    
    public int getUses(){
        return uses;
    }
    
    public float useIt(){
        float aux;
        if (uses > MIN_USES){
            uses=+ -1;
            aux = boost;
        }
        else {
            aux = DEFAULT_BOOST;
        }
        
        return aux;
    }
}
