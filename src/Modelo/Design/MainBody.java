package Modelo.Design;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;

public class MainBody extends javax.swing.JPanel {

    public MainBody() {
        initComponents();
        setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void displayForm(Component form) {
        displayForm(form, "");
    }

    public void displayForm(String title) {
        //lbTitle.setText(title);
    }
    
    public void displayForm(Component form, String title) {
        //lbTitle.setText(title);
        panelBody.removeAll();
        panelBody.add(form);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void changeColor(Color color) {
        //lbTitle.setForeground(color);
        if (panelBody.getComponentCount() != 0) {
            Form com = (Form) panelBody.getComponent(0);
            com.changeColor(color);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitle = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        panelBody = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();

        panelTitle.setForeground(new java.awt.Color(76, 76, 76));
        panelTitle.setToolTipText("");
        panelTitle.setOpaque(false);

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        panelBody.setOpaque(false);
        panelBody.setLayout(new java.awt.BorderLayout());

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(245, 245, 245));
        panelBody.add(lbTitle, java.awt.BorderLayout.CENTER);

        scroll.setViewportView(panelBody);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
