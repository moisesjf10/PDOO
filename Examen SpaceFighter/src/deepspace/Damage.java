package deepspace;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Damage {
   private int nShields;
   
   Damage(int s){
       nShields=s;
   }

   public abstract Damage copy();

   public abstract Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);

   public abstract void discardWeapon(Weapon w);

   public abstract boolean hasNoEffect();

   public abstract String toString();

    public void discardShieldBooster(){
        if(nShields>0) nShields--;
    }

    public int getNShields(){
        return nShields;
    }

    public abstract DamageToUI getUIversion();

}
