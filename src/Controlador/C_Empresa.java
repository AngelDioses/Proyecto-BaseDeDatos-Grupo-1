package Controlador;

import Modelo.DAO.Empresa_VtaDAO;
import Modelo.Empresa_Vta;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Vistas.V_Empresa;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

public class C_Empresa implements ActionListener, KeyListener, MouseListener {

    V_Empresa vEmpresa = new V_Empresa();
    Empresa_VtaDAO empresaDAO=new Empresa_VtaDAO();
    DefaultTableModel modelEmpresa = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int fila=-1;
    int optionBusqueda=0;
    
    public C_Empresa(V_Empresa vEmpresa){
        this.vEmpresa=vEmpresa;
        this.vEmpresa.btt_Actualizar.addActionListener(this); //Nota: Los botones, tablas tienen que estar en publico.
        this.vEmpresa.btt_Registrar.addActionListener(this);
        this.vEmpresa.btt_Eliminar.addActionListener(this);
        this.vEmpresa.tablaEmpresa.addMouseListener(this);
        this.vEmpresa.actualizaTabla.addActionListener(this);
        this.vEmpresa.nuevo.addActionListener(this);
        this.vEmpresa.txtBusqueda.addKeyListener(this);
        init();
    }
    
    public void init(){
        initTablaEmpresa();
        vEmpresa.init();
        ((AbstractDocument)vEmpresa.desPersona.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vEmpresa.desCorta.getDocument()).setDocumentFilter(new LimitDocumentFilter(30,0));
        ((AbstractDocument)vEmpresa.descAlterna.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vEmpresa.desCortaAlt.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
        ((AbstractDocument)vEmpresa.nroRuc.getDocument()).setDocumentFilter(new LimitDocumentFilter(20,1));
        ((AbstractDocument)vEmpresa.observac.getDocument()).setDocumentFilter(new LimitDocumentFilter(2000,0));
    }
    
    public void initTablaEmpresa(){
        List<Empresa_Vta> lista = empresaDAO.listarPorCodCia(varCodCiaGlobalDeLogin);
        System.out.println(lista.size());
        modelEmpresa = (DefaultTableModel)vEmpresa.tablaEmpresa.getModel();
        Object[] o=new Object[7];
        sorter = new TableRowSorter<>(modelEmpresa);
        vEmpresa.tablaEmpresa.setRowSorter(sorter);
        limpiarTabla(modelEmpresa);
        for(int i=0;i<lista.size();i++){ 
            o[0]=lista.get(i).getCodPersona();
            o[1]=lista.get(i).getDesPersona();
            o[2]=lista.get(i).getNroRuc();
            o[3]=(lista.get(i).getFlgEmpConsorcio())=='1'?"Consorcio":"Devenco";
            o[4]=lista.get(i).getFecIni();
            o[5]=lista.get(i).getFecFin();
            o[6]=(lista.get(i).getVigente())=='1'?"Si":"No";
            modelEmpresa.addRow(o);
        }
        vEmpresa.tablaEmpresa.setModel(modelEmpresa);
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelEmpresa);
        initTablaEmpresa();
        System.out.println("Refrescando tabla automaticamente.");
        fila=-1;
    }
    
    public void filtrar1(){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vEmpresa.txtBusqueda.getText().trim()));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void filtrar2(int num){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vEmpresa.txtBusqueda.getText().trim(),num));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION-C_EMPRESA");
        if(e.getSource()==vEmpresa.btt_Registrar){
            registrarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vEmpresa.btt_Actualizar){
            actualizarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vEmpresa.btt_Eliminar){
            eliminarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vEmpresa.actualizaTabla){
            actualizarTabla();
            fila=-1;
        }
        if(e.getSource()==vEmpresa.nuevo){
            vaciarCampos();
            actualizarTabla();
            fila=-1;
        }
    }
    
    public void registrarDatos(){
        //Parseo las fechas en string a un formato dd/MM/yyyy, de manera que pueda almacenarlas en los campos DATE de empresa.
        Date ini = null;
        Date fini = null;
        try {
            ini = new SimpleDateFormat("dd/MM/yyyy").parse(vEmpresa.fecIni.getText());
            fini = new SimpleDateFormat("dd/MM/yyyy").parse(vEmpresa.fecFin.getText());
        } catch (ParseException ex) {
            Logger.getLogger(C_Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Objeto empresa que sera agregado.
        Empresa_Vta empresa = new Empresa_Vta();
        
        //Atributos persona
        //empresa.setCodCia(Integer.parseInt(vEmpresa.codCompania_cb.getSelectedItem().toString()));
        empresa.setCodCia(varCodCiaGlobalDeLogin);
        empresa.setTipPersona('4');
        empresa.setDesPersona(vEmpresa.desPersona.getText());
        empresa.setDescorta(vEmpresa.descAlterna.getText());
        empresa.setDesAlterna(vEmpresa.descAlterna.getText());
        empresa.setDesCortaAlt(vEmpresa.desCortaAlt.getText());
        empresa.setVigente(new Date().compareTo(fini)>0?'0':'1'); //Si la fecha fin es mayor, entonces es vigente (1).
        //Atributos Empresa_Vta
        empresa.setCodPersona(0); //SECUENCIA
        empresa.setNroRuc(vEmpresa.nroRuc.getText());
        empresa.setFlgEmpConsorcio(vEmpresa.consorcioEmpresarial_cb.getSelectedItem()=="Perteneciente"?'1':'0');
        empresa.setFecIni(new java.sql.Date(ini.getTime()));
        empresa.setFecFin(new java.sql.Date(fini.getTime()));
        empresa.setCodEmpresa(0); //NULL
        empresa.setObservac(vEmpresa.observac.getText());

        //Falta agregar verificacion de campos
        if(empresaDAO.add(empresa)==0){
            showMessage2("Empresa registrado correctamente");
            vaciarCampos();
            fila=-1;
        }else{
            showMessage1("Error al registrar Empresa");
        }
        actualizarTabla();
    }
    
    public void actualizarDatos(){
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int ciaContrata = Integer.parseInt(vEmpresa.tablaEmpresa.getValueAt(fila,0).toString());
            
            //Objeto empresa que sera actualizado.
            Empresa_Vta empresa = new Empresa_Vta();
            
            //Parseo las fechas en string a un formato dd/MM/yyyy, de manera que pueda almacenarlas en los campos DATE de empresa.
            Date ini = null;
            Date fini = null;
            try {
                ini = new SimpleDateFormat("dd/MM/yyyy").parse(vEmpresa.fecIni.getText());
                fini = new SimpleDateFormat("dd/MM/yyyy").parse(vEmpresa.fecFin.getText());
            } catch (ParseException ex) {
                Logger.getLogger(C_Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Atributos persona
            empresa.setCodCia(varCodCiaGlobalDeLogin);
            empresa.setTipPersona('2');
            empresa.setDesPersona(vEmpresa.desPersona.getText());
            empresa.setDescorta(vEmpresa.descAlterna.getText());
            empresa.setDesAlterna(vEmpresa.descAlterna.getText());
            empresa.setDesCortaAlt(vEmpresa.desCortaAlt.getText());
            empresa.setVigente(new Date().compareTo(fini)>0?'0':'1'); //Si la fecha fin es mayor, entonces es vigente (1).
            //Atributos Empresa_Vta
            empresa.setCodPersona(ciaContrata); //Con este valor realizaremos el UPDATE.
            empresa.setNroRuc(vEmpresa.nroRuc.getText());
            empresa.setFlgEmpConsorcio(vEmpresa.consorcioEmpresarial_cb.getSelectedItem()=="Perteneciente"?'1':'0');
            empresa.setFecIni(new java.sql.Date(ini.getTime()));
            empresa.setFecFin(new java.sql.Date(fini.getTime()));
            empresa.setCodEmpresa(0); //NULL
            empresa.setObservac(vEmpresa.observac.getText());
            //new Empresa_VtaDAO().actualizar(empresa);
            if(empresaDAO.actualizar(empresa)==0){
                showMessage2("Empresa registrado correctamente");
                vaciarCampos();
                fila=-1;
            }else{
                showMessage1("Error al registrar Elementos");
            }
        }else{
            showMessage1("Debe seleccionar una fila");
        }
    }
    
    public void eliminarDatos(){
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int ciaContrata = Integer.parseInt(vEmpresa.tablaEmpresa.getValueAt(fila,0).toString());
            empresaDAO.eliminar(ciaContrata);
            fila=-1;
        }else{
            showMessage1("Debe seleccionar una fila");
        }
    }

    public void vaciarCampos(){
        vEmpresa.nroRuc.setText("");
        vEmpresa.desCorta.setText("");
        vEmpresa.desCortaAlt.setText("");
        vEmpresa.desPersona.setText("");
        vEmpresa.descAlterna.setText("");
        vEmpresa.observac.setText("");
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
        optionBusqueda=vEmpresa.opt;
        if(e.getComponent()==vEmpresa.txtBusqueda){
            switch(optionBusqueda){
                case 0 -> filtrar1();
                case 1 -> filtrar2(optionBusqueda);
                case 2 -> filtrar2(optionBusqueda);
                case 3 -> filtrar2(optionBusqueda);
            }
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vEmpresa.tablaEmpresa){
            fila = vEmpresa.tablaEmpresa.getSelectedRow();
            //vEmpresa.tablaEmpresa.getColumn("CiaContrata").getModelIndex()
            int ciaContrata = Integer.parseInt(vEmpresa.tablaEmpresa.getValueAt(fila,0).toString());
            System.out.println("trabajador id = "+ciaContrata);
            Empresa_Vta emp = new Empresa_VtaDAO().listarId(ciaContrata);
            vEmpresa.nroRuc.setText(emp.getNroRuc());
            vEmpresa.fecIni.setText(emp.getFecIni().toString());
            vEmpresa.fecFin.setText(emp.getFecFin().toString());
            vEmpresa.desPersona.setText(emp.getDesPersona());
            vEmpresa.descAlterna.setText(emp.getDesAlterna());
            vEmpresa.desCorta.setText(emp.getDescorta());
            vEmpresa.desCortaAlt.setText(emp.getDesCortaAlt());
            vEmpresa.observac.setText(emp.getObservac());
            vEmpresa.consorcioEmpresarial_cb.setSelectedIndex(Integer.parseInt(Character.toString(emp.getFlgEmpConsorcio())));            
        }
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
