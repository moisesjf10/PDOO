package deepspace;
import java.util.ArrayList;
import java.util.Iterator;

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
        if(gameState.getState()==GameState.CANNOTPLAY){
            CardDealer dealer = CardDealer.getInstance();
            for(String name: names){
                SuppliesPackage supplies=new SuppliesPackage(dealer.nextSuppliesPackage());
                SpaceStation station=new SpaceStation(name,supplies);
                spaceStations.add(station);

                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();

                Loot lo = new Loot(0,nw,ns,nh,0);
                station.setLoot(lo);
            }
            currentStationIndex=dice.whoStarts(names.size());
            currentStation=spaceStations.get(currentStationIndex);
            EnemyStarShip currentEnemy = dealer.nextEnemy();

            gameState.next(turns, spaceStations.size());
        }
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
        boolean nexturn=false;
        if(gameState.getState()==GameState.AFTERCOMBAT){
            boolean stationState=currentStation.validState();
            if(stationState){
                currentStationIndex=(currentStationIndex+1)%spaceStations.size();
                turns++;
            }
        }

        return nexturn;
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
