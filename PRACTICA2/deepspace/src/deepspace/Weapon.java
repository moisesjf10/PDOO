
package deepspace;

class Weapon {
    private String name;
    private WeaponType type;
    private int uses;
    private static final float DEFAULT_POWER=1.0f;
    private static final float MIN_USES=0;
    
    Weapon(String name, WeaponType type, int uses){
        this.name=name;
        this.type=type;
        this.uses=uses;
    }
    
    Weapon(Weapon w){
        this(w.name, w.type, w.uses);
    }
    
    public WeaponType getType(){
        return type;
    }
    
    public int getUses(){
        return uses;
    }
    
    public float power(){
        return type.getPower();
    }
    
    public float useIt(){
        float result;
        if(uses>MIN_USES){
            uses--;
            result=power();
        }else result=DEFAULT_POWER;
        
        return result;
    }
    
    WeaponToUI getUIversion(){
        return (new WeaponToUI(this));
    }
            
    
    @Override
    public String toString (){
        String salida = "[Weapon] -> name: " + name + ", type:" + type +
                        ", uses: " + uses ; 
        
        return salida;
    }
    
}

