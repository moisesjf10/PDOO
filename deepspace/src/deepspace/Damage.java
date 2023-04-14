package deepspace;
import java.util.ArrayList;
import java.util.Iterator;

public class Damage {
   private int nWeapons;
   private int nShields;
   private ArrayList<WeaponType> weapons;
   private static final int NO_USE=-1;
   
   private Damage(int w, int s, ArrayList<WeaponType> wl){
       nWeapons=w;
       nShields=s;
       weapons=wl;
   }
   
   Damage(int w, int s){
       this(w,s,null);
   }
   
   Damage(ArrayList<WeaponType> wl, int s){
       this(NO_USE,s,wl);
   }
   
   Damage(Damage d){
      this(d.nWeapons, d.nShields,d.weapons);
   }
   
   DamageToUI getUIversion(){
       return (new DamageToUI(this));
   }
   
   //Devuelve el indice de la posicion de la primera arma de la coleccion de armas cuyo tipo coincida con el segundo parametro
   private int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
       Iterator<Weapon> it=w.iterator();
       boolean encontrado=false;
       int pos=-1;
       int i=0;
       while(it.hasNext() && !encontrado){
           if(it.next().getType() == t){
               pos=i;
               encontrado=true;
           }
           i++;
           //puede faltar un next (aunque creo que está correcto no se como van las ED's en JAVA)
       }

       return pos;
   }
   
   public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
       int nshields=Integer.min(nShields, s.size());
       Damage d;
       if(weapons == null){
           int nweapon=Integer.min(nWeapons, w.size());
           d=new Damage(nweapon,nshields);
       }else{
           ArrayList<Weapon> aux = new ArrayList<Weapon>(w);
           ArrayList<WeaponType> typeaux = new ArrayList<WeaponType>();
           
           for(WeaponType i: weapons){
                int index = arrayContainsType(aux, i);
          
                if(index != -1){
                    typeaux.add(i);
                    aux.remove(index);
                }
           }
           
           d=new Damage(typeaux,nshields);
           
       }
       
       return d;


   }
   
   public void discardWeapon(Weapon w){
       if(nWeapons==NO_USE){
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

       if(nWeapons==NO_USE) effect = (weapons.size() + nShields == 0);
       else effect = (nWeapons + nShields == 0);

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
   
   public String toString (){
        String salida;
        if(nWeapons==NO_USE){
            salida = "[Damage] -> weapons: " + getWeapons().toString() +
                    ", nShields: " + nShields;
        }else {
            salida = "[Damage] -> nWeapons: " + nWeapons +
                    ", nShields: " + nShields;
        }
        return salida;
   }
}
