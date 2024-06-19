
package Controlador;

import Modelo.Cliente;
import Modelo.DAO.ClienteDAO;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Vistas.V_Cliente;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
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

public class C_Cliente implements ActionListener, KeyListener,MouseListener  {
    ClienteDAO clienteDAO = new ClienteDAO();
    V_Cliente vCliente = new V_Cliente();
    DefaultTableModel modelCliente = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int fila=-1;
    int optionBusqueda=0;
    
    public C_Cliente(V_Cliente vCliente){
        this.vCliente=vCliente;
        this.vCliente.btt_Actualizar.addActionListener(this); //Nota: Los botones, tablas tienen que estar en publico.
        this.vCliente.btt_Registrar.addActionListener(this);
        this.vCliente.btt_Eliminar.addActionListener(this);
        this.vCliente.tablaCliente.addMouseListener(this);
        this.vCliente.actualizaTabla.addActionListener(this);
        this.vCliente.nuevo.addActionListener(this);
        this.vCliente.txtBusqueda.addKeyListener(this);
        init();
    }
    
    public void initListarCompanias(){
        vCliente.codCompania_cb.setText(""+varCodCiaGlobalDeLogin);
    }
    
    private void init() {
        initTablaCliente();
        //filtrar();
        vCliente.init();
        initListarCompanias();
        ((AbstractDocument)vCliente.desPersona.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vCliente.desCorta.getDocument()).setDocumentFilter(new LimitDocumentFilter(30,0));
        ((AbstractDocument)vCliente.desAlterna.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vCliente.desCortaAlt.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
        ((AbstractDocument)vCliente.nroRuc.getDocument()).setDocumentFilter(new LimitDocumentFilter(20,1));
    }
    
    public void filtrar1(){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vCliente.txtBusqueda.getText().trim()));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void filtrar2(int num){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vCliente.txtBusqueda.getText().trim(),num));
            System.out.println("HOLA_FILTRO_Cliente");
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    private void initTablaCliente() {
        limpiarTabla(modelCliente);
        List<Cliente> lista = new ClienteDAO().listar();
        System.out.println(lista.size());
        modelCliente = (DefaultTableModel)vCliente.tablaCliente.getModel();
        Object[] o=new Object[4];
        sorter = new TableRowSorter<>(modelCliente);
        vCliente.tablaCliente.setRowSorter(sorter);
        limpiarTabla(modelCliente);
        for(int i=0;i<lista.size();i++){ 
            o[0]=lista.get(i).getCodPersona();
            o[1]=lista.get(i).getDesPersona();
            o[2]=lista.get(i).getNroRuc();
            o[3]=(lista.get(i).getVigente())=='1'?"Si":"No";
            modelCliente.addRow(o);
        }
        vCliente.tablaCliente.setModel(modelCliente);
    }
    public void actualizarTabla(){
        limpiarTabla(modelCliente);
        initTablaCliente();
        System.out.println("Refrescando tabla automaticamente.");
        fila=-1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION-C_EMPRESA");
        if(e.getSource()==vCliente.btt_Registrar){
            registrarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vCliente.btt_Actualizar){
            actualizarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vCliente.btt_Eliminar){
            eliminarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vCliente.actualizaTabla){
            actualizarTabla();
            fila=-1;
        }
        if(e.getSource()==vCliente.nuevo){
            vaciarCampos();
            actualizarTabla();
            fila=-1;
        }
    }
    public void registrarDatos(){    
        //Objeto empresa que sera agregado.
        Cliente cliente = new Cliente();
        
        //Atributos persona
        cliente.setCodCia(varCodCiaGlobalDeLogin);
        cliente.setTipPersona('4');
        cliente.setDesPersona(vCliente.desPersona.getText());
        cliente.setDescorta(vCliente.desCorta.getText());
        cliente.setDesAlterna(vCliente.desAlterna.getText());
        cliente.setDesCortaAlt(vCliente.desCortaAlt.getText());
        char vig="Vigente".equals(vCliente.vigente.getSelectedItem().toString())?'1':'0';
        cliente.setVigente(vig);
        //Atributos Cliente
        cliente.setNroRuc(vCliente.nroRuc.getText());
        //Falta agregar verificacion de campos
        if(clienteDAO.add(cliente)==1){
            showMessage2("Cliente registrado correctamente");
            vaciarCampos();
            fila=-1;
        }else{
            showMessage1("Error al registrar el Cliente");
        }
    }
    
    public void actualizarDatos(){
        //int fila = vCliente.tablaCliente.getSelectedRow();
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int codCliente = Integer.parseInt(vCliente.tablaCliente.getValueAt(fila,0).toString());
            
            //Objeto Cliente que sera actualizado.
            Cliente cliente = new Cliente();
            cliente.setCodPersona(codCliente); //Con este valor realizaremos el UPDATE.
            cliente.setDesPersona(vCliente.desPersona.getText());
            cliente.setDescorta(vCliente.desCorta.getText());
            cliente.setDesAlterna(vCliente.desAlterna.getText());
            cliente.setDesCortaAlt(vCliente.desCortaAlt.getText());
            char vig="Vigente".equals(vCliente.vigente.getSelectedItem().toString())?'1':'0';
            cliente.setVigente(vig);
            //Atributos persona
            cliente.setCodCia(varCodCiaGlobalDeLogin);
            cliente.setTipPersona('4');
            //Atributos Cliente
            cliente.setNroRuc(vCliente.nroRuc.getText());
            if(clienteDAO.actualizar(cliente)==1){
                showMessage2("Cliente registrado correctamente");
                vaciarCampos();
                fila=-1;
            }else{
                showMessage1("Error al registrar Cliente");
            }
        }else{
            showMessage1("Debe seleccionar una fila");
        }
    }
    public void eliminarDatos(){
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int ciaContrata = Integer.parseInt(vCliente.tablaCliente.getValueAt(fila,0).toString());
            clienteDAO.eliminar(ciaContrata);
            fila=-1;
            vaciarCampos();
        }else{
            showMessage1("Debe seleccionar una fila");
        }
    }
    
    public void vaciarCampos(){
        vCliente.nroRuc.setText("");
        vCliente.desPersona.setText("");
        vCliente.desAlterna.setText("");
        vCliente.desCorta.setText("");
        vCliente.desCortaAlt.setText("");
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
        optionBusqueda=vCliente.opt;
        if(e.getComponent()==vCliente.txtBusqueda){
            switch(optionBusqueda){
                case 0 -> filtrar1();
                case 1 -> filtrar2(optionBusqueda);
                case 2 -> filtrar2(optionBusqueda);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vCliente.tablaCliente){
            fila = vCliente.tablaCliente.getSelectedRow();
            int codcliente = Integer.parseInt(vCliente.tablaCliente.getValueAt(fila,0).toString());
            System.out.println("Cliente id = "+codcliente);
            Cliente cln = new ClienteDAO().listarId(codcliente);
            vCliente.nroRuc.setText(cln.getNroRuc());
            vCliente.codCompania_cb.setText(""+varCodCiaGlobalDeLogin);  
            vCliente.desPersona.setText(cln.getDesPersona());
            vCliente.desAlterna.setText(cln.getDesAlterna());
            vCliente.desCorta.setText(cln.getDescorta());
            vCliente.desCortaAlt.setText(cln.getDesCortaAlt());
            vCliente.vigente.setSelectedItem((cln.getVigente()=='1'?"Vigente":"No Vigente"));
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
