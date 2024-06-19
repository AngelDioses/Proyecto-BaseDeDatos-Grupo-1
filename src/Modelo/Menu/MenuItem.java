package Modelo.Menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class MenuItem extends javax.swing.JPanel {

    float alpha;
    ModeloMenu menu;
    boolean open;
    EventMenuSelected eventSelected;
    int index;

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public ModeloMenu getMenu() {
        return menu;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(EventMenuSelected eventSelected) {
        this.eventSelected = eventSelected;
    }

    public int getIndex() {
        return index;
    }

    public MenuItem(ModeloMenu menu, EventMenu event, EventMenuSelected eventSelected, int index) {
        initComponents();
        this.menu = menu;
        this.eventSelected = eventSelected;
        this.index = index;

        setOpaque(false);
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, 40!]0[fill, 35!]"));
        MenuButton primerItem = new MenuButton(menu.getIcon(), "      " + menu.getMenuName());
        primerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (menu.getSubMenu().length > 0) {
                    if (event.selectedMenu(MenuItem.this, !open)) {
                        open = !open;
                    }
                    //System.out.println("Click primer item");
                }
                eventSelected.menuSelected(index, -1);
            }
        });
        add(primerItem);
        int subMenuIndex = -1;
        for (String st : menu.getSubMenu()) {
            MenuButton item = new MenuButton(st);
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    eventSelected.menuSelected(index, item.getIndex());
                }
            });
            add(item);
        }
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

    public void paintComponent(Graphics g){
        int width=getWidth();
        int height=getPreferredSize().height;
        Graphics2D G2D=(Graphics2D) g;
        G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        G2D.setColor(new Color(106,113,193));
        G2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        G2D.fillRect(0, 2, width, 38);
        G2D.setComposite(AlphaComposite.SrcOver);
        G2D.fillRect(0, 40, width, height-40);
        G2D.setColor(new Color(240,240,240));
        G2D.drawLine(30, 40, 30, height-17);
        for(int i=0;i<menu.getSubMenu().length;i++){
            int y=((i+1)*35+40)-17;
            G2D.drawLine(30, y, 38, y);
        }
        if(menu.getSubMenu().length>0)
            creaBotonFlecha(G2D);
        super.paintComponent(g);
    }
    
    public void creaBotonFlecha(Graphics2D gr){
        int magnitud=4;
        int y=19;
        int x=205;
        gr.setColor(Color.WHITE);
        float a=alpha*magnitud;
        float b=(1f-alpha)*magnitud;
        gr.drawLine(x, (int) (y + a), x + 4, (int) (y + b));
        gr.drawLine(x + 4, (int) (y + b), x + 8, (int) (y + a));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
