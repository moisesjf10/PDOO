package practica1;


class Weapon {
    private String name;
    private WeaponType type;
    private int uses;
    private static final float DEFAULT_POWER=1.0f;
    
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
        if(uses>0){
            uses--;
            result=power();
        }else result=DEFAULT_POWER;
        
        return result;
    }
    
    
}
