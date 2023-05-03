package deepspace;

public class PowerEfficientSpaceStation extends SpaceStation{
    private static final float EFFICIENCYFACTOR = 1.1F;

    public PowerEfficientSpaceStation(SpaceStation station){
        super(station);
    }

    @Override
    public float fire(){
        return super.fire()*EFFICIENCYFACTOR;
    }

    @Override
    public float protection() {
        return super.protection()*EFFICIENCYFACTOR;
    }

    @Override
    public Transformation setLoot(Loot loot){
        Transformation t=super.setLoot(loot);
        if(t==Transformation.SPACECITY) t=Transformation.NOTRANSFORM;

        return t;

    }

    @Override
    public PowerEfficientSpaceStationToUI getUIversion(){
        return new PowerEfficientSpaceStationToUI(this);
    }
}