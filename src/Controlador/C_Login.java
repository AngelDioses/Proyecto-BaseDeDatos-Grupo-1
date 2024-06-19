package Controlador;

import Modelo.Cia;
import Modelo.DAO.CiaDAO;
import Modelo.DAO.EmpleadoDAO;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Vistas.V_Login;
import Vistas.V_Principal;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class C_Login implements ActionListener, MouseListener, MouseMotionListener{

    V_Login vLog=new V_Login();
    CiaDAO ciaDAO=new CiaDAO();
    EmpleadoDAO emplDAO = new EmpleadoDAO();
    int xMouse, yMouse;
    //LoginDAO loginDAO=new LoginDAO();
    
    public C_Login(V_Login vl){
        this.vLog=vl;
        this.vLog.loginF.login.addEventLogin(this);
        this.vLog.addMouseListener(this);
        this.vLog.addMouseMotionListener(this);
        this.vLog.init();
        initListarNombresCias();
    }
    
    public void initListarNombresCias(){
        vLog.loginF.login.comboboxCia.removeAllItems();
        List<Cia> lista = new CiaDAO().listar();
        for(int i=0;i<lista.size();i++){
            vLog.loginF.login.comboboxCia.addItem(lista.get(i).getDesCia());
        }
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("LOG1");
        if(e.getSource()==vLog.loginF.login.btIngresar){
            checkUser();
            initListarNombresCias();
            System.out.println("LOG2");
        }
    }
    
    public void checkUser(){
        System.out.println("LOG3");
        if (vLog.loginF.login.checkUser()) {  //check si relleno el usr y el pass
            login(vLog.loginF.login.getUserName(),vLog.loginF.login.getPassword());
        }
    }
    
    public void login(String username, String password){
        if(vLog.loginF.login.comboboxCia.getSelectedItem()!=null){ //Probar cuando no hay CIAS en la BDD.
            int codCia = ciaDAO.getCodCiaWithDesCia(vLog.loginF.login.comboboxCia.getSelectedItem().toString());
            if(username.equals(password)){
                //Verificar que el empleado seleccionado sea de la empresa
                System.out.println("Pertenece = "+emplDAO.getPerteneceACia(codCia, Integer.parseInt(username)));
                if(emplDAO.getPerteneceACia(codCia, Integer.parseInt(username))){
                    vLog.varCodCiaGlobalDeLogin = codCia;
                    vLog.varNombreCiaGlobalDeLogin = new CiaDAO().getCiaNombre(codCia);
                    System.out.println("SIGUIENTE");
                    showMessage2("¡USUARIO IDENTIFICADO!");
                    V_Principal vPrincipal=new V_Principal();
                    vPrincipal.setVisible(true);
                    this.vLog.dispose();
                }else{
                    if(username.equals("0") && password.equals("0")){
                        vLog.varCodCiaGlobalDeLogin = codCia;
                        vLog.varNombreCiaGlobalDeLogin = new CiaDAO().getCiaNombre(codCia);
                        System.out.println("SIGUIENTE - MODO ADMINISTRADOR");
                        showMessage2("¡MODO ADMINISTRADOR!");
                        V_Principal vPrincipal=new V_Principal();
                        vPrincipal.setVisible(true);
                        this.vLog.dispose();
                    }else{
                        System.out.println("EL EMPLEADO NO PERTENECE A LA CIA SELECCIONADA");
                        showMessage1("EL EMPLEADO NO PERTENECE A LA CIA SELECCIONADA");
                        vLog.loginF.login.checkUserReset();
                    }
                }
            }else{
                System.out.println("¡USUARIO O PASSWORD INCORRECTA!");
                showMessage1("¡USUARIO O PASSWORD INCORRECTA!");
                vLog.loginF.login.checkUserReset();
            }
        }else{
            System.out.println("¡NECESITA SELECCIONAR UNA CIA!");
            showMessage1("¡NECESITA SELECCIONAR UNA CIA!");
            vLog.loginF.login.checkUserReset();
        }
    }
    
    private boolean showMessage1(String message){
        Mensaje1 obj = new Mensaje1(Frame.getFrames()[0],true);
        obj.showMessage(message);
        return obj.isAceptar();
    }
    
    private boolean showMessage2(String message) {
        Mensaje2 obj = new Mensaje2(Frame.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isAceptar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==vLog){
            xMouse = e.getX();
            yMouse = e.getY();
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getSource()==vLog){
            int x,y;
            x = e.getXOnScreen();
            y = e.getYOnScreen();
            this.vLog.setLocation(x-xMouse,y-yMouse);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        return;
    }
    
}
