package Controlador;

import Modelo.Empleado;
import Modelo.DAO.EmpleadoDAO;
import Modelo.Message.Mensaje;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.jnafilechooser.api.JnaFileChooser;
import Vistas.V_Empleado;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

public class C_Empleado implements ActionListener, KeyListener, MouseListener{

    EmpleadoDAO empDAO=new EmpleadoDAO();
    V_Empleado vEmp=new V_Empleado();
    DefaultTableModel modelEmpleado = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorter;
    int id=-1;
    int fila=-1;
    int optionBusqueda=0;
    boolean verificaImagen=false; //false=no hay imagen    true=hay imagen
    
    public C_Empleado(V_Empleado ve){
        this.vEmp=ve;
        this.vEmp.btt_Actualizar.addActionListener(this);
        this.vEmp.btt_Eliminar.addActionListener(this);
        this.vEmp.btt_Registrar.addActionListener(this);
        this.vEmp.buttonCircle1.addActionListener(this);
        this.vEmp.tablaEmpleado.addMouseListener(this);
        this.vEmp.actualizaTabla.addActionListener(this);
        this.vEmp.nuevo.addActionListener(this);
        this.vEmp.txtBusqueda.addKeyListener(this);
        init();
    }
    public void initListarCompanias(){
        vEmp.codCia.setText(""+varCodCiaGlobalDeLogin);
    }
    
    public void init(){
        initTablaEmpleado();
        initListarCompanias();
        vEmp.init();
        ((AbstractDocument)vEmp.direcc.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vEmp.dni.getDocument()).setDocumentFilter(new LimitDocumentFilter(20,0));
        ((AbstractDocument)vEmp.nroCIP.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,1));
        ((AbstractDocument)vEmp.celular.getDocument()).setDocumentFilter(new LimitDocumentFilter(33,0));
        ((AbstractDocument)vEmp.codCargo.getDocument()).setDocumentFilter(new LimitDocumentFilter(4,0));
        ((AbstractDocument)vEmp.email.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vEmp.hobby.getDocument()).setDocumentFilter(new LimitDocumentFilter(2000,0));
        ((AbstractDocument)vEmp.desPersona.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vEmp.desCorta.getDocument()).setDocumentFilter(new LimitDocumentFilter(30,0));
        ((AbstractDocument)vEmp.desAlterna.getDocument()).setDocumentFilter(new LimitDocumentFilter(100,0));
        ((AbstractDocument)vEmp.desCortaAlt.getDocument()).setDocumentFilter(new LimitDocumentFilter(10,0));
        ((AbstractDocument)vEmp.observac.getDocument()).setDocumentFilter(new LimitDocumentFilter(2000,0));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION");
        if(e.getSource()==vEmp.btt_Registrar){
            registrarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vEmp.btt_Actualizar){
            actualizarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vEmp.btt_Eliminar){
            eliminarDatos();
            actualizarTabla();
        }
        if(e.getSource()==vEmp.buttonCircle1){
            JnaFileChooser ch = new JnaFileChooser();
            boolean save=ch.showOpenDialog(Frame.getFrames()[1]);
            if (save) {
                File file = ch.getSelectedFile();
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                String ruta1 = ch.getSelectedFile().getAbsolutePath();
                vEmp.txtRuta.setText(ruta1);
                System.out.println(ruta1);
                System.out.println(vEmp.txtRuta.getText());
                vEmp.pictureBox2.setImage(icon);
                vEmp.repaint();
            }       
        }
        if(e.getSource()==vEmp.actualizaTabla){
            actualizarTabla();
            fila=-1;
        }
        if(e.getSource()==vEmp.nuevo){
            vaciarCampos();
            actualizarTabla();
            fila=-1;
        }
    }
    
    public void filtrar1(){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vEmp.txtBusqueda.getText().trim()));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void filtrar2(int num){
        try{
            sorter.setRowFilter(RowFilter.regexFilter(vEmp.txtBusqueda.getText().trim(),num));
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelEmpleado);
        initTablaEmpleado();
        System.out.println("Refrescando tabla automaticamente.");
        fila=-1;
    }
    
    public void initTablaEmpleado(){
        List<Empleado> lista = new EmpleadoDAO().listar();
        modelEmpleado = (DefaultTableModel)vEmp.tablaEmpleado.getModel();
        Object[] o=new Object[8];
        sorter = new TableRowSorter<> (modelEmpleado);
        vEmp.tablaEmpleado.setRowSorter(sorter);
        for(int i=0;i<lista.size();i++){
            o[0]=lista.get(i).getCodPersona();
            o[1]=lista.get(i).getDesPersona();
            o[2]=lista.get(i).getDni();
            o[3]=lista.get(i).getNroCIP();
            o[4]=lista.get(i).getFecNac();
            o[5]=(lista.get(i).getLicCond())=='1'?"Si":"No";
            o[6]=lista.get(i).getCelular();
            o[7]=(lista.get(i).getVigente())=='1'?"Si":"No";
            modelEmpleado.addRow(o);
        }
        vEmp.tablaEmpleado.setModel(modelEmpleado);
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
        optionBusqueda=vEmp.opt;
        if(e.getComponent()==vEmp.txtBusqueda){
            switch(optionBusqueda){
                case 0 -> filtrar1();
                case 1 -> filtrar2(optionBusqueda);
                case 2 -> filtrar2(optionBusqueda);
                case 3 -> filtrar2(optionBusqueda);
                case 4 -> filtrar2(6);
            }
            
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vEmp.tablaEmpleado){
            recuperarDatosTabla();
        }
    }

    @Override// esta funcion ya no va
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
    
    public void recuperarDatosTabla(){
        fila = vEmp.tablaEmpleado.getSelectedRow();
        System.out.println("FILA "+fila);
        int empleadoID = Integer.parseInt(vEmp.tablaEmpleado.getValueAt(fila,0).toString());
        id=empleadoID;
        //System.out.println("empleado id = "+empleadoID);
        Empleado emp = new EmpleadoDAO().listarId(empleadoID);
        // vEmp.codPersona.setText(String.valueOf(emp.getCodPersona())); -- Ya no es necesario porque el código de persona ya no se rellena
        vEmp.direcc.setText(emp.getDirecc());
        vEmp.celular.setText(emp.getCelular());
        vEmp.hobby.setText(emp.getHobby());
        visualizarImagen();
        vEmp.fecNac.setText(emp.getFecNac().getDate()+"/"+(emp.getFecNac().getMonth()+1)+"/"+(emp.getFecNac().getYear()+1900));
        vEmp.dni.setText(emp.getDni());
        vEmp.nroCIP.setText(emp.getNroCIP());
        vEmp.fecCIPVig.setText(emp.getFecCIPVig().getDate()+"/"+(emp.getFecCIPVig().getMonth()+1)+"/"+(emp.getFecCIPVig().getYear()+1900));
        vEmp.licCond.setSelectedItem((emp.getLicCond()=='1'?"Sí":"No"));
        //vEmp.flgEmplIEA.setSelectedIndex(Integer.parseInt(Character.toString(emp.getFlgEmplIEA()))-1);
        if((emp.getFlgEmplIEA()+"")!=null)
            switch (emp.getFlgEmplIEA()+"") {
            case "1" -> vEmp.flgEmplIEA.setSelectedIndex(0);//eml.setFlgEmplIEA('1');
            case "2" -> vEmp.flgEmplIEA.setSelectedIndex(1);
            case "3" -> vEmp.flgEmplIEA.setSelectedIndex(2);
        }
        vEmp.codCargo.setText(emp.getCodCargo()+"");
        vEmp.email.setText(emp.getEmail());
        vEmp.desPersona.setText(emp.getDesPersona());
        vEmp.desAlterna.setText(emp.getDesAlterna());
        vEmp.desCorta.setText(emp.getDescorta());
        vEmp.desCortaAlt.setText(emp.getDesCortaAlt());
        vEmp.observac.setText(emp.getObservac());
        vEmp.vigente.setSelectedItem((emp.getVigente()=='1'?"Vigente":"No Vigente"));
        vEmp.codCia.setText(String.valueOf(varCodCiaGlobalDeLogin));
    }
    
    public void registrarDatos(){
        Empleado eml=new Empleado();
        //eml.setCodPersona(Integer.parseInt(vEmp.codPersona.getText())); -- Adecuar en base a procedimiento para agregar
        eml.setCodCia(varCodCiaGlobalDeLogin);
        eml.setTipPersona('1');
        eml.setDesPersona(vEmp.desPersona.getText());
        eml.setDescorta(vEmp.desCorta.getText());
        eml.setDesAlterna(vEmp.desAlterna.getText());
        eml.setDesCortaAlt(vEmp.desCortaAlt.getText());
        char vig="Vigente".equals(vEmp.vigente.getSelectedItem().toString())?'1':'0';
        char lic="Sí".equals(vEmp.licCond.getSelectedItem().toString())?'1':'0';
        eml.setVigente(vig);
        eml.setDirecc(vEmp.direcc.getText());
        eml.setCelular(vEmp.celular.getText());
        eml.setHobby(vEmp.hobby.getText());
        File ruta = new File(vEmp.txtRuta.getText()); //codigo para tomar la ruta del archivo (ubicarlo)
        try{
            byte[] icono = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(icono);
            eml.setFoto(icono);
        }catch(Exception ex){
            eml.setFoto(null);
        }
        Date fecn = null;
        Date fecc = null;
        try {
            fecn = new SimpleDateFormat("dd/MM/yyyy").parse(vEmp.fecNac.getText());
            fecc = new SimpleDateFormat("dd/MM/yyyy").parse(vEmp.fecCIPVig.getText());
        } catch (ParseException ex) {
            Logger.getLogger(V_Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        long time1 = fecn.getTime();
        long time2 = fecc.getTime();
        java.sql.Date fechaRegistro1 = new java.sql.Date(time1);
        java.sql.Date fechaRegistro2 = new java.sql.Date(time2);
        eml.setFecNac(fechaRegistro1);
        eml.setDni(vEmp.dni.getText());
        eml.setNroCIP(vEmp.nroCIP.getText());
        eml.setFecCIPVig(fechaRegistro2);
        eml.setLicCond(lic);
        System.out.println("3");
        if(null!=vEmp.flgEmplIEA.getSelectedItem().toString())
            switch (vEmp.flgEmplIEA.getSelectedItem().toString()) {
            case "Empresa Consorcio" -> eml.setFlgEmplIEA('1');
            case "Cliente" -> eml.setFlgEmplIEA('2');
            case "Ambos" -> eml.setFlgEmplIEA('3');
        }
        eml.setObservac(vEmp.observac.getText());
        eml.setCodCargo(Integer.parseInt(vEmp.codCargo.getText()));
        eml.setEmail(vEmp.email.getText());
        eml.setVigente(vig);
        if(empDAO.add(eml)==1){
            showMessage2("Empleado registrado correctamente");
            vaciarCampos();
            fila=-1;
            id=-1;
        }else{
            showMessage1("Error al registrar el Empleado");
        }
        
        
    }
    
    public void actualizarDatos(){
        if(fila==-1){
            showMessage1("Debe escoger a un empleado");
            return;
        }
       
        int valorCia = Integer.parseInt(vEmp.tablaEmpleado.getValueAt(fila,0).toString());
        
        Empleado eml=new Empleado();
        eml.setCodPersona(valorCia);
        eml.setCodCia(varCodCiaGlobalDeLogin);
        eml.setTipPersona('1');
        eml.setDesPersona(vEmp.desPersona.getText());
        eml.setDescorta(vEmp.desCorta.getText());
        eml.setDesAlterna(vEmp.desAlterna.getText());
        eml.setDesCortaAlt(vEmp.desCortaAlt.getText());
        char vig="Vigente".equals(vEmp.vigente.getSelectedItem().toString())?'1':'0';
        char lic="Sí".equals(vEmp.licCond.getSelectedItem().toString())?'1':'0';
        eml.setVigente(vig);
        eml.setDirecc(vEmp.direcc.getText());
        eml.setCelular(vEmp.celular.getText());
        eml.setHobby(vEmp.hobby.getText());
        File ruta = new File(vEmp.txtRuta.getText());  //codigo para tomar la ruta del archivo (ubicarlo)
        if(verificaImagen && !vEmp.txtRuta.getText().equals("")){
            try{
                byte[] icono = new byte[(int) ruta.length()];
                InputStream input = new FileInputStream(ruta);
                input.read(icono);
                eml.setFoto(icono);
            }catch(Exception ex){
                System.out.println("HA OCURRIDO UN ERROR");
                eml.setFoto(null);
            }
        }else{
            if(!verificaImagen && !vEmp.txtRuta.getText().equals("")){
                try{
                    byte[] icono = new byte[(int) ruta.length()];
                    InputStream input = new FileInputStream(ruta);
                    input.read(icono);
                    eml.setFoto(icono);
                }catch(Exception ex){
                    System.out.println("HA OCURRIDO UN ERROR");
                    eml.setFoto(null);
                }
            }else{
                System.out.println("EN FIN: NO SE VA A ACTUALIZAR LA IMAGEN");
            }
        }
        
        Date fecn = null;
        Date fecc = null;
        try {
            fecn = new SimpleDateFormat("dd/MM/yyyy").parse(vEmp.fecNac.getText());
            fecc = new SimpleDateFormat("dd/MM/yyyy").parse(vEmp.fecCIPVig.getText());
        } catch (ParseException ex) {
            Logger.getLogger(V_Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        long time1 = fecn.getTime();
        long time2 = fecc.getTime();
        java.sql.Date fechaRegistro1 = new java.sql.Date(time1);
        java.sql.Date fechaRegistro2 = new java.sql.Date(time2);
        eml.setFecNac(fechaRegistro1);
        eml.setDni(vEmp.dni.getText());
        eml.setNroCIP(vEmp.nroCIP.getText());
        eml.setFecCIPVig(fechaRegistro2);
        eml.setLicCond(lic);
        //eml.setFlgEmplIEA(vEmp.flgEmplIEA.getSelectedItem().toString().charAt(0));
        if(null!=vEmp.flgEmplIEA.getSelectedItem().toString())
            switch (vEmp.flgEmplIEA.getSelectedItem().toString()) {
            case "Empresa Consorcio" -> eml.setFlgEmplIEA('1');
            case "Cliente" -> eml.setFlgEmplIEA('2');
            case "Ambos" -> eml.setFlgEmplIEA('3');
        }
        eml.setObservac(vEmp.observac.getText());
        eml.setCodCargo(Integer.parseInt(vEmp.codCargo.getText()));
        eml.setEmail(vEmp.email.getText());
        eml.setVigente(vig);
        if(empDAO.actualizar(eml)==1){
            showMessage2("Empleado actualizado correctamente");
            vaciarCampos();
            fila=-1;
            id=-1;
        }else{
            showMessage1("Error al actualizar Empleado");
        }
    }
    
    public void eliminarDatos(){
        if(fila==-1){
            showMessage1("Debe escoger a un empleado");
        }else{
            empDAO.eliminar(id);
            showMessage2("Empleado "+id+" eliminado correctamente");
            fila=-1;
            id=-1;
            vaciarCampos();
        }
        
    }
    
    public void visualizarImagen(){
        Icon imgi=null;
        try{
            Empleado p=new Empleado();
            EmpleadoDAO pdao=new EmpleadoDAO();
            p=pdao.listarId(id);
            System.out.println("Prueba2");
            if(p.getFoto()==null){
                System.out.println("Prueba3");
                verificaImagen=false;
                imgi = new ImageIcon(getClass().getResource("/image/imagenNoDisponible.png"));
            }else{
                System.out.println("Prueba4");
                byte[] bi = p.getFoto();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);
                imgi = new ImageIcon(image);
                verificaImagen=true;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        vEmp.pictureBox2.setImage(imgi);
        vEmp.pictureBox2.repaint();
    }
    
    public void vaciarCampos(){
        Icon imgi=null;
        vEmp.direcc.setText("");
        vEmp.dni.setText("");
        vEmp.nroCIP.setText("");
        vEmp.celular.setText("");
        vEmp.email.setText("");
        vEmp.codCargo.setText("");
        vEmp.hobby.setText("");
        vEmp.desPersona.setText("");
        vEmp.desCorta.setText("");
        vEmp.desAlterna.setText("");
        vEmp.desCortaAlt.setText("");
        vEmp.observac.setText("");
        imgi = new ImageIcon(getClass().getResource("/image/imagenNoDisponible.png"));
        vEmp.pictureBox2.setImage(imgi);
        vEmp.pictureBox2.repaint();
        vEmp.txtRuta.setText("");
    }
}
