package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.Tabs;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TabsDAO implements CRUD<Tabs> {

    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    int r;
    
    @Override
    public List listar() {
        List<Tabs> lista = new ArrayList<>();
        String sql="SELECT * FROM TABS order by CODTAB";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Tabs tb = new Tabs();
                tb.setCodTab(rs.getString(1));
                tb.setDenTab(rs.getString(2));
                tb.setDenCorta(rs.getString(3));
                tb.setVigente(rs.getString(4).charAt(0));
                lista.add(tb);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println("terminando la lista");
        System.out.println(conexion.getValue());
        return lista;
    }
    
    public int listarUltimo(){
        int codigo=0;
        String sql="SELECT * FROM (SELECT * FROM TABS ORDER BY CODTAB DESC) WHERE ROWNUM=1";
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                codigo=rs.getInt(1);
            }    
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return codigo;
    }
    
    public Tabs listarId(int id) {
        Tabs tb=new Tabs();
        String sql="SELECT * FROM TABS where CODTAB="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                tb.setCodTab(rs.getString(1));
                tb.setDenTab(rs.getString(2));
                tb.setDenCorta(rs.getString(3));
                tb.setVigente(rs.getString(4).charAt(0));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println("terminando la lista");
        return tb;
    }

    @Override
    public int add(Tabs elem) {
        System.out.println("{call INSERTAR_TABS(?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_TABS(?,?,?)}");
           myCall.setString(1, elem.getDenTab());
           myCall.setString(2, elem.getDenCorta());
           myCall.setString(3, String.valueOf(elem.getVigente()));
           myCall.execute();
           myCall.close();
           con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return 0;
        }
       return 1;
    }

    @Override
    public int actualizar(Tabs elem) {
        String sql1= "update TABS set DENTAB=?,DENCORTA=?,VIGENTE=? where CODTAB=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           pst.setString(1, elem.getDenTab());
           pst.setString(2, elem.getDenCorta());
           pst.setString(3, String.valueOf(elem.getVigente()));
           pst.setString(4, elem.getCodTab());
           pst.execute();
           pst.close();
           con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return 0;
        }
        return 1;
    }

    public void eliminarxId(int id) {
        /*String sql1="DELETE from PARTIDA_MEZCLA where TUNIMED="+id;
        String sql2="DELETE from DPROY_PARTIDA_MEZCLA where TDESEMBOLSO="+id;
        String sql3="DELETE from DPROY_PARTIDA_MEZCLA where TCOMPPAGO="+id;
        String sql4="DELETE from PROY_PARTIDA_MEZCLA where TUNIMED="+id;*/
        //primero buscar en partida los que tengan en TUNIMED=id y recoger sus id's (codcia,igegr,codpartida)
        //luego consultar id's de partida y hacer INNERJOIN con proy_partida where (codcia,igegr,codpartida) ;
        // lista=listarIdPartida(String id);
        /*String sql5="DELETE from PROY_PARTIDA where CODCIA="+id+"AND INGEGR="+id+" AND CODPARTIDA="+id;
        String sql6="DELETE from PARTIDA where CODCIA="+id+"AND INGEGR="+id+" AND CODPARTIDA="+id;*/
        
        String sql7="DELETE from ELEMENTOS where CODTAB='"+id+"'";
        String sql8="DELETE from TABS where CODTAB='"+id+"'";
        
        try{
            con=conexion.conectar();
            /*ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            ps=con.prepareStatement(sql2);
            rs=ps.executeQuery(sql2);
            ps=con.prepareStatement(sql3);
            rs=ps.executeQuery(sql3);
            ps=con.prepareStatement(sql4);
            rs=ps.executeQuery(sql4);
            ps=con.prepareStatement(sql5);
            rs=ps.executeQuery(sql5);
            ps=con.prepareStatement(sql6);
            rs=ps.executeQuery(sql6);*/
            ps=con.prepareStatement(sql7);
            rs=ps.executeQuery(sql7);
            ps=con.prepareStatement(sql8);
            rs=ps.executeQuery(sql8);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Tabs eliminado con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }

    @Override
    public void eliminar(int id) {
        return;
    }
}
