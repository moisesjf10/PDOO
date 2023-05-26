package deepspace;
import java.util.ArrayList;

public class GameUniverse{
    private static final int WIN=10;
    private int currentStationIndex;
    private int turns;
    private Dice dice;
    private GameStateController gameState;
    private EnemyStarShip currentEnemy;
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations;
    private boolean haveSpaceCity;
    
    
    public GameUniverse(){
        currentStationIndex=-1;
        turns=0;
        gameState=new GameStateController();
        dice=new Dice();
        currentEnemy=null;
        currentStation=null;
        spaceStations=new ArrayList<>();
        haveSpaceCity=false;
    }

    private void makeStationEfficient(){
        if(dice.extraEfficiency()){
            currentStation=new BetaPowerEfficientSpaceStation(currentStation);
        }else{
            currentStation=new PowerEfficientSpaceStation(currentStation);
        }
        spaceStations.set(currentStationIndex, currentStation);
    }

    private void createSpaceCity(){
        if(!haveSpaceCity){
            ArrayList<SpaceStation> rest = new ArrayList<>();
            for( SpaceStation s: spaceStations){
                if(s!=currentStation) rest.add(s);
            }
            currentStation=new SpaceCity(currentStation, rest);
            spaceStations.set(currentStationIndex, currentStation);
            haveSpaceCity=true;
        }
    }
    
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        boolean enemyWins;
        GameCharacter ch = dice.firstShot();
        CombatResult combatResult;

        if(ch == GameCharacter.ENEMYSTARSHIP){
            float fire = enemy.fire();
            ShotResult result = station.receiveShot(fire);

            if(result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
                enemyWins = (result==ShotResult.RESIST);
            }else{
                enemyWins = true;
            }
        }else{
            float fire = station.fire();
            ShotResult result = enemy.receiveShot(fire);
            enemyWins = (result==ShotResult.RESIST);
        }

        if(enemyWins){
            float s = station.getSpeed();
            boolean moves = dice.spaceStationMoves(s);

            if(!moves){
                Damage damage = enemy.getDamage();
                station.setPendingDamage(damage);
                combatResult = CombatResult.ENEMYWINS;
            }else{
                station.move();
                combatResult = CombatResult.STATIONESCAPES;
            }
        }else{
            Loot aLoot = enemy.getLoot();
            Transformation t= station.setLoot(aLoot);

            if(t == Transformation.GETEFFICIENT){
                makeStationEfficient();
                combatResult = CombatResult.STATIONWINSANDCONVERTS;
            }else if(t == Transformation.SPACECITY){
                createSpaceCity();
                combatResult = CombatResult.STATIONWINSANDCONVERTS;
            }else{
                combatResult = CombatResult.STATIONWINS;
            }
        }

        gameState.next(turns, spaceStations.size());

        return combatResult;
    }
    
    public CombatResult combat(){
        CombatResult result=CombatResult.NOCOMBAT;
        GameState state=getState();
        if(state==GameState.BEFORECOMBAT || state==GameState.INIT){
            result=combat(currentStation,currentEnemy);
        }
        return result;
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
        return (currentStation.getNMedals()>=WIN);
        
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
            currentEnemy = dealer.nextEnemy();

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
                currentStation=spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                CardDealer dealer=CardDealer.getInstance();
                currentEnemy=dealer.nextEnemy();
                gameState.next(turns,spaceStations.size());
                nexturn=true;
            }
        }
        return nexturn;
    }

    @Override
    public String toString(){
        String salida="[GameUniverse] -> WIN: "+ WIN +
                ", currentStationIndex: "+ currentStationIndex +
                ", gameState: "+ gameState.toString() +
                ", currentEnemy: "+ currentEnemy.toString()+
                ", currentStation: "+ currentStation.toString() +
                ", spaceStations: "+ spaceStations.toString() ;
        return salida;
    }
    
    
    public ShieldBooster dameEscudoPrueba(){
        return CardDealer.getInstance().nextShieldBooster(); 
    }
    
    public Loot dameloot(){
        return new Loot(0,0,0,0,0);
    }
    
    public Hangar dameHangar(){
        Hangar h=new Hangar(4);
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        
        return h;
    }
    
    public SpaceStation dameEstacion(){
        
        SpaceStation s = new SpaceStation("Estacion",CardDealer.getInstance().nextSuppliesPackage());
        Hangar h = CardDealer.getInstance().nextHangar();
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        
        s.receiveHangar(h);
        
        s.mountWeapon(0);
        s.mountShieldBooster(0);
        ArrayList<WeaponType> l=new ArrayList<>();
        l.add(WeaponType.LASER);
        l.add(WeaponType.MISSILE);
        SpecificDamage damage = new SpecificDamage(l,1);
        
        s.setPendingDamage(damage);
        
        System.out.println(s.toString());
        
        return s;
        
    }
    
}
