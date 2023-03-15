package deepspace;

public class TestP1 {
    public static void main(String[] args) {
        
        System.out.println("\t Elementos del tipo numerado WeaponType:");
        WeaponType laser = WeaponType.LASER;
        WeaponType missile = WeaponType.MISSILE;
        WeaponType plasma = WeaponType.PLASMA;
        
        System.out.println(plasma.getPower());
        System.out.println(missile.getPower());
        System.out.println(laser.getPower());
        System.out.println("\n");
        
        
        //
        //
        
        Loot loot = new Loot(1,1,1,1,1);
        System.out.println("\t Elementos de la clase Loot:");
        System.out.println(loot.getNSupplies());
        System.out.println(loot.getNWeapons());
        System.out.println(loot.getNShields());
        System.out.println(loot.getNHangars());
        System.out.println(loot.getNMedals());
        System.out.println("\n");
        
        //
        //
        
        SuppliesPackage suppliespackage = new SuppliesPackage (1.43f,2.34f,5.76f);
        System.out.println("\t Elementos de la clase SuppliesPackage:");
        System.out.println(suppliespackage.getAmmoPower()) ;
        System.out.println(suppliespackage.getFuelUnits()) ;
        System.out.println(suppliespackage.getShieldPower()) ;
        
        System.out.println("\n");
        
        SuppliesPackage other = new SuppliesPackage(suppliespackage);
        System.out.println(other.getAmmoPower());
        System.out.println(other.getFuelUnits());
        System.out.println(other.getShieldPower());
        
        System.out.println("\n");
        
        //
        //
        
        ShieldBooster shieldbooster = new ShieldBooster("nave",3.26f,26);
        System.out.println("\t Elementos de la clase ShieldBooster:");
        System.out.println(shieldbooster.getBoost());
        System.out.println(shieldbooster.getUses());
        System.out.println(shieldbooster.useIt());
        
        ShieldBooster otro = new ShieldBooster(shieldbooster);
        
        System.out.println(otro.getBoost());
        System.out.println(otro.getUses());
        System.out.println(otro.useIt());
        
        
        System.out.println("\n");
        
        //
        //
        
        Weapon weapon = new Weapon ("arma",laser, 40);
        System.out.println("\t Elementos de la clase Weapon:");
        System.out.println(weapon.getType());
        System.out.println(weapon.getUses());
        System.out.println(weapon.power());
        System.out.println(weapon.useIt());
        
        Weapon w = new Weapon(weapon);
        
        System.out.println(w.getType());
        System.out.println(w.getUses());
        System.out.println(w.power());
        System.out.println(w.useIt());
        System.out.println("\n");
        
        
        //
        //
        
        Dice dice = new Dice ();
        System.out.println("\t Elementos de la clase Dice:");
        // Compruebo el initWithNHangars()
        System.out.println("Compruebo el initWithNHangars()");
        
        int ceros = 0;
        int unos = 0;
        int dos = 0;
        
        final float REPS = 10000f;
        
        for(int i = 0; i <= REPS; i+= 1){
            if (dice.initWithNHangars() == 0){
               ceros += 1; 
            }
            else {
                unos += 1;
            }
            
        }
        
        System.out.println("Probabilidad de 1: \n");
        System.out.println(unos/REPS);
        System.out.println("Probabilidad de 0: \n");
        System.out.println(ceros/REPS);
        
        System.out.println("\n");
        
        System.out.println("Compruebo el initWithNWeapons()");
        
        ceros = 0;
        unos = 0;
        dos = 0;
        
        for(int i = 0; i <= REPS; i+= 1){
            float valor = dice.initWithNWeapons();
            if (valor == 1){
               ceros += 1; 
            }
            else if (valor == 2) {
                unos += 1;
            }
            else if (valor == 3){
                dos += 1;
            }
            
        }
        System.out.println("Probabilidad de 3: \n");
        System.out.println(dos/REPS);
        System.out.println("Probabilidad de 2: \n");
        System.out.println(unos/REPS);
        System.out.println("Probabilidad de 1: \n");
        System.out.println(ceros/REPS);
        
        System.out.println("\n");
        
        System.out.println("Compruebo el initWithNShields()");
        
        ceros = 0;
        unos = 0;
        dos = 0;
        
        for(int i = 0; i <= REPS; i+= 1){
            if (dice.initWithNShields() == 0){
                ceros += 1; 
            }
            else {
                unos += 1;
            }
            
        }
        System.out.println("Probabilidad de 1: \n");
        System.out.println(unos/REPS);
        System.out.println("Probabilidad de 0: \n");
        System.out.println(ceros/REPS);
        
        System.out.println("\n");
        
        final int PLAYERS = 10;
        int [] v = new int[PLAYERS] ;
        System.out.println("Compruebo el whoStarts():");
        for (int i = 0 ; i < PLAYERS; i+= 1){
            v[i]= 0;
        }
        
        for (int i= 0; i <= REPS; i+= 1){
            v[dice.whoStarts(PLAYERS)] += 1;
        }
        
        for (int i = 0 ; i < PLAYERS; i+= 1){
            System.out.println("Probabilidad de " + i + ": " + v[i]/REPS + "\n");
        }
        
        System.out.println("\n");
        
        System.out.println("Compruebo el firstShot():");
        
        int estacion = 0;
        int enemigo = 0;
        
        GameCharacter game = GameCharacter.SPACESTATION; 
        for (int i = 0 ; i <= REPS; i+= 1){
            if (dice.firstShot() == game){
                estacion += 1;
            }
            else {
                enemigo += 1;
            }
            
        }
        
        System.out.println("Probabilidad de estacion: \n");
        System.out.println(estacion/REPS);
        System.out.println("Probabilidad de enemigo: \n");
        System.out.println(enemigo/REPS);
        
        
        ceros = 0; 
        unos = 0;
        dos = 0;
        
        float speed = 0.4f;
        System.out.println("Compruebo el spaceStationMove():");
        
        for(int i = 0; i <= REPS; i+= 1){
            if (dice.spaceStationMoves(speed) == false){
               ceros += 1; 
            }
            else {
                unos += 1;
            }
            
        }
        System.out.println("Probabilidad de 1: \n");
        System.out.println(unos/REPS);
        System.out.println("Probabilidad de 0: \n");
        System.out.println(ceros/REPS);
        
        System.out.println("\n");
        
    }
    
}