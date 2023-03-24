package deepspace;


class EnemyStarShip {
    private String name;
    private float ammoPower;
    private float shieldPower;
    private Loot loot;
    private Damage damage;
    
    
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        name = n;
        ammoPower = a;
        shieldPower = s;
        loot = l;
        damage = d;
    }
    
    EnemyStarShip(EnemyStarShip e){
        this(e.name,e.ammoPower,e.shieldPower,e.loot,e.damage);
    }
    
    EnemyToUI getUIVersion(){
        return new EnemyToUI(this);
    }
    
    public float fire(){
        return ammoPower;
    }
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public Loot getLoot(){
        return loot;
    }
    
    public String getName(){
        return name;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    public float protection(){
        return shieldPower;
    }
    
    public ShotResult receiveShot(float shot){
        ShotResult resultado = ShotResult.RESIST;
        if (protection() < shot)
            resultado = ShotResult.DONOTRESIST;
        
        return resultado;
    }
    
    public String toString (){
        String salida="[EnemyStarShip] -> nWeapons: "+ name +
                ", ammoPower: "+ ammoPower +
                ", shieldPower: "+ shieldPower +
                ", loot: "+ loot.toString() +
                ", damage: "+ damage.toString();
        return salida;
    }
}
