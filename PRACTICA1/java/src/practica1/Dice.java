package practica1;
import java.util.Random;
/**
 *
 * @author manuel
 */

class Dice {
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    private Random generator;
    
    Dice (){
        
        NHANGARSPROB=0.25f;
        NSHIELDSPROB=0.25f;
        NWEAPONSPROB=0.33f;
        FIRSTSHOTPROB=0.5f;
        generator = new Random();
    }
    
    int initWithNHangars(){
        return ((generator.nextFloat() <= NHANGARSPROB )? 0:1);
    }
    
    int initWithNWeapons(){
        int valor = 1;
        float prob = generator.nextFloat();
        if (prob <= NWEAPONSPROB){
            valor = 1;
        }
        else if ( prob <= 2*NWEAPONSPROB ){
            valor = 2;
        }
        else if (prob <= 1f){
            valor = 3;
        }
            
        return valor;
    }
    
    int initWithNShields(){
        return ((generator.nextFloat() <= NSHIELDSPROB )? 0:1);
    }
    
    int whoStarts(int nPlayers){
       return generator.nextInt(nPlayers); //revisar.
    }
    
    GameCharacter firstShot(){
        GameCharacter shot=((generator.nextFloat() <= FIRSTSHOTPROB )? GameCharacter.SPACESTATION : GameCharacter.ENEMYSTARSHIP);
        return shot;
    }
    
    boolean spaceStationMoves(float speed){
        return ((generator.nextFloat() <= speed )? true:false);
    }
    
    
    
    
    
    
}
