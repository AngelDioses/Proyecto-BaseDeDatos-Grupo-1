package Controlador;

import Modelo.DAO.ElementosDAO;
import Modelo.DAO.TabsDAO;
import Modelo.Elementos;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Tabs;
import Vistas.V_Configuracion_Elementos;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

public class C_Elementos implements java.awt.event.ActionListener, java.awt.event.MouseListener{
    ElementosDAO eleDAO=new ElementosDAO();
    V_Configuracion_Elementos configEle=new V_Configuracion_Elementos();
    DefaultTableModel modelEle = new DefaultTableModel();
    int fila=-1;
    
    public C_Elementos(V_Configuracion_Elementos configEle){
        this.configEle=configEle;
        this.configEle.btt_Registrar.addActionListener(this);
        this.configEle.btt_Actualizar.addActionListener(this);
        this.configEle.btt_Eliminar.addActionListener(this);
        this.configEle.tablaElementos.addMouseListener(this);
        this.configEle.actualizaTabla.addActionListener(this);
        this.configEle.nuevo.addActionListener(this);
        this.configEle.codTab.addActionListener(this);
        init();
    }
    
    public void init(){
        initTablaTab();
        initListarTabs();
        this.configEle.init();
        ((AbstractDocument)configEle.descEle.getDocument()).setDocumentFilter(new LimitDocumentFilter(50,0));
        ((AbstractDocument)configEle.descCorta.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
    }
    
    public void initListarTabs(){
        configEle.codTab.removeAllItems();
        List<Tabs> tab=new TabsDAO().listar();
        for (int i = 0; i < tab.size(); i++) {
            configEle.codTab.addItem(tab.get(i).getCodTab());
        }
    }
    
    public void initDatoTab(){
        int num=Integer.parseInt(configEle.codTab.getSelectedItem().toString());
        Tabs nomPyto=new TabsDAO().listarId(num);
        configEle.descTab.setText(nomPyto.getDenTab());
    }
    
    public void initTablaTab(){
        limpiarTabla(modelEle);
        List<Elementos> lista = new ElementosDAO().listar();
        modelEle = (DefaultTableModel)configEle.tablaElementos.getModel();
        Object[] o=new Object[5];
        for(int i=0;i<lista.size();i++){ 
            o[0]=lista.get(i).getCodTab();
            o[1]=lista.get(i).getCodElemento();
            o[2]=lista.get(i).getDenElemento();
            o[3]=lista.get(i).getDenCorta();
            o[4]=(lista.get(i).getVigente())=='1'?"Si":"No";
            modelEle.addRow(o);
        }
        this.configEle.tablaElementos.setModel(modelEle);
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelEle);
        initTablaTab();
        System.out.println("Refrescando tabla automaticamente.");
        fila=-1;
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==configEle.btt_Registrar){
            registrarDatos();
            actualizarTabla();
        }
        if(e.getSource()==configEle.btt_Actualizar){
            actualizarDatos();
            actualizarTabla();
        }
        if(e.getSource()==configEle.btt_Eliminar){
            eliminarDatos();
            actualizarTabla();
        }
        if(e.getSource()==configEle.actualizaTabla){
            actualizarTabla();
            fila=-1;
        }
        if(e.getSource()==configEle.nuevo){
            vaciarCampos();
            actualizarTabla();
            fila=-1;
        }
        if(e.getSource()==configEle.codTab){
            if(configEle.codTab.getSelectedItem()!=null || configEle.codTab.getSelectedIndex()==-1){
                initDatoTab();
            }
        }
    }
    
    public void registrarDatos(){    
        //Objeto tab que sera agregado.
        Elementos ele = new Elementos();
        
        //Atributos elementos
        ele.setCodTab(configEle.codTab.getSelectedItem().toString());
        ele.setCodElemento(configEle.codigoElemento.getText());
        ele.setDenElemento(configEle.descEle.getText());
        ele.setDenCorta(configEle.descCorta.getText());
        char vig="Vigente".equals(configEle.vigente.getSelectedItem().toString())?'1':'0';
        ele.setVigente(vig);
        if(eleDAO.add(ele)==1){
            showMessage2("Elemento registrado correctamente");
            vaciarCampos();
            fila=-1;
        }else{
            showMessage1("Error al registrar Elementos");
        }
    }
    
    public void actualizarDatos(){
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int codEl = Integer.parseInt(configEle.tablaElementos.getValueAt(fila,1).toString());
            
            //Objeto Cliente que sera actualizado.
            Elementos ele = new Elementos();
            ele.setCodTab(configEle.codTab.getSelectedItem().toString());
            ele.setCodElemento(codEl+"");
            ele.setDenElemento(configEle.descEle.getText());
            ele.setDenCorta(configEle.descCorta.getText());
            char vig="Vigente".equals(configEle.vigente.getSelectedItem().toString())?'1':'0';
            ele.setVigente(vig);
            if(eleDAO.actualizar(ele)==1){
                showMessage2("Elemento registrado correctamente");
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
            int idEle = Integer.parseInt(configEle.tablaElementos.getValueAt(fila,1).toString());
            int idTab = Integer.parseInt(configEle.tablaElementos.getValueAt(fila,0).toString());
            eleDAO.eliminar(idEle,idTab);
            fila=-1;
        }else{
            showMessage1("Debe seleccionar una fila");
        }
    }
    
    public void vaciarCampos(){
        configEle.descEle.setText("");
        configEle.descCorta.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==configEle.tablaElementos){
            fila = configEle.tablaElementos.getSelectedRow();
            int codEle = Integer.parseInt(configEle.tablaElementos.getValueAt(fila,1).toString());
            int codTabcito = Integer.parseInt(configEle.tablaElementos.getValueAt(fila,0).toString());
            Elementos ele =new ElementosDAO().listarID(codEle,codTabcito);
            configEle.codTab.setSelectedItem(ele.getCodTab());
            initDatoTab();
            configEle.codigoElemento.setText(ele.getCodElemento());
            configEle.descEle.setText(ele.getDenElemento());
            configEle.descCorta.setText(ele.getDenCorta());
            configEle.vigente.setSelectedItem(ele.getVigente()=='1'?"Vigente":"No Vigente");
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
