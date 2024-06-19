package Vistas;
import Modelo.GenerarPDF.PDFVenta;
import javax.swing.JOptionPane;

public class V_Venta extends javax.swing.JPanel {

    public V_Venta() {
        initComponents();
        setOpaque(false);
    }
    
    public void init(){
        tablaVenta.fixTable(jScrollPane1);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelShadow1 = new Modelo.Design.PanelShadow();
        pictureBox2 = new Modelo.Design.PictureBox();
        desPersona = new Modelo.Design.TextField();
        jLabel1 = new javax.swing.JLabel();
        codCliente = new Modelo.Design.Combobox();
        panelShadow2 = new Modelo.Design.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVenta = new Modelo.DesignTable.Tabla();
        btt_Limpiar = new Modelo.Design.Button();
        btt_Borrar = new Modelo.Design.Button();
        btt_Generar = new Modelo.Design.Button();
        impMonto = new Modelo.Design.TextField();
        tipCambio = new Modelo.Design.TextField();
        impNetoMN = new Modelo.Design.TextField();
        impIGVMN = new Modelo.Design.TextField();
        impTotalMN = new Modelo.Design.TextField();
        eMoneda = new Modelo.Design.Combobox();
        fecAbono = new Modelo.Design.TextField();
        fecCompra = new Modelo.Design.TextField();
        pictureBox6 = new Modelo.Design.PictureBox();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelShadow3 = new Modelo.Design.PanelShadow();
        pictureBox3 = new Modelo.Design.PictureBox();
        jLabel2 = new javax.swing.JLabel();
        codPyto = new Modelo.Design.Combobox();
        nomPyto = new Modelo.Design.TextField();
        panelShadow4 = new Modelo.Design.PanelShadow();
        pictureBox4 = new Modelo.Design.PictureBox();
        jLabel5 = new javax.swing.JLabel();
        nroCP = new Modelo.Design.TextField();
        codEstado = new Modelo.Design.Combobox();
        eCompPago = new Modelo.Design.Combobox();
        textAreaScroll1 = new Modelo.Design.TextAreaScroll();
        desAbono = new Modelo.Design.TextArea();
        nroPago = new Modelo.Design.TextField();
        btt_Agregar = new Modelo.Design.Button();
        btnTablaDetalleV = new Modelo.Design.ButtonCircle();
        btnTablaV = new Modelo.Design.ButtonCircle();
        panelShadow5 = new Modelo.Design.PanelShadow();
        pictureBox5 = new Modelo.Design.PictureBox();
        jLabel6 = new javax.swing.JLabel();
        codPartida = new Modelo.Design.Combobox();
        desPartida = new Modelo.Design.TextField();
        semilla = new Modelo.Design.TextField();
        impNetoMNPart = new Modelo.Design.TextField();
        impIgvMNPart = new Modelo.Design.TextField();

        dateChooser1.setTextRefernce(fecCompra);

        dateChooser2.setTextRefernce(fecAbono);

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/ventaT.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("¡Registre las ventas!");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 179, 255));
        jLabel4.setText("Complete la información de sus ventas");

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/lapiz_registro.png"))); // NOI18N

        desPersona.setEditable(false);
        desPersona.setLabelText("Nombre Cliente");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Datos del Cliente");

        codCliente.setLabeText("Código Cliente");

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(desPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(codCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pictureBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(codCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(desPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sec", "Cod. Partida", "Nombre Part.", "Semilla", "SubTotal", "IGV", "Total"
            }
        ));
        jScrollPane1.setViewportView(tablaVenta);

        btt_Limpiar.setBackground(new java.awt.Color(211, 215, 252));
        btt_Limpiar.setForeground(new java.awt.Color(51, 102, 255));
        btt_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clean.png"))); // NOI18N
        btt_Limpiar.setText("Limpiar");

        btt_Borrar.setBackground(new java.awt.Color(252, 221, 221));
        btt_Borrar.setForeground(new java.awt.Color(255, 0, 0));
        btt_Borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borrar.png"))); // NOI18N
        btt_Borrar.setText("Borrar");

        btt_Generar.setBackground(new java.awt.Color(221, 252, 234));
        btt_Generar.setForeground(new java.awt.Color(0, 153, 51));
        btt_Generar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dventa.png"))); // NOI18N
        btt_Generar.setText("Generar");

        impMonto.setLabelText("Monto");

        tipCambio.setLabelText("Tipo Cambio");

        impNetoMN.setLabelText("SubTotal");

        impIGVMN.setLabelText("IGV");

        impTotalMN.setLabelText("Total");

        eMoneda.setLabeText("Moneda");

        fecAbono.setLabelText("Fecha Abono");

        fecCompra.setLabelText("Fecha Compra");

        pictureBox6.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/lapiz_registro.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Fechas");

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
                .addGap(28, 28, 28)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(impMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(impNetoMN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(impIGVMN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(impTotalMN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                            .addComponent(fecCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(fecAbono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(btt_Generar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                            .addComponent(btt_Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btt_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(pictureBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pictureBox6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(impMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(impNetoMN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(impIGVMN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(impTotalMN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelShadow3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/lapiz_registro.png"))); // NOI18N
        panelShadow3.add(pictureBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 25, 25));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Datos Proyecto");
        panelShadow3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 25));

        codPyto.setLabeText("Proyecto");
        panelShadow3.add(codPyto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 44));

        nomPyto.setEditable(false);
        nomPyto.setLabelText("Nombre Proyecto");
        panelShadow3.add(nomPyto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 140, -1));

        panelShadow4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pictureBox4.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/lapiz_registro.png"))); // NOI18N
        panelShadow4.add(pictureBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 25, 25));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Datos Generales");
        panelShadow4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 25));

        nroCP.setLabelText("Nro CP");
        panelShadow4.add(nroCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 90, 60));

        codEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disponible", "No Disp.", "Reservado" }));
        codEstado.setSelectedIndex(-1);
        codEstado.setLabeText("Estado");
        panelShadow4.add(codEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 110, 40));

        eCompPago.setLabeText("CompPago");
        panelShadow4.add(eCompPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 40));

        textAreaScroll1.setLabelText("Desc. Abono");

        desAbono.setColumns(20);
        desAbono.setRows(5);
        textAreaScroll1.setViewportView(desAbono);

        panelShadow4.add(textAreaScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 220, 60));

        nroPago.setLabelText("Nro Pago");
        panelShadow4.add(nroPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 90, 60));

        btt_Agregar.setBackground(new java.awt.Color(236, 76, 124));
        btt_Agregar.setForeground(new java.awt.Color(255, 255, 255));
        btt_Agregar.setText("AGREGAR");
        btt_Agregar.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N

        btnTablaDetalleV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnTablaDetalleV.setToolTipText("ver tabla detalle venta");

        btnTablaV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnTablaV.setToolTipText("ver tabla venta");

        panelShadow5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pictureBox5.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/lapiz_registro.png"))); // NOI18N
        panelShadow5.add(pictureBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 25, 25));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Datos Partida");
        panelShadow5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 25));

        codPartida.setLabeText("Partida");
        panelShadow5.add(codPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 44));

        desPartida.setEditable(false);
        desPartida.setLabelText("Desc. Partida");
        panelShadow5.add(desPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 120, -1));

        semilla.setLabelText("Semilla");
        panelShadow5.add(semilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 120, 60));

        impNetoMNPart.setLabelText("ImpNeto");
        panelShadow5.add(impNetoMNPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 120, 60));

        impIgvMNPart.setLabelText("IGV");
        panelShadow5.add(impIgvMNPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 120, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTablaV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTablaDetalleV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btt_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelShadow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnTablaV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTablaDetalleV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btt_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelShadow5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public javax.swing.JTable getTablaVenta() {
        return tablaVenta;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PDFVenta pdf = new PDFVenta();
        pdf.generarPDF(tablaVenta);
        JOptionPane.showMessageDialog(this, "PDF generado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Modelo.Design.ButtonCircle btnTablaDetalleV;
    public Modelo.Design.ButtonCircle btnTablaV;
    public Modelo.Design.Button btt_Agregar;
    public Modelo.Design.Button btt_Borrar;
    public Modelo.Design.Button btt_Generar;
    public Modelo.Design.Button btt_Limpiar;
    public Modelo.Design.Combobox codCliente;
    public Modelo.Design.Combobox codEstado;
    public Modelo.Design.Combobox codPartida;
    public Modelo.Design.Combobox codPyto;
    public com.raven.datechooser.DateChooser dateChooser1;
    public com.raven.datechooser.DateChooser dateChooser2;
    public Modelo.Design.TextArea desAbono;
    public Modelo.Design.TextField desPartida;
    public Modelo.Design.TextField desPersona;
    public Modelo.Design.Combobox eCompPago;
    public Modelo.Design.Combobox eMoneda;
    public Modelo.Design.TextField fecAbono;
    public Modelo.Design.TextField fecCompra;
    public Modelo.Design.TextField impIGVMN;
    public Modelo.Design.TextField impIgvMNPart;
    public Modelo.Design.TextField impMonto;
    public Modelo.Design.TextField impNetoMN;
    public Modelo.Design.TextField impNetoMNPart;
    public Modelo.Design.TextField impTotalMN;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public Modelo.Design.TextField nomPyto;
    public Modelo.Design.TextField nroCP;
    public Modelo.Design.TextField nroPago;
    private Modelo.Design.PanelShadow panelShadow1;
    private Modelo.Design.PanelShadow panelShadow2;
    private Modelo.Design.PanelShadow panelShadow3;
    private Modelo.Design.PanelShadow panelShadow4;
    private Modelo.Design.PanelShadow panelShadow5;
    private Modelo.Design.PictureBox pictureBox1;
    private Modelo.Design.PictureBox pictureBox2;
    private Modelo.Design.PictureBox pictureBox3;
    private Modelo.Design.PictureBox pictureBox4;
    private Modelo.Design.PictureBox pictureBox5;
    private Modelo.Design.PictureBox pictureBox6;
    public Modelo.Design.TextField semilla;
    public Modelo.DesignTable.Tabla tablaVenta;
    private Modelo.Design.TextAreaScroll textAreaScroll1;
    public Modelo.Design.TextField tipCambio;
    // End of variables declaration//GEN-END:variables
}
