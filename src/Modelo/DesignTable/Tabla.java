package Modelo.DesignTable;

import Modelo.Design.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

public class Tabla extends JTable{
    
    int enFlujoDeCaja=0;
    
    public Tabla(){
        setShowHorizontalLines(true);
        setGridColor(new Color(225,225,225));
        setRowHeight(30);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TablaHead header = new TablaHead(o + "");
//                if (i1 == 0) {
//                    header.setHorizontalAlignment(JLabel.CENTER);
//                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {

                    Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1); // i es fila , i1 es columna
                    
                    if (selected) {
                        System.out.println("fila"+i+" columna"+i1);
                            com.setBackground(new Color(168,202,237));
                    } else {
                        if(i%2==0){
                            com.setBackground(new Color(250,250,250));
                        }else{
                            com.setBackground(new Color(225,238,244));
                        }
                    }
                    setBorder(new EmptyBorder(5,5,5,5));
                    com.setForeground(new Color(40,40,40));
                    setHorizontalAlignment(CENTER);
                    return com; 

            }
        });
    }
    
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(new Color(0,112,168));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    public Tabla(int enFlujoDeCaja){
        this.enFlujoDeCaja=enFlujoDeCaja;
        setShowHorizontalLines(true);
        setGridColor(new Color(225,225,225));
        setRowHeight(30);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TablaHead header = new TablaHead(o + "");
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {

                    Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1); // i es fila , i1 es columna
                    
                    if (selected) {
                        System.out.println("fila"+i+" columna"+i1);
                            com.setBackground(new Color(168,202,237));
                    } else {
                        if(i%2==0){
                            com.setBackground(new Color(250,250,250));
                        }else{
                            com.setBackground(new Color(225,238,244));
                        }
                    }
                    setBorder(new EmptyBorder(5,5,5,5));
                    com.setForeground(new Color(40,40,40));
                    setHorizontalAlignment(CENTER);
                    return com;
            }
        });
    }
    
    //Para sombrear a los totales del flujo de caja.
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component c = super.prepareRenderer(renderer, row, col);
        //Si estamos en empleado, y el estado es retirado 'R'.
        if(enFlujoDeCaja==1){
            if ("-".equals(getValueAt(row, 0))) {
            c.setBackground(new Color(0,112,168)); //Color para las columnas de TOTALES.
            c.setForeground(Color.WHITE);
            }
        }
        return c;
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }
}
