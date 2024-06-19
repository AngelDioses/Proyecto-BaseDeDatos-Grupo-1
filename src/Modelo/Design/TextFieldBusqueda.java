package Modelo.Design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class TextFieldBusqueda extends JTextField{
    
    Color backgroundColor=Color.WHITE;
    final Icon iconBuscar;
    Color animacionColor=new Color(100,167,11);
    String hintText="Buscar...";
    Border border;
    
    public TextFieldBusqueda(){
        super.setBackground(new Color(255,255,255));
        setOpaque(false);
        setBorder(new EmptyBorder(10,10,10,50));
        setFont(new java.awt.Font("sanserif",0,14));
        setSelectionColor(new Color(100,167,11));
        iconBuscar=new ImageIcon(getClass().getResource("/image/search.png"));
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (checkMouseOver(me.getPoint())) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.TEXT_CURSOR));
                }
            }
        });
        addMouseListener(new MouseAdapter(){
           @Override 
            public void mousePressed(MouseEvent me){
                if(SwingUtilities.isLeftMouseButton(me)){
                    if(checkMouseOver(me.getPoint())){
                        System.out.println("Presionado");
                    }
                }
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics grphcs){
        int width=getWidth();
        int height=getHeight();
        Graphics2D g2=(Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, width, height, height, height);
        super.paintComponent(grphcs);
        
        //creando el boton buscar
        int margenButton=5;
        int buttonSize=height-margenButton*2;
        GradientPaint grad=new GradientPaint(0,0,new Color(255,255,255),width,0,animacionColor);
        g2.setPaint(grad);
        g2.fillOval(width-height+3, margenButton, buttonSize, buttonSize);
        
        //crando el icono de buscar
        int margenImg=5;
        int imageSize=buttonSize-margenImg*2;
        Image img=((ImageIcon)iconBuscar).getImage();
        g2.drawImage(img, width-height+margenImg+3, margenButton+margenImg, imageSize, imageSize, null);
        g2.dispose();
    }
    
    public boolean checkMouseOver(Point mouse) {
        int width = getWidth();
        int height = getHeight();
        int marginButton = 5;
        int buttonSize = height - marginButton * 2;
        Point point = new Point(width - height + 3, marginButton);
        Ellipse2D.Double circle = new Ellipse2D.Double(point.x, point.y, buttonSize, buttonSize);
        return circle.contains(mouse);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(hintText, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
}

