package Controlador;

import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.DAO.ProveedorDAO;
import Modelo.Proveedor;
import Vistas.V_Proveedor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;


public class C_Proveedor implements ActionListener, KeyListener, MouseListener{
    
    ProveedorDAO proDAO = new ProveedorDAO();
    V_Proveedor vPro = new V_Proveedor();
    DefaultTableModel modelProveedor = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int id=-1;
    int fila=-1;
    int optionBusqueda=0;
    
    public C_Proveedor(V_Proveedor vp){
        this.vPro=vp;
        this.vPro.btt_Actualizar.addActionListener(this);
        this.vPro.btt_Registrar.addActionListener(this);
        this.vPro.btt_Eliminar.addActionListener(this);
        this.vPro.tablaProveedor.addMouseListener(this);
        this.vPro.actualizaTabla.addActionListener(this);
        this.vPro.nuevo.addActionListener(this);
        this.vPro.txtBusqueda.addKeyListener(this);
        init();
    }
    
    public void init(){
        initTablaProveedor();
        vPro.init();
        ((AbstractDocument)vPro.desPersona.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vPro.desCorta.getDocument()).setDocumentFilter(new LimitDocumentFilter(30,0));
        ((AbstractDocument)vPro.descAlterna.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vPro.desCortaAlt.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
        ((AbstractDocument)vPro.nroRuc.getDocument()).setDocumentFilter(new LimitDocumentFilter(20,1));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
         System.out.println("DENTRO DE ACTION PROVEEDOR");
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
    
    public void actualizarTabla(){
        limpiarTabla(modelProveedor);
        initTablaProveedor();
        System.out.println("Refrescando tabla automaticamente.");
        fila=-1;
    }
    
    public void initTablaProveedor(){
        List<Proveedor> lista = new ProveedorDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        modelProveedor = (DefaultTableModel)vPro.tablaProveedor.getModel();
        Object[] o=new Object[5];
        sorter = new TableRowSorter<>(modelProveedor);
        vPro.tablaProveedor.setRowSorter(sorter);
        limpiarTabla(modelProveedor);
        for(int i=0;i<lista.size();i++){ 
            o[0]=lista.get(i).getCodPersona();
            o[1]=lista.get(i).getDesPersona();
            o[2]=lista.get(i).getNroRuc();
            o[3]=(lista.get(i).getVigente())=='1'?"Si":"No";
            modelProveedor.addRow(o);
        }
        vPro.tablaProveedor.setModel(modelProveedor);
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
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
                case 0 -> filtrar1();
                case 1 -> filtrar2(optionBusqueda);
                case 2 -> filtrar2(optionBusqueda);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vPro.tablaProveedor){
            fila = vPro.tablaProveedor.getSelectedRow();
            int codProveedor = Integer.parseInt(vPro.tablaProveedor.getValueAt(fila,0).toString());
            System.out.println("Proveedor = "+codProveedor);
            Proveedor pro = new ProveedorDAO().listarId(codProveedor);
            vPro.nroRuc.setText(pro.getNroRuc());
            vPro.desPersona.setText(pro.getDesPersona());
            vPro.descAlterna.setText(pro.getDesAlterna());
            vPro.desCorta.setText(pro.getDescorta());
            vPro.desCortaAlt.setText(pro.getDesCortaAlt());
            vPro.vigente.setSelectedItem((pro.getVigente()=='1'?"Vigente":"No Vigente"));           
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
    
    public void registrarDatos(){
        Proveedor pro=new Proveedor();
        pro.setCodCia(varCodCiaGlobalDeLogin);
        pro.setTipPersona('1');
        pro.setNroRuc(vPro.nroRuc.getText());
        pro.setDesPersona(vPro.desPersona.getText());
        pro.setDescorta(vPro.desCorta.getText());
        pro.setDesAlterna(vPro.descAlterna.getText());
        pro.setDesCortaAlt(vPro.desCortaAlt.getText());
        char vig="Vigente".equals(vPro.vigente.getSelectedItem().toString())?'1':'0';
        pro.setVigente(vig);
        if(proDAO.add(pro)==1){
            showMessage2("Empleado registrado correctamente");
            vaciarCampos();
            fila=-1;
        }else{
            showMessage1("Error al registrar el Proveedor");
        }
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
    
    public void actualizarDatos(){
        int fila = vPro.tablaProveedor.getSelectedRow();
        if(fila!=-1){
            System.out.println("Hay filas seleccionadas.");
            int codProveedor = Integer.parseInt(vPro.tablaProveedor.getValueAt(fila,0).toString());
            
            Proveedor proveedor = new Proveedor();
            proveedor.setCodCia(varCodCiaGlobalDeLogin);
            proveedor.setTipPersona('1');
            proveedor.setDesPersona(vPro.desPersona.getText());
            proveedor.setDescorta(vPro.descAlterna.getText());
            proveedor.setDesAlterna(vPro.descAlterna.getText());
            proveedor.setDesCortaAlt(vPro.desCortaAlt.getText());
            char vig="Vigente".equals(vPro.vigente.getSelectedItem().toString())?'1':'0';
            proveedor.setVigente(vig);
            proveedor.setCodPersona(codProveedor);
            proveedor.setNroRuc(vPro.nroRuc.getText());
            if(proDAO.actualizar(proveedor)==1){
                showMessage2("Elemento registrado correctamente");
                vaciarCampos();
                fila=-1;
            }else{
                showMessage1("Error al registrar Elementos");
            }
        }else{
            showMessage1("Debe seleccionar una fila");
        }
        actualizarTabla();
    }
    
    public void eliminarDatos(){
        int fila = vPro.tablaProveedor.getSelectedRow();
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int codProveedor = Integer.parseInt(vPro.tablaProveedor.getValueAt(fila,0).toString());
            proDAO.eliminar(codProveedor);
            fila=-1;
            vaciarCampos();
        }else{
            showMessage1("Debe seleccionar una fila");
        }
        actualizarTabla();
    }
    
    public void vaciarCampos(){
        vPro.nroRuc.setText("");
        vPro.desCorta.setText("");
        vPro.desCortaAlt.setText("");
        vPro.desPersona.setText("");
        vPro.descAlterna.setText("");
    }
}
