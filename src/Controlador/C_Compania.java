package Controlador;

import Modelo.Cia;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.DAO.CiaDAO;
import Vistas.V_Compania;
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

public class C_Compania implements ActionListener, KeyListener, MouseListener{
    
    CiaDAO ciaDAO = new CiaDAO();
    V_Compania vCia = new V_Compania();
    DefaultTableModel modelCia = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int id=-1;
    int optionBusqueda=0;
    
    public C_Compania(V_Compania vc){
        this.vCia=vc;
        this.vCia.btt_Actualizar.addActionListener(this);
        this.vCia.btt_Registrar.addActionListener(this);
        this.vCia.btt_Eliminar.addActionListener(this);
        this.vCia.tablaCia.addMouseListener(this);
        this.vCia.txtBusqueda.addKeyListener(this);
        init();
    }
    
    public void init(){
        initTablaCia();
        vCia.init();
        vCia.inhabilitarCampos();
        ((AbstractDocument)vCia.desCia.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vCia.desCorta.getDocument()).setDocumentFilter(new LimitDocumentFilter(30,0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION PROVEEDOR");
        if(e.getSource()==vCia.btt_Registrar){
            registrarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vCia.btt_Actualizar){
            actualizarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vCia.btt_Eliminar){
            eliminarDatos();
            actualizarTabla();
        }
    }
    
    public void filtrar1(){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vCia.txtBusqueda.getText().trim()));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void filtrar2(int num){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vCia.txtBusqueda.getText().trim(),num));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void initTablaCia(){
        List<Cia> lista = new CiaDAO().listar();
        modelCia = (DefaultTableModel)vCia.tablaCia.getModel();
        Object[] o=new Object[4];
        sorter = new TableRowSorter<>(modelCia);
        limpiarTabla(modelCia);
        vCia.tablaCia.setRowSorter(sorter);
        for(int i=0;i<lista.size();i++){ 
            o[0]=lista.get(i).getCodCia(); 
            o[1]=lista.get(i).getDesCia();
            o[2]=lista.get(i).getDesCorta();
            o[3]=(lista.get(i).getVigente())=='1'?"Si":"No";
            modelCia.addRow(o);
        }
        vCia.tablaCia.setModel(modelCia);
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelCia);
        initTablaCia();
        System.out.println("Refrescando tabla automaticamente.");
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
        optionBusqueda=vCia.opt;
        if(e.getComponent()==vCia.txtBusqueda){
            switch(optionBusqueda){
                case 0 -> filtrar1();
                case 1 -> filtrar2(optionBusqueda);
                case 2 -> filtrar2(optionBusqueda);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vCia.tablaCia){
            int fila = vCia.tablaCia.getSelectedRow();
            int codCia = Integer.parseInt(vCia.tablaCia.getValueAt(fila,0).toString());
            System.out.println("Compania = "+codCia);
            Cia cia = new CiaDAO().listarId(codCia);
            vCia.desCia.setText(cia.getDesCia());
            vCia.desCorta.setText(cia.getDesCorta());
            vCia.vigente.setSelectedItem((cia.getVigente()=='1'?"Vigente":"No Vigente"));
    
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
        
        Cia cia=new Cia.Builder()
                .desCia(vCia.desCia.getText())
                .desCorta(vCia.desCorta.getText())
                .vigente("Vigente".equals(vCia.vigente.getSelectedItem().toString())?'1':'0')
                .build();
        
        if(ciaDAO.add(cia)==1){
            showMessage2("Compañia registrada correctamente");
        }else{
            showMessage1("Error al registrar compañia");
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
        int fila = vCia.tablaCia.getSelectedRow();
        if(fila!=-1){
            System.out.println("Hay filas seleccionadas.");
            int codCia = Integer.parseInt(vCia.tablaCia.getValueAt(fila,0).toString());            
            Cia cia=new Cia.Builder()
                .desCia(vCia.desCia.getText())
                .desCorta(vCia.desCorta.getText())
                .vigente("Vigente".equals(vCia.vigente.getSelectedItem().toString())?'1':'0')
                .codCia(codCia)
                .build();
            
            new CiaDAO().actualizar(cia);
        }
        actualizarTabla();
    }
    
    public void eliminarDatos(){
        int fila = vCia.tablaCia.getSelectedRow();
        System.out.println("La fila es"+fila);
        if(fila!=-1){//Si hay filas seleccionadas (cuando no se selecciona ninguna se retorna -1, por ello la comparacion), entonces:
            System.out.println("Hay filas seleccionadas.");
            int codCia = Integer.parseInt(vCia.tablaCia.getValueAt(fila,0).toString());
            new CiaDAO().eliminar(codCia);
        }
        actualizarTabla();
    }
}
