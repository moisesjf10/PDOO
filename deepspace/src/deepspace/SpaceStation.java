package deepspace;
import java.util.ArrayList;
import java.util.Iterator;

class SpaceStation {
    private static final int MAXFUEL = 100;
    private static final float SHIELDLOSSPERUNITSHOT = 0.1f;
    private float ammoPower;
    private float fuelUnits ;
    private String name; 
    private int nMedals;
    private float shieldPower;
    private Damage pendingDamage;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;
    private Hangar hangar;
    
    private void assingFuelValue(float f){
        if (f < MAXFUEL) {
            fuelUnits = f;
        }else fuelUnits=MAXFUEL;
    }
    
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoEffect())
            pendingDamage = null;
    }
    
    SpaceStation(String n, SuppliesPackage supplies){
        name = n;
        ammoPower = 0;
        fuelUnits = 0;
        shieldPower = 0;
        this.receiveSupplies(supplies);
        nMedals =0;
        weapons = new ArrayList<Weapon>();
        shieldBoosters = new ArrayList<ShieldBooster>();
        hangar= null;
        pendingDamage=null;
    }
    
    public void cleanUpMountedItems(){
        Iterator<Weapon> i = weapons.iterator();
        while(i.hasNext()){
            if(i.next().getUses() == 0){
                i.remove();
            }
        }
        Iterator<ShieldBooster> j = shieldBoosters.iterator();
        while(j.hasNext()){
            if(j.next().getUses() == 0){
                j.remove();
            }
        }
    }
            
    public void discardHangar(){
       hangar = null;
    }
          
    public void discardShieldBooster(int i){
        throw new UnsupportedOperationException();
    }
    public void discardShieldBoosterInHangar(int i){
        if(hangar != null){
            hangar.removeShieldBoosters(i);
        }
    }
    public void discardWeapon(int i){
        throw new UnsupportedOperationException();
    }
    public void discardWeaponInHangar(int i){
        if (hangar != null){
            hangar.removeWeapon(i);
        }
    }
    public float fire(){
        throw new UnsupportedOperationException();
    }
    public float getAmmoPower(){
        return ammoPower;
    }
    public float getFuelUnits(){
        return fuelUnits;
    }
    public Hangar getHangar(){
        return hangar;
    }
    public String getName(){
        return name;
    }
    public int getNMedals(){
        return nMedals;
    }
    public Damage getPendingDamage(){
        return pendingDamage;
    }
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    public float getShieldPower(){
        return shieldPower;
    }
    public float getSpeed(){
        return (fuelUnits/MAXFUEL);
    }
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    public void mountShieldBooster(int i){
        if (hangar != null){
            ShieldBooster boost = hangar.removeShieldBoosters(i);
            if (boost != null){
                shieldBoosters.add(boost);
            }
        }

    }
    public void mountWeapon(int i){
        if (hangar != null){
            Weapon w = hangar.removeWeapon(i);
            if (w != null){
                weapons.add(w);
            }
        }

    }   
    
    public void move(){
        if (fuelUnits - getSpeed() > 0){
            fuelUnits = fuelUnits - getSpeed();
        }
    }
    
    public float protection(){
        throw new UnsupportedOperationException();
    }
    
    public void receiveHangar(Hangar h){
        if (hangar != null)
            hangar = h;

    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        boolean resultado = false;
        if (hangar != null){
            if (shieldBoosters.add(s) != false)
            resultado = true ;
        }
        return resultado;
    }
    
    public ShotResult receiveShot(float shot){
        throw new UnsupportedOperationException();
    }
    public void receiveSupplies(SuppliesPackage s){
        ammoPower += s.getAmmoPower();
        fuelUnits += s.getFuelUnits();
        shieldPower += s.getShieldPower();
    }
    public boolean receiveWeapon(Weapon w){
        boolean resultado = false;
        if(hangar != null){
            if(weapons.add(w) != false){
                resultado = true;
            }
        }
        return resultado;
        
    }
    public void setLoot(Loot loot){
        throw new UnsupportedOperationException();
    }
    public void setPendingDamage(Damage d){
        d.adjust(weapons, shieldBoosters);
        
    }
    public boolean validState(){
        boolean resultado = false;
        if (pendingDamage.hasNoEffect()){
            resultado = true;
        }
        return resultado;
    }
    
    public String toString (){
        String salida="[SpaceStation] -> ammoPower: "+ ammoPower + 
                ", fuelUnits: "+ fuelUnits +
                ", name: "+ name +
                ", nMedals: "+ nMedals +
                ", shieldPower: "+ shieldPower +
                ", pendingDamage: "+ pendingDamage.toString() +
                ", weapons: "+ weapons.toString() +
                ", shieldBoosters: "+ shieldBoosters.toString() +
                ", hangar: "+ hangar.toString();
        return salida;
    }
    
    
}
