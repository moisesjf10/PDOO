package deepspace;
import java.util.ArrayList;

public class TestP2 {
    public static void main(String[] args) {
        /*
        Weapon aux = new Weapon("primera",WeaponType.LASER,30);
        Weapon aux2 = new Weapon("segunda",WeaponType.PLASMA,30);
        Weapon aux3 = new Weapon("tercera",WeaponType.MISSILE,30);
        ShieldBooster s1 = new ShieldBooster("primera", 10, 30);
        ShieldBooster s2 = new ShieldBooster("segunda", 20, 30);
        ShieldBooster s3 = new ShieldBooster("tercera", 30, 30);

        // Probamos la clase Hangar
        System.out.println("Probamos la clase Hangar \n");
        int capacidad=10;
        Hangar hangar = new Hangar(capacidad);



        hangar.addWeapon(aux);
        hangar.addWeapon(aux2);
        hangar.addWeapon(aux3);
        hangar.addShieldBooster(s1);
        hangar.addShieldBooster(s2);
        hangar.addShieldBooster(s3);

        Hangar hangar2 = new Hangar(hangar);

        Weapon borrado=hangar2.removeWeapon(0);
        ShieldBooster borrado2 = hangar2.removeShieldBooster(0);

        System.out.println(borrado.toString());
        System.out.println("\n");
        System.out.println(borrado2.toString());
        System.out.println("\n");

        ArrayList<Weapon> w = hangar2.getWeapons();
        ArrayList<ShieldBooster> s = hangar2.getShieldBoosters();

        for(Weapon arma: w){
            System.out.println(arma.toString());
            System.out.println("\n");
        }

        for(ShieldBooster escudo: s){
            System.out.println(escudo.toString());
            System.out.println("\n");
        }
        System.out.println("Elementos " + hangar.getMaxElements());*/
        System.out.println("Probando clase Damage");
        Damage damage = new Damage(15, 20);
        System.out.println(damage.getNShields());
        System.out.println(damage.getNWeapons());
        System.out.println(damage.getWeapons());
        System.out.println(damage.toString());
        Weapon arma1 = new Weapon("laser", WeaponType.LASER, 8);
        Weapon arma2 = new Weapon("misil", WeaponType.MISSILE, 10);
        Weapon arma3 = new Weapon("plasma", WeaponType.PLASMA, 15);
        ArrayList<Weapon> armas = new ArrayList<>();
        armas.add(arma1);
        armas.add(arma2);
        armas.add(arma3);

        ArrayList<WeaponType> arma = new ArrayList<>();
        arma.add(arma1.getType());
        arma.add(arma2.getType());
        Damage damage2 = new Damage(arma, 10);
        ShieldBooster escudo = new ShieldBooster("name", 2, 2);
        ArrayList<ShieldBooster> escudos = new ArrayList<>();
        escudos.add(escudo);
        System.out.println(damage2.getNShields());
        System.out.println(damage2.getNWeapons());
        System.out.println(damage2.getWeapons());
        Damage damage3 = new Damage(damage2);
        System.out.println(damage3.toString());
        System.out.println(damage.hasNoEffect());
        Damage damage4 = new Damage(damage2.adjust(armas, escudos));
        System.out.println("-----------");
        System.out.println(damage2.toString());
        System.out.println(damage4.toString());
        System.out.println("-----------");
        System.out.println(damage.getNWeapons());
        damage.discardWeapon(arma1);
        System.out.println(damage.getNWeapons());
        System.out.println(damage2.getWeapons());
        damage2.discardWeapon(arma1);
        System.out.println(damage2.getWeapons());
        System.out.println(damage.getNShields());
        damage.discardShieldBooster();
        System.out.println(damage.getNShields());


        //Prueba del adjust
        ArrayList<WeaponType> armas2 = new ArrayList<>();
        armas2.add(arma1.getType());
        armas2.add(arma2.getType());
        ArrayList<WeaponType> armas3 = new ArrayList<>();
        armas3.add(arma1.getType());
        armas3.add(arma2.getType());
        armas3.add(arma3.getType());
        armas3.add(arma1.getType());
        ArrayList<ShieldBooster> escudos2 = new ArrayList<>();
        escudos2.add(escudo);

        //Más armas en damage que en los argumentos.

        Damage damage5 = new Damage(armas2,4);

        Damage damage6 = new Damage(armas3,1);

        Damage damage7 = new Damage(0,0);

        Damage damage8 = new Damage(0,5);

        System.out.println(damage5.adjust(armas,escudos2).toString());
        System.out.println(damage6.adjust(armas,escudos2).toString());
        System.out.println(damage7.adjust(armas,escudos2).toString());
        System.out.println(damage8.adjust(armas,escudos2).toString());







    }
}
