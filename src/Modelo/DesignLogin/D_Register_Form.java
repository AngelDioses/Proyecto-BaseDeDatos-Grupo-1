/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Modelo.DesignLogin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

/**
 *
 * @author DELL FULL HD
 */
public class D_Register_Form extends PanelCustom {

    private Color color2 = new Color(60, 66, 102);
    
    public D_Register_Form() {
        initComponents();
        setOpaque(false);
        init();
        //setAlpha(1);
    }
    
    public void init(){
        textAreaScroll1.setColorLabelText(new Color(240,240,240));
        textAreaScroll2.setColorLabelText(new Color(240,240,240));
    }
    
    public void addEventLogin(ActionListener event) {
        btRegistrar.addActionListener(event);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(color2);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(grphcs);
    }
    
    public boolean checkRegister() {
        boolean action = true;
        if (descCia.getText().trim().equals("")) {
            action = false;
        }
        if (descCorta.getText().trim().equals("")) {
            action = false;
        }
        return action;
    }
    
    public void checkRegisterReset() {
        descCia.setText("");
        descCorta.setText("");
    }

    public String getDescCia() {
        return descCia.getText().trim();
    }

    public String getDescCorta() {
        return descCorta.getText().trim();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btRegistrar = new Modelo.Design.Button();
        jLabel2 = new javax.swing.JLabel();
        textAreaScroll1 = new Modelo.Design.TextAreaScroll();
        descCia = new Modelo.Design.TextArea();
        textAreaScroll2 = new Modelo.Design.TextAreaScroll();
        descCorta = new Modelo.Design.TextArea();

        setBackground(new java.awt.Color(58, 58, 58));

        btRegistrar.setBackground(new java.awt.Color(70, 79, 150));
        btRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btRegistrar.setText("REGISTRAR");
        btRegistrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SIGN UP");

        textAreaScroll1.setLabelText("Descripción Compañia");

        descCia.setBackground(new java.awt.Color(58, 58, 58));
        descCia.setBorder(null);
        descCia.setColumns(20);
        descCia.setForeground(new java.awt.Color(255, 255, 255));
        descCia.setRows(5);
        textAreaScroll1.setViewportView(descCia);

        textAreaScroll2.setLabelText("Descripción Corta");

        descCorta.setBackground(new java.awt.Color(58, 58, 58));
        descCorta.setBorder(null);
        descCorta.setColumns(20);
        descCorta.setForeground(new java.awt.Color(255, 255, 255));
        descCorta.setRows(5);
        textAreaScroll2.setViewportView(descCorta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Modelo.Design.Button btRegistrar;
    public Modelo.Design.TextArea descCia;
    public Modelo.Design.TextArea descCorta;
    private javax.swing.JLabel jLabel2;
    private Modelo.Design.TextAreaScroll textAreaScroll1;
    private Modelo.Design.TextAreaScroll textAreaScroll2;
    // End of variables declaration//GEN-END:variables
}
