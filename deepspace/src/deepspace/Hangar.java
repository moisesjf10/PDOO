package deepspace;
import java.util.ArrayList;

class Hangar {
    private int maxElements;
    private ArrayList<ShieldBooster> shieldBoosters;
    private ArrayList<Weapon> weapons;
    
    
    Hangar(int capacity){
        maxElements=capacity;
        shieldBoosters=new ArrayList<ShieldBooster>();
        weapons=new ArrayList<Weapon>();
    }
    
    Hangar(Hangar h){
        this(h.maxElements);
        
        for(ShieldBooster s: h.shieldBoosters)
            addShieldBooster(s);
        
        for(Weapon w: h.weapons)
            addWeapon(w);
    }
    
    HangarToUI getUIversion(){
        return (new HangarToUI(this));
    }
    
    private boolean spaceAvailable(){
        return (shieldBoosters.size()+weapons.size()) < maxElements;
    }
    
    public boolean addWeapon(Weapon w){
        boolean insertar=true;
        if(spaceAvailable()) weapons.add(w);
        else insertar=false;
        
        return insertar;
    }
    
    public boolean addShieldBooster(ShieldBooster s){
        boolean insertar=true;
        if(spaceAvailable()) shieldBoosters.add(s);
        else insertar=false;
        
        return insertar;
    }
    
    public ShieldBooster removeShieldBooster(int s){
        ShieldBooster shield;
        if(s>=0 && s<shieldBoosters.size())
            shield=shieldBoosters.remove(s);
        
        else shield=null;
        
        return shield;
    }
    
    public Weapon removeWeapon(int w){
        Weapon weapon;
        if(w>=0 && w<weapons.size())
            weapon=weapons.remove(w);
        
        else weapon=null;
        
        return weapon;
    }
    
    public int getMaxElements(){
        return maxElements;
    }
    
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    
    public String toString (){
        String salida="[Hangar] -> maxElements: "+ maxElements + 
                ", shieldBoosters: "+ shieldBoosters.toString() +
                ", weapons: "+ weapons.toString();
        return salida;
    }
}
