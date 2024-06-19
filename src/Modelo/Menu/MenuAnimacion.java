package Modelo.Menu;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MenuAnimacion {
    final MigLayout layout;
    final MenuItem menuItem;
    Animator animator;
    boolean open;
    
    public MenuAnimacion(MigLayout layout, Component component){
        this.layout=layout;
        this.menuItem=(MenuItem) component;
        initAnimator(component, 200);
    }
    
    public MenuAnimacion(MigLayout layout, Component component, int duracion){
        this.layout=layout;
        this.menuItem=(MenuItem) component;
        initAnimator(component, duracion);
    }
    //abrir o cerrar menu
    public void initAnimator(Component component, int duracion){
        int height=component.getPreferredSize().height;
        TimingTarget target=new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraccion){
                float h;
                if(open){
                    h=40+((height-40)*fraccion);
                    menuItem.setAlpha(fraccion);
                }else{
                    h=40+((height-40)*(1f-fraccion));
                    menuItem.setAlpha(1f-fraccion);
                }
                layout.setComponentConstraints(menuItem,"h "+h+"!");
                component.revalidate();
                component.repaint();
            }
        };
        animator=new Animator(duracion,target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
    }
    public void openMenu(){
        open=true;
        animator.start();
    }
    
    public void closeMenu(){
        open=false;
        animator.start();
    }
}
