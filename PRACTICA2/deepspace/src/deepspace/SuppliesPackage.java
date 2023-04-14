
package deepspace;

class SuppliesPackage {
    private float ammoPower;
    private float fuelUnits;
    private float shieldPower;
    
    SuppliesPackage(float ammoPower,float fuelUnits, float shieldPower){
        this.ammoPower=ammoPower;
        this.fuelUnits=fuelUnits;
        this.shieldPower=shieldPower;
    }
    
    SuppliesPackage(SuppliesPackage s){
        this(s.ammoPower, s.fuelUnits, s.shieldPower);
        
 
    }
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public float getFuelUnits(){
        return fuelUnits;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    @Override
    public String toString(){
        String salida="[SuppliesPackage] -> ammoPower: "+ ammoPower + 
                ", fuelUnits: "+ fuelUnits +
                ", shieldPower: "+ shieldPower;
        return salida;
    }
}

