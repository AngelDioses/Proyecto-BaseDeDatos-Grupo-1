package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.Partida;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PartidaDAO implements CRUD<Partida>{
    
    //ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;

    @Override
    public List listar() {
        List<Partida> lista = new ArrayList<>();
        String sql="SELECT * FROM PARTIDA order by CODCIA";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Partida pm = new Partida();
                pm.setCodCia(rs.getInt(1));
                pm.setIngEgr(rs.getString(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodPartidas(rs.getString(4));
                pm.setDesPartida(rs.getString(5));
                pm.setFlgCC(rs.getString(6));
                pm.setNivel(rs.getInt(7));
                pm.settUniMed(rs.getString(8));
                pm.seteUniMed(rs.getString(9));
                pm.setSemilla(rs.getInt(10));
                pm.setVigente(rs.getString(11).charAt(0));
                lista.add(pm);
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

    @Override
    public int add(Partida p) {
        System.out.println("{call INSERTAR_PARTIDA(?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_PARTIDA(?,?,?,?,?,?)}");
           myCall.setInt(1, p.getCodCia());
           myCall.setString(2, p.getIngEgr());
           myCall.setString(3, p.getDesPartida());
           myCall.setString(4, p.gettUniMed());
           myCall.setString(5, p.geteUniMed());
           myCall.setString(6, String.valueOf(p.getVigente()));
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
    public int actualizar(Partida p) {
            String sql1= "update PARTIDA set DesPartida=?,TUnidMed=?,EUnidMed=?,Vigente=? where CodCIA=? AND CodPartida=? AND IngEgr=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           myCall.setString(1, p.getDesPartida());
           myCall.setString(2, p.gettUniMed());
           myCall.setString(3, p.gettUniMed());
           myCall.setString(4, String.valueOf(p.getVigente()));
           myCall.setInt(5, p.getCodCia());
           myCall.setInt(6, p.getCodPartida());
           myCall.setString(7, p.getIngEgr());
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

    @Override
    public void eliminar(int id) {
      
    }
    
    public void eliminarDatos(int cia, int cod, String tip) {
       String sql1="DELETE from PARTIDA where CodCIA="+cia+" AND CodPartida="+cod+" AND IngEgr='"+tip+"'";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Partida eliminado con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    public Partida listarId(int cia,int id, String tip){
        Partida pm = new Partida();
        String sql="SELECT DesPartida,tUniMed,eUniMed,vigente,CODPARTIDAS FROM PARTIDA WHERE CODCIA="+cia+" AND CODPARTIDA="+id+" AND INGEGR='"+tip+"'";
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                pm.setDesPartida(rs.getString(1));
                pm.settUniMed(rs.getString(2));
                pm.seteUniMed(rs.getString(3));
                pm.setVigente(rs.getString(4).charAt(0));
                pm.setCodPartidas(rs.getString(5));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return pm;
    }   
    
    public Partida listarIdv2(int cia,int id){
        Partida pm = new Partida();
        String sql="SELECT DesPartida,tUniMed,eUniMed,vigente,CODPARTIDAS FROM PARTIDA WHERE CODCIA="+cia+" AND CODPARTIDA="+id;
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                pm.setDesPartida(rs.getString(1));
                pm.settUniMed(rs.getString(2));
                pm.seteUniMed(rs.getString(3));
                pm.setVigente(rs.getString(4).charAt(0));
                pm.setCodPartidas(rs.getString(5));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return pm;
    }   
    
    public String listarDescPartida(int cia,int id, String tip){
        //List<String> lista=new ArrayList<>();
        String desc="";
        String sql="SELECT DesPartida FROM PARTIDA WHERE CODCIA="+cia+" AND CODPARTIDA="+id+" AND INGEGR='"+tip+"'";
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                //lista.add(rs.getString(1));
                desc=rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return desc;
    }  
    
    public List listarPorCodCia(int id,String tip){
        List<Partida> lista = new ArrayList<>();
        String sql="SELECT "
                + "p.CodPartida,p.CodPartidas,p.DesPartida,p.tUniMed,p.eUniMed,p.Vigente "
                + "FROM PARTIDA p WHERE p.CodCIA="+id+" AND p.IngEgr='"+tip+"' order by p.codPartida";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Partida pm = new Partida();
                pm.setCodPartida(rs.getInt(1));
                pm.setCodPartidas(rs.getString(2));
                pm.setDesPartida(rs.getString(3));
                pm.settUniMed(rs.getString(4));
                pm.seteUniMed(rs.getString(5));
                pm.setVigente(rs.getString(6).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }
    
     public List listarPartidas(String id) {
        List<Partida> lista = new ArrayList<>();
        String sql="SELECT * FROM PARTIDA WHERE INGEGR="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Partida pm = new Partida();
                pm.setCodCia(rs.getInt(1));
                pm.setIngEgr(rs.getString(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodPartidas(rs.getString(4));
                pm.setDesPartida(rs.getString(5));
                pm.setFlgCC(rs.getString(6));
                pm.setNivel(rs.getInt(7));
                pm.settUniMed(rs.getString(8));
                pm.seteUniMed(rs.getString(9));
                pm.setSemilla(rs.getInt(10));
                pm.setVigente(rs.getString(11).charAt(0));
                lista.add(pm);
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
    
}