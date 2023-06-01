package deepspace;

import java.util.ArrayList;

public class NumericDamage extends Damage{
    int nWeapons;

    public NumericDamage(int nweapons, int nshields){
        super(nshields);
        nWeapons=nweapons;
    }

    @Override
    public NumericDamage copy(){
        return new NumericDamage(nWeapons,getNShields());
    }

    public int getNWeapons(){
        return nWeapons;
    }

    @Override
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        int nshields=Integer.min(getNShields(), s.size());
        int nweapons=Integer.min(nWeapons, w.size());

        return new NumericDamage(nweapons,nshields);

    }

    @Override
    public void discardWeapon(Weapon w){
        if(nWeapons>0) nWeapons--;
    }

    @Override
    public boolean hasNoEffect(){
        return (nWeapons + getNShields() == 0);
    }

    @Override
    public String toString(){
        String salida = "[Damage] -> nWeapons: " + nWeapons +
                ", nShields: " + getNShields();

        return salida;
    }


    @Override
    public NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }


}