package deepspace;
import java.util.Random;

public class Dice {
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;

    private static final float EXTRAEFFICIENCYPROB = 0.8F;
    private Random generator;
    
    Dice (){
        NHANGARSPROB=0.25f;
        NSHIELDSPROB=0.25f;
        NWEAPONSPROB=0.33f;
        FIRSTSHOTPROB=0.5f;
        generator = new Random();
    }

    public int initWithNHangars(){
        return ((generator.nextFloat() <= NHANGARSPROB )? 0:1);
    }

    public int initWithNWeapons(){
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

    public int initWithNShields(){
        return ((generator.nextFloat() <= NSHIELDSPROB )? 0:1);
    }

    public int whoStarts(int nPlayers){
       return generator.nextInt(nPlayers); 
    }

    public GameCharacter firstShot(){
        GameCharacter shot=((generator.nextFloat() <= FIRSTSHOTPROB )? GameCharacter.SPACESTATION : GameCharacter.ENEMYSTARSHIP);
        return shot;
    }

    public boolean spaceStationMoves(float speed){
        return (generator.nextFloat() <= speed );
    }
    
    public boolean extraEfficiency(){
        return ((generator.nextFloat() <= EXTRAEFFICIENCYPROB)? true:false);
    }
    
    
    
    
}
