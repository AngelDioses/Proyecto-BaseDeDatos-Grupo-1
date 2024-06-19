package Controlador;

import Modelo.Cliente;
import Modelo.DAO.ClienteDAO;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.Empresa_VtaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.Empleado;
import Modelo.Empresa_Vta;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Proyecto;
import Modelo.jnafilechooser.api.JnaFileChooser;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_Proyecto;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

public class C_Proyecto implements ActionListener, KeyListener, MouseListener{

    V_Proyecto vPro=new V_Proyecto();
    DefaultTableModel modelProyecto = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int fila=-1;
    int optionBusqueda=-1;
    
    public C_Proyecto(V_Proyecto vPro){
        this.vPro=vPro;
        this.vPro.btt_Registrar.addActionListener(this);
        this.vPro.btt_Eliminar.addActionListener(this);
        this.vPro.btt_Actualizar.addActionListener(this);
        this.vPro.btnAdd.addActionListener(this);
        this.vPro.tablaProyecto.addMouseListener(this);
        this.vPro.actualizaTabla.addActionListener(this);
        this.vPro.nuevo.addActionListener(this);
        this.vPro.txtBusqueda.addKeyListener(this);
        init();
    }

    public void init(){
        initTablaEmpresa();
        vPro.init();
        initListarIDsCombosBox();
        ((AbstractDocument)vPro.nomPyto.getDocument()).setDocumentFilter(new LimitDocumentFilter(1000,0));
        ((AbstractDocument)vPro.valRefer.getDocument()).setDocumentFilter(new LimitDocumentFilter(15,0));
        ((AbstractDocument)vPro.annoIni.getDocument()).setDocumentFilter(new LimitDocumentFilter(4,1));
        ((AbstractDocument)vPro.annoFin.getDocument()).setDocumentFilter(new LimitDocumentFilter(4,1));
        ((AbstractDocument)vPro.observac.getDocument()).setDocumentFilter(new LimitDocumentFilter(500,0));
        ((AbstractDocument)vPro.costoTotSinIGV.getDocument()).setDocumentFilter(new LimitDocumentFilter(15,0));
        ((AbstractDocument)vPro.impIGV.getDocument()).setDocumentFilter(new LimitDocumentFilter(15,0));
        ((AbstractDocument)vPro.costoTotal.getDocument()).setDocumentFilter(new LimitDocumentFilter(15,0));
    }
    
    public void initListarIDsCombosBox(){
        initListarEmpleados();
        initListarEmpresas_Vta();
        initListarClientes();
    }
    
    public void initListarEmpleados(){
        vPro.emplJefeProy.removeAllItems(); //FALTA LISTAR POR COD CIA, NO EXISTE TODAVIA EL METODO EN DAO.
        List<Empleado> lista = new EmpleadoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vPro.emplJefeProy.addItem(lista.get(i).getCodPersona());
        }
    }
    
    public void initListarEmpresas_Vta(){
        vPro.ciaContrata.removeAllItems();
        List<Empresa_Vta> lista = new Empresa_VtaDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vPro.ciaContrata.addItem(lista.get(i).getCodPersona());
        }
    }
    
    public void initListarClientes(){
        vPro.codCliente.removeAllItems();
        List<Cliente> lista = new ClienteDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vPro.codCliente.addItem(lista.get(i).getCodPersona());
        }
    }
    
    public void initTablaEmpresa(){
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        System.out.println(lista.size());
        modelProyecto = (DefaultTableModel)vPro.tablaProyecto.getModel();
        Object[] o=new Object[20];
        sorter = new TableRowSorter<>(modelProyecto);
        vPro.tablaProyecto.setRowSorter(sorter);
        limpiarTabla(modelProyecto);
        for(int i=0;i<lista.size();i++){ 
            System.out.println("listando");
            //o[0]=lista.get(i).getCodCia();
            o[0]=lista.get(i).getCodPyto();
            o[1]=lista.get(i).getNomPyto();
            o[2]=lista.get(i).getEmplJefeProy();
            o[3]=lista.get(i).getCiaContrata();
            o[4]=lista.get(i).getCodCliente();
            o[5]=lista.get(i).getFecReg();
            o[6]=lista.get(i).getEstPyto();
            o[7]=lista.get(i).getFecEstado();
            o[8]=lista.get(i).getCostoTotal();
            o[9]=lista.get(i).getAnnoIni(); //date
            o[10]=lista.get(i).getAnnoFin(); //date
            o[11]=lista.get(i).getVigente();
            modelProyecto.addRow(o);
        }
        vPro.tablaProyecto.setModel(modelProyecto);
    }
    
        
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelProyecto);
        initTablaEmpresa();
        System.out.println("Refrescando tabla automaticamente.");
        fila=-1;
    }
    
    public void filtrar1(){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vPro.txtBusqueda.getText().trim()));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void filtrar2(int num){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vPro.txtBusqueda.getText().trim(),num));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE PROYECTO");
        if(e.getSource()==vPro.btt_Registrar){
            registrarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vPro.btt_Actualizar){
            actualizarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vPro.btt_Eliminar){
            eliminarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vPro.btnAdd){
            JnaFileChooser ch = new JnaFileChooser();
            boolean save=ch.showOpenDialog(Frame.getFrames()[1]);
            if (save) {
                File file = ch.getSelectedFile();
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                String ruta1 = ch.getSelectedFile().getAbsolutePath();
                vPro.txtRuta.setText(ruta1);
                System.out.println(ruta1);
                System.out.println(vPro.txtRuta.getText());
                vPro.pictureBox2.setImage(icon);
                vPro.repaint();
            }       
        }
        if(e.getSource()==vPro.actualizaTabla){
            actualizarTabla();
            fila=-1;
        }
        if(e.getSource()==vPro.nuevo){
            vaciarCampos();
            actualizarTabla();
            fila=-1;
        }
    }
    
    public void registrarDatos(){
        //[a-z0-9]+@[a-z]+\.[a-z]{2,3} ->Regex para validar EMAIL.
        //[0-9]{1,12}\.[0-9]{1,2} -> Validar numeros -> Mejorar regex

        Proyecto proyecto = new Proyecto.Builder()
                .codCia(varCodCiaGlobalDeLogin)
                .nomPyto(vPro.nomPyto.getText())
                .emplJefeProy(Integer.parseInt(vPro.emplJefeProy.getSelectedItem().toString()))
                .ciaContrata(Integer.parseInt(vPro.ciaContrata.getSelectedItem().toString()))
                .codCliente(Integer.parseInt(vPro.codCliente.getSelectedItem().toString()))
                .fecReg(new java.sql.Date(new java.util.Date().getTime()))
                .estPyto(Integer.parseInt(vPro.estPyto.getSelectedItem().toString()))
                .fecEstado(new java.sql.Date(new java.util.Date().getTime()))
                .valRefer(Double.parseDouble(vPro.valRefer.getText()))
                .costoTotSinIGV(Double.parseDouble(vPro.costoTotSinIGV.getText()))
                .impIGV(Double.parseDouble(vPro.impIGV.getText()))
                .costoTotal(Double.parseDouble(vPro.costoTotal.getText()))
                .observac(vPro.observac.getText())
                .annoIni(Integer.parseInt(vPro.annoIni.getText()))
                .annoFin(Integer.parseInt(vPro.annoFin.getText()))
                .vigente(String.valueOf(Integer.valueOf(vPro.annoFin.getText())>=Year.now().getValue()?'1':'0').charAt(0))
                .logoProy(vPro.txtRuta.getText())
                .build();
        
        ///////////////////////////////Ingreso de Logo/////////////////////////////
//        File ruta = new File(vPro.txtRuta.getText());
//        try{
//            byte[] icono = new byte[(int) ruta.length()];
//            InputStream input = new FileInputStream(ruta);
//            input.read(icono);
//            proyecto.setLogoProy(icono);
//        }catch(Exception ex){
//            proyecto.setLogoProy(null);
//        }
        //////////////////////////////////////////////////////////////////////////
        
        if(new ProyectoDAO().add(proyecto)==1){
            showMessage2("Proyecto registrado correctamente");
            vaciarCampos();
            fila=-1;
        }else{
            showMessage1("Error al registrar Proyecto");
        }  
    }
    
    public void actualizarDatos(){
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int codProyecto = Integer.parseInt(vPro.tablaProyecto.getValueAt(fila,0).toString());

            Proyecto proyecto = new Proyecto.Builder()
                .codPyto(codProyecto)
                .codCia(varCodCiaGlobalDeLogin)
                .nomPyto(vPro.nomPyto.getText())
                .emplJefeProy(Integer.parseInt(vPro.emplJefeProy.getSelectedItem().toString()))
                .ciaContrata(Integer.parseInt(vPro.ciaContrata.getSelectedItem().toString()))
                .codCliente(Integer.parseInt(vPro.codCliente.getSelectedItem().toString()))
                .fecReg(new java.sql.Date(new java.util.Date().getTime()))
                .estPyto(Integer.parseInt(vPro.estPyto.getSelectedItem().toString()))
                .fecEstado(new java.sql.Date(new java.util.Date().getTime()))
                .valRefer(Double.parseDouble(vPro.valRefer.getText()))
                .costoTotSinIGV(Double.parseDouble(vPro.costoTotSinIGV.getText()))
                .impIGV(Double.parseDouble(vPro.impIGV.getText()))
                .costoTotal(Double.parseDouble(vPro.costoTotal.getText()))
                .observac(vPro.observac.getText())
                .annoIni(Integer.parseInt(vPro.annoIni.getText()))
                .annoFin(Integer.parseInt(vPro.annoFin.getText()))
                .vigente(String.valueOf(Integer.valueOf(vPro.annoFin.getText())>=Year.now().getValue()?'1':'0').charAt(0))
                .logoProy(vPro.txtRuta.getText())
                .build();
            
//            ///////////////////////////////Ingreso de Logo/////////////////////////////
//            File ruta = new File(vPro.txtRuta.getText());
//            try{
//                byte[] icono = new byte[(int) ruta.length()];
//                InputStream input = new FileInputStream(ruta);
//                input.read(icono);
//                proyecto.setLogoProy(icono);
//            }catch(Exception ex){
//                proyecto.setLogoProy(null);
//            }
//            //////////////////////////////////////////////////////////////////////////
            if(new ProyectoDAO().actualizar(proyecto)==1){
                showMessage2("Proyecto actualizado correctamente");
                vaciarCampos();
                fila=-1;
            }else{
                showMessage1("Error al actualizar Proyecto");
            }
        }
    }
    
    public void eliminarDatos(){
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int codProyecto = Integer.parseInt(vPro.tablaProyecto.getValueAt(fila,0).toString());
            new ProyectoDAO().eliminar(codProyecto);
        }else{
            showMessage1("Error al actualizar Proyecto");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        optionBusqueda=vPro.opt;
        if(e.getComponent()==vPro.txtBusqueda){
            switch(optionBusqueda){
                case -1 -> filtrar1();
                case 0 -> filtrar2(optionBusqueda);
                case 1 -> filtrar2(optionBusqueda);
                case 2 -> filtrar2(optionBusqueda);
                case 3 -> filtrar2(optionBusqueda);
                case 4 -> filtrar2(optionBusqueda);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vPro.tablaProyecto){
            fila = vPro.tablaProyecto.getSelectedRow();
            int codProyecto = Integer.parseInt(vPro.tablaProyecto.getValueAt(fila,0).toString());
            System.out.println("trabajador id = "+codProyecto);
            Proyecto pyto = new ProyectoDAO().listarId(codProyecto);
            vPro.nomPyto.setText(pyto.getNomPyto());
            vPro.costoTotSinIGV.setText(String.valueOf(pyto.getCostoTotSinIGV()));
            vPro.impIGV.setText(String.valueOf(pyto.getImpIGV()));
            vPro.costoTotal.setText(String.valueOf(pyto.getCostoTotal()));
            vPro.valRefer.setText(String.valueOf(pyto.getValRefer()));
            vPro.annoIni.setText(String.valueOf(pyto.getAnnoIni()));
            vPro.annoFin.setText(String.valueOf(pyto.getAnnoFin()));
            vPro.observac.setText(pyto.getObservac());
            vPro.emplJefeProy.setSelectedItem(pyto.getEmplJefeProy());
            vPro.ciaContrata.setSelectedItem(pyto.getCiaContrata());
            vPro.codCliente.setSelectedItem(pyto.getCodCliente());
            vPro.estPyto.setSelectedIndex(pyto.getEstPyto());
            traerLogoBDD(pyto);
        }
    }

    public void traerLogoBDD(Proyecto pyto){
        if(pyto.getLogoProy()!=null){
            System.out.println("Si hay LOGO en la BDD.");
            Icon imgi=null;
            byte[] bi = pyto.getLogoProy();
            BufferedImage image = null;
            InputStream in = new ByteArrayInputStream(bi);
            try {
                image = ImageIO.read(in);
            } catch (IOException ex) {
                Logger.getLogger(C_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgi = new ImageIcon(image);
            vPro.pictureBox2.setImage(imgi);
            vPro.pictureBox2.repaint();
        } else{
            System.out.println("No hay LOGO en la BDD");
            vPro.pictureBox2.setImage(null);
            vPro.pictureBox2.repaint();
            vPro.txtRuta.setText("");
        }
    }
    
    public void vaciarCampos(){
        Icon imgi=null;
        vPro.nomPyto.setText("");
        vPro.costoTotSinIGV.setText("");
        vPro.costoTotal.setText("");
        vPro.impIGV.setText("");
        vPro.observac.setText("");
        vPro.valRefer.setText("");
        imgi = new ImageIcon(getClass().getResource("/image/imagenNoDisponible.png"));
        vPro.pictureBox2.setImage(imgi);
        vPro.pictureBox2.repaint();
        vPro.txtRuta.setText("");
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
    
    private boolean showMessage(String message) {
        Mensaje obj = new Mensaje(Frame.getFrames()[1], true);
        obj.showMessage(message);
        return obj.isOk();
    }
    
    private boolean showMessage1(String message){
        Mensaje1 obj = new Mensaje1(Frame.getFrames()[1],true);
        obj.showMessage(message);
        return obj.isAceptar();
    }
    
    private boolean showMessage2(String message) {
        Mensaje2 obj = new Mensaje2(Frame.getFrames()[1], true);
        obj.showMessage(message);
        return obj.isAceptar();
    }
}
