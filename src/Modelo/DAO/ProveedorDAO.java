package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Proveedor;
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

public class ProveedorDAO implements CRUD<Proveedor>{
    //ConectarOracle conexion = new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement cstmt;
    int r;
    
    @Override
    
    public List listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql="SELECT p.codcia,p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "pr.nroruc,p.vigente "
                    + "FROM PERSONA p INNER JOIN PROVEEDOR pr ON p.CODPERSONA=pr.CODPROVEEDOR order by p.codcia asc";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proveedor pro = new Proveedor();
                pro.setCodCia(rs.getInt(1));
                pro.setCodPersona(rs.getInt(2));
                pro.setTipPersona(rs.getString(3).charAt(0));
                pro.setDesPersona(rs.getString(4));
                pro.setDescorta(rs.getString(5));
                pro.setDesAlterna(rs.getString(6));
                pro.setDesCortaAlt(rs.getString(7));
                pro.setNroRuc(rs.getString(8));
                pro.setVigente(rs.getString(9).charAt(0));
                lista.add(pro);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println(conexion.getValue());
        return lista;
    }

    @Override
    public int add(Proveedor t) {
        try{
           con=conexion.conectar();
           cstmt=con.prepareCall("{call INSERTAR_PROVEEDOR(?,?,?,?,?,?,?,?)}");
           cstmt.setString(1, t.getCodCia()+"");
           cstmt.setString(2, t.getTipPersona()+"");
           cstmt.setString(3, t.getDesPersona());
           cstmt.setString(4, t.getDescorta());
           cstmt.setString(5, t.getDesAlterna());
           cstmt.setString(6, t.getDesCortaAlt());
           cstmt.setString(7, t.getVigente()+"");
           cstmt.setString(8, t.getNroRuc());
           System.out.println("{call INSERTAR_PROVEEDOR("+t.getCodCia()+","+t.getTipPersona()+","+t.getDesPersona()+","+t.getDescorta()+","+t.getDesAlterna()+","+t.getDesCortaAlt()+","+t.getVigente()+","+t.getNroRuc()+")}");
           cstmt.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            r=1;
            return 0;
        }
       return 1;
    }

    @Override
    public int actualizar(Proveedor t) {
        String sql1= "update PERSONA set CODCIA=?, TIPPERSONA=?,DESPERSONA=?,DESCORTA=?,DESCALTERNA=?,DesCortaAlt=?,VIGENTE=? where CODPERSONA=?";
        
        System.out.println(sql1);
        r=0;
        String sql2 = "update PROVEEDOR set NRORUC=? where CODPROVEEDOR=?";
        
        System.out.println(sql2);
        
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           
           pst.setString(1, t.getCodCia()+"");
           pst.setString(2, t.getTipPersona()+"");
           pst.setString(3, t.getDesPersona());
           pst.setString(4, t.getDescorta());
           pst.setString(5, t.getDesAlterna());
           pst.setString(6, t.getDesCortaAlt());
           pst.setString(7, t.getVigente()+"");
           pst.setInt(8, t.getCodPersona());
           pst.execute();
           
           pst=con.prepareStatement(sql2);
           pst.setString(1, t.getNroRuc());
           pst.setInt(2, t.getCodPersona());
           pst.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            r=1;
            return 0;
        }
       return 1;
    }

    @Override
    public void eliminar(int id) {
        String sql1="delete from PROVEEDOR where CODPROVEEDOR="+id;
        String sql2="delete from PERSONA where CODPERSONA="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            ps=con.prepareStatement(sql2);
            rs=ps.executeQuery(sql2);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Proveedor eliminada con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
        }
    }
    
    public Proveedor listarId(int id){
        Proveedor pro = new Proveedor();
        String sql="SELECT p.codcia,p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "pr.nroruc,p.vigente "
                    + "FROM PERSONA p INNER JOIN PROVEEDOR pr ON p.CODPERSONA=pr.CODPROVEEDOR WHERE pr.CODPROVEEDOR="+id;
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                pro.setCodCia(rs.getInt(1));
                pro.setCodPersona(rs.getInt(2));
                pro.setTipPersona(rs.getString(3).charAt(0));
                pro.setDesPersona(rs.getString(4));
                pro.setDescorta(rs.getString(5));
                pro.setDesAlterna(rs.getString(6));
                pro.setDesCortaAlt(rs.getString(7));
                pro.setNroRuc(rs.getString(8));
                pro.setVigente(rs.getString(9).charAt(0));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return pro;
    }
    
     public List listarPorCodCia(int id){
        List<Proveedor> lista = new ArrayList<>();
        String sql="SELECT p.codcia,p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "pr.nroruc,p.vigente "
                    + "FROM PERSONA p INNER JOIN PROVEEDOR pr ON p.CODPERSONA=pr.CODPROVEEDOR WHERE p.CODCIA="+id+" order by p.codpersona";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proveedor pro = new Proveedor();
                pro.setCodCia(rs.getInt(1));
                pro.setCodPersona(rs.getInt(2));
                pro.setTipPersona(rs.getString(3).charAt(0));
                pro.setDesPersona(rs.getString(4));
                pro.setDescorta(rs.getString(5));
                pro.setDesAlterna(rs.getString(6));
                pro.setDesCortaAlt(rs.getString(7));
                pro.setNroRuc(rs.getString(8));
                pro.setVigente(rs.getString(9).charAt(0));
                lista.add(pro);
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

}
