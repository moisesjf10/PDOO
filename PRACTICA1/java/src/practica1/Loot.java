/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica1;

/**
 *
 * @author manuel
 */
public class Loot {
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;
    
    Loot (int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals ){
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
    }
    
    public int getNSupplies(){
        return nSupplies;
    }
    
    public int getNWeapons(){
        return nWeapons;
    } 
    
    public int getNShields(){
        return nShields;
    } 
    
    public int getNHangars(){
        return nHangars;
    } 
    
    public int getNMedals(){
        return nMedals;
    } 
    
    
}
