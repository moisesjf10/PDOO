/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import View.GUI.ExamView;
import controller.ExamController;
import deepspace.GameUniverse;

/**
 *
 * @author manuel
 */
public class Examen {
    public static void main(String[] args){
        GameUniverse game = new GameUniverse();
        ExamView view= ExamView.getInstance();
        ExamController controller = ExamController.getInstance();
        controller.setModelView(game, view);
        controller.start();
    }
}
