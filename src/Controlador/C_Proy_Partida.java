package Controlador;

import Modelo.DAO.PartidaDAO;
import Modelo.DAO.Proy_PartidaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida;
import Modelo.Proy_Partida;
import Modelo.Proyecto;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_Proy_Partida;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class C_Proy_Partida implements ActionListener, KeyListener, MouseListener{
    
    Proy_PartidaDAO ppDAO = new Proy_PartidaDAO();
    V_Proy_Partida vpp = new V_Proy_Partida();
    DefaultTableModel modelProy_PartidaI = new DefaultTableModel();
    DefaultTableModel modelProy_PartidaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    
    public C_Proy_Partida(V_Proy_Partida vpp){
        this.vpp=vpp;
        this.vpp.btt_Actualizar_I.addActionListener(this);
        this.vpp.btt_Registrar_I.addActionListener(this);
        this.vpp.btt_Eliminar_I.addActionListener(this);
        this.vpp.tablaProy_Partida_I.addMouseListener(this);
        this.vpp.btt_Actualizar_E.addActionListener(this);
        this.vpp.btt_Registrar_E.addActionListener(this);
        this.vpp.btt_Eliminar_E.addActionListener(this);
        this.vpp.tablaProy_Partida_E.addMouseListener(this);
        this.vpp.actualizaTabla.addActionListener(this);
        this.vpp.nuevo.addActionListener(this);
        init();
    }
    
    public void init(){
        initTablaProy_Partida_I();
        initTablaProy_Partida_E();
        vpp.init();
        initListarPartidas();
        initListarProyectos_I();
        initListarProyectos_E();
    }
    
    public void initListarProyectos_I(){
        vpp.codPyto_I.removeAllItems();
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vpp.codPyto_I.addItem(lista.get(i).getCodPyto());
        }
    }
    
    public void initListarProyectos_E(){
        vpp.codPyto_E.removeAllItems();
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vpp.codPyto_E.addItem(lista.get(i).getCodPyto());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION PARTIDA");
        if(e.getSource()==vpp.btt_Registrar_I){
            registrarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Actualizar_I){
            actualizarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Eliminar_I){
            eliminarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Registrar_E){
            registrarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Actualizar_E){
            actualizarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Eliminar_E){
            eliminarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpp.actualizaTabla){
            actualizarTabla();
        }
        if(e.getSource()==vpp.nuevo){
            vaciarCampos();
            actualizarTabla();
        }
    }
    
    public void initListarPartidas(){
        System.out.println("Partidas");
        vpp.codPartida_I.removeAllItems();
        vpp.codPartida_E.removeAllItems();
        List<Partida> listaParI = new PartidaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"I");
        List<Partida> listaParE = new PartidaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"E");
        for(int i=0;i<listaParI.size();i++){
            vpp.codPartida_I.addItem(listaParI.get(i).getCodPartida());
        }
        for(int i=0;i<listaParE.size();i++){
            vpp.codPartida_E.addItem(listaParE.get(i).getCodPartida());
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelProy_PartidaI);
        limpiarTabla(modelProy_PartidaE);
        initTablaProy_Partida_I();
        initTablaProy_Partida_E();
        System.out.println("Refrescando tabla automaticamente.");
    }
    
    public void initTablaProy_Partida_I(){
        List<Proy_Partida> listaI = new Proy_PartidaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"I");
        modelProy_PartidaI = (DefaultTableModel)vpp.tablaProy_Partida_I.getModel();
        Object[] o=new Object[5];
        sorterI = new TableRowSorter<>(modelProy_PartidaI);
        vpp.tablaProy_Partida_I.setRowSorter(sorterI);
        limpiarTabla(modelProy_PartidaI);
        for(int i=0;i<listaI.size();i++){
            o[0]=listaI.get(i).getCodPyto();
            o[1]=listaI.get(i).getNroVersion();
            o[2]=listaI.get(i).getCodPartida();
            if(listaI.get(i).getCodEstado().equals("1")){
                o[3]="Disponible";}
            else{
                o[3]=(listaI.get(i).getCodEstado().equals("2"))?"No Disp.":"Reservado";}
            o[4]=(listaI.get(i).getVigente())=='1'?"Si":"No";
            modelProy_PartidaI.addRow(o);
        }
        vpp.tablaProy_Partida_I.setModel(modelProy_PartidaI);
    }
    
    public void initTablaProy_Partida_E(){
        List<Proy_Partida> listaE = new Proy_PartidaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"E");
        modelProy_PartidaE = (DefaultTableModel)vpp.tablaProy_Partida_E.getModel();
        Object[] o=new Object[7];
        sorterE = new TableRowSorter<>(modelProy_PartidaE);
        vpp.tablaProy_Partida_E.setRowSorter(sorterE);
        limpiarTabla(modelProy_PartidaE);
        for(int i=0;i<listaE.size();i++){
            o[0]=listaE.get(i).getCodPyto();
            o[1]=listaE.get(i).getNroVersion();
            o[2]=listaE.get(i).getCodPartida();
             if(listaE.get(i).getCodEstado().equals("1")){
                o[3]="Disponible";}
            else{
                o[3]=(listaE.get(i).getCodEstado().equals("2"))?"No Disp.":"Reservado";}
            o[4]=(listaE.get(i).getVigente())=='1'?"Si":"No";
            modelProy_PartidaE.addRow(o);
        }
        vpp.tablaProy_Partida_E.setModel(modelProy_PartidaE);
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
        int fila,pyto,ver,cod;
        if(e.getSource()==vpp.tablaProy_Partida_I){
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            pyto = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,0).toString());
            ver = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,1).toString());
            cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,2).toString());
            System.out.println("PartidaMezcla = "+cod);
            Proy_Partida pI = new Proy_PartidaDAO().listarId(varCodCiaGlobalDeLogin,pyto,ver,cod,"I");
            vpp.codPyto_I.setSelectedItem(String.valueOf(pI.getCodPyto()));
            vpp.codPartida_I.setSelectedItem(String.valueOf(pI.getCodPartida()));  
            vpp.nroVersion_I.setValue(pI.getNroVersion());
        }
        
        if(e.getSource()==vpp.tablaProy_Partida_E){
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            pyto = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,0).toString());
            ver = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,1).toString());
            cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,2).toString());
            System.out.println("PartidaMezcla = "+cod);
            Proy_Partida pE = new Proy_PartidaDAO().listarId(varCodCiaGlobalDeLogin,pyto,ver,cod,"E");
            vpp.codPyto_E.setSelectedItem(pE.getCodPyto());
            vpp.codPartida_E.setSelectedItem(pE.getCodPartida());
            vpp.nroVersion_E.setValue(pE.getNroVersion());
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
        Proy_Partida pm = new Proy_Partida();
        if(tip == "I"){
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            pm.setCodPyto(Integer.parseInt(vpp.codPyto_I.getSelectedItem().toString()));
            pm.setCodPartida(Integer.parseInt(vpp.codPartida_I.getSelectedItem().toString()));
            pm.setNroVersion(Integer.parseInt(vpp.nroVersion_I.getValue().toString()));
            pm.setTabEstado("-1");
            pm.setCodEstado("1");
            Partida p = new PartidaDAO().listarId(varCodCiaGlobalDeLogin,pm.getCodPartida(),"I");
            pm.setCodPartidas(p.getCodPartidas());
            pm.setVigente(p.getVigente());
        }
        else{
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            pm.setCodPyto(Integer.parseInt(vpp.codPyto_E.getSelectedItem().toString()));
            pm.setCodPartida(Integer.parseInt(vpp.codPartida_E.getSelectedItem().toString()));
            pm.setNroVersion(Integer.parseInt(vpp.nroVersion_E.getValue().toString()));
            pm.setTabEstado("-1");
            pm.setCodEstado("1");
            Partida p = new PartidaDAO().listarId(varCodCiaGlobalDeLogin,pm.getCodPartida(),"E");
            pm.setCodPartidas(p.getCodPartidas());
            pm.setVigente(p.getVigente());
        }
        if(ppDAO.add(pm)==1){
            showMessage2("Proy_Partida registrado correctamente");
            vaciarCampos();
        }else{
            showMessage1("Error al registrar Proy_Partida");
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
        int fila,pyto,cod;
        char vig;
        Proy_Partida pm = new Proy_Partida();
        if(tip == "I"){
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,0).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,2).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCodPyto(pyto);
                pm.setNroVersion(Integer.parseInt(vpp.nroVersion_I.getValue().toString()));
                if(ppDAO.actualizar(pm)==1){
                    showMessage2("Proy_Partida registrado correctamente");
                    vaciarCampos();
                }else{
                    showMessage1("Error al registrar Proy_Partida");
                }
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,0).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,2).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCodPyto(pyto);
                pm.setNroVersion(Integer.parseInt(vpp.nroVersion_E.getValue().toString()));
                if(ppDAO.actualizar(pm)==1){
                    showMessage2("Proy_Partida registrado correctamente");
                    vaciarCampos();
                }else{
                    showMessage1("Error al registrar Proy_Partida");
                }
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
    }
    
    public void eliminarDatos(String tip){
        int fila,cod,pyto,ver;
        if(tip=="I"){
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,0).toString());
                ver = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,1).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,2).toString());
                ppDAO.eliminarDatos(varCodCiaGlobalDeLogin,cod,tip,pyto,ver);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,0).toString());
                ver = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,1).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,2).toString());
                ppDAO.eliminarDatos(varCodCiaGlobalDeLogin,cod,tip,pyto,ver);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
    }
    
    public void vaciarCampos(){
        vpp.nroVersion_E.setValue(1);
        vpp.nroVersion_I.setValue(1);
        vpp.nroVersion_I.setModel(new SpinnerNumberModel(1,1,9,1));
        
        vpp.nroVersion_E.setModel(new SpinnerNumberModel(1,1,9,1));
    }
}