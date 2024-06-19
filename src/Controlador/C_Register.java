package Controlador;

import Modelo.Cia;
import Modelo.DAO.CiaDAO;
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

public class C_Register implements ActionListener{

    V_Login vLog=new V_Login();
    int id;
    CiaDAO ciaDAO=new CiaDAO();
    
    public C_Register(V_Login vl){
        this.vLog=vl;
        this.vLog.loginF.register.addEventLogin(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("LOG1");
        if(e.getSource()==vLog.loginF.register.btRegistrar){
            checkC_Register();
            System.out.println("LOG2");
        }
    }
    
    public void checkC_Register(){
        System.out.println("LOG3");
        if (vLog.loginF.register.checkRegister()) {  //campo vacio??
            Cia cia = new Cia.Builder()
                    .desCia(vLog.loginF.register.getDescCia())
                    .desCorta(vLog.loginF.register.getDescCorta())
                    .vigente('1')
                    .build();
            
            if(ciaDAO.add(cia)==1){
                showMessage2("¡COMPAÑIA REGISTRADA!");
                id=ciaDAO.maxId();
                showMessage2("SU ID ASIGNADO ES LA SIGUIENTE: "+id);
            }else{
                showMessage1("¡HA OCURRIDO UN ERROR!");
            }
            
        }else{
            showMessage1("¡CAMPO VACIO!");
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

}
