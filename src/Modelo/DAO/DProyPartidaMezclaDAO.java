package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.DProyPartidaMezcla;
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

public class DProyPartidaMezclaDAO implements CRUD<DProyPartidaMezcla> {

    //ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    
    @Override
    public List listar() {
        List<DProyPartidaMezcla> lista = new ArrayList<>();
        String sql="SELECT * FROM DPROY_PARTIDA_MEZCLA order by CODCIA";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                DProyPartidaMezcla dppm = new DProyPartidaMezcla();
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
                lista.add(dppm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println("terminando la lsita");
        return lista;
    }

    @Override
    public int add(DProyPartidaMezcla dppm) {
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_DPROY_PARTIDA_MEZCLA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           myCall.setInt(1, dppm.getCodCia());
           myCall.setInt(2, dppm.getCodPyto());
           myCall.setString(3, dppm.getIngEgr());
           myCall.setInt(4, dppm.getNroVersion());
           myCall.setInt(5, dppm.getCodPartida());
           myCall.setInt(6, dppm.getCorr());
           myCall.setString(7, dppm.geteDesembolso());
           myCall.setString(8, dppm.geteCompPago());
           myCall.setDate(9, dppm.getFecDesembolso());
           myCall.setFloat(10, dppm.getImpDesemNeto());
           myCall.setFloat(11, dppm.getImpDesemIgv());
           myCall.setFloat(12, dppm.getImpDesemTotal());
           myCall.setInt(13, dppm.getSemilla());
           myCall.setInt(14, dppm.getRep());
           myCall.execute();
           myCall.close();
           con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return -1;
        }
       return 0;
    }

    @Override
    public int actualizar(DProyPartidaMezcla dppm) {
        String sql1= "update DPROY_PARTIDA_MEZCLA set CODCIA=?,CODPYTO=?,INGEGR=?,NROVERSION=?,CODPARTIDA=?,CORR=?,TDESEMBOLSO=?,EDESEMBOLSO=?,NROPAGO=?,TCOMPPAGO=?,ECOMPPAGO=?,FECDESEMBOLSO=?,IMPDESEMBNETO=?,IMPDESEMBIGV=?,IMPDESEMBTOT=?,SEMILLA=? where SEC=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           pst.setInt(1, dppm.getCodCia());
           pst.setInt(2, dppm.getCodPyto());
           pst.setString(3, dppm.getIngEgr());
           pst.setInt(4, dppm.getNroVersion());
           pst.setInt(5, dppm.getCodPartida());
           pst.setInt(6, dppm.getCorr());
           pst.setString(7, dppm.gettDesembolso());
           pst.setString(8, dppm.geteDesembolso());
           pst.setInt(9, dppm.getNroPago());
           pst.setString(10, dppm.gettCompPago());
           pst.setString(11, dppm.geteCompPago());
           pst.setDate(12, dppm.getFecDesembolso());
           pst.setFloat(13, dppm.getImpDesemNeto());
           pst.setFloat(14, dppm.getImpDesemIgv());
           pst.setFloat(15, dppm.getImpDesemTotal());
           pst.setInt(16, dppm.getSemilla());
           pst.setInt(17, dppm.getSec());
           pst.execute();
           pst.close();
           con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return -1;
        }
        return 0;
    }

    @Override
    public void eliminar(int id) {
        String sql1="DELETE from DPROY_PARTIDA_MEZCLA where SEC="+id;
        //quizas siga algo mas
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "DPROY_PARTIDA_MEZCLA eliminado con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    public List listarPorCodCia(int id, String tip){
        List<DProyPartidaMezcla> lista = new ArrayList<>();
        String sql="SELECT * FROM DPROY_PARTIDA_MEZCLA WHERE codcia="+id+" AND ingegr='"+tip+"'";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                DProyPartidaMezcla dppm = new DProyPartidaMezcla();
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
                lista.add(dppm);
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
    
    public List listarPorCodCia(int id, String tip, int pyto){
        List<DProyPartidaMezcla> lista = new ArrayList<>();
        String sql="SELECT * FROM DPROY_PARTIDA_MEZCLA WHERE codcia="+id+" AND ingegr='"+tip+"' AND CODPYTO="+pyto;
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                DProyPartidaMezcla dppm = new DProyPartidaMezcla();
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
                lista.add(dppm);
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
    
    public DProyPartidaMezcla listarId(int semilla, String tipo){
        DProyPartidaMezcla dppm = new DProyPartidaMezcla();
        String sql="SELECT * FROM DPROY_PARTIDA_MEZCLA WHERE SEMILLA="+semilla+" AND INGEGR='"+tipo+"'";
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return dppm;
    }    
    
    public int buscarSemilla(int cia,int cod, String tipo){
        int semilla=0;
        String sql="SELECT SEMILLA FROM DPROY_PARTIDA_MEZCLA WHERE CODCIA="+cia+" AND INGEGR='"+tipo+"' AND CODPARTIDA="+cod;
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                semilla = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return semilla;
    }    
}
