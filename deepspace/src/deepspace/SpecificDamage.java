package deepspace;
import java.util.ArrayList;
import java.util.Iterator;

public class SpecificDamage extends Damage {
    private ArrayList<WeaponType> weapons;

    public SpecificDamage(ArrayList<WeaponType> w, int nshields) {
        super(nshields);
        if (w != null) {
            weapons = new ArrayList<>(w);
        } else {
            weapons = new ArrayList<>();
        }

    }

    @Override
    public SpecificDamage copy() {
        return new SpecificDamage(weapons, getNShields());
    }

    private int arrayContainsType(ArrayList<Weapon> w, WeaponType t) {
        Iterator<Weapon> it = w.iterator();
        boolean encontrado = false;
        int pos = -1;
        int i = 0;
        while (it.hasNext() && !encontrado) {
            if (it.next().getType() == t) {
                pos = i;
                encontrado = true;
            }
            i++;
        }

        return pos;
    }

    @Override
    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int nshields = Integer.min(getNShields(), s.size());
        ArrayList<Weapon> aux = new ArrayList<Weapon>(w);
        ArrayList<WeaponType> typeaux = new ArrayList<WeaponType>();

        for (WeaponType i : weapons) {
            int index = arrayContainsType(aux, i);

            if (index != -1) {
                typeaux.add(i);
                aux.remove(index);
            }
        }

        return new SpecificDamage(typeaux, nshields);

    }

    public ArrayList<WeaponType> getWeapons() {
        ArrayList<WeaponType> w;
        if (weapons != null) {
            w = new ArrayList<>(weapons);
        } else {
            w = new ArrayList<>();
        }
        return w;
    }

    @Override
    public void discardWeapon(Weapon w) {
        if (!weapons.isEmpty()) {
            weapons.remove(w.getType());
        }
    }

    @Override
    public boolean hasNoEffect() {
        boolean hasnoeffect;
        if (weapons != null)
            hasnoeffect = weapons.isEmpty() && getNShields() == 0;
        else
            hasnoeffect = getNShields() == 0;

        return hasnoeffect;
    }

    @Override
    public SpecificDamageToUI getUIversion() {
        return new SpecificDamageToUI(this);
    }

    @Override
    public String toString() {
        String salida = "[Damage] -> weapons: " + weapons +
                ", nShields: " + getNShields();

        return salida;

    }
}