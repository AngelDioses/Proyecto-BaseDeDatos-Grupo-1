package Controlador;

import Modelo.DAO.ElementosDAO;
import Modelo.DAO.PartidaDAO;
import Modelo.DAO.TabsDAO;
import Modelo.Elementos;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida;
import Modelo.Tabs;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_Partida;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

public class C_Partida implements ItemListener,ActionListener, KeyListener, MouseListener{
    
    PartidaDAO pDAO = new PartidaDAO();
    V_Partida vp = new V_Partida();
    DefaultTableModel modelPartidaI = new DefaultTableModel();
    DefaultTableModel modelPartidaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    int id=-1;
    
    public C_Partida(V_Partida vp){
        this.vp=vp;
        this.vp.btt_Actualizar_I.addActionListener(this);
        this.vp.btt_Registrar_I.addActionListener(this);
        this.vp.btt_Eliminar_I.addActionListener(this);
        this.vp.tablaPartida_I.addMouseListener(this);
        this.vp.tUniMed_I.addItemListener(this);
        this.vp.btt_Actualizar_E.addActionListener(this);
        this.vp.btt_Registrar_E.addActionListener(this);
        this.vp.btt_Eliminar_E.addActionListener(this);
        this.vp.tablaPartida_E.addMouseListener(this);
        this.vp.tUniMed_E.addItemListener(this);
        this.vp.actualizaTabla.addActionListener(this);
        this.vp.nuevo.addActionListener(this);
        init();
    }
    
    public void init(){
        initTablaPartida_I();
        initTablaPartida_E();
        vp.init();
        initListarTabs();
        ((AbstractDocument)vp.desPartida_I.getDocument()).setDocumentFilter(new LimitDocumentFilter(30,0));
        ((AbstractDocument)vp.desPartida_E.getDocument()).setDocumentFilter(new LimitDocumentFilter(30,0));
    }
    
     public void initListarTabs(){
        System.out.println("Tabs");
        vp.tUniMed_I.removeAllItems();
        vp.tUniMed_E.removeAllItems();
        List<Tabs> listaTab = new TabsDAO().listar();
        for(int i=0;i<listaTab.size();i++){
            vp.tUniMed_I.addItem(listaTab.get(i).getCodTab());
            vp.tUniMed_E.addItem(listaTab.get(i).getCodTab());
        }
    }
    
     public void initListarElementos(int cod){
        System.out.println("Elementos");
        vp.eUniMed_I.removeAllItems();
        vp.eUniMed_E.removeAllItems();
        List<Elementos> listaEle = new ElementosDAO().listarTabs(cod);
        for(int i=0;i<listaEle.size();i++){
            vp.eUniMed_I.addItem(listaEle.get(i).getCodElemento());
            vp.eUniMed_E.addItem(listaEle.get(i).getCodElemento());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION PARTIDA");
        if(e.getSource()==vp.btt_Registrar_I){
            registrarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vp.btt_Actualizar_I){
            actualizarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vp.btt_Eliminar_I){
            eliminarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vp.btt_Registrar_E){
            registrarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vp.btt_Actualizar_E){
            actualizarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vp.btt_Eliminar_E){
            eliminarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vp.actualizaTabla){
            actualizarTabla();
        }
        if(e.getSource()==vp.nuevo){
            vaciarCampos();
            actualizarTabla();
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelPartidaI);
        limpiarTabla(modelPartidaE);
        initTablaPartida_I();
        initTablaPartida_E();
        System.out.println("Refrescando tabla automaticamente.");
    }
    
    public void initTablaPartida_I(){
        List<Partida> listaI = pDAO.listarPorCodCia(varCodCiaGlobalDeLogin,"I");
        modelPartidaI = (DefaultTableModel)vp.tablaPartida_I.getModel();
        Object[] o=new Object[6];
        sorterI = new TableRowSorter<>(modelPartidaI);
        vp.tablaPartida_I.setRowSorter(sorterI);
        limpiarTabla(modelPartidaI);
        for(int i=0;i<listaI.size();i++){
            o[0]=listaI.get(i).getCodPartida();
            o[1]=listaI.get(i).getCodPartidas();
            o[2]=listaI.get(i).getDesPartida();
            o[3]=listaI.get(i).gettUniMed();
            o[4]=listaI.get(i).geteUniMed();
            o[5]=(listaI.get(i).getVigente())=='1'?"Si":"No";
            modelPartidaI.addRow(o);
        }
        vp.tablaPartida_I.setModel(modelPartidaI);
    }
    
    public void initTablaPartida_E(){
        List<Partida> listaE = pDAO.listarPorCodCia(varCodCiaGlobalDeLogin,"E");
        modelPartidaE = (DefaultTableModel)vp.tablaPartida_E.getModel();
        Object[] o=new Object[6];
        sorterE = new TableRowSorter<>(modelPartidaE);
        vp.tablaPartida_E.setRowSorter(sorterE);
        limpiarTabla(modelPartidaE);
        for(int i=0;i<listaE.size();i++){
            o[0]=listaE.get(i).getCodPartida();
            o[1]=listaE.get(i).getCodPartidas();
            o[2]=listaE.get(i).getDesPartida();
            o[3]=listaE.get(i).gettUniMed();
            o[4]=listaE.get(i).geteUniMed();
            o[5]=(listaE.get(i).getVigente())=='1'?"Si":"No";
            modelPartidaE.addRow(o);
        }
        vp.tablaPartida_E.setModel(modelPartidaE);
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
        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila,cod;
        if(e.getSource()==vp.tablaPartida_I){
            fila = vp.tablaPartida_I.getSelectedRow();
            cod = Integer.parseInt(vp.tablaPartida_I.getValueAt(fila,0).toString());
            System.out.println("PartidaMezcla = "+cod);
            Partida pI = new PartidaDAO().listarId(varCodCiaGlobalDeLogin,cod,"I");
            vp.desPartida_I.setText(pI.getDesPartida());
            vp.tUniMed_I.setSelectedItem(pI.gettUniMed());
            vp.eUniMed_I.setSelectedItem(pI.geteUniMed());
            vp.vigente_I.setSelectedItem((pI.getVigente()=='1'?"Vigente":"No Vigente"));           
        }
        
        if(e.getSource()==vp.tablaPartida_E){
            fila = vp.tablaPartida_E.getSelectedRow();
            cod = Integer.parseInt(vp.tablaPartida_E.getValueAt(fila,0).toString());
            System.out.println("PartidaMezcla = "+cod);
            Partida pE = new PartidaDAO().listarId(varCodCiaGlobalDeLogin,cod,"E");
            vp.desPartida_E.setText(pE.getDesPartida());
            vp.tUniMed_E.setSelectedItem(pE.gettUniMed());
            vp.eUniMed_E.setSelectedItem(pE.geteUniMed());
            vp.vigente_E.setSelectedItem((pE.getVigente()=='1'?"Vigente":"No Vigente"));           
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
    
    public void registrarDatos(String tip){
        Partida pm = new Partida();
        char vig;
        if(tip == "I"){
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            pm.settUniMed(vp.tUniMed_I.getSelectedItem().toString());
            pm.seteUniMed(vp.eUniMed_I.getSelectedItem().toString());
            pm.setDesPartida(vp.desPartida_I.getText());
            vig = "Vigente".equals(vp.vigente_I.getSelectedItem().toString())?'1':'0';
            pm.setVigente(vig);
        }
        else{
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            pm.settUniMed(vp.tUniMed_E.getSelectedItem().toString());
            pm.seteUniMed(vp.eUniMed_E.getSelectedItem().toString());
            pm.setDesPartida(vp.desPartida_E.getText());
            vig = "Vigente".equals(vp.vigente_E.getSelectedItem().toString())?'1':'0';
            pm.setVigente(vig);
        }
        if(pDAO.add(pm)==1){ //Corregir
            showMessage2("Partida registrada correctamente");
            vaciarCampos();
        }else{
            showMessage1("Error al registrar Partida");
        }
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
    
    public void actualizarDatos(String tip){
        int fila,cod;
        char vig;
        Partida pm = new Partida();
        if(tip == "I"){
            fila = vp.tablaPartida_I.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                cod = Integer.parseInt(vp.tablaPartida_I.getValueAt(fila,0).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setDesPartida(vp.desPartida_I.getText());
                pm.settUniMed(vp.tUniMed_I.getSelectedItem().toString());
                pm.seteUniMed(vp.eUniMed_I.getSelectedItem().toString());
                vig="Vigente".equals(vp.vigente_I.getSelectedItem().toString())?'1':'0';
                pm.setVigente(vig);
                if(pDAO.actualizar(pm)==1){
                    showMessage2("Partida registrado correctamente");
                    vaciarCampos();
                }else{
                    showMessage1("Error al registrar Partida");
                }
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vp.tablaPartida_E.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                cod = Integer.parseInt(vp.tablaPartida_E.getValueAt(fila,0).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setDesPartida(vp.desPartida_E.getText());
                pm.settUniMed(vp.tUniMed_E.getSelectedItem().toString());
                pm.seteUniMed(vp.eUniMed_E.getSelectedItem().toString());
                vig="Vigente".equals(vp.vigente_E.getSelectedItem().toString())?'1':'0';
                pm.setVigente(vig);
                if(pDAO.actualizar(pm)==1){
                    showMessage2("Partida registrado correctamente");
                    vaciarCampos();
                }else{
                    showMessage1("Error al registrar Partida");
                }
            }else{
                showMessage1("Debe seleccionar una fila");
            } 
        }
   } 
    
    public void eliminarDatos(String tip){
        int fila,cod;
        if(tip=="I"){
            fila = vp.tablaPartida_I.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                cod = Integer.parseInt(vp.tablaPartida_I.getValueAt(fila,0).toString());
                new PartidaDAO().eliminarDatos(varCodCiaGlobalDeLogin,cod,tip);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vp.tablaPartida_E.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                cod = Integer.parseInt(vp.tablaPartida_E.getValueAt(fila,0).toString());
                new PartidaDAO().eliminarDatos(varCodCiaGlobalDeLogin,cod,tip);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
    }
    
    public void vaciarCampos(){
        vp.desPartida_E.setText("");
        vp.desPartida_I.setText("");
    }
    
    int codT;
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==vp.tUniMed_I){
            codT = Integer.parseInt(vp.tUniMed_I.getSelectedItem().toString());
            initListarElementos(codT);
        }
        
        if(e.getSource()==vp.tUniMed_E){
            codT = Integer.parseInt(vp.tUniMed_E.getSelectedItem().toString());
            initListarElementos(codT);
        }
    }
}