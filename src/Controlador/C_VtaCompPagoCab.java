package Controlador;

import Modelo.Cliente;
import Modelo.DAO.ClienteDAO;
import Modelo.DAO.DProyPartidaMezclaDAO;
import Modelo.DAO.ElementosDAO;
import Modelo.DAO.PartidaDAO;
import Modelo.DAO.Proy_Partida_MezclaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.DAO.VtaComp_PagoCabDAO;
import Modelo.DAO.VtaComp_PagoDetDAO;
import Modelo.DProyPartidaMezcla;
import Modelo.Elementos;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Proyecto;
import Modelo.VtaComp_PagoCab;
import Modelo.VtaComp_PagoDet;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_TablaC_TablaDetalleC;
import Vistas.V_Venta;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

public class C_VtaCompPagoCab implements ActionListener,MouseListener, ItemListener{
    
    V_Venta vVenta = new V_Venta();
    DefaultTableModel modelVenta = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int fila=-1;
    int secuencia = 0;

    public C_VtaCompPagoCab(V_Venta vVenta){
        this.vVenta=vVenta;
        init();
        this.vVenta.codCliente.addItemListener(this);
        this.vVenta.codPyto.addItemListener(this);
        this.vVenta.codPartida.addItemListener(this);
        this.vVenta.btt_Agregar.addActionListener(this);
        this.vVenta.btt_Limpiar.addActionListener(this);
        this.vVenta.btt_Borrar.addActionListener(this);
        this.vVenta.btt_Generar.addActionListener(this);
        this.vVenta.tablaVenta.addMouseListener(this);
        this.vVenta.btnTablaV.addActionListener(this);
        this.vVenta.btnTablaDetalleV.addActionListener(this);
   }
    
    public void init(){
        initTablaVenta();
        initListarClientes();
        vVenta.init();
        initListarMoneda();
        initListarCompPago();
        vVenta.codEstado.setSelectedIndex(0);
        vVenta.impMonto.setText("0.00");
        vVenta.impNetoMN.setText("0.00");
        vVenta.impIGVMN.setText("0.00");
        vVenta.impTotalMN.setText("0.00");
        vVenta.desPersona.setEditable(false);
        vVenta.nomPyto.setEditable(false);
        vVenta.desPartida.setEditable(false);
        ((AbstractDocument)vVenta.desAbono.getDocument()).setDocumentFilter(new LimitDocumentFilter(1000,0));
        ((AbstractDocument)vVenta.impMonto.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vVenta.tipCambio.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vVenta.impNetoMN.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vVenta.impIGVMN.getDocument()).setDocumentFilter(new LimitDocumentFilter(9,0));
        ((AbstractDocument)vVenta.impTotalMN.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
        ((AbstractDocument)vVenta.semilla.getDocument()).setDocumentFilter(new LimitDocumentFilter(5,1));
    }
    
    public void initTablaVenta(){
        modelVenta = (DefaultTableModel)vVenta.tablaVenta.getModel();
        limpiarTabla(modelVenta);
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    
    public void initListarMoneda(){
        vVenta.eMoneda.removeAllItems();
        List<Elementos> lista=new ElementosDAO().listarTabs(2);//2 es el idTabs de Moneda
        for (int i = 0; i < lista.size(); i++) {
            vVenta.eMoneda.addItem(lista.get(i).getDenElemento());
        }
    }
    
    public void initListarCompPago(){
        vVenta.eCompPago.removeAllItems();
        List<Elementos> lista=new ElementosDAO().listarTabs(4); //4 es el idTabs de Comprobantes de Venta
        for (int i = 0; i < lista.size(); i++) {
            vVenta.eCompPago.addItem(lista.get(i).getDenElemento());
        }
    }
    
    public void initListarClientes(){
        vVenta.codCliente.removeAllItems();
        List<Cliente> lista = new ClienteDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vVenta.codCliente.addItem(lista.get(i).getCodPersona());
        }
        vVenta.codCliente.setSelectedIndex(-1);
    }
    
    public void initListarProyectos(int codCliente){
        vVenta.codPyto.removeAllItems();
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCliente(codCliente);
        for(int i=0;i<lista.size();i++){
            vVenta.codPyto.addItem(lista.get(i).getCodPyto());
        }
        
        //Bug fix
        if(lista.size()==0){
            vVenta.codPartida.removeAllItems();
        }
        
        vVenta.codPyto.setSelectedIndex(-1);
    }
    
    public void initListarPartidas(int codPyto){
        vVenta.codPartida.removeAllItems();
        List<DProyPartidaMezcla> lista = new DProyPartidaMezclaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"I",codPyto);
        for(int i=0;i<lista.size();i++){
            vVenta.codPartida.addItem(lista.get(i).getCodPartida());
        }
        vVenta.codPartida.setSelectedIndex(-1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vVenta.btt_Agregar){
            reiniciaHelperTextG();
            if(verificaDatosAntesDeAgregar()){
                agregarVentaDetalleATabla();
                //Ya se agrego dicha partida previamente, se pdoria crear un array de cod_partidas ya usadas.
            }
        }
        if(e.getSource()==vVenta.btt_Limpiar){
            initListarClientes();
            limpiarTabla(modelVenta);
            vVenta.codCliente.setEnabled(true);
            vVenta.codPyto.setEnabled(true);
            reiniciarCampos();
            reiniciaHelperTextG();
        }
        if(e.getSource()==vVenta.btt_Borrar){
            borrarFila();
        }
        if(e.getSource()==vVenta.btt_Generar){
            if(verificaDatosAntesDeGenerar()){
                if(showMessage("¿Desea generar la compra?")){
                    if(agregarCompraBD()){
                        agregarDetalleCompra();
                        limpiarTabla(modelVenta);
                        vVenta.codCliente.setEnabled(true);
                        vVenta.codPyto.setEnabled(true);
                        reiniciarCampos();
                    }else{
                        showMessage1("¡OCURRIÓ UNA EXCEPCIÓN!");
                    }
                }else {
                    showMessage1(" ¡CANCELADO!" );
                }
            }            
        }
        if(e.getSource()==vVenta.btnTablaV){
            new V_TablaC_TablaDetalleC("SELECT * FROM VTACOMP_PAGOCAB ORDER BY NROCP").setVisible(true);
        }
        if(e.getSource()==vVenta.btnTablaDetalleV){
            new V_TablaC_TablaDetalleC("SELECT * FROM VTACOMP_PAGODET order by NROCP").setVisible(true);
        }
    }
    
    public void borrarFila(){        
        if(fila!=-1){
            String codpar = vVenta.tablaVenta.getValueAt(fila,1).toString();
            System.out.println("Borrar");
            if (showMessage(" ¿Desea borrar la partida "+codpar+" de la tabla?" )) {
                modelVenta.removeRow(fila);
                int actualizadorDeSecuencia = 1;
                for(int i = 0; i < modelVenta.getRowCount();i++){
                    vVenta.tablaVenta.setValueAt(actualizadorDeSecuencia, i, 0);
                    actualizadorDeSecuencia++;
                }
                secuencia=actualizadorDeSecuencia-1;
                showMessage2("¡REALIZADO CON ÉXITO!" );
            } else {
                System.out.println("HOLA!");
            }
        }else{
            showMessage1(" ¡SELECCIONE UN FILA EN LA TABLA!" );
        }
    }
    
    public void reiniciarCampos(){
        vVenta.desAbono.setText("");
        vVenta.impMonto.setText("0.00");
        vVenta.impNetoMN.setText("0.00");
        vVenta.impIGVMN.setText("0.00");
        vVenta.impTotalMN.setText("0.00");
        vVenta.semilla.setText("");
        vVenta.tipCambio.setText("");
        vVenta.impNetoMNPart.setText("");
        vVenta.impIgvMNPart.setText("");
    }
    
    public boolean verificaDatosAntesDeAgregar(){
        reiniciaHelperTextG();
        boolean action=true;
        if(vVenta.codCliente.getSelectedIndex()==-1){
            showMessage1("Seleccione un cliente");
            action=false;
        }
        if(vVenta.codPyto.getSelectedIndex()==-1){
            showMessage1("Seleccione un un proyecto");
            action=false;
        }
        if(vVenta.codPartida.getSelectedIndex()==-1){
            showMessage1("Seleccione una partida");
            action=false;
        }
        if(vVenta.semilla.getText().trim().equals("")){
            vVenta.semilla.setHelperText("Ingrese la semilla");
            action=false;
        }
        if(vVenta.impIgvMNPart.getText().trim().equals("")){
            vVenta.impIgvMNPart.setHelperText("Ingrese un ImpNeto");
            action=false;
        }
        if(vVenta.impNetoMNPart.getText().trim().equals("")){
            vVenta.impNetoMNPart.setHelperText("Ingrese un Monto");
            action=false;
        }
        if(!vVenta.eMoneda.getSelectedItem().equals("Soles") && vVenta.tipCambio.getText().trim().equals("")){
            vVenta.tipCambio.setHelperText("Complete");
            action=false;
        } 
        return action;
    }

    public void agregarVentaDetalleATabla(){
        vVenta.codCliente.setEnabled(false);
        vVenta.codPyto.setEnabled(false);
        secuencia++;
        DecimalFormat df=new DecimalFormat("0.00");
        String codProy=vVenta.codPyto.getSelectedItem().toString();
        String codPart=vVenta.codPartida.getSelectedItem().toString();
        double igv=0, subtotal=0;
        
        subtotal=Double.parseDouble(vVenta.impNetoMNPart.getText());
        igv=Double.parseDouble(vVenta.impIgvMNPart.getText());
        
        String tot=df.format(subtotal+igv);        
        String imp=df.format(igv);
        String neto=df.format(subtotal);
        
        modelVenta=(DefaultTableModel)vVenta.tablaVenta.getModel();
        Object[] o=new Object[7];
        o[0]=secuencia;
        o[1]=codPart;
        o[2]=vVenta.desPartida.getText();
        o[3]=vVenta.semilla.getText();
        o[4]=neto;
        o[5]=imp;
        o[6]=tot;
        modelVenta.addRow(o);
        calcularTotal();
        calcularMonto_TipoDeCambio();
    }
    
    public void calcularTotal(){
        DecimalFormat df=new DecimalFormat("0.00");
        double impuestoTotal=0,impuestoIndividual=0,subTotal=0;
        double totalPagar=0,total=0;
        for(int i=0;i<vVenta.tablaVenta.getRowCount();i++){
            total=Double.parseDouble(vVenta.tablaVenta.getValueAt(i, 6).toString());
            totalPagar=totalPagar+total;
            
            impuestoIndividual=Double.parseDouble(vVenta.tablaVenta.getValueAt(i, 5).toString());
            impuestoTotal=impuestoTotal+impuestoIndividual;
        }
        
        String tot=df.format(totalPagar);
        vVenta.impTotalMN.setText(tot);
        
        String imp=df.format(impuestoTotal);
        vVenta.impIGVMN.setText(imp);
        
        subTotal=totalPagar-impuestoTotal;
        String sub=df.format(subTotal);
        
        vVenta.impNetoMN.setText(sub+"");
    }
    
    public void calcularMonto_TipoDeCambio(){
        DecimalFormat df=new DecimalFormat("0.00");
        if(vVenta.eMoneda.getSelectedItem().equals("Soles")){
            vVenta.tipCambio.setText("1");
            vVenta.impMonto.setText(vVenta.impTotalMN.getText().toString());
        } else{
            double tipCambio = Double.parseDouble(vVenta.tipCambio.getText().toString());
            double impTotal = Double.parseDouble(vVenta.impTotalMN.getText().toString());
            vVenta.impMonto.setText(df.format(impTotal/tipCambio));
        }
    }
    
    public boolean verificaDatosAntesDeGenerar(){
        reiniciaHelperTextG();
        boolean action=true;
        if(!vVenta.eMoneda.getSelectedItem().equals("Soles") && vVenta.tipCambio.getText().trim().equals("")){
            vVenta.tipCambio.setHelperText("Complete");
            action=false;
        } else{
            vVenta.tipCambio.setText("1");
        }
        if(vVenta.impMonto.getText().trim().equals("0.00")){
            vVenta.impMonto.setHelperText("Complete");
            action=false;
        }
        if(vVenta.impTotalMN.getText().trim().equals("0.00")){
            vVenta.impTotalMN.setHelperText("Compre algo");
            action=false;
        }
        if(vVenta.desAbono.getText().trim().equals("")){
            action=false;
            showMessage1("Ingrese una descripcion para el abono");
        }
        if(vVenta.tablaVenta.getRowCount()<= 0){
            showMessage1("No existe ninguna partida en los detalles");
            action=false;
        }
        if(vVenta.nroCP.getText().trim().equals("")){
            vVenta.nroCP.setHelperText("Ingrese un Nro CP");
            action=false;
        }
        if(vVenta.nroPago.getText().trim().equals("")){
            vVenta.nroCP.setHelperText("Ingrese un Nro Pago");
            action=false;
        }
        return action;
    }
    
    public boolean agregarCompraBD(){
        boolean action=true;
        
        Date fecCompra = null;
        Date fecAbono = null;
        try {
            fecCompra = new SimpleDateFormat("dd/MM/yyyy").parse(vVenta.fecCompra.getText());
            fecAbono = new SimpleDateFormat("dd/MM/yyyy").parse(vVenta.fecAbono.getText());
        } catch (ParseException ex) {
            Logger.getLogger(C_Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        VtaComp_PagoCab vtaComp_PagoCab = new VtaComp_PagoCab();
            vtaComp_PagoCab.setCodCia(varCodCiaGlobalDeLogin);
            vtaComp_PagoCab.setNroCP(vVenta.nroCP.getText());
            vtaComp_PagoCab.setCodPyto((int) vVenta.codPyto.getSelectedItem());
            vtaComp_PagoCab.setCodCliente((int) vVenta.codCliente.getSelectedItem());
            vtaComp_PagoCab.setNroPago(Integer.valueOf(vVenta.nroPago.getText()));
            vtaComp_PagoCab.settCompPago(4);
            vtaComp_PagoCab.seteCompPago(new ElementosDAO().listarIdxDesc(4, vVenta.eCompPago.getSelectedItem().toString()));
            vtaComp_PagoCab.setFecCP(new java.sql.Date(fecCompra.getTime()));
            vtaComp_PagoCab.settMoneda(2); //Elemento MONEDA = 2
            vtaComp_PagoCab.seteMoneda(new ElementosDAO().listarIdxDesc(2, vVenta.eMoneda.getSelectedItem().toString()));
            vtaComp_PagoCab.setTipCambio(Double.parseDouble(vVenta.tipCambio.getText()));
            vtaComp_PagoCab.setImpMO(Double.parseDouble(vVenta.impMonto.getText()));
            vtaComp_PagoCab.setImpNetoMN(Double.parseDouble(vVenta.impNetoMN.getText()));
            vtaComp_PagoCab.setImpIGVMN(Double.parseDouble(vVenta.impIGVMN.getText()));
            vtaComp_PagoCab.setImpTotalMN(Double.parseDouble(vVenta.impTotalMN.getText()));
            vtaComp_PagoCab.setFotoCP("RUTA_FOTO_CP");
            vtaComp_PagoCab.setFotoAbono("RUTA_FOTO_ABONO");
            vtaComp_PagoCab.setFecAbono(new java.sql.Date(fecAbono.getTime()));
            vtaComp_PagoCab.setDesAbono(vVenta.desAbono.getText());
            vtaComp_PagoCab.setSemilla(999);
            vtaComp_PagoCab.setTabEstado(String.valueOf(-1)); //falta agregar
            vtaComp_PagoCab.setCodEstado(String.valueOf(1)); //falta definir
            
        if(new VtaComp_PagoCabDAO().add(vtaComp_PagoCab)==0){
            showMessage2("VentaCabecera registrada correctamente");
            fila=-1;
        }else{
            showMessage1("Error al registrar el VentaCabecera");
            action=false;
        }
        return action;
    }
    
    public void agregarDetalleCompra(){
        for(int i = 0; i < vVenta.tablaVenta.getRowCount();i++){
            VtaComp_PagoDet vtaComp_PagoDet = new VtaComp_PagoDet();
            vtaComp_PagoDet.setCodCia(varCodCiaGlobalDeLogin);
            vtaComp_PagoDet.setNroCP(vVenta.nroCP.getText());
            vtaComp_PagoDet.setSec(Integer.parseInt(vVenta.tablaVenta.getValueAt(i,0).toString()));
            vtaComp_PagoDet.setIngEgr("I");
            vtaComp_PagoDet.setCodPartida(Integer.parseInt(vVenta.tablaVenta.getValueAt(i, 1).toString()));
            vtaComp_PagoDet.setImpNetoMN(Double.parseDouble(vVenta.tablaVenta.getValueAt(i, 4).toString()));
            vtaComp_PagoDet.setImpIGVMN(Double.parseDouble(vVenta.tablaVenta.getValueAt(i, 5).toString()));
            vtaComp_PagoDet.setImpTotalMN(Double.parseDouble(vVenta.tablaVenta.getValueAt(i, 6).toString()));
            vtaComp_PagoDet.setSemilla(Integer.parseInt(vVenta.tablaVenta.getValueAt(i, 3).toString()));
            vtaComp_PagoDet.setCodPytoAuxiliar(Integer.parseInt(vVenta.codPyto.getSelectedItem().toString()));
            
            if(new VtaComp_PagoDetDAO().add(vtaComp_PagoDet)==0){
                showMessage2("VentaDetalle Nro. "+i+" registrado correctamente.");
            }else{
                showMessage1("Error al registrar el VentaDetalle Nro. "+i+".");
            }
        }
    }
    
    public void reiniciaHelperTextG(){
        vVenta.tipCambio.setHelperText("");
        vVenta.impMonto.setHelperText("");
        vVenta.impTotalMN.setHelperText("");
        vVenta.semilla.setHelperText("");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vVenta.tablaVenta){
            fila=vVenta.tablaVenta.getSelectedRow();
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

    //Cuando hay CAMBIOS en los COMBOBOX, a diferencia de un ActionPerformed, un itemStateChanged es mas optimo.
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==vVenta.codCliente){
            if(vVenta.codCliente.getSelectedIndex()!=-1){
                vVenta.desPersona.setText(new ClienteDAO().listarId((int) vVenta.codCliente.getSelectedItem()).getDesPersona());
                initListarProyectos((int) vVenta.codCliente.getSelectedItem());
            } else{
                vVenta.desPersona.setText("");
            }
        }
        if(e.getSource()==vVenta.codPyto){
            if(vVenta.codPyto.getSelectedIndex()!=-1){
                vVenta.nomPyto.setText(new ProyectoDAO().listarId((int) vVenta.codPyto.getSelectedItem()).getNomPyto());
                initListarPartidas((int) vVenta.codPyto.getSelectedItem());
            } else{
                vVenta.nomPyto.setText("");
            }
        }
        if(e.getSource()==vVenta.codPartida){
            if(vVenta.codPartida.getSelectedIndex()!=-1){
                vVenta.desPartida.setText(new PartidaDAO().listarId(varCodCiaGlobalDeLogin, Integer.parseInt(vVenta.codPartida.getSelectedItem().toString()),"I").getDesPartida());
                vVenta.semilla.setText(String.valueOf(new DProyPartidaMezclaDAO().buscarSemilla(varCodCiaGlobalDeLogin, Integer.parseInt(vVenta.codPartida.getSelectedItem().toString()), "I")));
                float precioDePartida = new Proy_Partida_MezclaDAO().buscarTotalCodPartCodProy(vVenta.codPyto.getSelectedItem().toString(), vVenta.codPartida.getSelectedItem().toString());
                vVenta.impNetoMNPart.setText(String.valueOf(new Proy_Partida_MezclaDAO().buscarTotalCodPartCodProy(vVenta.codPyto.getSelectedItem().toString(), vVenta.codPartida.getSelectedItem().toString())));
                vVenta.impIgvMNPart.setText(String.valueOf(new Proy_Partida_MezclaDAO().buscarTotalCodPartCodProy(vVenta.codPyto.getSelectedItem().toString(), vVenta.codPartida.getSelectedItem().toString())*0.18));
            } else{
                vVenta.desPartida.setText("");
                vVenta.semilla.setText("");
                vVenta.impNetoMNPart.setText("");
                vVenta.impIgvMNPart.setText("");
            }
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
}
