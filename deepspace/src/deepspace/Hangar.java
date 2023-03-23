package deepspace;
import java.util.ArrayList;

class Hangar {
    private int maxElements;
    private ArrayList<ShieldBooster> shieldBoosters;
    private ArrayList<Weapon> weapons;
    
    
    Hangar(int capacity){
        maxElements=capacity;
        shieldBoosters=new ArrayList<>();
        weapons=new ArrayList<>();
    }
    
    Hangar(Hangar h){
        this(h.maxElements);
        
        for(ShieldBooster s: h.shieldBoosters)
            addShieldBooster(s);
        
        for(Weapon w: h.weapons)
            addWeapon(w);
    }
    
    HangarToUI getUIVersion(){
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
    
    public ShieldBooster removeShieldBoosters(int s){
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
        throw new UnsupportedOperationException();
    }
    
    
    public ShieldBooster[] getShieldBoosters(){
        throw new UnsupportedOperationException();
    }
    
    public Weapon[] getWeapons(){
        throw new UnsupportedOperationException();
    }
    
    
    public String toString (){
         String salida="[Hangar] -> maxElements: "+ maxElements + 
                ", shieldBoosters: "+ shieldBoosters.toString() +
                ", weapons: "+ weapons.toString();
        return salida;
    }
}
