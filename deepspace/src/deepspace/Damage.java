package deepspace;
import java.util.ArrayList;

class Damage {
   private int nShields; 
   private int nWeapons;
   private ArrayList<WeaponType> weapons;
   
   
   Damage(int w, int s){
       
   }
   
   Damage(WeaponType[] wl, int s){
       
   }
   
   Damage(Damage d){
       
   }
   
   DamageToUI getUIVersion(){
       
   }
   
   private int arrayContainsType(Weapon w[], WeaponType t){
       
   }
   
   public Damage adjust(Weapon w[], ShieldBooster s[]){
       
   }
   
   public void discardWeapon(Weapon w){
       
   }
   
   public void discardShieldBooster(){
       
   }
   
   public boolean hasNoEffect(){
       
   }
   
   public int getNShields(){
       
   }
   
   public int getNWeapons(){
       
   }
   
   public WeaponType[] getWeapons(){
       
   }
   
   public String toString (){
       
   }
}
