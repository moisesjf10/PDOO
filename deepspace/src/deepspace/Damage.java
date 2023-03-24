package deepspace;
import java.util.ArrayList;
import java.util.Iterator;

class Damage {
   private int nWeapons;
   private int nShields;
   private ArrayList<WeaponType> weapons;
   
   
   Damage(int w, int s){
       nWeapons=w;
       nShields=s;
       weapons=new ArrayList<WeaponType>();
   }
   
   Damage(ArrayList<WeaponType> wl, int s){
       weapons = wl;
       nShields=s;
       nWeapons=-1;
   }
   
   Damage(Damage d){
      this(d.nWeapons, d.nShields);
      weapons=d.weapons;
   }
   
   DamageToUI getUIVersion(){
       return (new DamageToUI(this));
   }
   
   private int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
       Iterator<Weapon> it=w.iterator();
       boolean encontrado=false;
       int pos;
       int i=0;
       while(it.hasNext()){
           if(it.next().getType() == t){
               pos=i;
               encontrado=true;
           }
           i++;
           it.next();
       }
       if(!encontrado) pos=-1;

       return pos;
   }
   
   public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
       
   }
   
   public void discardWeapon(Weapon w){
       if(nWeapons==-1){
            if(!weapons.isEmpty()){
                weapons.remove(w.getType());
            }
       }else{
           if(nWeapons>0) nWeapons--;
       }
   }
   
   public void discardShieldBooster(){
       if(nShields>0) nShields--;
   }
   
   public boolean hasNoEffect(){
       boolean effect;

       if(nWeapons==-1) effect=(weapons.size() + nShields == 0);
       else effect= (nWeapons + nShields == 0);

       return effect;
   }
   
   public int getNShields(){
       return nShields;
   }
   
   public int getNWeapons(){
       return nWeapons;
   }

   public ArrayList<WeaponType> getWeapons(){
       return weapons;
    }
   
   public ArrayList<WeaponType> getWeapons(){
       return weapons;
   }
   
   public String toString (){
        String salida;
        if(nWeapons==-1){
            salida = "[Damage] -> weapons: " + getWeapons().toString() +
                    ", nShields: " + nShields;
        }else {
            salida = "[Damage] -> nWeapons: " + nWeapons +
                    ", nShields: " + nShields;
        }
        return salida;
   }
}
