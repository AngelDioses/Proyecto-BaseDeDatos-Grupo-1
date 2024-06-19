
package Modelo.DesignTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class TablaHead extends JLabel{

    public TablaHead(String text){
        super(text);
        setOpaque(true);
        setBackground(new Color(0,112,168));
        setFont(new Font("sansserif", 1, 12));
        this.setHorizontalAlignment(CENTER);
        setForeground(new Color(247,247,247));
        setBorder(new EmptyBorder(5, 5, 5, 5));        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230, 230, 230));
        g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);        
    }
}
