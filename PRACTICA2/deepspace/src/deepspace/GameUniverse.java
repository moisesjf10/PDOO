package deepspace;
import java.util.ArrayList;

class GameUniverse{
    private static final int WIN=10;
    private int currentStationIndex;
    private int turns;
    private Dice dice;
    private GameStateController gameState;
    private EnemyStarShip currentEnemy;
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations;
    
    
    public GameUniverse(){
        currentStationIndex=-1;
        turns=0;
        gameState=new GameStateController();
        dice=new Dice();
        currentStation=null;
        spaceStations=new ArrayList<>();
         
    }
    
    CombatResult combat(SpaceStation space, EnemyStarShip enemy){
        throw new UnsupportedOperationException();
        
    }
    
    public CombatResult combat(){
        throw new UnsupportedOperationException();
    }
    
    public void discardHangar(){
        if(gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT){
            currentStation.discardHangar();
        }
    }
    public void discardShieldBooster(int i){
        if(gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT){
            currentStation.discardShieldBooster(i);
        }
        
    }
    public void discardShieldBoosterInHangar(int i){
        if(gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT){
            currentStation.discardShieldBoosterInHangar(i);
        }  
    }
    public void discardWeapon(int i){
        if(gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT){
            currentStation.discardWeapon(i);
        }
    }
    
    public void discardWeaponInHangar(int i){
        if(gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT){
            currentStation.discardWeaponInHangar(i);
        }
    }
    public GameState getState(){
        return gameState.getState();
    }
    
    public GameUniverseToUI getUIversion(){
        return (new GameUniverseToUI(this.currentStation, this.currentEnemy));
    }
    
    public boolean haveAWinner(){
        return (currentStation.getNMedals()==WIN);
        
    }
    
    public void init(ArrayList<String> names){
        throw new UnsupportedOperationException(); 
    }
    
    public void mountShieldBooster(int i){
        if(gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT){
            currentStation.mountShieldBooster(i);
        }
        
    }
    
    public void mountWeapon(int i){
        if(gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT){
            currentStation.mountWeapon(i);
        }
    }
    
    public boolean nextTurn(){
        throw new UnsupportedOperationException();
    }
    
    public String toString(){
        String salida="[GameUniverse] -> WIN: "+ WIN +
                ", currentStationIndex: "+ currentStationIndex +
                ", gameState: "+ gameState.toString() +
                ", currentEnemy: "+ currentEnemy.toString()+
                ", currentStation: "+ currentStation.toString() +
                ", spaceStations: "+ spaceStations.toString() ;
        return salida;
    }
    
    
}
