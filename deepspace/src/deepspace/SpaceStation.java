package deepspace;
import java.util.ArrayList;

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
        if (MAXFUEL > f) {
            fuelUnits = f;
        }
    }
    
    private void cleanPendingDamage(){
        pendingDamage = null;
    }
    
    SpaceStation(String n, SuppliesPackage supplies){
        name = n;
        ammoPower = supplies.getAmmoPower();
        fuelUnits = supplies.getFuelUnits();
        shieldPower = supplies.getShieldPower();
        nMedals =0;
        weapons = new ArrayList<Weapon>;
        shieldBoosters = new ArrayList<ShieldBooster>;
        hangar= null;
    }
    
    public void cleanUpMountedItems(){
        
    }
            
    public void discardHangar(){
       hangar = null;
    }
          
    public void discardShieldBooster(int i){

    }
    public void discardShieldBoosterInHangar(int i){
    
    }
    public void discardWeapon(int i){
    
    }
    public void discardWeaponInHangar(int i){
        if (hangar != null){
            hangar.removeWeapon(i);
        }
    }
    public float fire(){
    
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
    
    }
    public void receiveHangar(Hangar h){
        if (hangar != null)
            hangar = h;

    }
    public boolean receiveShieldBooster(ShieldBooster s){
        boolean resultado = false;
        if (hangar != null){
            if (shieldBoosters.add(s) != null)
            resultado = true ;
        }
        return resultado;
    }
    public ShotResult receiveShot(float shot){
    
    }
    public void receiveSupplies(SuppliesPackage s){
    
    }
    public boolean receiveWeapon(Weapon w){
    
    }
    public void setLoot(Loot loot){
    
    }
    public void setPendingDamage(Damage d){
    
    }
    public boolean validState(){
    
    }
    
    public String toString (){
        
    }
    
    
}
