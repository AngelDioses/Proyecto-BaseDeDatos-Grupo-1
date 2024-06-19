
package Vistas;

import Modelo.DAO.PartidaDAO;
import Modelo.DAO.Proy_Partida_MezclaDAO;
import Modelo.Proy_Partida_Mezcla;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import net.miginfocom.swing.MigLayout;

public class V_GeneratorTree extends javax.swing.JFrame{
    
    public V_GeneratorTree(){// es lo más simple que he podido hacerlo
        
    }
    
    public V_GeneratorTree(int codCia, int codPyto, String tipo){
        init(codCia,codPyto,tipo);
    }
    
    public void init(int codC, int codPy, String tip){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new MigLayout());
        setSize(500,400);
        
        List<String> listDescPart=new ArrayList();
        List<Integer> listNiveles=new ArrayList();
        
        Map<List,List> mapita=new HashMap();
        String listPartida;
        List<Proy_Partida_Mezcla> listProPartMezcla=new Proy_Partida_MezclaDAO().listarNiveles(codC, codPy, tip);
        DefaultMutableTreeNode coll=null;//padre o raiz
        
        for(int i=0;i<listProPartMezcla.size();i++){
            listPartida=new PartidaDAO().listarDescPartida(codC, listProPartMezcla.get(i).getCodPartida(), tip);//obtenemos la descPartida
            System.out.println("CODPARTIDA: "+listProPartMezcla.get(i).getCodPartida()+" NIVEL: "+listProPartMezcla.get(i).getNivel()+" DESCRIPCION "+listPartida);
            listDescPart.add(listPartida);
            listNiveles.add(listProPartMezcla.get(i).getNivel());
        }
        
        mapita.put(listDescPart, listNiveles);
        
        for(Map.Entry<List, List> ma:mapita.entrySet()){//recorremos el map
            System.out.println("CLAVE "+ma.getKey()+" VALOR "+ma.getValue());
            DefaultTreeModel modelo=null;
            JTree tree;
            JScrollPane jsp;
            DefaultMutableTreeNode node1=new DefaultMutableTreeNode(); // nivel 2
            DefaultMutableTreeNode node2=new DefaultMutableTreeNode(); // nivel 3
            DefaultMutableTreeNode node3=new DefaultMutableTreeNode(); // nivel 4
            
            for (int i = 0; i < listNiveles.size(); i++) {             
                if(ma.getValue().get(i).equals(1)){    //raiz o nivel 1
                    coll=new DefaultMutableTreeNode(ma.getKey().get(i));  //inicializamos la raiz
                    modelo=new DefaultTreeModel(coll);   //agregamos la raiz
                    
                    tree = new JTree(modelo);  //creamos el arbol
                    jsp=new JScrollPane(tree);
                    add(jsp,"pushy,growy,pushx,growx");  //ajustamos la visulaizacion del arbol o los arboles
                    nuevoDesign(tree,coll);

                }else if(ma.getValue().get(i).equals(2)){  //nivel 2
                    node1=new DefaultMutableTreeNode(ma.getKey().get(i)); 
                    coll.add(node1); //enlazamos a la raiz o nivel 1

                }else if(ma.getValue().get(i).equals(3)){   //nivel 3
                    node2=new DefaultMutableTreeNode(ma.getKey().get(i));
                    node1.add(node2); //enlazamos al nivel 2

                }else if(ma.getValue().get(i).equals(4)){   //nivel 4
                    node3=new DefaultMutableTreeNode(ma.getKey().get(i),true);
                    node2.add(node3);  //enlazamos al nivel 3
                }else{
                    System.out.println("SOBREPASA"); //F
                }
            }
            
        }
        
        setVisible(true);
                
    }
    
    public void nuevoDesign(JTree tree,DefaultMutableTreeNode coll){
        Image img= new ImageIcon(V_GeneratorTree.class.getResource("/Image/leaf.png")).getImage();
        ImageIcon img2=new ImageIcon(img.getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        Image img3= new ImageIcon(V_GeneratorTree.class.getResource("/Image/fileopen.png")).getImage();
        ImageIcon img4=new ImageIcon(img3.getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        Image img5= new ImageIcon(V_GeneratorTree.class.getResource("/Image/fileclose.png")).getImage();
        ImageIcon img6=new ImageIcon(img5.getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        DefaultTreeCellRenderer renderer = new OwnRenderer();
        renderer.setClosedIcon(img4);
        renderer.setOpenIcon(img6);
        renderer.setLeafIcon(img2);
        renderer.setFont(new Font("sanserif",Font.PLAIN,14));
        renderer.setTextSelectionColor(new Color(250,250,250));
        tree.setCellRenderer(renderer);
        tree.expandPath(new TreePath(coll.getPath()));
        ToolTipManager.sharedInstance().registerComponent(tree);
    }
    
    private class OwnRenderer extends DefaultTreeCellRenderer {
        
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean sel, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            setToolTipText(value.toString());
            return super.getTreeCellRendererComponent(tree, value, sel,
                    expanded, leaf, row, hasFocus);
        }
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new V_GeneratorTree().setVisible(true);
            }
        });
    }
    
}
