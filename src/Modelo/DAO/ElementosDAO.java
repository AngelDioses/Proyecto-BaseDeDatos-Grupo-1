package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Elementos;
import Modelo.Interface.CRUD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ElementosDAO implements CRUD<Elementos>{

    //ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    int r;
    
    public int listarIdxDesc(int tEle, String desc){
        int num=0;
        String sql="SELECT CODELEM FROM ELEMENTOS WHERE CODTAB='"+tEle+"' AND DENELE='"+desc+"'";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                num=Integer.parseInt(rs.getString(1));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println("terminando la lista");
        return num;
    }
    
    @Override
    public List listar() {
        List<Elementos> lista = new ArrayList<>();
        String sql="SELECT * FROM ELEMENTOS order by CODTAB";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Elementos elem = new Elementos();
                elem.setCodTab(rs.getString(1));
                elem.setCodElemento(rs.getString(2));
                elem.setDenElemento(rs.getString(3));
                elem.setDenCorta(rs.getString(4));
                elem.setVigente(rs.getString(5).charAt(0));
                lista.add(elem);
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
    
    public List listarTabs(int cod) {
        List<Elementos> lista = new ArrayList<>();
        String sql="SELECT * FROM ELEMENTOS WHERE CODTAB='"+cod+"' order by CODTAB";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Elementos elem = new Elementos();
                elem.setCodTab(rs.getString(1));
                elem.setCodElemento(rs.getString(2));
                elem.setDenElemento(rs.getString(3));
                elem.setDenCorta(rs.getString(4));
                elem.setVigente(rs.getString(5).charAt(0));
                lista.add(elem);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println("terminando la lista");
        return lista;
    }
    
    
    
    public Elementos listarID(int idE, int idT) {
        //List<Elementos> lista = new ArrayList<>();
        Elementos elem=new Elementos();
        String sql="SELECT * FROM ELEMENTOS where CODELEM='"+idE+"' and CODTAB='"+idT+"'";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                elem.setCodTab(rs.getString(1));
                elem.setCodElemento(rs.getString(2));
                elem.setDenElemento(rs.getString(3));
                elem.setDenCorta(rs.getString(4));
                elem.setVigente(rs.getString(5).charAt(0));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return elem;
    }

    @Override
    public int add(Elementos elem) {
        System.out.println("{call INSERTAR_ELEMENTOS(?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_ELEMENTOS(?,?,?,?,?)}");
           myCall.setString(1, elem.getCodTab());
           myCall.setString(2, elem.getCodElemento());
           myCall.setString(3, elem.getDenElemento());
           myCall.setString(4, elem.getDenCorta());
           myCall.setString(5, String.valueOf(elem.getVigente()));
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
    public int actualizar(Elementos elem) {
        String sql1= "update ELEMENTOS set CODTAB=?,DENELE=?,DENCORTA=?,VIGENTE=? where CODELEM=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           pst.setString(1, elem.getCodTab());
           pst.setString(2, elem.getDenElemento());
           pst.setString(3, elem.getDenCorta());
           pst.setString(4, String.valueOf(elem.getVigente()));
           pst.setString(5, elem.getCodElemento());
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

    public void eliminar(int idE,int idT) {
        /*String sql1="DELETE from PARTIDA_MEZCLA where EUNIMED="+id;
        String sql2="DELETE from DPROY_PARTIDA_MEZCLA where EDESEMBOLSO="+id;
        String sql3="DELETE from DPROY_PARTIDA_MEZCLA where ECOMPPAGO="+id;
        String sql4="DELETE from PROY_PARTIDA_MEZCLA where EUNIMED="+id;*/
        //primero buscar en partida los que tengan en EUNIMED=id y recoger sus id's (codcia,igegr,codpartida)
        //luego consultar id's de partida y hacer INNERJOIN con proy_partida where (codcia,igegr,codpartida) ;
        // lista=listarIdPartida(String id);
        /*String sql5="DELETE from PROY_PARTIDA where CODCIA="+id+"AND INGEGR="+id+" AND CODPARTIDA="+id;
        String sql6="DELETE from PARTIDA where CODCIA="+id+"AND INGEGR="+id+" AND CODPARTIDA="+id;*/
        String sql7="DELETE from ELEMENTOS where CODELEM='"+idE+"' AND CODTAB='"+idT+"'";
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
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Elemento eliminado con exito.");
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
