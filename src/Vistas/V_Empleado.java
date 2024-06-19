package Vistas;

import Modelo.Interface.SearchOptinEvent;
import Modelo.SearchOption;
import javax.swing.ImageIcon;
import Modelo.GenerarPDF.PDFEmpleado;
import javax.swing.JOptionPane;




public class V_Empleado extends javax.swing.JPanel {
    public int opt=0;
    public V_Empleado() {
        initComponents();
        setOpaque(false);
    }
    
    public void init(){
        tablaEmpleado.fixTable(jScrollPane1);
        txtBusqueda.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                opt=index;
                txtBusqueda.setHint("Buscar " + option.getName() + "...");
            }
        });
        txtBusqueda.addOption(new SearchOption("todo", new ImageIcon(getClass().getResource("/image/loadall.png"))));
        txtBusqueda.addOption(new SearchOption("por codigo", new ImageIcon(getClass().getResource("/image/user.png"))));
        txtBusqueda.addOption(new SearchOption("por DNI", new ImageIcon(getClass().getResource("/image/document.png"))));
        txtBusqueda.addOption(new SearchOption("por CIP", new ImageIcon(getClass().getResource("/image/ruc.png"))));
        txtBusqueda.addOption(new SearchOption("por celular", new ImageIcon(getClass().getResource("/image/tel.png"))));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        txtRuta = new Modelo.Design.TextField();
        popupMenu = new javax.swing.JPopupMenu();
        actualizaTabla = new javax.swing.JMenuItem();
        nuevo = new javax.swing.JMenuItem();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelShadow1 = new Modelo.Design.PanelShadow();
        celular = new Modelo.Design.TextField();
        direcc = new Modelo.Design.TextField();
        fecNac = new Modelo.Design.TextField();
        email = new Modelo.Design.TextField();
        dni = new Modelo.Design.TextField();
        codCargo = new Modelo.Design.TextField();
        hobby = new Modelo.Design.TextField();
        nroCIP = new Modelo.Design.TextField();
        pictureBox2 = new Modelo.Design.PictureBox();
        fecCIPVig = new Modelo.Design.TextField();
        textAreaScroll3 = new Modelo.Design.TextAreaScroll();
        desPersona = new Modelo.Design.TextArea();
        textAreaScroll5 = new Modelo.Design.TextAreaScroll();
        desCorta = new Modelo.Design.TextArea();
        textAreaScroll4 = new Modelo.Design.TextAreaScroll();
        desAlterna = new Modelo.Design.TextArea();
        textAreaScroll6 = new Modelo.Design.TextAreaScroll();
        desCortaAlt = new Modelo.Design.TextArea();
        textAreaScroll2 = new Modelo.Design.TextAreaScroll();
        observac = new Modelo.Design.TextArea();
        flgEmplIEA = new Modelo.Design.Combobox();
        vigente = new Modelo.Design.Combobox();
        buttonCircle1 = new Modelo.Design.ButtonCircle();
        licCond = new Modelo.Design.Combobox();
        codCia = new Modelo.Design.TextField();
        panelShadow2 = new Modelo.Design.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleado = new Modelo.DesignTable.Tabla();
        btt_Registrar = new Modelo.Design.Button();
        btt_Eliminar = new Modelo.Design.Button();
        btt_Actualizar = new Modelo.Design.Button();
        jButton1 = new javax.swing.JButton();
        txtBusqueda = new Modelo.Design.TextFieldSearchOption();

        dateChooser1.setTextRefernce(fecNac);

        dateChooser2.setTextRefernce(fecCIPVig);

        txtRuta.setBackground(new java.awt.Color(40, 40, 40));
        txtRuta.setForeground(new java.awt.Color(245, 245, 245));
        txtRuta.setLabelText("Ruta Foto");

        popupMenu.setLabel("Acciones");

        actualizaTabla.setText("Actualizar");
        popupMenu.add(actualizaTabla);

        nuevo.setText("Nuevo");
        popupMenu.add(nuevo);

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/registraEmpleado.jpg"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("¡Registre los empleados!");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 179, 255));
        jLabel2.setText("Complete la información de sus empleados");

        panelShadow1.setComponentPopupMenu(popupMenu);

        celular.setForeground(new java.awt.Color(40, 40, 40));
        celular.setLabelText("Celular:");

        direcc.setForeground(new java.awt.Color(40, 40, 40));
        direcc.setLabelText("Dirección:");

        fecNac.setForeground(new java.awt.Color(40, 40, 40));
        fecNac.setLabelText("Fecha Nacimiento:");

        email.setForeground(new java.awt.Color(40, 40, 40));
        email.setLabelText("Email: ");

        dni.setForeground(new java.awt.Color(40, 40, 40));
        dni.setLabelText("DNI:");

        codCargo.setForeground(new java.awt.Color(40, 40, 40));
        codCargo.setLabelText("Código del Cargo:");

        hobby.setForeground(new java.awt.Color(40, 40, 40));
        hobby.setLabelText("Hobby");

        nroCIP.setForeground(new java.awt.Color(40, 40, 40));
        nroCIP.setLabelText("N° CIP:");

        pictureBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150), 4));
        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/imagenNoDisponible.png"))); // NOI18N

        fecCIPVig.setForeground(new java.awt.Color(40, 40, 40));
        fecCIPVig.setLabelText("Fecha CIP Vigente:");

        textAreaScroll3.setLabelText("Descripción Persona:");

        desPersona.setBorder(null);
        desPersona.setColumns(20);
        desPersona.setForeground(new java.awt.Color(40, 40, 40));
        desPersona.setRows(5);
        textAreaScroll3.setViewportView(desPersona);

        textAreaScroll5.setLabelText("Descripción Corta:");

        desCorta.setBorder(null);
        desCorta.setColumns(20);
        desCorta.setForeground(new java.awt.Color(40, 40, 40));
        desCorta.setRows(5);
        textAreaScroll5.setViewportView(desCorta);

        textAreaScroll4.setLabelText("Descripción Alterna:");

        desAlterna.setBorder(null);
        desAlterna.setColumns(20);
        desAlterna.setForeground(new java.awt.Color(40, 40, 40));
        desAlterna.setRows(5);
        textAreaScroll4.setViewportView(desAlterna);

        textAreaScroll6.setLabelText("Descripción Corta Alterna:");

        desCortaAlt.setBorder(null);
        desCortaAlt.setColumns(20);
        desCortaAlt.setForeground(new java.awt.Color(40, 40, 40));
        desCortaAlt.setRows(5);
        textAreaScroll6.setViewportView(desCortaAlt);

        textAreaScroll2.setLabelText("Observaciones:");

        observac.setBorder(null);
        observac.setColumns(20);
        observac.setForeground(new java.awt.Color(40, 40, 40));
        observac.setRows(5);
        textAreaScroll2.setViewportView(observac);

        flgEmplIEA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empresa Consorcio", "Cliente", "Ambos" }));
        flgEmplIEA.setSelectedIndex(-1);
        flgEmplIEA.setLabeText("Tipo:");

        vigente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigente", "No Vigente" }));
        vigente.setSelectedIndex(-1);
        vigente.setLabeText("Estado:");

        buttonCircle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N

        licCond.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sí", "No" }));
        licCond.setSelectedIndex(-1);
        licCond.setLabeText("Licencia Conducir");

        codCia.setEditable(false);
        codCia.setForeground(new java.awt.Color(40, 40, 40));
        codCia.setLabelText("CodCia:");

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(flgEmplIEA, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(codCia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fecNac, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(celular, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelShadow1Layout.createSequentialGroup()
                                        .addComponent(direcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))
                                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelShadow1Layout.createSequentialGroup()
                                        .addComponent(licCond, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hobby, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(17, 17, 17)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nroCIP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codCargo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fecCIPVig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textAreaScroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textAreaScroll6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(direcc, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nroCIP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codCia, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fecNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hobby, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fecCIPVig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(licCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(pictureBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)))
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textAreaScroll4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textAreaScroll5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textAreaScroll6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(flgEmplIEA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );

        panelShadow2.setComponentPopupMenu(popupMenu);

        tablaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodPer", "Desc Persona", "DNI", "CIP", "Fecha Nac", "Lic Cond", "Celular", "Vigente"
            }
        ));
        tablaEmpleado.setComponentPopupMenu(popupMenu);
        jScrollPane1.setViewportView(tablaEmpleado);

        btt_Registrar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar.setText("Registrar");

        btt_Eliminar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Eliminar.setText("Eliminar");

        btt_Actualizar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Actualizar.setText("Actualizar");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(31, 31, 31))
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
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelShadow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public javax.swing.JTable getTablaEmpleado() {
        return tablaEmpleado;
    }
     
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PDFEmpleado pdf = new PDFEmpleado();
        pdf.generarPDF(tablaEmpleado);
        JOptionPane.showMessageDialog(this, "PDF generado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem actualizaTabla;
    public Modelo.Design.Button btt_Actualizar;
    public Modelo.Design.Button btt_Eliminar;
    public Modelo.Design.Button btt_Registrar;
    public Modelo.Design.ButtonCircle buttonCircle1;
    public Modelo.Design.TextField celular;
    public Modelo.Design.TextField codCargo;
    public Modelo.Design.TextField codCia;
    public com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    public Modelo.Design.TextArea desAlterna;
    public Modelo.Design.TextArea desCorta;
    public Modelo.Design.TextArea desCortaAlt;
    public Modelo.Design.TextArea desPersona;
    public Modelo.Design.TextField direcc;
    public Modelo.Design.TextField dni;
    public Modelo.Design.TextField email;
    public Modelo.Design.TextField fecCIPVig;
    public Modelo.Design.TextField fecNac;
    public Modelo.Design.Combobox flgEmplIEA;
    public Modelo.Design.TextField hobby;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public Modelo.Design.Combobox licCond;
    public Modelo.Design.TextField nroCIP;
    public javax.swing.JMenuItem nuevo;
    public Modelo.Design.TextArea observac;
    private Modelo.Design.PanelShadow panelShadow1;
    private Modelo.Design.PanelShadow panelShadow2;
    private Modelo.Design.PictureBox pictureBox1;
    public Modelo.Design.PictureBox pictureBox2;
    public javax.swing.JPopupMenu popupMenu;
    public Modelo.DesignTable.Tabla tablaEmpleado;
    private Modelo.Design.TextAreaScroll textAreaScroll2;
    private Modelo.Design.TextAreaScroll textAreaScroll3;
    private Modelo.Design.TextAreaScroll textAreaScroll4;
    private Modelo.Design.TextAreaScroll textAreaScroll5;
    private Modelo.Design.TextAreaScroll textAreaScroll6;
    public Modelo.Design.TextFieldSearchOption txtBusqueda;
    public Modelo.Design.TextField txtRuta;
    public Modelo.Design.Combobox vigente;
    // End of variables declaration//GEN-END:variables
}
