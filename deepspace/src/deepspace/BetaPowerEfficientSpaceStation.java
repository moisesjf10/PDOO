package deepspace;

public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    private static final float EXTRAEFFICIENCY= 1.2F;
    private Dice dice;
    public BetaPowerEfficientSpaceStation(SpaceStation station){
        super(station);
        dice=new Dice();
    }

    @Override
    public float fire(){
        float f;
        if(dice.extraEfficiency()) f=super.fire()*EXTRAEFFICIENCY;
        else f=super.fire();

        return f;
    }

    @Override
    public String toString(){
        String salida = "[Beta]" + super.toString();
        return salida;
    }

    @Override
    public BetaPowerEfficientSpaceStationToUI getUIversion(){
        return new BetaPowerEfficientSpaceStationToUI(this);
    }

}