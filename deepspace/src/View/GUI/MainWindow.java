/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.GUI;

import View.DeepSpaceView;
import controller.Controller;
import deepspace.HangarToUI;
import java.util.ArrayList;
import deepspace.ShieldToUI;
import deepspace.LootToUI;
import deepspace.SpaceStationToUI;

/**
 *
 * @author moises
 */
public class MainWindow extends javax.swing.JFrame implements DeepSpaceView {
    private static MainWindow instance=null;
    private String appName;
    
    private SpaceStationView stationView;
    private EnemyView enemyView;

    //hay mas datos miembro
    public static MainWindow getInstance () {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }
    /**
     * Creates new form MainWindow
     */
    private MainWindow() {
        initComponents();
        //hay que hacer algo mas
        appName = "DeepSpace";
        setTitle(appName);
        
        stationView=new SpaceStationView();
        enemyView=new EnemyView();
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Controller.getInstance().finish(0);
            }
        });

    }

    public String getAppName() {
        return appName;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SpaceStation = new javax.swing.JPanel();
        enemy = new javax.swing.JPanel();
        combatir = new javax.swing.JButton();
        nextturn = new javax.swing.JButton();
        exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SpaceStation.setBorder(javax.swing.BorderFactory.createTitledBorder("Station"));

        javax.swing.GroupLayout SpaceStationLayout = new javax.swing.GroupLayout(SpaceStation);
        SpaceStation.setLayout(SpaceStationLayout);
        SpaceStationLayout.setHorizontalGroup(
            SpaceStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        SpaceStationLayout.setVerticalGroup(
            SpaceStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );

        enemy.setBorder(javax.swing.BorderFactory.createTitledBorder("Enemy"));

        javax.swing.GroupLayout enemyLayout = new javax.swing.GroupLayout(enemy);
        enemy.setLayout(enemyLayout);
        enemyLayout.setHorizontalGroup(
            enemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );
        enemyLayout.setVerticalGroup(
            enemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
        );

        combatir.setText("COMBAT");
        combatir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combatirActionPerformed(evt);
            }
        });

        nextturn.setText("Next Turn");
        nextturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextturnActionPerformed(evt);
            }
        });

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(SpaceStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(enemy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(combatir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(nextturn)
                        .addGap(126, 126, 126)
                        .addComponent(exit)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SpaceStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enemy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(combatir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nextturn)
                            .addComponent(exit))
                        .addGap(72, 72, 72))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combatirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combatirActionPerformed
        // TODO add your handling code here:
        Controller.getInstance().combat();
        updateView();
        revalidate();
    }//GEN-LAST:event_combatirActionPerformed

    private void nextturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextturnActionPerformed
        // TODO add your handling code here:
        Controller.getInstance().nextTurn();
        updateView();
        revalidate();
    }//GEN-LAST:event_nextturnActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        Controller.getInstance().finish(0);
    }//GEN-LAST:event_exitActionPerformed


    @Override
    public void updateView(){
        
        
    }
    @Override
    public void showView(){
        this.setVisible(true);
    }
    // Inputs
    @Override
    public ArrayList<String> readNamePlayers(){
        NamesCapture namesCapt = new NamesCapture(this);
        return namesCapt.readNamePlayers();
    }
    // Outputs
    public boolean confirmExitMessage(){
           return true;
    }
    public void nextTurnNotAllowedMessage(){

    }
    public void lostCombatMessage(){

    }
    public void escapeMessage(){

    }
    public void wonCombatMessage(){

    }
    public void wonGameMessage(){

    }
    public void conversionMessage(){

    }
    public void noCombatMessage(){

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SpaceStation;
    private javax.swing.JButton combatir;
    private javax.swing.JPanel enemy;
    private javax.swing.JButton exit;
    private javax.swing.JButton nextturn;
    // End of variables declaration//GEN-END:variables
}
