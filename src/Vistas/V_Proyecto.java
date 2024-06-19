package Vistas;

import Modelo.Interface.SearchOptinEvent;
import Modelo.SearchOption;
import javax.swing.ImageIcon;
import Modelo.GenerarPDF.PDFConsultaProyecto;
import javax.swing.JOptionPane;


public class V_Proyecto extends javax.swing.JPanel {
    public int opt=-1;
    public V_Proyecto() {
        initComponents();
        setOpaque(false);
    }
    
    public void init(){
        tablaProyecto.fixTable(jScrollPane1);
        txtBusqueda.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                opt=index-1;
                txtBusqueda.setHint("Buscar " + option.getName() + "...");
            }
        });
        txtBusqueda.addOption(new SearchOption("todo", new ImageIcon(getClass().getResource("/image/loadall.png"))));
        txtBusqueda.addOption(new SearchOption("por codigo proyecto", new ImageIcon(getClass().getResource("/image/license.png"))));
        txtBusqueda.addOption(new SearchOption("por nombre del proyecto", new ImageIcon(getClass().getResource("/image/general.png"))));
        txtBusqueda.addOption(new SearchOption("por empleado jefe", new ImageIcon(getClass().getResource("/image/6_s.png"))));
        txtBusqueda.addOption(new SearchOption("por ciaContrata", new ImageIcon(getClass().getResource("/image/document.png"))));
        txtBusqueda.addOption(new SearchOption("por codigo cliente", new ImageIcon(getClass().getResource("/image/user.png"))));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        txtRuta = new Modelo.Design.TextField();
        popupMenu = new javax.swing.JPopupMenu();
        actualizaTabla = new javax.swing.JMenuItem();
        nuevo = new javax.swing.JMenuItem();
        textFieldBusqueda3 = new Modelo.Design.TextFieldBusqueda();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelShadow1 = new Modelo.Design.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProyecto = new Modelo.DesignTable.Tabla();
        panelShadow2 = new Modelo.Design.PanelShadow();
        nomPyto = new Modelo.Design.TextField();
        textAreaScroll1 = new Modelo.Design.TextAreaScroll();
        observac = new Modelo.Design.TextArea();
        btt_Registrar = new Modelo.Design.Button();
        btnAdd = new Modelo.Design.ButtonCircle();
        pictureBox2 = new Modelo.Design.PictureBox();
        emplJefeProy = new Modelo.Design.Combobox();
        impIGV = new Modelo.Design.TextField();
        costoTotSinIGV = new Modelo.Design.TextField();
        annoFin = new Modelo.Design.TextField();
        annoIni = new Modelo.Design.TextField();
        ciaContrata = new Modelo.Design.Combobox();
        codCliente = new Modelo.Design.Combobox();
        estPyto = new Modelo.Design.Combobox();
        valRefer = new Modelo.Design.TextField();
        btt_Eliminar = new Modelo.Design.Button();
        btt_Actualizar = new Modelo.Design.Button();
        costoTotal = new Modelo.Design.TextField();
        jButton1 = new javax.swing.JButton();
        txtBusqueda = new Modelo.Design.TextFieldSearchOption();

        txtRuta.setBackground(new java.awt.Color(40, 40, 40));
        txtRuta.setForeground(new java.awt.Color(245, 245, 245));
        txtRuta.setLabelText("Ruta Foto");

        popupMenu.setLabel("Acciones");

        actualizaTabla.setText("Actualizar");
        popupMenu.add(actualizaTabla);

        nuevo.setText("Nuevo");
        popupMenu.add(nuevo);

        textFieldBusqueda3.setOpaque(true);

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/proyecto.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("¡Registre los proyectos!");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 179, 255));
        jLabel4.setText("Complete la información de sus proyectos");

        panelShadow1.setComponentPopupMenu(popupMenu);

        tablaProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodPyto", "NomPyto", "EmplJefe", "CiaContrata", "CodCliente", "FecReg", "EstPyto", "FecEstado", "CostoTotal", "Año Inicio", "Año Fin", "Vigente"
            }
        ));
        tablaProyecto.setComponentPopupMenu(popupMenu);
        jScrollPane1.setViewportView(tablaProyecto);
        if (tablaProyecto.getColumnModel().getColumnCount() > 0) {
            tablaProyecto.getColumnModel().getColumn(1).setPreferredWidth(130);
            tablaProyecto.getColumnModel().getColumn(3).setPreferredWidth(79);
        }

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        panelShadow2.setComponentPopupMenu(popupMenu);

        nomPyto.setForeground(new java.awt.Color(40, 40, 40));
        nomPyto.setLabelText("Nombre del Proyecto:");

        textAreaScroll1.setLabelText("Observaciones:");

        observac.setBorder(null);
        observac.setColumns(20);
        observac.setForeground(new java.awt.Color(40, 40, 40));
        observac.setRows(5);
        textAreaScroll1.setViewportView(observac);

        btt_Registrar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar.setLabel("Registrar");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N

        pictureBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150), 4));
        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/imagenNoDisponible.png"))); // NOI18N

        emplJefeProy.setLabeText("Empleado Jefe:");

        impIGV.setForeground(new java.awt.Color(40, 40, 40));
        impIGV.setLabelText("Impuesto IGV:");

        costoTotSinIGV.setForeground(new java.awt.Color(40, 40, 40));
        costoTotSinIGV.setLabelText("Costo Total sin IGV:");

        annoFin.setForeground(new java.awt.Color(40, 40, 40));
        annoFin.setLabelText("Año Fin:");

        annoIni.setForeground(new java.awt.Color(40, 40, 40));
        annoIni.setLabelText("Año Inicio:");

        ciaContrata.setLabeText("Empresa Venta Contratada:");

        codCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        codCliente.setSelectedIndex(-1);
        codCliente.setLabeText("Código Cliente:");

        estPyto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3" }));
        estPyto.setSelectedIndex(-1);
        estPyto.setLabeText("Estado del Proyecto:");

        valRefer.setForeground(new java.awt.Color(40, 40, 40));
        valRefer.setLabelText("Valor Referencial:");

        btt_Eliminar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Eliminar.setLabel("Eliminar");

        btt_Actualizar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Actualizar.setLabel("Actualizar");

        costoTotal.setForeground(new java.awt.Color(40, 40, 40));
        costoTotal.setLabelText("Costo Total:");

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
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                            .addComponent(emplJefeProy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ciaContrata, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(nomPyto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(annoIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(estPyto, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(annoFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valRefer, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btt_Registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(costoTotSinIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(impIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addComponent(btt_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btt_Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButton1)))
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomPyto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costoTotSinIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emplJefeProy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ciaContrata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(impIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(annoIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(annoFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(valRefer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estPyto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(0, 46, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(18, Short.MAX_VALUE))
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public javax.swing.JTable getTablaProyecto() {
        return tablaProyecto;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PDFConsultaProyecto pdf = new PDFConsultaProyecto();
        pdf.generarPDF(tablaProyecto);
        JOptionPane.showMessageDialog(this, "PDF generado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem actualizaTabla;
    public Modelo.Design.TextField annoFin;
    public Modelo.Design.TextField annoIni;
    public Modelo.Design.ButtonCircle btnAdd;
    public Modelo.Design.Button btt_Actualizar;
    public Modelo.Design.Button btt_Eliminar;
    public Modelo.Design.Button btt_Registrar;
    public Modelo.Design.Combobox ciaContrata;
    public Modelo.Design.Combobox codCliente;
    public Modelo.Design.TextField costoTotSinIGV;
    public Modelo.Design.TextField costoTotal;
    private com.raven.datechooser.DateChooser dateChooser1;
    public Modelo.Design.Combobox emplJefeProy;
    public Modelo.Design.Combobox estPyto;
    public Modelo.Design.TextField impIGV;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public Modelo.Design.TextField nomPyto;
    public javax.swing.JMenuItem nuevo;
    public Modelo.Design.TextArea observac;
    private Modelo.Design.PanelShadow panelShadow1;
    private Modelo.Design.PanelShadow panelShadow2;
    private Modelo.Design.PictureBox pictureBox1;
    public Modelo.Design.PictureBox pictureBox2;
    public javax.swing.JPopupMenu popupMenu;
    public Modelo.DesignTable.Tabla tablaProyecto;
    private Modelo.Design.TextAreaScroll textAreaScroll1;
    public Modelo.Design.TextFieldBusqueda textFieldBusqueda3;
    public Modelo.Design.TextFieldSearchOption txtBusqueda;
    public Modelo.Design.TextField txtRuta;
    public Modelo.Design.TextField valRefer;
    // End of variables declaration//GEN-END:variables
}
