/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.GUI;

import controller.Controller;
import controller.ExamController;
import deepspace.SpaceFighterToUI;
import java.awt.Component;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class ExamView extends javax.swing.JFrame {
    private static ExamView instance = null;
    /**
     * Creates new form ExamView
     */
    public ExamView() {
        initComponents();
    }
    
    public static ExamView getInstance () {
        if (instance == null) {
            instance = new ExamView();
        }
        return instance;
    }
    
    
    public void updateView(){
        
        repaint();
        revalidate();
        
    }
    
    public void showView(){
        this.setVisible(true);
    }
    
    public void addSpaceFighter(SpaceFighterToUI sf){
        SpaceFighterView view = new SpaceFighterView();
        view.SetSpaceFighter(sf);
        elements.add(view);
    }
    
    public ArrayList<Integer> getSelected(){
        ArrayList<Integer> selected = new  ArrayList<Integer>(); 
        int i=0;
        for(Component c: elements.getComponents()){
            if(((SpaceFighterView)c).isSelected()){
                selected.add(i);
            }
            i++;
        }
        
        return selected;
    }
    
    public void removeSelected(ArrayList<Integer> selected){
        for (Integer i: selected){
            elements.remove(i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        elements = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        elements.setBorder(javax.swing.BorderFactory.createTitledBorder("Elements"));
        jScrollPane1.setViewportView(elements);

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                        .addComponent(delete)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(delete))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        addSpaceFighter(ExamController.getInstance().getSpaceFighter());
        
        updateView();
        
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        ArrayList <Integer> selected;
        
        selected = getSelected();
        
        ExamController.getInstance().invertArray(selected);
        
        removeSelected(selected);
        
        updateView();
        
        
        
    }//GEN-LAST:event_deleteActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private javax.swing.JPanel elements;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
