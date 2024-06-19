
package Vistas;

import Modelo.Interface.SearchOptinEvent;
import Modelo.SearchOption;
import javax.swing.ImageIcon;
import Modelo.GenerarPDF.PDFCliente; // Importa la clase PDFCliente
import javax.swing.JOptionPane;
import javax.swing.JTable;



public class V_Cliente extends javax.swing.JPanel {
    
        
    public int opt=0;
    
    public V_Cliente() {
        initComponents();
        setOpaque(false);
        
        // Agregar ActionListener al botón jButton1
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    }
    
    public void init(){
        tablaCliente.fixTable(jScrollPane1);
        txtBusqueda.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                opt=index;
                txtBusqueda.setHint("Buscar " + option.getName() + "...");
            }
        });
        txtBusqueda.addOption(new SearchOption("todo", new ImageIcon(getClass().getResource("/image/loadall.png"))));
        txtBusqueda.addOption(new SearchOption("por codCliente", new ImageIcon(getClass().getResource("/image/user.png"))));
        txtBusqueda.addOption(new SearchOption("por RUC", new ImageIcon(getClass().getResource("/image/ruc.png"))));
    }   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        popupMenu = new javax.swing.JPopupMenu();
        actualizaTabla = new javax.swing.JMenuItem();
        nuevo = new javax.swing.JMenuItem();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelShadow1 = new Modelo.Design.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new Modelo.DesignTable.Tabla();
        jButton1 = new javax.swing.JButton();
        panelShadow2 = new Modelo.Design.PanelShadow();
        nroRuc = new Modelo.Design.TextField();
        textAreaScroll1 = new Modelo.Design.TextAreaScroll();
        desPersona = new Modelo.Design.TextArea();
        textAreaScroll3 = new Modelo.Design.TextAreaScroll();
        desAlterna = new Modelo.Design.TextArea();
        textAreaScroll5 = new Modelo.Design.TextAreaScroll();
        desCorta = new Modelo.Design.TextArea();
        textAreaScroll4 = new Modelo.Design.TextAreaScroll();
        desCortaAlt = new Modelo.Design.TextArea();
        vigente = new Modelo.Design.Combobox();
        btt_Registrar = new Modelo.Design.Button();
        btt_Actualizar = new Modelo.Design.Button();
        btt_Eliminar = new Modelo.Design.Button();
        codCompania_cb = new Modelo.Design.TextField();
        txtBusqueda = new Modelo.Design.TextFieldSearchOption();

        popupMenu.setLabel("Acciones");

        actualizaTabla.setText("Actualizar");
        popupMenu.add(actualizaTabla);

        nuevo.setText("Nuevo");
        popupMenu.add(nuevo);

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/registraCliente.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("¡Registre los clientes!");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 179, 255));
        jLabel4.setText("Complete la información de sus clientes");

        panelShadow1.setComponentPopupMenu(popupMenu);

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodClient", "DescPersona", "RUC", "Vigente"
            }
        ));
        tablaCliente.setComponentPopupMenu(popupMenu);
        jScrollPane1.setViewportView(tablaCliente);

        jButton1.setText("Generar PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(217, 217, 217))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelShadow2.setComponentPopupMenu(popupMenu);

        nroRuc.setForeground(new java.awt.Color(40, 40, 40));
        nroRuc.setLabelText("Número de RUC:");
        nroRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nroRucActionPerformed(evt);
            }
        });

        textAreaScroll1.setLabelText("Descripción Persona:");

        desPersona.setBorder(null);
        desPersona.setColumns(20);
        desPersona.setForeground(new java.awt.Color(40, 40, 40));
        desPersona.setRows(5);
        textAreaScroll1.setViewportView(desPersona);

        textAreaScroll3.setLabelText("Descripción Alterna:");

        desAlterna.setBorder(null);
        desAlterna.setColumns(20);
        desAlterna.setForeground(new java.awt.Color(40, 40, 40));
        desAlterna.setRows(5);
        textAreaScroll3.setViewportView(desAlterna);

        textAreaScroll5.setLabelText("Descripción Corta:");

        desCorta.setBorder(null);
        desCorta.setColumns(20);
        desCorta.setForeground(new java.awt.Color(40, 40, 40));
        desCorta.setRows(5);
        textAreaScroll5.setViewportView(desCorta);

        textAreaScroll4.setLabelText("Descripción Corta Alterna:");

        desCortaAlt.setBorder(null);
        desCortaAlt.setColumns(20);
        desCortaAlt.setForeground(new java.awt.Color(40, 40, 40));
        desCortaAlt.setRows(5);
        textAreaScroll4.setViewportView(desCortaAlt);

        vigente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigente", "No Vigente" }));
        vigente.setSelectedIndex(-1);
        vigente.setLabeText("Estado:");
        vigente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vigenteActionPerformed(evt);
            }
        });

        btt_Registrar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar.setText("Registrar");

        btt_Actualizar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Actualizar.setText("Actualizar");

        btt_Eliminar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Eliminar.setText("Eliminar");

        codCompania_cb.setEditable(false);
        codCompania_cb.setForeground(new java.awt.Color(40, 40, 40));
        codCompania_cb.setLabelText("Código Compañía:");

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textAreaScroll5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(nroRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textAreaScroll4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(textAreaScroll3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(codCompania_cb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                        .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nroRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codCompania_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textAreaScroll5, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(textAreaScroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JTable getTablaCliente() {
        return tablaCliente;
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PDFCliente pdf = new PDFCliente();
        pdf.generarPDF(tablaCliente);
        JOptionPane.showMessageDialog(this, "PDF generado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void vigenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vigenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vigenteActionPerformed

    private void nroRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nroRucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nroRucActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem actualizaTabla;
    public Modelo.Design.Button btt_Actualizar;
    public Modelo.Design.Button btt_Eliminar;
    public Modelo.Design.Button btt_Registrar;
    public Modelo.Design.TextField codCompania_cb;
    private com.raven.datechooser.DateChooser dateChooser1;
    public Modelo.Design.TextArea desAlterna;
    public Modelo.Design.TextArea desCorta;
    public Modelo.Design.TextArea desCortaAlt;
    public Modelo.Design.TextArea desPersona;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    public Modelo.Design.TextField nroRuc;
    public javax.swing.JMenuItem nuevo;
    private Modelo.Design.PanelShadow panelShadow1;
    private Modelo.Design.PanelShadow panelShadow2;
    private Modelo.Design.PictureBox pictureBox1;
    public javax.swing.JPopupMenu popupMenu;
    public Modelo.DesignTable.Tabla tablaCliente;
    private Modelo.Design.TextAreaScroll textAreaScroll1;
    private Modelo.Design.TextAreaScroll textAreaScroll3;
    private Modelo.Design.TextAreaScroll textAreaScroll4;
    private Modelo.Design.TextAreaScroll textAreaScroll5;
    public Modelo.Design.TextFieldSearchOption txtBusqueda;
    public Modelo.Design.Combobox vigente;
    // End of variables declaration//GEN-END:variables
}
