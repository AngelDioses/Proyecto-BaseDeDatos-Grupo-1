package Controlador;

import Modelo.DAO.Partida_MezclaDAO;
import Modelo.DAO.Proy_Partida_MezclaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida_Mezcla;
import Modelo.Proy_Partida_Mezcla;
import Modelo.Proyecto;
import Vistas.V_GeneratorTree;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Vistas.V_Proy_Partida_Mezcla;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class C_Proy_Partida_Mezcla implements ItemListener,ActionListener, KeyListener, MouseListener{
    
    Proy_Partida_MezclaDAO ppmDAO = new Proy_Partida_MezclaDAO();
    V_Proy_Partida_Mezcla vppm = new V_Proy_Partida_Mezcla();
    DefaultTableModel modelProyPartidaMezclaI = new DefaultTableModel();
    DefaultTableModel modelProyPartidaMezclaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    
    public C_Proy_Partida_Mezcla(V_Proy_Partida_Mezcla vppm){
        this.vppm=vppm;
        this.vppm.btt_Actualizar_I.addActionListener(this);
        this.vppm.btt_Registrar_I.addActionListener(this);
        this.vppm.btt_Eliminar_I.addActionListener(this);
        this.vppm.tablaProy_Partida_Mezcla_I.addMouseListener(this);
        this.vppm.padCodPartida_I.addItemListener(this);
        this.vppm.proyecto_I.addItemListener(this);
        
        this.vppm.btt_Actualizar_E.addActionListener(this);
        this.vppm.btt_Registrar_E.addActionListener(this);
        this.vppm.btt_Eliminar_E.addActionListener(this);
        this.vppm.tablaProy_Partida_Mezcla_E.addMouseListener(this);
        this.vppm.padCodPartida_E.addItemListener(this);
        this.vppm.proyecto_E.addItemListener(this);
        
        this.vppm.actualizaTablaIng.addActionListener(this);
        this.vppm.generaArbolIng.addActionListener(this);
        this.vppm.actualizaTablaEgr.addActionListener(this);
        this.vppm.generaArbolEgr.addActionListener(this);
        this.vppm.nuevoIng.addActionListener(this);
        this.vppm.nuevoEgr.addActionListener(this);
        init();
    }
    
    public void init(){
        initTablaProyPartidaMezcla_I();
        initTablaProyPartidaMezcla_E();
        vppm.init();
        initListarProyectos_I();
        initListarProyectos_E();
    }
    
    public void initListarProyectos_I(){
        vppm.proyecto_I.removeAllItems();
        List<Proyecto> listaPI = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<listaPI.size();i++){
            System.out.println(listaPI.get(i).getCodPyto());
            vppm.proyecto_I.addItem(listaPI.get(i).getCodPyto());
        }
    }
    
    public void initListarProyectos_E(){
        vppm.proyecto_E.removeAllItems();
        List<Proyecto> listaPE = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<listaPE.size();i++){
            vppm.proyecto_E.addItem(listaPE.get(i).getCodPyto());
        }
    }
    
    public void initListarPartidas(int pyto){
        System.out.println("Partidas");
        vppm.padCodPartida_I.removeAllItems();
        vppm.padCodPartida_E.removeAllItems();
        List<Partida_Mezcla> listaParIP = new Partida_MezclaDAO().listarMezclas(varCodCiaGlobalDeLogin,"I");
        List<Partida_Mezcla> listaParAuxIP = new Partida_MezclaDAO().listarMezclasPadre(varCodCiaGlobalDeLogin,"I");
        List<Partida_Mezcla> listaParEP = new Partida_MezclaDAO().listarMezclas(varCodCiaGlobalDeLogin,"E");
        List<Partida_Mezcla> listaParAuxEP = new Partida_MezclaDAO().listarMezclasPadre(varCodCiaGlobalDeLogin,"E");
        if(listaParIP.size()==0){
            for(int i=0;i<listaParAuxIP.size();i++){
                vppm.padCodPartida_I.addItem(listaParAuxIP.get(i).getCodPartida());
            }
        }
        else{
            for(int i=0;i<listaParIP.size();i++){
                vppm.padCodPartida_I.addItem(listaParIP.get(i).getPadCodPartida());
            }
        }
            
        if(listaParEP.size()==0){
            for(int i=0;i<listaParAuxEP.size();i++){
                vppm.padCodPartida_E.addItem(listaParAuxEP.get(i).getCodPartida());
            }
        }
        else{
            for(int i=0;i<listaParEP.size();i++){
                vppm.padCodPartida_E.addItem(listaParEP.get(i).getPadCodPartida());
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION PARTIDA_MEZCLA");
        if(e.getSource()==vppm.btt_Registrar_I){
            registrarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vppm.btt_Actualizar_I){
            actualizarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vppm.btt_Eliminar_I){
            eliminarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vppm.btt_Registrar_E){
            registrarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vppm.btt_Actualizar_E){
            actualizarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vppm.btt_Eliminar_E){
            eliminarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vppm.actualizaTablaIng){
            actualizarTabla();
        }
        if(e.getSource()==vppm.generaArbolIng){
            System.out.println("2");
            generaArbolIngreso();
        }
        if(e.getSource()==vppm.actualizaTablaEgr){
            actualizarTabla();
        }
        if(e.getSource()==vppm.generaArbolEgr){
            System.out.println("4");
            generaArbolEgreso();
        }
        if(e.getSource()==vppm.nuevoEgr){
            vaciarCampos();
            actualizarTabla();
        }
        if(e.getSource()==vppm.nuevoIng){
            vaciarCampos();
            actualizarTabla();
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelProyPartidaMezclaI);
        limpiarTabla(modelProyPartidaMezclaE);
        initTablaProyPartidaMezcla_I();
        initTablaProyPartidaMezcla_E();
        System.out.println("Refrescando tabla automaticamente.");
    }
    
    public void initTablaProyPartidaMezcla_I(){
        List<Proy_Partida_Mezcla> listaI = new Proy_Partida_MezclaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"I");
        modelProyPartidaMezclaI = (DefaultTableModel)vppm.tablaProy_Partida_Mezcla_I.getModel();
        Object[] o=new Object[12];
        sorterI = new TableRowSorter<>(modelProyPartidaMezclaI);
        vppm.tablaProy_Partida_Mezcla_I.setRowSorter(sorterI);
        limpiarTabla(modelProyPartidaMezclaI);
        for(int i=0;i<listaI.size();i++){
            o[0]=listaI.get(i).getCodPyto();
            o[1]=listaI.get(i).getNroVersion();
            o[2]=listaI.get(i).getCorr();
            o[3]=listaI.get(i).getCodPartida();
            o[4]=(listaI.get(i).getPadCodPartida())==0?"NULL":listaI.get(i).getPadCodPartida();
            o[5]=listaI.get(i).getNivel();
            o[6]=listaI.get(i).getOrden();
            o[7]=listaI.get(i).gettUnitMed();
            o[8]=listaI.get(i).geteUnitMed();
            o[9]=listaI.get(i).getCostoUnit();
            o[10]=listaI.get(i).getCant();
            o[11]=listaI.get(i).getCostoTot();
            modelProyPartidaMezclaI.addRow(o);
        }
        vppm.tablaProy_Partida_Mezcla_I.setModel(modelProyPartidaMezclaI);
    }
    
    public void initTablaProyPartidaMezcla_E(){
        List<Proy_Partida_Mezcla> listaE = new Proy_Partida_MezclaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"E");
        modelProyPartidaMezclaE = (DefaultTableModel)vppm.tablaProy_Partida_Mezcla_E.getModel();
        Object[] o=new Object[12];
        sorterE = new TableRowSorter<>(modelProyPartidaMezclaE);
        vppm.tablaProy_Partida_Mezcla_E.setRowSorter(sorterE);
        limpiarTabla(modelProyPartidaMezclaE);
        for(int i=0;i<listaE.size();i++){
            o[0]=listaE.get(i).getCodPyto();
            o[1]=listaE.get(i).getNroVersion();
            o[2]=listaE.get(i).getCorr();
            o[3]=listaE.get(i).getCodPartida();
            o[4]=(listaE.get(i).getPadCodPartida())==0?"NULL":listaE.get(i).getPadCodPartida();
            o[5]=listaE.get(i).getNivel();
            o[6]=listaE.get(i).getOrden();
            o[7]=listaE.get(i).gettUnitMed();
            o[8]=listaE.get(i).geteUnitMed();
            o[9]=listaE.get(i).getCostoUnit();
            o[10]=listaE.get(i).getCant();
            o[11]=listaE.get(i).getCostoTot();
            modelProyPartidaMezclaE.addRow(o);
        }
        vppm.tablaProy_Partida_Mezcla_E.setModel(modelProyPartidaMezclaE);
    }
    
    public void generaArbolIngreso(){
        int filaSelecIng, pyto;
        filaSelecIng = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();
        pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(filaSelecIng,0).toString()); //codPyto
        //codPartida = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(filaSelecIng,3).toString());//codPartida
        V_GeneratorTree tg1=new V_GeneratorTree(varCodCiaGlobalDeLogin,pyto,"I");
        tg1.setVisible(true);
        System.out.println("LA FILA DE INGRESO ES: "+filaSelecIng);
    }
    
    public void generaArbolEgreso(){
        int filaSelecEgr, pyto;
        filaSelecEgr = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
        pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(filaSelecEgr,0).toString()); //codPyto
        //codPartida = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(filaSelecEgr,3).toString());//codPartida
        V_GeneratorTree tg2=new V_GeneratorTree(varCodCiaGlobalDeLogin,pyto,"E");
        tg2.setVisible(true);
        System.out.println("LA FILA DE Egreso ES: "+filaSelecEgr);
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
       int fila,cor,cod,pyto,ver;
       if(e.getSource()==vppm.tablaProy_Partida_Mezcla_I){
            fila = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();
            pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,0).toString());
            ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,1).toString());
            cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,2).toString());
            cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,3).toString());
            
            String pad = vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,4).toString();
            System.out.println("PartidaMezcla = "+cod);
            Proy_Partida_Mezcla pmI = new Proy_Partida_MezclaDAO().listarId(varCodCiaGlobalDeLogin,"I",cod,cor,ver,pyto);
            vppm.proyecto_I.setSelectedItem(String.valueOf(pmI.getCodPyto()));
            System.out.println(pad);
            if(pad=="NULL"){
                vppm.padCodPartida_I.setSelectedItem(pmI.getCodPartida());
            }
            else{
                vppm.padCodPartida_I.setSelectedItem(pmI.getPadCodPartida());
            }
            vppm.cantidad_I.setValue(pmI.getCant());
            vppm.nroVersion_I.setValue(pmI.getNroVersion());
        }
       
       if(e.getSource()==vppm.tablaProy_Partida_Mezcla_E){
            fila = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
            pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,0).toString());
            ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,1).toString());
            cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,2).toString());
            cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,3).toString());
            
            String pad = vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,4).toString();
            System.out.println("PartidaMezcla = "+cor);
            Proy_Partida_Mezcla pmE = new Proy_Partida_MezclaDAO().listarId(varCodCiaGlobalDeLogin,"E",cod,cor,ver,pyto);
            vppm.proyecto_E.setSelectedItem(String.valueOf(pmE.getCodPyto()));
            if(pad=="NULL"){
                vppm.padCodPartida_E.setSelectedItem(pmE.getCodPartida());
            }
            else{
                vppm.padCodPartida_E.setSelectedItem(pmE.getPadCodPartida());
            }
            vppm.cantidad_E.setValue(pmE.getCant());
            vppm.nroVersion_E.setValue(pmE.getNroVersion());          
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
        Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
        if(tip == "I"){
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            
            pm.setCodPyto(Integer.parseInt(vppm.proyecto_I.getSelectedItem().toString()));
            pm.setPadCodPartida(Integer.parseInt(vppm.padCodPartida_I.getSelectedItem().toString()));
            pm.setNroVersion(Integer.parseInt(vppm.nroVersion_I.getValue().toString()));
            pm.setCant(Integer.parseInt(vppm.cantidad_I.getValue().toString()));
        }
        else{
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            
            pm.setCodPyto(Integer.parseInt(vppm.proyecto_E.getSelectedItem().toString()));
            pm.setPadCodPartida(Integer.parseInt(vppm.padCodPartida_E.getSelectedItem().toString()));
            pm.setNroVersion(Integer.parseInt(vppm.nroVersion_E.getValue().toString()));
            pm.setCant(Integer.parseInt(vppm.cantidad_E.getValue().toString()));
        }
        if(ppmDAO.add(pm)==1){
            showMessage2("Proy_Partida_Mezcla registrado correctamente");
            vaciarCampos();
        }else{
            showMessage1("Error al registrar el Proy_Partida_Mezcla");
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
        int fila,cod,cor,pyto;
        Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
        if(tip == "I"){
            fila = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,0).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,2).toString());
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,3).toString());
                
                
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCorr(cor);
                pm.setCodPyto(pyto);
                
                pm.setCant(Integer.parseInt(vppm.cantidad_I.getValue().toString()));
                pm.setNroVersion(Integer.parseInt(vppm.nroVersion_I.getValue().toString()));
                pm.setCostoTot(pm.getCant()*pm.getCostoUnit());
                
                new Proy_Partida_MezclaDAO().actualizar(pm);
                if(ppmDAO.actualizar(pm)==1){
                    showMessage2("Proy_Partida_Mezcla registrado correctamente");
                    vaciarCampos();
                }else{
                    showMessage1("Proy_Partida_Mezcla al registrar Elementos");
                }
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,0).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,2).toString());
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,3).toString());
                
                
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCorr(cor);
                pm.setCodPyto(pyto);
                
                pm.setCant(Integer.parseInt(vppm.cantidad_E.getValue().toString()));
                pm.setNroVersion(Integer.parseInt(vppm.nroVersion_E.getValue().toString()));
                pm.setCostoTot(pm.getCant()*pm.getCostoUnit());
                
                ppmDAO.actualizar(pm);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
    }
    
    public void eliminarDatos(String tip){
        int fila,cod,cor,pyto,ver;
        if(tip=="I"){
            fila = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,0).toString());
                ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,1).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,2).toString());
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila,3).toString());
                new Proy_Partida_MezclaDAO().eliminarDatos(varCodCiaGlobalDeLogin,cod,tip,cor,pyto,ver);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,0).toString());
                ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,1).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,2).toString());
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila,3).toString());
                new Proy_Partida_MezclaDAO().eliminarDatos(varCodCiaGlobalDeLogin,cod,tip,cor,pyto,ver);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
    }
    
    public void vaciarCampos(){
        vppm.nroVersion_E.setValue(1);
        vppm.nroVersion_I.setValue(1);
        vppm.cantidad_E.setValue(1);
        vppm.cantidad_I.setValue(1);
        
         vppm.nroVersion_I.setModel(new SpinnerNumberModel(1,1,9,1));
         vppm.cantidad_I.setModel(new SpinnerNumberModel(1,1,999,1));
        
         vppm.nroVersion_E.setModel(new SpinnerNumberModel(1,1,9,1));
         vppm.cantidad_E.setModel(new SpinnerNumberModel(1,1,999,1));
    }
    
    int pyto;
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==vppm.proyecto_I){
            if(vppm.proyecto_I.getSelectedIndex()!=-1){
                initListarPartidas(Integer.parseInt(vppm.proyecto_I.getSelectedItem().toString()));
            } else{
                vppm.proyecto_I.setSelectedItem(-1);
            }
        }
        
         if(e.getSource()==vppm.proyecto_E){
            if(vppm.proyecto_E.getSelectedIndex()!=-1){
                initListarPartidas(Integer.parseInt(vppm.proyecto_E.getSelectedItem().toString()));
            } else{
                vppm.proyecto_E.setSelectedItem(-1);
            }
        }
    }
    
}
