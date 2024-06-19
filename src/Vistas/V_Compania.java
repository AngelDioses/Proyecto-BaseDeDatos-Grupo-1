
package Vistas;

import Modelo.Interface.SearchOptinEvent;
import Modelo.SearchOption;
import javax.swing.ImageIcon;
import Modelo.GenerarPDF.PDFCompania;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.io.IOException;
import Modelo.GenerarExcel.ExcelGenerator;



public class V_Compania extends javax.swing.JPanel {
    
    private V_Cliente vCliente;
    private V_Empleado vEmpleado;
    private V_Proveedor vProveedor;
    private V_Empresa vEmpresa;
    private V_Proyecto vProyecto;
    private V_Partida vPartida;
    private V_Proy_Partida vProyPartida;
    private V_Partida_Mezcla vPartidaMezcla;
    private V_Proy_Partida_Mezcla vProyPartidaMezcla;
    private V_DProy_Partida_Mezcla vDProyPartidaMezcla;
    private V_Venta vVenta;
    private V_Compra vCompra;
    private V_Compania vCompania;
    
    
          
    public int opt=0;
    public V_Compania() {
        initComponents();
        setOpaque(false);
        
        vCliente = new V_Cliente();
        vEmpleado = new V_Empleado();
        vProveedor = new V_Proveedor();
        vEmpresa = new V_Empresa();
        vProyecto = new V_Proyecto();
        vPartida = new V_Partida();
        vProyPartida = new V_Proy_Partida();
        vPartidaMezcla = new V_Partida_Mezcla();
        vProyPartidaMezcla = new V_Proy_Partida_Mezcla();
        vDProyPartidaMezcla = new V_DProy_Partida_Mezcla();
        vVenta = new V_Venta();
        vCompra = new V_Compra();
        vCompania = this; 
    }
    
    public void init(){
        tablaCia.fixTable(jScrollPane1);
        txtBusqueda.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                opt=index;
                txtBusqueda.setHint("Buscar " + option.getName() + "...");
            }
        });
        txtBusqueda.addOption(new SearchOption("todo", new ImageIcon(getClass().getResource("/image/loadall.png"))));
        txtBusqueda.addOption(new SearchOption("por descripción", new ImageIcon(getClass().getResource("/image/document.png"))));
        txtBusqueda.addOption(new SearchOption("por desc. Corta", new ImageIcon(getClass().getResource("/image/ruc.png"))));
    }
    public void inhabilitarCampos(){
        desCia.setEnabled(false);
        desCorta.setEnabled(false);
        vigente.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelShadow2 = new Modelo.Design.PanelShadow();
        textAreaScroll1 = new Modelo.Design.TextAreaScroll();
        desCia = new Modelo.Design.TextArea();
        textAreaScroll5 = new Modelo.Design.TextAreaScroll();
        desCorta = new Modelo.Design.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCia = new Modelo.DesignTable.Tabla();
        btt_Registrar = new Modelo.Design.Button();
        btt_Eliminar = new Modelo.Design.Button();
        btt_Actualizar = new Modelo.Design.Button();
        vigente = new Modelo.Design.Combobox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtBusqueda = new Modelo.Design.TextFieldSearchOption();

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/compania.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("¡Registre la compañía!");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 179, 255));
        jLabel4.setText("Complete la información de la compañía");

        textAreaScroll1.setLabelText("Descripción Compania:");

        desCia.setBorder(null);
        desCia.setColumns(20);
        desCia.setForeground(new java.awt.Color(40, 40, 40));
        desCia.setRows(5);
        textAreaScroll1.setViewportView(desCia);

        textAreaScroll5.setLabelText("Descripción Corta:");

        desCorta.setBorder(null);
        desCorta.setColumns(20);
        desCorta.setForeground(new java.awt.Color(40, 40, 40));
        desCorta.setRows(5);
        textAreaScroll5.setViewportView(desCorta);

        tablaCia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodCia", "Descripción", "DescripciónCorta", "Vigente"
            }
        ));
        jScrollPane1.setViewportView(tablaCia);

        btt_Registrar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar.setText("Registrar");
        btt_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_RegistrarActionPerformed(evt);
            }
        });

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

        jButton2.setText("Generar Reporte Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vigente, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButton1)
                .addGap(48, 48, 48)
                .addComponent(jButton2)
                .addGap(47, 47, 47))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelShadow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4)))
                .addGap(32, 32, 32)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btt_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_RegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btt_RegistrarActionPerformed

    
   public javax.swing.JTable getTablaCompania() {
        return tablaCia;
    }
   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PDFCompania pdf = new PDFCompania();
        pdf.generarPDF(tablaCia);
        JOptionPane.showMessageDialog(this, "PDF generado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        generarExcelGlobal();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void generarExcelGlobal() {
        try {
            ExcelGenerator excelGenerator = new ExcelGenerator();

            // Obtener las tablas desde las vistas correspondientes
            List<JTable> tablas = new ArrayList<>();
            tablas.add(vCliente.getTablaCliente());  // Ejemplo, reemplaza con el método correcto para obtener la tabla de proyecto
            tablas.add(vEmpleado.getTablaEmpleado());  // Ejemplo, reemplaza con el método correcto para obtener la tabla de empleado
            tablas.add(vProveedor.getTablaProveedor());
            tablas.add(vEmpresa.getTablaEmpresa());
            tablas.add(vProyecto.getTablaProyecto());
            tablas.add(vPartida.getTablaPartida_I());
            tablas.add(vPartida.getTablaPartida_E());
            tablas.add(vProyPartida.getTablaProy_Partida_I());
            tablas.add(vProyPartida.getTablaProy_Partida_E());
            tablas.add(vPartidaMezcla.getTablaPartida_Mezcla_I());
            tablas.add(vPartidaMezcla.getTablaPartida_Mezcla_E());
            tablas.add(vProyPartidaMezcla.getTablaProy_Partida_Mezcla_I());
            tablas.add(vProyPartidaMezcla.getTablaProy_Partida_Mezcla_E());
            tablas.add(vDProyPartidaMezcla.getTablaDProy_Partida_I());
            tablas.add(vDProyPartidaMezcla.getTablaDProy_Partida_E());
            tablas.add(vVenta.getTablaVenta());
            tablas.add(vCompra.getTablaCompra());
            tablas.add(vCompania.getTablaCompania());
                      

            List<String> nombresHojas = new ArrayList<>();
            nombresHojas.add("Cliente");
            nombresHojas.add("Empleado");
            nombresHojas.add("Proveedor");
            nombresHojas.add("Empresa");
            nombresHojas.add("Proyecto");
            nombresHojas.add("Partida Ingreso");
            nombresHojas.add("Partida Egreso");
            nombresHojas.add("Proyecto Partida Ingreso");
            nombresHojas.add("Proyecto Partida Egreso");
            nombresHojas.add("Partida Mezcla Ingreso");
            nombresHojas.add("Partida Mezcla Egreso");
            nombresHojas.add("Proyecto Partida Mezcla Ingreso");
            nombresHojas.add("Proyecto Partida Mezcla Egreso");
            nombresHojas.add("Descripcion Proyecto Partida Ingreso");
            nombresHojas.add("Descripcion Proyecto Partida Egreso");
            nombresHojas.add("Venta");
            nombresHojas.add("Compra");
            nombresHojas.add("Compania");

            
            // Agrega más nombres de hojas según corresponda a cada tabla

            excelGenerator.generarExcel(tablas, nombresHojas, "reporteGlobal.xlsx");

            JOptionPane.showMessageDialog(this, "Archivos Excel generados exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar los archivos Excel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Modelo.Design.Button btt_Actualizar;
    public Modelo.Design.Button btt_Eliminar;
    public Modelo.Design.Button btt_Registrar;
    private com.raven.datechooser.DateChooser dateChooser1;
    public Modelo.Design.TextArea desCia;
    public Modelo.Design.TextArea desCorta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    private Modelo.Design.PanelShadow panelShadow2;
    private Modelo.Design.PictureBox pictureBox1;
    public Modelo.DesignTable.Tabla tablaCia;
    private Modelo.Design.TextAreaScroll textAreaScroll1;
    private Modelo.Design.TextAreaScroll textAreaScroll5;
    public Modelo.Design.TextFieldSearchOption txtBusqueda;
    public Modelo.Design.Combobox vigente;
    // End of variables declaration//GEN-END:variables
}
