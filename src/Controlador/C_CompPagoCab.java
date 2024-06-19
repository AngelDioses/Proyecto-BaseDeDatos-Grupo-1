package Controlador;

import Modelo.Comp_PagoCab;
import Modelo.Comp_PagoDet;
import Modelo.DAO.CompPagoCabDAO;
import Modelo.DAO.CompPagoDetDAO;
import Modelo.DAO.DProyPartidaMezclaDAO;
import Modelo.DAO.ElementosDAO;
import Modelo.DAO.PartidaDAO;
import Modelo.DAO.ProveedorDAO;
import Modelo.DAO.Proy_Partida_MezclaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.DProyPartidaMezcla;
import Modelo.Elementos;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida;
import Modelo.Proveedor;
import Modelo.Proyecto;
import Vistas.V_Compra;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_TablaC_TablaDetalleC;
import java.awt.Frame;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

public class C_CompPagoCab implements ActionListener,MouseListener{

    Comp_PagoCab compPagoCab = new Comp_PagoCab();
    V_Compra vCompra = new V_Compra();
    DefaultTableModel modelCompra = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int fila=-1;
    int secuencia = 0;
    
    public C_CompPagoCab(V_Compra vCompra){
        this.vCompra=vCompra;
        this.vCompra.codPartida.addActionListener(this);
        this.vCompra.codPyto.addActionListener(this);
        this.vCompra.codProveedor.addActionListener(this);
        this.vCompra.btnBorrar.addActionListener(this);
        this.vCompra.btnGenerar.addActionListener(this);
        this.vCompra.btnLimpiar.addActionListener(this);
        this.vCompra.btnAgregar.addActionListener(this);
        this.vCompra.tablaCompra.addMouseListener(this);
        this.vCompra.btnTablaC.addActionListener(this);
        this.vCompra.btnTablaDetalleC.addActionListener(this);
        init();
    }
    
    public void init(){
        vCompra.init();
        vCompra.codPartida.setEnabled(false);
        initListarProyectos();
        initListarProveedor();
        initListarMoneda();
        initListarCompPago();
        //initSec();
        vCompra.impMonto.setText("0.00");
        vCompra.impNetoMN.setText("0.00");
        vCompra.impIGVMN.setText("0.00");
        vCompra.impTotalMN.setText("0.00");
        int aux=secuencia;
        aux++;
        ((AbstractDocument)vCompra.nroPago.getDocument()).setDocumentFilter(new LimitDocumentFilter(3,1));
        ((AbstractDocument)vCompra.nroCP.getDocument()).setDocumentFilter(new LimitDocumentFilter(20,0));
        ((AbstractDocument)vCompra.desAbono.getDocument()).setDocumentFilter(new LimitDocumentFilter(1000,0));
        ((AbstractDocument)vCompra.impMonto.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vCompra.tipCambio.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vCompra.impNetoMN.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vCompra.impIGVMN.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vCompra.impTotalMN.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
        ((AbstractDocument)vCompra.semilla.getDocument()).setDocumentFilter(new LimitDocumentFilter(5,1));
    }
    
    public void initListarMoneda(){
        List<Elementos> lista=new ElementosDAO().listarTabs(2);//2 es el idTabs de Moneda
        for (int i = 0; i < lista.size(); i++) {
            vCompra.eMoneda.addItem(lista.get(i).getDenElemento());
        }
    }
    
    public void initListarCompPago(){
        List<Elementos> lista=new ElementosDAO().listarTabs(4);
        for (int i = 0; i < lista.size(); i++) {
            vCompra.eCompPago.addItem(lista.get(i).getDenElemento());
        }
    }
    
    public void initListarProveedor(){
        vCompra.codProveedor.removeAllItems();
        List<Proveedor> lista = new ProveedorDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vCompra.codProveedor.addItem(lista.get(i).getCodPersona());
        }
        vCompra.codProveedor.setSelectedIndex(-1);
    }
    
    public void initDatoProveedor(){
        if(vCompra.codProveedor.getSelectedIndex()!=-1){
            int num=Integer.parseInt(vCompra.codProveedor.getSelectedItem().toString());
            Proveedor nomProveedor=new ProveedorDAO().listarId(num);
            System.out.println("NUMERO: "+num);
            vCompra.txtDesProveedor.setText(nomProveedor.getDesPersona());
        } else{
            vCompra.txtDesProveedor.setText("");
        }
    }
    
    public void initListarProyectos(){
        vCompra.codPyto.removeAllItems();
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vCompra.codPyto.addItem(lista.get(i).getCodPyto());
        }
        vCompra.codPyto.setSelectedIndex(-1);
    }
    
    public void initDatoPyto(){
        if(vCompra.codPyto.getSelectedIndex()!=-1){
            int num=Integer.parseInt(vCompra.codPyto.getSelectedItem().toString());
            Proyecto nomPyto=new ProyectoDAO().listarId(num);
            vCompra.desPyto.setText(nomPyto.getNomPyto());
        }else{
            vCompra.desPyto.setText("");
        }
    }
    
    public void initListarPartida(int codPyto){
        vCompra.codPartida.removeAllItems();
        vCompra.codPartida.setEnabled(true);
        List<DProyPartidaMezcla> lista = new DProyPartidaMezclaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"E",codPyto);
        for(int i=0;i<lista.size();i++){
            vCompra.codPartida.addItem(lista.get(i).getCodPartida());
        }
        vCompra.codPartida.setSelectedIndex(-1);
    }
    
    public void initDatoPartida(){
        String pbm=vCompra.codPartida.getSelectedItem().toString();
        Partida list=new PartidaDAO().listarId(varCodCiaGlobalDeLogin,Integer.parseInt(pbm),"E");
        vCompra.desPartida.setText(list.getDesPartida());
        vCompra.semilla.setText(String.valueOf(new DProyPartidaMezclaDAO().buscarSemilla(varCodCiaGlobalDeLogin, Integer.parseInt(vCompra.codPartida.getSelectedItem().toString()), "E")));
        float precioDePartida = new Proy_Partida_MezclaDAO().buscarTotalCodPartCodProy(vCompra.codPyto.getSelectedItem().toString(), vCompra.codPartida.getSelectedItem().toString());
        vCompra.impNetoMNPart.setText(String.valueOf(new Proy_Partida_MezclaDAO().buscarTotalCodPartCodProy(vCompra.codPyto.getSelectedItem().toString(), vCompra.codPartida.getSelectedItem().toString())));
        vCompra.impIgvMNPart.setText(String.valueOf(new Proy_Partida_MezclaDAO().buscarTotalCodPartCodProy(vCompra.codPyto.getSelectedItem().toString(), vCompra.codPartida.getSelectedItem().toString())*0.18));
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.vCompra.codPyto){
            if(vCompra.codPyto.getSelectedIndex()!=-1){
                int idProyecto=Integer.parseInt(vCompra.codPyto.getSelectedItem().toString());
                initDatoPyto();
                initListarPartida(idProyecto);
            }else{
                System.out.println("Seleccione un item");
                vCompra.desPyto.setText("");
            }
                
        }
        if(e.getSource()==this.vCompra.codPartida){
            if(vCompra.codPartida.getSelectedIndex()!=-1){
                initDatoPartida();
            }else{
                System.out.println("DATOS");
                vCompra.desPartida.setText("");
                vCompra.semilla.setText("");
                vCompra.impNetoMNPart.setText("");
                vCompra.impIgvMNPart.setText("");
            }
        }
        if(e.getSource()==this.vCompra.codProveedor){
                initDatoProveedor();
        }
        if(e.getSource()==vCompra.btnAgregar){
            reiniciaHelperTextG();
            if(verificaDatosAntesDeAgregar()){
                agregarDatosTabla();
                vCompra.codProveedor.setEnabled(false);
                vCompra.codPyto.setEnabled(false);
                vCompra.semilla.setText("");
            }else{
                showMessage1(" ¡COMPLETE!" );
            }
        }
        if(e.getSource()==vCompra.btnBorrar){
            borrarFila();
        }
        if(e.getSource()==vCompra.btnGenerar){
            vCompra.txtDesProveedor.setHelperText("");
            vCompra.nroCP.setHelperText("");
            vCompra.desPyto.setHelperText("");
            vCompra.nroPago.setHelperText("");
            vCompra.tipCambio.setHelperText("");
            vCompra.impMonto.setHelperText("");
            vCompra.impTotalMN.setHelperText("");
            vCompra.semilla.setHelperText("");
            if(verificaDatosAntesDeGenerar()){
                if(showMessage("¿Desea generar la compra?")){
                    if(agregarCompraBD()){
                        agregarDetalleCompra();
                        limpiarTabla(modelCompra);
                        vCompra.codProveedor.setEnabled(true);
                        vCompra.codPyto.setEnabled(true);
                        reiniciarCampos();
                    }else{
                        showMessage1("¡OCURRIÓ UNA EXCEPCIÓN!");
                    }
                }else {
                    showMessage1(" ¡CANCELADO!" );
                }
            }else{
                showMessage1(" ¡COMPLETE!" );
            }
        }
        if(e.getSource()==vCompra.btnLimpiar){
            limpiarTabla(modelCompra);
            vCompra.codProveedor.setEnabled(true);
            vCompra.codPyto.setEnabled(true);
            secuencia=0;
            int aux=secuencia;
            aux++;
            reiniciarCampos();
            reiniciaHelperTextG();
        }
        if(e.getSource()==vCompra.btnTablaC){
            new V_TablaC_TablaDetalleC("SELECT * FROM COMP_PAGOCAB ORDER BY NROCP").setVisible(true);
        }
        if(e.getSource()==vCompra.btnTablaDetalleC){
            new V_TablaC_TablaDetalleC("SELECT * FROM COMP_PAGODET order by NROCP").setVisible(true);
        }
    }
    
    public boolean agregarCompraBD(){
        boolean action=true;
        int idCompPago=0, idMoneda=0;
        Comp_PagoCab cpc=new Comp_PagoCab();
        cpc.setCodCia(varCodCiaGlobalDeLogin);
        cpc.setCodProveedor(Integer.parseInt(vCompra.codProveedor.getSelectedItem().toString()));
        cpc.setNroCP(vCompra.nroCP.getText());
        cpc.setCodPyto(Integer.parseInt(vCompra.codPyto.getSelectedItem().toString()));
        cpc.setNroPago(Integer.parseInt(vCompra.nroPago.getText()));
        cpc.settCompPago(4+"");
        idCompPago=new ElementosDAO().listarIdxDesc(4, vCompra.eCompPago.getSelectedItem().toString());
        cpc.seteCompPago(idCompPago+"");
        System.out.println("VIENDO: "+idCompPago);
        Date fecn = null;
        Date fecc = null;
        try {
            fecn = new SimpleDateFormat("dd/MM/yyyy").parse(vCompra.fecCP.getText());
            fecc = new SimpleDateFormat("dd/MM/yyyy").parse(vCompra.fecAbono.getText());
        } catch (ParseException ex) {
            Logger.getLogger(V_Compra.class.getName()).log(Level.SEVERE, null, ex);
        }
        long time1 = fecn.getTime();
        long time2 = fecc.getTime();
        java.sql.Date fechaCP = new java.sql.Date(time1);
        java.sql.Date fechaAbono = new java.sql.Date(time2);
        cpc.setFecCP(fechaCP);
        cpc.settMoneda(2+"");
        idMoneda=new ElementosDAO().listarIdxDesc(2, vCompra.eMoneda.getSelectedItem().toString());
        cpc.seteMoneda(idMoneda+"");
        cpc.setTipCambio(Double.parseDouble(vCompra.tipCambio.getText()));
        cpc.setImpMO(Double.parseDouble(vCompra.impMonto.getText()));
        cpc.setImpNetoMN(Double.parseDouble(vCompra.impNetoMN.getText()));
        cpc.setImpIGVMN(Double.parseDouble(vCompra.impIGVMN.getText()));
        cpc.setImpTotalMN(Double.parseDouble(vCompra.impTotalMN.getText()));
        cpc.setFotoCP("RUTA FOTO_CP");
        cpc.setFotoAbono("RUTA FOTO_ABONO");
        cpc.setFecAbono(fechaAbono);
        cpc.setDesAbono(vCompra.desAbono.getText());
        cpc.setSemilla(999);
        cpc.setTabEstado("4");//falta agregar
        cpc.setCodEstado(vCompra.codEstado.getSelectedIndex()+"");//falta definir
        if(new CompPagoCabDAO().add(cpc)==1){
            showMessage2("Compra registrado correctamente");
            fila=-1;
        }else{
            showMessage1("Error al registrar el Detalle");
            action=false;
        }
        return action;
    }
    
    public void agregarDetalleCompra(){
        int a=0;
        for (int i = 0; i < vCompra.tablaCompra.getRowCount(); i++) {
            Comp_PagoDet cpd=new Comp_PagoDet();
            cpd.setCodCia(varCodCiaGlobalDeLogin);
            cpd.setCodProveedor(Integer.parseInt(vCompra.codProveedor.getSelectedItem().toString()));
            cpd.setNroCP(vCompra.nroCP.getText());
            cpd.setSec(Integer.parseInt(vCompra.tablaCompra.getValueAt(i, 0).toString()));
            cpd.setIngEgr("E");
            cpd.setCodPartida(Integer.parseInt(vCompra.tablaCompra.getValueAt(i, 1).toString()));
            cpd.setImpNetoMN(Double.parseDouble(vCompra.tablaCompra.getValueAt(i, 4).toString()));
            cpd.setImpIGVMN(Double.parseDouble(vCompra.tablaCompra.getValueAt(i, 5).toString()));
            cpd.setImpTotalMN(Double.parseDouble(vCompra.tablaCompra.getValueAt(i, 6).toString()));
            cpd.setSemilla(Integer.parseInt(vCompra.tablaCompra.getValueAt(i, 3).toString()));
            cpd.setCodPytoAuxiliar(Integer.parseInt(vCompra.codPyto.getSelectedItem().toString()));
            
            if(new CompPagoDetDAO().add(cpd)!=1){
                a++;
            }
        }
        if(a==0){
            showMessage2("CompraDetalle registrado correctamente");
        }else{
            showMessage1("Error al registrar el CompraDetalle de la fila "+a);
        }
    }
    
    public boolean verificaDatosAntesDeAgregar(){
        boolean action=true;
        if(vCompra.desPartida.getText().trim().equals("")){
            vCompra.desPartida.setHelperText("Ingrese el codigo");
            action=false;
        }
        if(vCompra.semilla.getText().trim().equals("")){
            vCompra.semilla.setHelperText("Ingrese la semilla");
            action=false;
        }
        if(vCompra.impIgvMNPart.getText().trim().equals("")){
            vCompra.impIgvMNPart.setHelperText("Ingrese un ImpNeto");
            action=false;
        }
        if(vCompra.impNetoMNPart.getText().trim().equals("")){
            vCompra.impNetoMNPart.setHelperText("Ingrese un Monto");
            action=false;
        }
        if(!vCompra.eMoneda.getSelectedItem().equals("Soles") && vCompra.tipCambio.getText().trim().equals("")){
            vCompra.tipCambio.setHelperText("Complete");
            action=false;
        } 
        return action;
    }
    
    public void reiniciaHelperTextG(){
        vCompra.txtDesProveedor.setHelperText("");
        vCompra.nroCP.setHelperText("");
        vCompra.desPyto.setHelperText("");
        vCompra.nroPago.setHelperText("");
        vCompra.tipCambio.setHelperText("");
        vCompra.impMonto.setHelperText("");
        vCompra.impTotalMN.setHelperText("");
        vCompra.semilla.setHelperText("");
    }
    
    public boolean verificaDatosAntesDeGenerar(){
        boolean action=true;
        if(vCompra.txtDesProveedor.getText().trim().equals("")){
            vCompra.txtDesProveedor.setHelperText("Ingrese el codigo");
            action=false;
        }
        if(vCompra.nroCP.getText().trim().equals("")){
            vCompra.nroCP.setHelperText("Ingrese el nroCP");
            action=false;
        }
        if(vCompra.desPyto.getText().trim().equals("")){
            vCompra.desPyto.setHelperText("Ingrese el codigo");
            action=false;
        }
        if(vCompra.nroPago.getText().trim().equals("")){
            vCompra.nroPago.setHelperText("Ingrese numPago");
            action=false;
        }
        if(vCompra.tipCambio.getText().trim().equals("")){
            vCompra.tipCambio.setHelperText("Complete");
            action=false;
        }
        if(vCompra.impMonto.getText().trim().equals("0.00")){
            vCompra.impMonto.setHelperText("Complete");
            action=false;
        }
        if(vCompra.impTotalMN.getText().trim().equals("0.00")){
            vCompra.impTotalMN.setHelperText("Compre algo");
            action=false;
        }
        if(vCompra.desAbono.getText().trim().equals("")){
            action=false;
        }
        return action;
    }
    
    public void reiniciarCampos(){
        vCompra.nroCP.setText("");
        vCompra.nroPago.setText("");
        vCompra.desAbono.setText("");
        vCompra.impMonto.setText("0.00");
        vCompra.impNetoMN.setText("0.00");
        vCompra.impIGVMN.setText("0.00");
        vCompra.impTotalMN.setText("0.00");
        vCompra.semilla.setText("");
        vCompra.tipCambio.setText("");
        //vCompra.sec.setText("");
        //initSec();
    }
    
    public void agregarDatosTabla(){
        vCompra.codProveedor.setEnabled(false);
        vCompra.codPyto.setEnabled(false);
        secuencia++;
        DecimalFormat df=new DecimalFormat("0.00");
        String codProy=vCompra.codPyto.getSelectedItem().toString();
        String codPart=vCompra.codPartida.getSelectedItem().toString();
        double igv=0, subtotal=0;
        
        subtotal=Double.parseDouble(vCompra.impNetoMNPart.getText());
        igv=Double.parseDouble(vCompra.impIgvMNPart.getText());
        
        String tot=df.format(subtotal+igv);        
        String imp=df.format(igv);
        String neto=df.format(subtotal);
        
        modelCompra=(DefaultTableModel)vCompra.tablaCompra.getModel();
        Object[] o=new Object[7];
        o[0]=secuencia;
        o[1]=codPart;
        o[2]=vCompra.desPartida.getText();
        o[3]=vCompra.semilla.getText();
        o[4]=neto;
        o[5]=imp;
        o[6]=tot;
        modelCompra.addRow(o);
        calcularTotal();
        calcularMonto_TipoDeCambio();
    }
    
    public void calcularTotal(){
        DecimalFormat df=new DecimalFormat("0.00");
        double impuestoTotal=0,impuestoIndividual=0,subTotal=0;
        double totalPagar=0,total=0;
        for(int i=0;i<vCompra.tablaCompra.getRowCount();i++){
            total=Double.parseDouble(vCompra.tablaCompra.getValueAt(i, 6).toString());
            totalPagar=totalPagar+total;
            
            impuestoIndividual=Double.parseDouble(vCompra.tablaCompra.getValueAt(i, 5).toString());
            impuestoTotal=impuestoTotal+impuestoIndividual;
        }
        
        String tot=df.format(totalPagar);
        vCompra.impTotalMN.setText(tot);
        
        String imp=df.format(impuestoTotal);
        vCompra.impIGVMN.setText(imp);
        
        subTotal=totalPagar-impuestoTotal;
        String sub=df.format(subTotal);
        
        vCompra.impNetoMN.setText(sub+"");
    }
    
    public void calcularMonto_TipoDeCambio(){
        DecimalFormat df=new DecimalFormat("0.00");
        if(vCompra.eMoneda.getSelectedItem().equals("Soles")){
            vCompra.tipCambio.setText("1");
            vCompra.impMonto.setText(vCompra.impTotalMN.getText().toString());
        } else{
            double tipCambio = Double.parseDouble(vCompra.tipCambio.getText().toString());
            double impTotal = Double.parseDouble(vCompra.impTotalMN.getText().toString());
            vCompra.impMonto.setText(df.format(impTotal/tipCambio));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vCompra.tablaCompra){
            fila=vCompra.tablaCompra.getSelectedRow();
        }
    }
    
    public void borrarFila(){
        if(fila!=-1){
            String codpar = vCompra.tablaCompra.getValueAt(fila,1).toString();
            System.out.println("Borrar");
            if (showMessage(" ¿Desea borrar la partida "+codpar+" de la tabla?" )) {
                modelCompra.removeRow(fila);
                int actualizadorDeSecuencia = 1;
                for(int i = 0; i < modelCompra.getRowCount();i++){
                    vCompra.tablaCompra.setValueAt(actualizadorDeSecuencia, i, 0);
                    actualizadorDeSecuencia++;
                }
                secuencia=actualizadorDeSecuencia-1;
                int aux=secuencia;
                aux++;
                showMessage2("¡REALIZADO CON ÉXITO!" );
            } else {
                System.out.println("HOLA!");
            }
        }else{
            showMessage1(" ¡SELECCIONE UN FILA EN LA TABLA!" );
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
