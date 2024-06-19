package Modelo.DAO;

import Modelo.Empresa_Vta;
import Modelo.Conexion.ConectarOracle;
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

public class Empresa_VtaDAO implements CRUD<Empresa_Vta> {
    
    //ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;

    @Override
    public List listar() {
        List<Empresa_Vta> lista = new ArrayList<>();
        String sql="SELECT p.codcia, p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt,"
                + "e.nroruc,e.flgempconsorcio,e.fecini,e.fecfin,e.codempresa,e.observac,p.vigente "
                + "FROM PERSONA p INNER JOIN EMPRESA_VTA e ON p.CODPERSONA=e.CIACONTRATA order by p.codpersona";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Empresa_Vta emp = new Empresa_Vta();
                emp.setCodCia(rs.getInt(1));
                emp.setCodPersona(rs.getInt(2));
                emp.setTipPersona(rs.getString(3).charAt(0));
                emp.setDesPersona(rs.getString(4));
                emp.setDescorta(rs.getString(5));
                emp.setDesAlterna(rs.getString(6));
                emp.setDesCortaAlt(rs.getString(7));
                emp.setNroRuc(rs.getString(8));
                emp.setFlgEmpConsorcio(rs.getString(9).charAt(0));
                emp.setFecIni(rs.getDate(10));
                emp.setFecFin(rs.getDate(11));
                emp.setCodEmpresa(rs.getInt(12));
                emp.setObservac(rs.getString(13));
                emp.setVigente(rs.getString(14).charAt(0));
                lista.add(emp);
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

    @Override
    public int add(Empresa_Vta e){
        System.out.println("{call INSERTAR_EMPRESA_VTA(?,?,?,?,?,?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_EMPRESA_VTA(?,?,?,?,?,?,?,?,?,?,?)}");
           myCall.setInt(1, e.getCodCia());
           myCall.setString(2, String.valueOf(e.getDesPersona()));
           myCall.setString(3, String.valueOf(e.getDescorta()));
           myCall.setString(4, String.valueOf(e.getDesAlterna()));
           myCall.setString(5, String.valueOf(e.getDesCortaAlt()));
           myCall.setString(6, String.valueOf(e.getVigente()));
           myCall.setString(7, String.valueOf(e.getNroRuc()));
           myCall.setString(8, String.valueOf(e.getFlgEmpConsorcio()));
           myCall.setDate(9, e.getFecIni());
           myCall.setDate(10, e.getFecFin());
           myCall.setString(11, String.valueOf(e.getObservac()));
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
    public int actualizar(Empresa_Vta e) {
        String sql1= "update PERSONA set DESPERSONA=?,DESCORTA=?,DESCALTERNA=?,DesCortaAlt=?,VIGENTE=? where CODPERSONA=?";
        System.out.println(sql1);
        
        String sql2 = "update EMPRESA_VTA set CodCIA=?, NroRuc=?, FlgEmpConsorcio=?, FecIni=?, FecFin=?, Observac=? where CIACONTRATA=?";
        System.out.println(sql2);
        
        try {
            con=conexion.conectar();
            
            pst=con.prepareStatement(sql1);
            pst.setString(1, e.getDesPersona());
            pst.setString(2, e.getDescorta());
            pst.setString(3, e.getDesAlterna());
            pst.setString(4, e.getDesCortaAlt());
            pst.setString(5, String.valueOf(e.getVigente()));
            pst.setInt(6, e.getCodPersona());
            pst.execute();
            
            pst=con.prepareStatement(sql2);
            pst.setInt(1, e.getCodCia());
            pst.setString(2, e.getNroRuc());
            pst.setString(3, String.valueOf(e.getFlgEmpConsorcio()));
            pst.setDate(4, e.getFecIni());
            pst.setDate(5, e.getFecFin());
            pst.setString(6, e.getObservac());
            pst.setInt(7, e.getCodPersona());
            pst.execute();
            
            pst.close();
            con.close();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return -1;
        }
        return 0;
    }

    @Override
    public void eliminar(int id) {
        String sql1="delete from EMPRESA_VTA where CIACONTRATA="+id;
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
            JOptionPane.showMessageDialog(null, "Empresa eliminada con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    //Metodos Adicionales
    public Empresa_Vta listarId(int id){
        Empresa_Vta emp = new Empresa_Vta();
        String sql="SELECT p.codcia, p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt,"
                + "e.nroruc,e.flgempconsorcio,e.fecini,e.fecfin,e.codempresa,e.observac,p.vigente "
                + "FROM PERSONA p INNER JOIN EMPRESA_VTA e ON p.CODPERSONA=e.CIACONTRATA WHERE e.CIACONTRATA="+id;
        
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                emp.setCodCia(rs.getInt(1));
                emp.setCodPersona(rs.getInt(2));
                emp.setTipPersona(rs.getString(3).charAt(0));
                emp.setDesPersona(rs.getString(4));
                emp.setDescorta(rs.getString(5));
                emp.setDesAlterna(rs.getString(6));
                emp.setDesCortaAlt(rs.getString(7));
                emp.setNroRuc(rs.getString(8));
                emp.setFlgEmpConsorcio(rs.getString(9).charAt(0));
                emp.setFecIni(rs.getDate(10));
                emp.setFecFin(rs.getDate(11));
                emp.setCodEmpresa(rs.getInt(12));
                emp.setObservac(rs.getString(13));
                emp.setVigente(rs.getString(14).charAt(0));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return emp;
    }    
    
    public List listarPorCodCia(int id){
        List<Empresa_Vta> lista = new ArrayList<>();
        String sql="SELECT p.codcia, p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt,"
                + "e.nroruc,e.flgempconsorcio,e.fecini,e.fecfin,e.codempresa,e.observac,p.vigente "
                + "FROM PERSONA p INNER JOIN EMPRESA_VTA e ON p.CODPERSONA=e.CIACONTRATA WHERE p.codcia="+id+" order by p.codpersona";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Empresa_Vta emp = new Empresa_Vta();
                emp.setCodCia(rs.getInt(1));
                emp.setCodPersona(rs.getInt(2));
                emp.setTipPersona(rs.getString(3).charAt(0));
                emp.setDesPersona(rs.getString(4));
                emp.setDescorta(rs.getString(5));
                emp.setDesAlterna(rs.getString(6));
                emp.setDesCortaAlt(rs.getString(7));
                emp.setNroRuc(rs.getString(8));
                emp.setFlgEmpConsorcio(rs.getString(9).charAt(0));
                emp.setFecIni(rs.getDate(10));
                emp.setFecFin(rs.getDate(11));
                emp.setCodEmpresa(rs.getInt(12));
                emp.setObservac(rs.getString(13));
                emp.setVigente(rs.getString(14).charAt(0));
                lista.add(emp);
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
