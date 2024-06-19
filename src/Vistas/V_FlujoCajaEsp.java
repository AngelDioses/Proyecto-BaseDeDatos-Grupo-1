package Vistas;

import Modelo.Conexion.PbConexion;
import Modelo.DAO.ProyectoDAO;
import Modelo.Message.Mensaje1;
import Modelo.Proyecto;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.awt.Color;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class V_FlujoCajaEsp extends javax.swing.JFrame {

    int xMouse, yMouse,fila=0;
    PbConexion conexion=new PbConexion();
    Connection con;
    Statement ps;
    ResultSet rs;
    //String sql="SELECT * FROM flujocaja_det";
    int estadoGeneral=0;
    String sql;
    String name="";
    String an="";
    List<Double> ingR=new ArrayList();
    List<Double> ingS=new ArrayList();
    List<Double> egrR=new ArrayList();
    List<Double> egrS=new ArrayList();
    
    DefaultTableModel model = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    Object[] o;
    
    public V_FlujoCajaEsp(){
        initComponents();
        titulo.setText("Flujo de Caja");
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        tablaConsulta.fixTable(jScrollPane1);
        iniciarEvento(this);
        initListarProyecto();
    }
    
    public void initListarProyecto(){
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            codpyto.addItem(lista.get(i).getCodPyto());
            System.out.println(lista.get(i).getCodPyto());
        }
        codpyto.setSelectedIndex(-1);
    }
    
    public void initListarAnio(){
        List<String> lista=retornarAnio();
        for(int i=0;i<lista.size();i++){
            anio.addItem(lista.get(i));
        }
        anio.setSelectedIndex(-1);
    }
    
    public void initTablaDinamica(){
        int bandIngEgr=0;
        int cantIng=0,cantEgr=0;
        System.out.println("AEA 1");
        cantIng=retornarCantIngEgr("I");
        cantEgr=retornarCantIngEgr("E");
        try {
            ResultSet aux; 
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            aux=rs;
            sorter = new TableRowSorter<>(model);
            tablaConsulta.setRowSorter(sorter);
            fila=aux.getMetaData().getColumnCount();
            o=new Object[fila];
            System.out.println("AEA 2");
            Object[] p=new Object[fila];
            System.out.println("AEA 3");
            for (int i = 0; i < o.length; i++) {
                o[i]=aux.getMetaData().getColumnName(i+1);
            }
            System.out.println("AEA 4");
            model.setColumnIdentifiers(o);
            while(aux.next()){
                for (int i = 0; i < o.length; i++) {
                    p[i]=rs.getObject(o[i]+"");
                    
                    System.out.println("Tabla flujo de caja = "+p[i]);
                }
                System.out.println("AEA 5");
                model.addRow(p);
                bandIngEgr++;
                System.out.println("Fila "+bandIngEgr);
                if(bandIngEgr==cantIng){
                    System.out.println("AGREGAR TOTAL INGRESO");
                    if(ingS.isEmpty()){
                        llenarCero(ingS);
                    }
                    if(ingR.isEmpty()){
                        llenarCero(ingR);
                    }
                    switch (estadoGeneral) {
                        case 1 -> model.addRow(new Object[]{"-","TOTAL INGRESO",ingS.get(0),ingR.get(0),ingS.get(1),ingR.get(1),ingS.get(2),ingR.get(2),ingS.get(3),ingR.get(3),ingS.get(4),ingR.get(4),ingS.get(5),ingR.get(5),ingS.get(6),ingR.get(6),ingS.get(7),ingR.get(7),ingS.get(8),ingR.get(8),ingS.get(9),ingR.get(9),ingS.get(10),ingR.get(10),ingS.get(11),ingR.get(11),ingS.get(12),ingR.get(12),ingS.get(13),ingR.get(13)});
                        case 2 -> model.addRow(new Object[]{"-","TOTAL INGRESO",ingR.get(0),ingR.get(1),ingR.get(2),ingR.get(3),ingR.get(4),ingR.get(5),ingR.get(6),ingR.get(7),ingR.get(8),ingR.get(9),ingR.get(10),ingR.get(11),ingR.get(12),ingR.get(13)});
                        case 3 -> model.addRow(new Object[]{"-","TOTAL INGRESO",ingS.get(0),ingS.get(1),ingS.get(2),ingS.get(3),ingS.get(4),ingS.get(5),ingS.get(6),ingS.get(7),ingS.get(8),ingS.get(9),ingS.get(10),ingS.get(11),ingS.get(12),ingS.get(13)});
                    }
                    bandIngEgr++;
                    System.out.println("TOTAL "+bandIngEgr);
                    cantEgr=cantEgr+cantIng+1;
                }
                
                if(bandIngEgr==cantEgr){
                    System.out.println("AGREGAR TOTAL EGRESO");
                    if(egrS.isEmpty()){
                        llenarCero(egrS);
                    }
                    if(egrR.isEmpty()){
                        llenarCero(egrR);
                    }
                    switch (estadoGeneral) {
                        case 1 -> model.addRow(new Object[]{"-","TOTAL EGRESO",egrS.get(0),egrR.get(0),egrS.get(1),egrR.get(1),egrS.get(2),egrR.get(2),egrS.get(3),egrR.get(3),egrS.get(4),egrR.get(4),egrS.get(5),egrR.get(5),egrS.get(6),egrR.get(6),egrS.get(7),egrR.get(7),egrS.get(8),egrR.get(8),egrS.get(9),egrR.get(9),egrS.get(10),egrR.get(10),egrS.get(11),egrR.get(11),egrS.get(12),egrR.get(12),egrS.get(13),egrR.get(13)});
                        case 2 -> model.addRow(new Object[]{"-","TOTAL EGRESO",egrR.get(0),egrR.get(1),egrR.get(2),egrR.get(3),egrR.get(4),egrR.get(5),egrR.get(6),egrR.get(7),egrR.get(8),egrR.get(9),egrR.get(10),egrR.get(11),egrR.get(12),egrR.get(13)});
                        case 3 -> model.addRow(new Object[]{"-","TOTAL EGRESO",egrS.get(0),egrS.get(1),egrS.get(2),egrS.get(3),egrS.get(4),egrS.get(5),egrS.get(6),egrS.get(7),egrS.get(8),egrS.get(9),egrS.get(10),egrS.get(11),egrS.get(12),egrS.get(13)});
                    }
                    bandIngEgr++;
                    System.out.println("TOTAL "+bandIngEgr);
                }
                
            }
            if(ingS.isEmpty()){
                llenarCero(ingS);
            }
            if(ingR.isEmpty()){
                llenarCero(ingR);
            }
            if(egrS.isEmpty()){
                llenarCero(egrS);
            }
            if(egrR.isEmpty()){
                llenarCero(egrR);
            }
            System.out.println("AEA 6");
            switch (estadoGeneral) {
                case 1 -> model.addRow(new Object[]{"-","TOTAL",(ingS.get(0)-egrS.get(0)),(ingR.get(0)-egrR.get(0)),(ingS.get(1)-egrS.get(1)),(ingR.get(1)-egrR.get(1)),(ingS.get(2)-egrS.get(2)),(ingR.get(2)-egrR.get(2)),(ingS.get(3)-egrS.get(3)),(ingR.get(3)-egrR.get(3)),(ingS.get(4)-egrS.get(4)),(ingR.get(4)-egrR.get(4)),(ingS.get(5)-egrS.get(5)),(ingR.get(5)-egrR.get(5)),(ingS.get(6)-egrS.get(6)),(ingR.get(6)-egrR.get(6)),(ingS.get(7)-egrS.get(7)),(ingR.get(7)-egrR.get(7)),(ingS.get(8)-egrS.get(8)),(ingR.get(8)-egrR.get(8)),(ingS.get(9)-egrS.get(9)),(ingR.get(9)-egrR.get(9)),(ingS.get(10)-egrS.get(10)),(ingR.get(10)-egrR.get(10)),(ingS.get(11)-egrS.get(11)),(ingR.get(11)-egrR.get(11)),(ingS.get(12)-egrS.get(12)),(ingR.get(12)-egrR.get(12)),(ingS.get(13)-egrS.get(13)),(ingR.get(13)-egrR.get(13))});
                case 2 -> model.addRow(new Object[]{"-","TOTAL",(ingR.get(0)-egrR.get(0)),(ingR.get(1)-egrR.get(1)),(ingR.get(2)-egrR.get(2)),(ingR.get(3)-egrR.get(3)),(ingR.get(4)-egrR.get(4)),(ingR.get(5)-egrR.get(5)),(ingR.get(6)-egrR.get(6)),(ingR.get(7)-egrR.get(7)),(ingR.get(8)-egrR.get(8)),(ingR.get(9)-egrR.get(9)),(ingR.get(10)-egrR.get(10)),(ingR.get(11)-egrR.get(11)),(ingR.get(12)-egrR.get(12)),(ingR.get(13)-egrR.get(13))});
                case 3 -> model.addRow(new Object[]{"-","TOTAL",(ingS.get(0)-egrS.get(0)),(ingS.get(1)-egrS.get(1)),(ingS.get(2)-egrS.get(2)),(ingS.get(3)-egrS.get(3)),(ingS.get(4)-egrS.get(4)),(ingS.get(5)-egrS.get(5)),(ingS.get(6)-egrS.get(6)),(ingS.get(7)-egrS.get(7)),(ingS.get(8)-egrS.get(8)),(ingS.get(9)-egrS.get(9)),(ingS.get(10)-egrS.get(10)),(ingS.get(11)-egrS.get(11)),(ingS.get(12)-egrS.get(12)),(ingS.get(13)-egrS.get(13))});
            }
            tablaConsulta.setModel(model);
        } catch (Exception e) {
            System.out.println("ERROR: "+e);;
            System.out.println("PRUEBA 123");
        }
        System.out.println("AEA 7");
        ingS.clear();
        ingR.clear();
        egrS.clear();
        egrR.clear();
    }
    
    public void llenarCero(List<Double> lista){
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
        lista.add(0.0);
    }
    
    public int retornarCantIngEgr(String tipo){
        int numFila=0;
        String sql="select count(ingegr) from flujocaja_det where ingegr='"+tipo+"' and anno="+anio.getSelectedItem().toString()+" and codpyto="+Integer.parseInt(codpyto.getSelectedItem().toString());
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                numFila=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println("Cantidad de fila por Ingreso o Egreso: "+numFila);
        return numFila;
    }
    
    public List retornarTotal(String tipo, String realOsupuesto){
        int tot=0;
        List<Double> lista=new ArrayList();
        String sql="select "+realOsupuesto+" from FLUJOCAJA_DET where anno="+Integer.parseInt(anio.getSelectedItem().toString())+" and codpartida=getpadre("+obtenerHijo(varCodCiaGlobalDeLogin,Integer.parseInt(codpyto.getSelectedItem().toString()),tipo)+",'"+tipo+"',"+Integer.parseInt(codpyto.getSelectedItem().toString())+") and codpyto="+Integer.parseInt(codpyto.getSelectedItem().toString())+" and codcia="+varCodCiaGlobalDeLogin+" and ingegr='"+tipo+"'";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                lista.add(rs.getDouble(1));
                lista.add(rs.getDouble(2));
                lista.add(rs.getDouble(3));
                lista.add(rs.getDouble(4));
                lista.add(rs.getDouble(5));
                lista.add(rs.getDouble(6));
                lista.add(rs.getDouble(7));
                lista.add(rs.getDouble(8));
                lista.add(rs.getDouble(9));
                lista.add(rs.getDouble(10));
                lista.add(rs.getDouble(11));
                lista.add(rs.getDouble(12));
                lista.add(rs.getDouble(13));
                lista.add(rs.getDouble(14));
            }
            
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println(" EL NUMERO ES "+tot);
        return lista;
    }
    
    public int obtenerHijo(int codci, int codpyt, String tip){
        int a=0;
        int cont=0;
        String sql="select codpartida from proy_partida where codcia="+codci+" and codpyto="+codpyt+" and ingegr='"+tip+"'";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                if(cont==1){
                    a=rs.getInt(1);
                    cont++;
                }
                cont++;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return a;
    }
    
    public List retornarAnio(){
        List<String> lista=new ArrayList();
        
        String sql="SELECT DISTINCT ANNO FROM FLUJOCAJA_DET WHERE CODPYTO="+Integer.parseInt(codpyto.getSelectedItem().toString())+" AND CODCIA="+varCodCiaGlobalDeLogin+" order by ANNO";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                lista.add(rs.getString(1));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public void iniciarEvento(JFrame frame){
        header.initEvent(this, panelBackground1,1);
    }
    
    private boolean showMessage1(String message){
        Mensaje1 obj = new Mensaje1(Frame.getFrames()[1],true);
        obj.showMessage(message);
        return obj.isAceptar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground1 = new Modelo.Design.PanelBackground();
        txtBuscar = new Modelo.Design.TextFieldBusqueda();
        titulo = new javax.swing.JLabel();
        header = new Modelo.Design.Headboard();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaConsulta = new Modelo.DesignTable.Tabla(1);
        codpyto = new Modelo.Design.Combobox();
        descripcion = new javax.swing.JLabel();
        anio = new Modelo.Design.Combobox();
        btnGeneral = new Modelo.Design.Button();
        btnReal = new Modelo.Design.Button();
        btnSupuesto = new Modelo.Design.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBackground1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelBackground1MouseDragged(evt);
            }
        });
        panelBackground1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBackground1MousePressed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        titulo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(40, 40, 40));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("VISUALICE SUS DATOS");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaConsulta);

        codpyto.setLabeText("Cod. Proyecto");
        codpyto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codpytoActionPerformed(evt);
            }
        });

        descripcion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        descripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descripcion.setText("Proyecto / Año");

        anio.setLabeText("Año");
        anio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                anioItemStateChanged(evt);
            }
        });

        btnGeneral.setBackground(new java.awt.Color(240, 240, 240));
        btnGeneral.setText("General");
        btnGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralActionPerformed(evt);
            }
        });

        btnReal.setBackground(new java.awt.Color(240, 240, 240));
        btnReal.setText("Real");
        btnReal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealActionPerformed(evt);
            }
        });

        btnSupuesto.setBackground(new java.awt.Color(240, 240, 240));
        btnSupuesto.setText("Supuesto");
        btnSupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupuestoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addComponent(codpyto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(codpyto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btnGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBackground1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackground1MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelBackground1MousePressed

    private void panelBackground1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackground1MouseDragged
        int x,y;
        x = evt.getXOnScreen();
        y = evt.getYOnScreen();
        this.setLocation(x-xMouse,y-yMouse);
    }//GEN-LAST:event_panelBackground1MouseDragged

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtrar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void codpytoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codpytoActionPerformed
        if(codpyto.getSelectedIndex()!=-1){
            name = new ProyectoDAO().retornarNombrePyto(varCodCiaGlobalDeLogin, Integer.parseInt(codpyto.getSelectedItem().toString()));
            descripcion.setText(name+" / Año");
            anio.setEnabled(true);
            anio.removeAllItems();
            initListarAnio();
        }else{
            limpiarTabla();
            anio.setEnabled(false);
            anio.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_codpytoActionPerformed

    private void anioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_anioItemStateChanged
        if(anio.getSelectedIndex()!=-1){
            an=anio.getSelectedItem().toString();
        }else{
            limpiarTabla();
        }
    }//GEN-LAST:event_anioItemStateChanged

    private void btnRealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealActionPerformed
        //int a=0;
        if(codpyto.getSelectedIndex()!=-1 && anio.getSelectedIndex()!=-1){
            titulo.setText("Flujo de Caja Real");
            descripcion.setText(name+" / "+an);
            limpiarTabla();
            sql="SELECT F.ingegr, CONCAT(F.codpartida,CONCAT('. ',P.DESPARTIDA)) AS PARTIDA, F.imprealini as INICIAL, F.imprealene as ENERO,  F.imprealfeb AS FEBRERO, F.imprealmar AS MARZO, F.imprealabr AS ABRIL, F.imprealmay AS MAYO, F.imprealjun AS JUNIO, F.imprealjul AS JULIO, F.imprealago AS AGOSTO, F.imprealsep AS SEPTIEMBRE, F.imprealoct AS OCTUBRE, F.imprealnov AS NOVIEMBRE, F.imprealdic AS DICIEMBRE, F.imprealacum AS ACUMULADO "
                    + "FROM flujocaja_det F INNER JOIN PARTIDA P ON F.CODPARTIDA=P.CODPARTIDA AND F.INGEGR=P.INGEGR INNER JOIN FLUJOCAJA FC ON F.CODPARTIDA = FC.CODPARTIDA AND F.INGEGR=FC.INGEGR WHERE F.CODCIA="+varCodCiaGlobalDeLogin+" AND F.CODPYTO="+Integer.parseInt(codpyto.getSelectedItem().toString())+" AND F.ANNO="+Integer.parseInt(anio.getSelectedItem().toString())+" AND FC.CODCIA="+varCodCiaGlobalDeLogin+" AND FC.CODPYTO="+Integer.parseInt(codpyto.getSelectedItem().toString())+" ORDER BY INGEGR DESC, FC.NIVEL ASC,F.ORDEN ASC";
            ingR=retornarTotal("I","imprealini, imprealene, imprealfeb, imprealmar, imprealabr, imprealmay, imprealjun, imprealjul, imprealago, imprealsep, imprealoct, imprealnov, imprealdic, imprealacum");
            egrR=retornarTotal("E","imprealini, imprealene, imprealfeb, imprealmar, imprealabr, imprealmay, imprealjun, imprealjul, imprealago, imprealsep, imprealoct, imprealnov, imprealdic, imprealacum");
            estadoGeneral=2;
            initTablaDinamica();
        }else{
            showMessage1("Escoja un proyecto y el año");
        }
    }//GEN-LAST:event_btnRealActionPerformed

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralActionPerformed
        if(codpyto.getSelectedIndex()!=-1 && anio.getSelectedIndex()!=-1){
            titulo.setText("Flujo de Caja General");
            descripcion.setText(name+" / "+an);
            limpiarTabla();
            sql="SELECT F.ingegr, CONCAT(F.codpartida,CONCAT('. ',P.DESPARTIDA)) AS PARTIDA, F.impini AS S_INICIAL, F.imprealini  AS R_INICIAL, F.impene AS S_ENERO, F.imprealene AS R_ENERO, F.impfeb AS S_FEBRERO,  F.imprealfeb AS R_FEBRERO, F.impmar AS S_MARZO, F.imprealmar AS R_MARZO, F.impabr AS S_ABRIL, F.imprealabr AS R_ABRIL, F.impmay AS S_MAYO, F.imprealmay AS R_MAYO, F.impjun AS S_JUNIO, F.imprealjun AS R_JUNIO, F.impjul AS S_JULIO, F.imprealjul AS R_JULIO, F.impago AS S_AGOSTO, F.imprealago AS R_AGOSTO, F.impsep AS S_SEPTIEMBRE, F.imprealsep AS R_SEPTIEMBRE, F.impoct AS S_OCTUBRE, F.imprealoct AS R_OCTUBRE, F.impnov AS S_NOVIEMBRE, F.imprealnov AS R_NOVIEMBRE, F.impdic AS S_DICIEMBRE, F.imprealdic AS R_DICIEMBRE, F.impacum AS S_ACUMULADO, F.imprealacum AS R_ACUMULADO "
                    + " FROM flujocaja_det F INNER JOIN PARTIDA P ON F.CODPARTIDA=P.CODPARTIDA AND F.INGEGR=P.INGEGR INNER JOIN FLUJOCAJA FC ON F.CODPARTIDA = FC.CODPARTIDA AND F.INGEGR=FC.INGEGR WHERE F.CODCIA="+varCodCiaGlobalDeLogin+" AND F.CODPYTO="+Integer.parseInt(codpyto.getSelectedItem().toString())+" AND F.ANNO="+Integer.parseInt(anio.getSelectedItem().toString())+" AND FC.CODCIA="+varCodCiaGlobalDeLogin+" AND FC.CODPYTO="+Integer.parseInt(codpyto.getSelectedItem().toString())+" ORDER BY INGEGR DESC, FC.NIVEL ASC,F.ORDEN ASC";
            ingR=retornarTotal("I","imprealini, imprealene, imprealfeb, imprealmar, imprealabr, imprealmay, imprealjun, imprealjul, imprealago, imprealsep, imprealoct, imprealnov, imprealdic, imprealacum");
            egrR=retornarTotal("E","imprealini, imprealene, imprealfeb, imprealmar, imprealabr, imprealmay, imprealjun, imprealjul, imprealago, imprealsep, imprealoct, imprealnov, imprealdic, imprealacum");
            ingS=retornarTotal("I","impini, impene, impfeb, impmar, impabr, impmay, impjun, impjul, impago, impsep, impoct, impnov, impdic, impacum");
            egrS=retornarTotal("E","impini, impene, impfeb, impmar, impabr, impmay, impjun, impjul, impago, impsep, impoct, impnov, impdic, impacum");
            estadoGeneral=1;
            initTablaDinamica();
        }else{
            showMessage1("Escoja un proyecto y el año");
        }
    }//GEN-LAST:event_btnGeneralActionPerformed

    private void btnSupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupuestoActionPerformed
        if(codpyto.getSelectedIndex()!=-1 && anio.getSelectedIndex()!=-1){
            titulo.setText("Flujo de Caja Supuesto");
            descripcion.setText(name+" / "+an);
            limpiarTabla();
            sql="SELECT F.ingegr, CONCAT(F.codpartida,CONCAT('. ',P.DESPARTIDA)) AS PARTIDA, F.impini AS INICIAL, F.impene AS ENERO, F.impfeb AS FEBRERO, F.impmar AS MARZO, F.impabr AS ABRIL, F.impmay AS MAYO, F.impjun AS JUNIO, F.impjul AS JULIO, F.impago AS AGOSTO, F.impsep AS SEPTIEMBRE, F.impoct AS OCTUBRE, F.impnov AS NOVIEMBRE, F.impdic AS DICIEMBRE, F.impacum AS ACUMULADO "
                    + "FROM flujocaja_det F INNER JOIN PARTIDA P ON F.CODPARTIDA=P.CODPARTIDA AND F.INGEGR=P.INGEGR INNER JOIN FLUJOCAJA FC ON F.CODPARTIDA = FC.CODPARTIDA AND F.INGEGR=FC.INGEGR WHERE F.CODCIA="+varCodCiaGlobalDeLogin+" AND F.CODPYTO="+Integer.parseInt(codpyto.getSelectedItem().toString())+" AND F.ANNO="+Integer.parseInt(anio.getSelectedItem().toString())+" AND FC.CODCIA="+varCodCiaGlobalDeLogin+" AND FC.CODPYTO="+Integer.parseInt(codpyto.getSelectedItem().toString())+" ORDER BY INGEGR DESC, FC.NIVEL ASC,F.ORDEN ASC";
            ingS=retornarTotal("I","impini, impene, impfeb, impmar, impabr, impmay, impjun, impjul, impago, impsep, impoct, impnov, impdic, impacum");
            egrS=retornarTotal("E","impini, impene, impfeb, impmar, impabr, impmay, impjun, impjul, impago, impsep, impoct, impnov, impdic, impacum");
            estadoGeneral=3;
            initTablaDinamica();
        }else{
            showMessage1("Escoja un proyecto y el año");
        }
    }//GEN-LAST:event_btnSupuestoActionPerformed

    public void filtrar(){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().trim()));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void limpiarTabla(){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Modelo.Design.Combobox anio;
    private Modelo.Design.Button btnGeneral;
    private Modelo.Design.Button btnReal;
    private Modelo.Design.Button btnSupuesto;
    private Modelo.Design.Combobox codpyto;
    private javax.swing.JLabel descripcion;
    private Modelo.Design.Headboard header;
    private javax.swing.JScrollPane jScrollPane1;
    private Modelo.Design.PanelBackground panelBackground1;
    private Modelo.DesignTable.Tabla tablaConsulta;
    private javax.swing.JLabel titulo;
    private Modelo.Design.TextFieldBusqueda txtBuscar;
    // End of variables declaration//GEN-END:variables
}
