/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controlador.C_Elementos;
import Controlador.C_Tabs;
import Modelo.ModeloBConfig;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL FULL HD
 */
public class V_Configuracion extends javax.swing.JPanel {

    /**
     * Creates new form V_Configuracion
     */
    public V_Configuracion() {
        initComponents();
        setOpaque(false);
        init();
    }
    
    public void init(){
        //buttonConfig1.setImg(img);
        Icon icon1 = new ImageIcon(getClass().getResource("/image/ventaT.png"));
        buttonConfiguration1.setDatos(new ModeloBConfig("Tabs", "Agrega, modifica y elimina", icon1));
        Icon icon2 = new ImageIcon(getClass().getResource("/image/check_circle.png"));
        buttonConfiguration2.setDatos(new ModeloBConfig("Elementos", "Agrega, modifica y elimina", icon2));
        Icon icon3 = new ImageIcon(getClass().getResource("/image/block.png"));
        buttonConfiguration3.setDatos(new ModeloBConfig("Consulta", "Oracle-Consulta", icon3));
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonConfiguration1 = new Modelo.Design.buttonConfiguration();
        buttonConfiguration2 = new Modelo.Design.buttonConfiguration();
        buttonConfiguration3 = new Modelo.Design.buttonConfiguration();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel3 = new javax.swing.JLabel();

        buttonConfiguration1.setEffectColor(new java.awt.Color(4, 115, 219));
        buttonConfiguration1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfiguration1ActionPerformed(evt);
            }
        });

        buttonConfiguration2.setEffectColor(new java.awt.Color(4, 115, 219));
        buttonConfiguration2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfiguration2ActionPerformed(evt);
            }
        });

        buttonConfiguration3.setEffectColor(new java.awt.Color(4, 115, 219));
        buttonConfiguration3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfiguration3ActionPerformed(evt);
            }
        });

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/config.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("Modo Configuración");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonConfiguration1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(buttonConfiguration2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(buttonConfiguration3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfiguration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonConfiguration2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonConfiguration3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(642, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfiguration3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfiguration3ActionPerformed
        V_Consultas vc=new V_Consultas();
        vc.setVisible(true);
    }//GEN-LAST:event_buttonConfiguration3ActionPerformed

    private void buttonConfiguration1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfiguration1ActionPerformed
        V_Configuracion_Tab vct=new V_Configuracion_Tab();
        C_Tabs ct=new C_Tabs(vct);
        vct.setVisible(true);
    }//GEN-LAST:event_buttonConfiguration1ActionPerformed

    private void buttonConfiguration2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfiguration2ActionPerformed
        V_Configuracion_Elementos vce=new V_Configuracion_Elementos();
        C_Elementos ce=new C_Elementos(vce);
        vce.setVisible(true);
    }//GEN-LAST:event_buttonConfiguration2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Modelo.Design.buttonConfiguration buttonConfiguration1;
    private Modelo.Design.buttonConfiguration buttonConfiguration2;
    private Modelo.Design.buttonConfiguration buttonConfiguration3;
    private javax.swing.JLabel jLabel3;
    private Modelo.Design.PictureBox pictureBox1;
    // End of variables declaration//GEN-END:variables
}
