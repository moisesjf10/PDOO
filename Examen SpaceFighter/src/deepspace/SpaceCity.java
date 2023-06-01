package deepspace;

import java.util.ArrayList;

public class SpaceCity extends SpaceStation{
    private SpaceStation base;
    private ArrayList<SpaceStation> collaborators;
    public SpaceCity (SpaceStation base, ArrayList<SpaceStation> rest){
        super(base);
        this.base = base;
        collaborators = new ArrayList<>(rest);
    }

    public ArrayList<SpaceStation> getCollaborators(){
        return collaborators;
    }
    
    @Override
    public float fire(){
        float fire = super.fire();
        for(SpaceStation s : collaborators ){
            fire += s.fire();
        }
        return fire;
    }
       
    @Override
    public float protection(){
        float protection = super.protection();
        for( SpaceStation s: collaborators){
            protection += s.protection();
        }
        return protection;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        return(Transformation.NOTRANSFORM);
    }

    @Override
    public SpaceCityToUI getUIversion() {
        return new SpaceCityToUI(this);
    }
}