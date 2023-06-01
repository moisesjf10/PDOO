/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.GUI.ExamView;
import deepspace.GameUniverse;
import deepspace.SpaceFighterToUI;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author manuel
 */
public class ExamController {
    private static final ExamController instance = new ExamController();
    
    public static final int WEAPON = 0x1;
    public static final int SHIELD = 0x2;
    public static final int HANGAR = 0x4;
    private GameUniverse game ;
    private ExamView view;
    
    private ExamController () {}
    
    public static ExamController getInstance () {
      return instance;
    }
    
    public void setModelView (GameUniverse aGame, ExamView aView) {
      game = aGame;
      view = aView;
    }
    
    public void start() {
        view.updateView();
        view.showView();
    }
    
    public void invertArray (ArrayList<Integer> array) {
      int i, n;
      
      n = array.size();
      for (i = 0; i < n/2; i++)
        Collections.swap(array, i, n-i-1);
    }
    
     public SpaceFighterToUI getSpaceFighter(){
        return (new SpaceFighterToUI(game.getSpaceFighter()));
    }
}
