package Vistas;

import Controlador.C_Login;
import Controlador.C_Register;
import java.awt.Color;

public class V_Login extends javax.swing.JFrame {
   
    int xMouse, yMouse;
    public static int varCodCiaGlobalDeLogin = 0;
    public static String varNombreCiaGlobalDeLogin = "ERROR_DEFAULT";
    
    public V_Login() {
        initComponents();
    }

    public void init(){
        head.initEvent(this, loginF);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        head = new Modelo.Design.Headboard();
        loginF = new Modelo.DesignLogin.D_Login();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(head, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, -1, -1));
        getContentPane().add(loginF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(V_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        V_Login vl=new V_Login();
        C_Register r1=new C_Register(vl);
        C_Login cl=new C_Login(vl);
        
        vl.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Modelo.Design.Headboard head;
    public Modelo.DesignLogin.D_Login loginF;
    // End of variables declaration//GEN-END:variables
}
