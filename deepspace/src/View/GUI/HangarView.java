/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.GUI;
import deepspace.HangarToUI;
import deepspace.ShieldToUI;
import deepspace.WeaponToUI;
import controller.Controller;
import java.util.ArrayList;
import java.awt.Component;
/**
 *
 * @author moises
 */
public class HangarView extends javax.swing.JPanel {

    /**
     * Creates new form HangarView
     */
    public HangarView() {
        initComponents();
    }
    
    public void setHangar(HangarToUI h){
        if(h==null){
            setVisible(false);
        }else{
            capacity.setText(Integer.toString(h.getMaxElements()));
            panelWeapons.removeAll();
            ArrayList<WeaponToUI> weapons = h.getWeapons();
            WeaponView weaponview;
            for (WeaponToUI w : weapons) {
                weaponview = new WeaponView();
                weaponview.setWeapon(w);
                panelWeapons.add(weaponview);
            }
            panelShields.removeAll();
            ArrayList<ShieldToUI> shields = h.getShieldBoosters();
            ShieldView shieldview;
            for (ShieldToUI s : shields) {
                shieldview = new ShieldView();
                shieldview.setShield(s);
                panelShields.add(shieldview);
            }
            repaint();
            revalidate();
         }
    }
    
    void getSelectedInHangar(ArrayList<Integer> weaponsSelected, ArrayList<Integer> shieldsSelected){
        /*//No sé si esto está permitido
        int numWeapons = Controller.getInstance().getUIversion().getCurrentStation().getHangar().getWeapons().size();
        int numShields = Controller.getInstance().getUIversion().getCurrentStation().getHangar().getShieldBoosters().size();
        
        
        for(int i=0; i<numWeapons; i++){
            Component c = panelWeapons.getComponent(i);
            if(((CombatElementView) c).isSelected()){
                weaponsSelected.add(i);
            }
        }
        
        for(int i=0; i<numShields; i++){
            Component c = panelShields.getComponent(i+numWeapons);
            if(((CombatElementView) c).isSelected()){
                shieldsSelected.add(i);
            }
        }*/
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tcapacity = new javax.swing.JLabel();
        capacity = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelWeapons = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelShields = new javax.swing.JPanel();

        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        Tcapacity.setText("Capacity:");

        capacity.setText("jLabel2");

        panelWeapons.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Weapons"));
        jScrollPane1.setViewportView(panelWeapons);

        panelShields.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Shields"));
        jScrollPane2.setViewportView(panelShields);
        panelShields.getAccessibleContext().setAccessibleName("Weapons");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Tcapacity)
                        .addGap(18, 18, 18)
                        .addComponent(capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tcapacity)
                    .addComponent(capacity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jScrollPane1.getAccessibleContext().setAccessibleName("Weapons");
        jScrollPane2.getAccessibleContext().setAccessibleName("Shields");

        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tcapacity;
    private javax.swing.JLabel capacity;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelShields;
    private javax.swing.JPanel panelWeapons;
    // End of variables declaration//GEN-END:variables
}
