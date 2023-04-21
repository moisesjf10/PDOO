package deepspace;

public class testp3 {
    public static void main(String[] args) {
        SuppliesPackage supplies = new SuppliesPackage(1, 2, 3);
        SpaceStation space = new SpaceStation("prueba", supplies);

        Hangar hangar = new Hangar(100);

        space.receiveHangar(hangar);

        Weapon weapon1 = new Weapon("arma1",WeaponType.LASER,5);
        Weapon weapon2 = new Weapon("arma2",WeaponType.LASER,8);
        Weapon weapon3 = new Weapon("arma3",WeaponType.PLASMA,8);
        Weapon weapon4 = new Weapon("arma4",WeaponType.PLASMA,4);
        Weapon weapon5 = new Weapon("arma5",WeaponType.MISSILE,8);
        Weapon weapon6 = new Weapon("arma6",WeaponType.MISSILE,5);

        ShieldBooster escudo1 = new ShieldBooster("escudo1", 1, 1);
        ShieldBooster escudo2 = new ShieldBooster("Escudo ACME",1,2);
        ShieldBooster escudo3 =new ShieldBooster("Escudo ACME",1,2);
        ShieldBooster escudo4 =new ShieldBooster("Escudo normal",3.0f,2);


        space.receiveWeapon(weapon1);
        space.receiveWeapon(weapon2);
        space.receiveWeapon(weapon3);
        space.receiveWeapon(weapon4);
        space.receiveWeapon(weapon5);
        space.receiveWeapon(weapon6);

        space.receiveShieldBooster(escudo1);
        space.receiveShieldBooster(escudo2);
        space.receiveShieldBooster(escudo3);
        space.receiveShieldBooster(escudo4);

        float fire = space.fire();

        float protection = space.protection();

        System.out.println("fuego: " + fire + "\n");

        System.out.println("proteccion: " + protection + "\n");

    }
}
