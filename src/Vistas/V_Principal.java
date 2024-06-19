package Vistas;

import Controlador.C_Cliente;
import Controlador.C_CompPagoCab;
import Controlador.C_Compania;
import Controlador.C_Configuracion;
import Controlador.C_DProy_Partida_Mezcla;
import Controlador.C_Empleado;
import Controlador.C_Empresa;
import Controlador.C_Login;
import Controlador.C_Partida;
import Controlador.C_Proyecto;
import Controlador.C_Proveedor;
import Controlador.C_Partida_Mezcla;
import Controlador.C_Proy_Partida;
import Controlador.C_Proy_Partida_Mezcla;
import Controlador.C_Register;
import Controlador.C_VtaCompPagoCab;
import Modelo.Menu.EventMenuSelected;
import Modelo.Menu.EventShowPopupMenu;
import Modelo.Menu.MenuItem;
import Modelo.Menu.PopupMenu;
import Modelo.Message.Mensaje;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class V_Principal extends javax.swing.JFrame {

    private int xMouse,yMouse;
    public Animator animator;
    public MigLayout layout;
    
    public V_Principal() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setLocationRelativeTo(null);
        init();
    }
    
    public void init(){
        header.initEvent(this, panelBackground1,0);
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int index,int subindex) {
                if (index == 0) { //INICIO
                    if(subindex==0){
                        mainBody.displayForm(new V_Inicio(),"Inicio");
                    }
                }else if (index == 1) { //MAESTROS
                    if(subindex==0){
                        V_Cliente vc = new V_Cliente();
                        mainBody.displayForm(vc, "Cliente");
                        C_Cliente cc = new C_Cliente(vc);
                    }else if(subindex==1){
                        V_Empleado ve=new V_Empleado();
                        mainBody.displayForm(ve, "Empleado");
                        C_Empleado ce=new C_Empleado(ve);
                    }else if(subindex==2){
                        V_Empresa vEmpresa=new V_Empresa();
                        mainBody.displayForm(vEmpresa, "Empresa");
                        C_Empresa cEmpresa=new C_Empresa(vEmpresa);
                    }else if(subindex==3){
                        V_Proveedor vp =new V_Proveedor();
                        mainBody.displayForm(vp, "Proveedor");
                        C_Proveedor cp=new C_Proveedor(vp);
                    }
                }else if (index == 2) {//PARTIDAS
                    if(subindex==0){
                        V_Partida vp=new V_Partida();
                        mainBody.displayForm(vp,"Partidas");
                        C_Partida cp=new C_Partida(vp);
                    }else if(subindex==1){
                        V_Partida_Mezcla vpm=new V_Partida_Mezcla();
                        mainBody.displayForm(vpm,"Partida Mezcla");
                        C_Partida_Mezcla cp=new C_Partida_Mezcla(vpm);
                    }
                }else if (index == 3) {//PROYECTOS
                    if(subindex==0){
                        V_Proyecto vp=new V_Proyecto();
                        mainBody.displayForm( vp,"Proyecto");
                        C_Proyecto cp=new C_Proyecto(vp);
                    }else if(subindex==1){
                        //V_Partida_Proyecto vpp=new V_Partida_Proyecto();
                        V_Proy_Partida vpp = new V_Proy_Partida();
                        mainBody.displayForm(vpp,"Partidas");
                        C_Proy_Partida cpp = new C_Proy_Partida(vpp);
                        //C_Partida_Proyecto cpp=new C_Partida_Proyecto(vpp);
                    }else if(subindex==2){
                        V_Proy_Partida_Mezcla vppm =new V_Proy_Partida_Mezcla();
                        mainBody.displayForm(vppm,"Partidas");
                        C_Proy_Partida_Mezcla cppc=new C_Proy_Partida_Mezcla(vppm);
                    }
                }else if (index == 4) {//PRESUPUESTO
                    if(subindex==0){
                        V_DProy_Partida_Mezcla vdppm =new V_DProy_Partida_Mezcla();
                        mainBody.displayForm(vdppm,"Partidas");
                        C_DProy_Partida_Mezcla cdppm=new C_DProy_Partida_Mezcla(vdppm);
                    }
                }else if (index == 5) {//VENTAS
                    if(subindex==0){
                        V_Venta vv=new V_Venta();
                        mainBody.displayForm( vv,"VtaCompPagoCab");
                        C_VtaCompPagoCab cc=new C_VtaCompPagoCab(vv);
                    }
                }else if (index == 6) {//EGRESOS
                    if(subindex==0){
                        V_Compra vc=new V_Compra();
                        mainBody.displayForm( vc,"CompPagoCab");
                        C_CompPagoCab cc=new C_CompPagoCab(vc);
                    }
                }else if (index == 7) {//FLUJO DE CAJA
                    if(subindex==0){
                        V_FlujoCajaEsp vfce=new V_FlujoCajaEsp();
                        vfce.setVisible(true);
                    }
                }else if (index == 8) {//ADMINISTRADOR
                    if(subindex==0){
                        V_Compania vc=new V_Compania();
                        mainBody.displayForm( vc,"Compania");
                        C_Compania cc=new C_Compania(vc);
                    }
                }else if (index == 9) {//CONFIGURACION
                    if(subindex==0){
                        V_Configuracion vt=new V_Configuracion();
                        mainBody.displayForm( vt,"Tabs");
                        C_Configuracion ct=new C_Configuracion(vt);
                    }
                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com){
                //System.out.println("Show Menu Replegado");
                MenuItem item=(MenuItem) com;
                PopupMenu popup = new PopupMenu(V_Principal.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = V_Principal.this.getX() + 52;
                int y = V_Principal.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);                
            }
        });
        menu.initMenuItem();
        TimingTarget target=new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraccion){
                double width;
                if(menu.isShowMenu()){
                    width=60+(170*(1f-fraccion));
                }else{
                    width=60+(170*fraccion);
                }
                //layout.setComponentConstraints(menu, "w "+width+"!, spany2");
                menu.revalidate();
            }
            @Override
            public void end(){
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }
        };
        animator=new Animator(500,target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        //en proceso
        btn.addActionListener(new ActionListener(){
           @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.ocultarMenu();
                }
        }});
        mainBody.displayForm(new V_Inicio(),"Inicio");
        repaint();
    }

    private boolean showMessage(String message) {
        Mensaje obj = new Mensaje(Frame.getFrames()[1], true);
        obj.showMessage(message);
        return obj.isOk();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn = new Modelo.Design.Button();
        panelBackground1 = new Modelo.Design.PanelBackground();
        menu = new Modelo.Menu.Menu();
        rSLabelFecha1 = new rojeru_san.RSLabelFecha();
        mainBody = new Modelo.Design.MainBody();
        panelBackground2 = new Modelo.Design.PanelBackground();
        header = new Modelo.Design.Headboard();
        jPanel1 = new javax.swing.JPanel();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        button1 = new Modelo.Design.Button();

        btn.setText("button1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBackground1.setBackground(new java.awt.Color(242, 242, 242));

        rSLabelFecha1.setForeground(new java.awt.Color(245, 245, 245));

        panelBackground2.setBackground(new java.awt.Color(54, 87, 185));

        header.setBackground(new java.awt.Color(54, 87, 185));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelBackground2Layout = new javax.swing.GroupLayout(panelBackground2);
        panelBackground2.setLayout(panelBackground2Layout);
        panelBackground2Layout.setHorizontalGroup(
            panelBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackground2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBackground2Layout.setVerticalGroup(
            panelBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground2Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(54, 87, 185));

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        button1.setBackground(new java.awt.Color(54, 87, 185));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/out.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 894, Short.MAX_VALUE))
            .addComponent(panelBackground2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBackground2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x,y;
        x = evt.getXOnScreen();
        y = evt.getYOnScreen();
        this.setLocation(x-xMouse,y-yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        V_Login vLogin=new V_Login();
        C_Register r1=new C_Register(vLogin);
        C_Login cl=new C_Login(vLogin);
        if(showMessage("¿Desea salir de la sesión?")){
            System.out.println("User click OK");
            vLogin.setVisible(true);
            dispose();
        }else{
            System.out.println("User click Cancel");
        }
    }//GEN-LAST:event_button1ActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new V_Principal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Modelo.Design.Button btn;
    private Modelo.Design.Button button1;
    private Modelo.Design.Headboard header;
    private javax.swing.JPanel jPanel1;
    public Modelo.Design.MainBody mainBody;
    private Modelo.Menu.Menu menu;
    private Modelo.Design.PanelBackground panelBackground1;
    private Modelo.Design.PanelBackground panelBackground2;
    private rojeru_san.RSLabelFecha rSLabelFecha1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    // End of variables declaration//GEN-END:variables
}
