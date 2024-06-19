
package Modelo.DesignLogin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class D_Login extends javax.swing.JPanel {

    private Color color1 = new Color(63, 64, 112);
    private Color color2 = new Color(31, 36, 61);
    private MigLayout layout;
    public D_Register_Form register;
    public D_Login_Form login;
    private Animator animator;
    private boolean isLogin;
    //public static Color mainColor = new Color(246, 207, 104);
    
    public void setAnimate(int animate) {
        layout.setComponentConstraints(register, "pos (50%)-290px-" + animate + " 0.5al n n");
        layout.setComponentConstraints(login, "pos (50%)-10px+" + animate + " 0.5al n n");
        if (animate == 30 || animate==29 ) {
            if (isLogin) {
                setComponentZOrder(login, 0);
            } else {
                setComponentZOrder(register, 0);
            }
        }
        revalidate();
    }
    
    public D_Login() {
        initComponents();
        setOpaque(false);
        init();
        setPreferredSize(new Dimension(1200, 700));
        initAnimator();
    }
    
    private void initAnimator() {
        animator = new Animator(1000, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (isLogin) {
                    register.setAlpha(fraction);
                    login.setAlpha(1f - fraction);
                    login.txtPass.setFocusable(true);
                    login.txtUser.setFocusable(true);
                    login.comboboxCia.setEnabled(true);
                    login.btIngresar.setEnabled(true);
                    register.descCia.setFocusable(false);
                    register.descCorta.setFocusable(false);
                    register.btRegistrar.setEnabled(false);
                } else {
                    register.setAlpha(1f - fraction);
                    login.setAlpha(fraction);
                    login.txtPass.setFocusable(false);
                    login.txtUser.setFocusable(false);
                    login.comboboxCia.setEnabled(false);
                    login.btIngresar.setEnabled(false);
                    register.descCia.setFocusable(true);
                    register.descCorta.setFocusable(true);
                    register.btRegistrar.setEnabled(true);
                }
            }
        });
        animator.addTarget(new PropertySetter(this, "animate", 0, 30, 0));
        animator.setResolution(0);
    }
    
    private void init() {
        layout = new MigLayout("fill", "fill", "fill");
        setLayout(layout);
        register = new D_Register_Form();
        login = new D_Login_Form();
        this.applyEvent(register, false);
        this.applyEvent(login, true);
        this.add(register, "pos (50%)-290px 0.5al n n");
        this.add(login, "pos (50%)-10px 0.5al n n");
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    showLogin(false);
                }
            }
        });
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    showLogin(true);
                }
            }
        });
    }
    
    public void showLogin(boolean show) {
        if (show != isLogin) {
            if (!animator.isRunning()) {
                isLogin = show;
                animator.start();
            }
        }
    }

    private void applyEvent(JComponent panel, boolean login) {
        for (Component com : panel.getComponents()) {
            com.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    showLogin(login);
                }
            });
        }
    }
    
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        fillBox(g2);
        super.paintComponent(grphcs);
    }

    private void fillBox(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(-10, -400, 620, 620);
        g2.fillOval(400, -370, 620, 620);
        //g2.fillOval(x, y, WIDTH, HEIGHT);
        g2.fillOval(1000, -40, 770, 770);
        g2.fillOval(950, 380, 570, 570);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
