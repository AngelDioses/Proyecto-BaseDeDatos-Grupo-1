package Modelo.DAO;
//Select * from (select * from empleado order by  codempleado desc) where rownum = 1
import Modelo.Cliente;
import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO implements CRUD<Cliente>{
    
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    int r;
    @Override
    public List listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql="SELECT p.codcia,p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "c.nroruc,p.vigente "
                    + "FROM PERSONA p INNER JOIN CLIENTE c ON p.CODPERSONA=c.CODCLIENTE "+"WHERE C.CODCIA="+varCodCiaGlobalDeLogin+" order by c.codcliente asc";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setCodCia(rs.getInt(1));
                cl.setCodPersona(rs.getInt(2));
                cl.setTipPersona(rs.getString(3).charAt(0));
                cl.setDesPersona(rs.getString(4));
                cl.setDescorta(rs.getString(5));
                cl.setDesAlterna(rs.getString(6));
                cl.setDesCortaAlt(rs.getString(7));
                cl.setNroRuc(rs.getString(8));
                cl.setVigente(rs.getString(9).charAt(0));
                lista.add(cl);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        System.out.println(conexion.getValue());
        return lista;
    }

    @Override
    public int add(Cliente e) {
    System.out.println("{call INSERTAR_CLIENTE(?,?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_CLIENTE(?,?,?,?,?,?,?)}");
           myCall.setInt(1, e.getCodCia());
           myCall.setString(2,e.getDesPersona());
           myCall.setString(3,e.getDescorta());
           myCall.setString(4,e.getDesAlterna());
           myCall.setString(5,e.getDesCortaAlt());
           myCall.setString(6,e.getVigente()+"");
           myCall.setString(7,e.getNroRuc());
           myCall.execute();
           myCall.close();
           con.close();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            return 0;
        }
       return 1;
    
    }
    
    @Override
    public int actualizar(Cliente e) {
        String sql1= "update PERSONA set CODCIA=?, TIPPERSONA=?,DESPERSONA=?,DESCORTA=?,DESCALTERNA=?,DesCortaAlt=?,VIGENTE=? where CODPERSONA=?";
        System.out.println(sql1);
        
        String sql2 = "update CLIENTE set NRORUC=? where CODCLIENTE=?";
        System.out.println(sql2);
        
        try {
            con=conexion.conectar();
          
            pst=con.prepareStatement(sql1);
            pst.setString(1, e.getCodCia()+"");
            pst.setString(2, e.getTipPersona()+"");
            pst.setString(3, e.getDesPersona());
            pst.setString(4, e.getDescorta());
            pst.setString(5, e.getDesAlterna());
            pst.setString(6, e.getDesCortaAlt());
            pst.setString(7, String.valueOf(e.getVigente()));
            pst.setInt(8, e.getCodPersona());
            pst.execute();
            
            pst=con.prepareStatement(sql2);
            pst.setString(1, e.getNroRuc());
            pst.setInt(2, e.getCodPersona());
            pst.execute();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return 0;
        }
        return 1;
    }

    @Override
    public void eliminar(int id) {
        String sql1="delete from CLIENTE where CODCLIENTE="+id;
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
            JOptionPane.showMessageDialog(null, "CLIENTE eliminadO con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }

    public Cliente listarId(int id) {
        Cliente cl = new Cliente();
        String sql="SELECT p.codcia, p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt,"
                + "c.nroruc,p.vigente "
                + "FROM PERSONA p INNER JOIN CLIENTE c ON p.CODPERSONA=c.CODCLIENTE WHERE c.CODCLIENTE="+id;
        
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                cl.setCodCia(rs.getInt(1));
                cl.setCodPersona(rs.getInt(2));
                cl.setTipPersona(rs.getString(3).charAt(0));
                cl.setDesPersona(rs.getString(4));
                cl.setDescorta(rs.getString(5));
                cl.setDesAlterna(rs.getString(6));
                cl.setDesCortaAlt(rs.getString(7));
                cl.setNroRuc(rs.getString(8));
                cl.setVigente(rs.getString(9).charAt(0));
            }
            //rs.close();
            //ps.close();
            //con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println("ga"+e.toString());
        }
        return cl;
    }
    
    public List listarPorCodCia(int codCia) {
        List<Cliente> lista = new ArrayList<>();
        String sql="SELECT p.codcia,p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "c.nroruc,p.vigente "
                    + "FROM PERSONA p INNER JOIN CLIENTE c ON p.CODPERSONA=c.CODCLIENTE WHERE p.codcia="+codCia+" order by p.codcia, p.codpersona asc";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setCodCia(rs.getInt(1));
                cl.setCodPersona(rs.getInt(2));
                cl.setTipPersona(rs.getString(3).charAt(0));
                cl.setDesPersona(rs.getString(4));
                cl.setDescorta(rs.getString(5));
                cl.setDesAlterna(rs.getString(6));
                cl.setDesCortaAlt(rs.getString(7));
                cl.setNroRuc(rs.getString(8));
                cl.setVigente(rs.getString(9).charAt(0));
                lista.add(cl);
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

    public int contarClientes(){
        int cl=0;
        String sql="select count(codcliente) from cliente where codcia="+varCodCiaGlobalDeLogin;
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                cl=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return cl;
    }
}
