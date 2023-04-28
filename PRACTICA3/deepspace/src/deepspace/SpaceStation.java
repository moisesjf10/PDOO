package deepspace;
import java.util.ArrayList;
import java.util.Iterator;

public class SpaceStation {
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
        if(pendingDamage!=null){
            if(pendingDamage.hasNoEffect())
                pendingDamage = null;
        }
       
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
        int size = shieldBoosters.size();
        if (i>= 0 && i < size){
            ShieldBooster sh = new ShieldBooster(shieldBoosters.remove(i));
            if(pendingDamage != null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }
    public void discardShieldBoosterInHangar(int i){
        if(hangar != null){
            hangar.removeShieldBooster(i);
        }
    }
    public void discardWeapon(int i){
        int size = weapons.size();
        if (i>= 0 && i < size){
            Weapon w = new Weapon(weapons.remove(i));
            if(pendingDamage != null){
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
        }
    }
    public void discardWeaponInHangar(int i){
        if (hangar != null){
            hangar.removeWeapon(i);
        }
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
        float speed;
        if (MAXFUEL == 0){
            speed=0;
        }else{
            speed = fuelUnits/MAXFUEL;
        }
        return speed;
    }
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    public void mountShieldBooster(int i){
        if (hangar != null){
            if(i >= 0 && i < hangar.getShieldBoosters().size()){
                ShieldBooster s = new ShieldBooster(hangar.removeShieldBooster(i));
                if (s != null){
                    shieldBoosters.add(s);
                }
            }
        }
    }
    public void mountWeapon(int i){
        if (hangar != null){
            if(i >= 0 && i < hangar.getWeapons().size()){
                Weapon w = new Weapon(hangar.removeWeapon(i));
                if (w != null){
                    weapons.add(w);
                }
               
            }        
        }

    }   
    
    public void move(){
        fuelUnits-=fuelUnits*getSpeed();
        if (fuelUnits<0){
            fuelUnits = 0;
        }
    }
    
    public float protection(){
        float factor = 1;
        for(ShieldBooster s: shieldBoosters){
            factor*=s.useIt();
        }
        return shieldPower*factor;
    }
    
    public float fire(){
        float factor = 1;
        for(Weapon w: weapons){
            factor*=w.useIt();
        }
        return ammoPower*factor;
    }
    
    public void receiveHangar(Hangar h){
        if (hangar == null)
            hangar = h;

    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        boolean resultado = false;
        if (hangar != null){
           resultado = hangar.addShieldBooster(s) ;
        }
        return resultado;
    }
    
    public ShotResult receiveShot(float shot){
        float myProtection = protection();
        ShotResult resultado = ShotResult.DONOTRESIST;
        if(myProtection >= shot){
            shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
            shieldPower = Math.max(0.0f, shieldPower);
            resultado = ShotResult.RESIST;
        }else{
            shieldPower = 0.0f;
        }
        return resultado;
    }
    
    public void receiveSupplies(SuppliesPackage s){
        ammoPower += s.getAmmoPower();
        assingFuelValue(fuelUnits+s.getFuelUnits());
        shieldPower += s.getShieldPower();
    }
    
    public boolean receiveWeapon(Weapon w){
        boolean resultado = false;
        if(hangar != null){
            resultado = hangar.addWeapon(w);
            
        }
        return resultado;
        
    }
    
    public void setLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();
        int h = loot.getNHangars();
        if (h>0){
            Hangar hangar = dealer.nextHangar();
            receiveHangar(hangar);
        }
        int elements = loot.getNSupplies();
        for (int i = 0; i < elements; i++ ) {
            SuppliesPackage sup = new SuppliesPackage(dealer.nextSuppliesPackage());
            receiveSupplies(sup);
        }
        elements = loot.getNWeapons();
        for(int i = 0; i < elements; i++){
            Weapon weap = new Weapon(dealer.nextWeapon());
            receiveWeapon(weap);
        }
        elements = loot.getNShields();
        for(int i = 0; i < elements; i++){
            ShieldBooster sh = new ShieldBooster(dealer.nextShieldBooster());
            receiveShieldBooster(sh);
        }
        nMedals += loot.getNMedals();
    }
    
    public void setPendingDamage(Damage d){
        if(d!=null)
            pendingDamage=d.adjust(weapons, shieldBoosters);
        
    }
    public boolean validState(){
        boolean resultado = false;
        if (pendingDamage==null || pendingDamage.hasNoEffect()){
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
