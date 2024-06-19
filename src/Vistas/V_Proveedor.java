
package Vistas;

import Modelo.Interface.SearchOptinEvent;
import Modelo.SearchOption;
import javax.swing.ImageIcon;
import Modelo.GenerarPDF.PDFProveedor;
import javax.swing.JOptionPane;



public class V_Proveedor extends javax.swing.JPanel {

    public int opt=0;
    public V_Proveedor() {
        initComponents();
        setOpaque(false);
    }
    
    public void init(){
        tablaProveedor.fixTable(jScrollPane1);
        txtBusqueda.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                opt=index;
                txtBusqueda.setHint("Buscar " + option.getName() + "...");
            }
        });
        txtBusqueda.addOption(new SearchOption("todo", new ImageIcon(getClass().getResource("/image/loadall.png"))));
        txtBusqueda.addOption(new SearchOption("por codigo", new ImageIcon(getClass().getResource("/image/user.png"))));
        txtBusqueda.addOption(new SearchOption("por RUC", new ImageIcon(getClass().getResource("/image/ruc.png"))));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        actualizaTabla = new javax.swing.JMenuItem();
        nuevo = new javax.swing.JMenuItem();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelShadow2 = new Modelo.Design.PanelShadow();
        nroRuc = new Modelo.Design.TextField();
        textAreaScroll1 = new Modelo.Design.TextAreaScroll();
        desPersona = new Modelo.Design.TextArea();
        textAreaScroll3 = new Modelo.Design.TextAreaScroll();
        descAlterna = new Modelo.Design.TextArea();
        textAreaScroll5 = new Modelo.Design.TextAreaScroll();
        desCorta = new Modelo.Design.TextArea();
        textAreaScroll4 = new Modelo.Design.TextAreaScroll();
        desCortaAlt = new Modelo.Design.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedor = new Modelo.DesignTable.Tabla();
        btt_Registrar = new Modelo.Design.Button();
        btt_Eliminar = new Modelo.Design.Button();
        btt_Actualizar = new Modelo.Design.Button();
        vigente = new Modelo.Design.Combobox();
        jButton1 = new javax.swing.JButton();
        txtBusqueda = new Modelo.Design.TextFieldSearchOption();

        popupMenu.setLabel("Acciones");

        actualizaTabla.setText("Actualizar");
        popupMenu.add(actualizaTabla);

        nuevo.setText("Nuevo");
        popupMenu.add(nuevo);

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/proveedor.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("¡Registre los proveedores!");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 179, 255));
        jLabel4.setText("Complete la información de sus proveedores");

        panelShadow2.setComponentPopupMenu(popupMenu);

        nroRuc.setForeground(new java.awt.Color(40, 40, 40));
        nroRuc.setLabelText("Número de RUC:");

        textAreaScroll1.setLabelText("Descripción Persona:");

        desPersona.setBorder(null);
        desPersona.setColumns(20);
        desPersona.setForeground(new java.awt.Color(40, 40, 40));
        desPersona.setRows(5);
        textAreaScroll1.setViewportView(desPersona);

        textAreaScroll3.setLabelText("Descripción Alterna:");

        descAlterna.setBorder(null);
        descAlterna.setColumns(20);
        descAlterna.setForeground(new java.awt.Color(40, 40, 40));
        descAlterna.setRows(5);
        textAreaScroll3.setViewportView(descAlterna);

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

        tablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodProveedor", "DescPersona", "Ruc", "Vigente"
            }
        ));
        tablaProveedor.setComponentPopupMenu(popupMenu);
        jScrollPane1.setViewportView(tablaProveedor);

        btt_Registrar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar.setText("Registrar");

        btt_Eliminar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Eliminar.setText("Eliminar");

        btt_Actualizar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Actualizar.setText("Actualizar");

        vigente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigente", "No Vigente" }));
        vigente.setSelectedIndex(-1);
        vigente.setLabeText("Estado:");

        jButton1.setText("Generar PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nroRuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelShadow2Layout.createSequentialGroup()
                                        .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelShadow2Layout.createSequentialGroup()
                                        .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textAreaScroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(nroRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textAreaScroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public javax.swing.JTable getTablaProveedor() {
        return tablaProveedor;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PDFProveedor pdf = new PDFProveedor();
        pdf.generarPDF(tablaProveedor);
        JOptionPane.showMessageDialog(this, "PDF generado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem actualizaTabla;
    public Modelo.Design.Button btt_Actualizar;
    public Modelo.Design.Button btt_Eliminar;
    public Modelo.Design.Button btt_Registrar;
    public Modelo.Design.TextArea desCorta;
    public Modelo.Design.TextArea desCortaAlt;
    public Modelo.Design.TextArea desPersona;
    public Modelo.Design.TextArea descAlterna;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public Modelo.Design.TextField nroRuc;
    public javax.swing.JMenuItem nuevo;
    private Modelo.Design.PanelShadow panelShadow2;
    private Modelo.Design.PictureBox pictureBox1;
    public javax.swing.JPopupMenu popupMenu;
    public Modelo.DesignTable.Tabla tablaProveedor;
    private Modelo.Design.TextAreaScroll textAreaScroll1;
    private Modelo.Design.TextAreaScroll textAreaScroll3;
    private Modelo.Design.TextAreaScroll textAreaScroll4;
    private Modelo.Design.TextAreaScroll textAreaScroll5;
    public Modelo.Design.TextFieldSearchOption txtBusqueda;
    public Modelo.Design.Combobox vigente;
    // End of variables declaration//GEN-END:variables
}
