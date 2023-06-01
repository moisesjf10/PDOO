/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deepspace;

/**
 *
 * @author manuel
 */
public class SpaceFighterToUI {
    
    private float fire;
    
    public SpaceFighterToUI(SpaceFighter f){
        fire = f.fire();
    }
    
    public float getFire (){
        return fire; 
    }
}
