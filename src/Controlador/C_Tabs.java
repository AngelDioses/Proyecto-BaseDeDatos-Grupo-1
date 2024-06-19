package Controlador;

import Modelo.DAO.TabsDAO;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Tabs;
import Vistas.V_Configuracion_Tab;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

public class C_Tabs implements java.awt.event.ActionListener, java.awt.event.MouseListener{
    TabsDAO tabDAO=new TabsDAO();
    V_Configuracion_Tab configTab=new V_Configuracion_Tab();
    DefaultTableModel modelTab = new DefaultTableModel();
    int fila=-1;
    
    public C_Tabs(V_Configuracion_Tab configTab){
        this.configTab=configTab;
        this.configTab.btt_Registrar.addActionListener(this);
        this.configTab.btt_Actualizar.addActionListener(this);
        this.configTab.btt_Eliminar.addActionListener(this);
        this.configTab.tablaTabs.addMouseListener(this);
        this.configTab.actualizaTabla.addActionListener(this);
        this.configTab.nuevo.addActionListener(this);
        init();
    }
    
    public void init(){
        initTablaTab();
        //generarId();
        this.configTab.init();
        ((AbstractDocument)configTab.descTab.getDocument()).setDocumentFilter(new LimitDocumentFilter(50,0));
        ((AbstractDocument)configTab.descCorta.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
    }
    
    public void initTablaTab(){
        limpiarTabla(modelTab);
        List<Tabs> lista = new TabsDAO().listar();
        modelTab = (DefaultTableModel)configTab.tablaTabs.getModel();
        Object[] o=new Object[4];
        for(int i=0;i<lista.size();i++){ 
            o[0]=lista.get(i).getCodTab();
            o[1]=lista.get(i).getDenTab();
            o[2]=lista.get(i).getDenCorta();
            o[3]=(lista.get(i).getVigente())=='1'?"Si":"No";
            modelTab.addRow(o);
        }
        this.configTab.tablaTabs.setModel(modelTab);
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelTab);
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
        if(e.getSource()==configTab.btt_Registrar){
            registrarDatos();
            actualizarTabla();
        }
        if(e.getSource()==configTab.btt_Actualizar){
            actualizarDatos();
            actualizarTabla();
        }
        if(e.getSource()==configTab.btt_Eliminar){
            eliminarDatos();
            actualizarTabla();
        }
        if(e.getSource()==configTab.actualizaTabla){
            actualizarTabla();
            fila=-1;
        }
        if(e.getSource()==configTab.nuevo){
            actualizarTabla();
            vaciarCampos();
            fila=-1;
        }
    }
    
    public void registrarDatos(){    
        //Objeto tab que sera agregado.
        Tabs tb = new Tabs();
        
        //Atributos tab
        //tb.setCodTab(configTab.codTab.getText());
        tb.setDenTab(configTab.descTab.getText());
        tb.setDenCorta(configTab.descCorta.getText());
        char vig="Vigente".equals(configTab.vigente.getSelectedItem().toString())?'1':'0';
        tb.setVigente(vig);
        if(tabDAO.add(tb)==1){
            showMessage2("Tabs registrado correctamente");
            vaciarCampos();
            fila=-1;
        }else{
            showMessage1("Error al registrar el Tabs");
        }
    }
    
    public void actualizarDatos(){
        //fila = configTab.tablaTabs.getSelectedRow();
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            String codTb = configTab.tablaTabs.getValueAt(fila,0).toString();
            
            //Objeto Cliente que sera actualizado.
            Tabs tb = new Tabs();
            tb.setCodTab(codTb);
            tb.setDenTab(configTab.descTab.getText());
            tb.setDenCorta(configTab.descCorta.getText());
            char vig="Vigente".equals(configTab.vigente.getSelectedItem().toString())?'1':'0';
            tb.setVigente(vig);
            if(tabDAO.actualizar(tb)==1){
                showMessage2("Tabs actualizado correctamente");
                vaciarCampos();
                fila=-1;
            }else{
                showMessage1("Error al registrar el Tabs");
            }
        }else{
            showMessage1("Debe seleccionar una fila");
        }
    }
    public void eliminarDatos(){
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int idTab = Integer.parseInt(configTab.tablaTabs.getValueAt(fila,0).toString());
            tabDAO.eliminarxId(idTab);
            fila=-1;
        }else{
            showMessage1("Debe seleccionar una fila");
        }
    }
    
    public void vaciarCampos(){
        configTab.descTab.setText("");
        configTab.descCorta.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==configTab.tablaTabs){
            fila = configTab.tablaTabs.getSelectedRow();
            int codTab = Integer.parseInt(configTab.tablaTabs.getValueAt(fila,0).toString());
            Tabs tb = new TabsDAO().listarId(codTab);
            //configTab.codTab.setText(tb.getCodTab()+"");
            configTab.descTab.setText(tb.getDenTab());
            configTab.descCorta.setText(tb.getDenCorta());
            configTab.vigente.setSelectedItem(tb.getVigente()=='1'?"Vigente":"No Vigente");
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
