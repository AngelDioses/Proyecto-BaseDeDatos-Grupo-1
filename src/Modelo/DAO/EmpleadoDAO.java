package Modelo.DAO;

import Modelo.Empleado;
import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class EmpleadoDAO implements CRUD <Empleado> {

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
        List<Empleado> lista = new ArrayList<>();
        String sql="SELECT p.codCia, p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "e.direcc,e.celular,e.hobby,e.foto,e.fecnac,e.dni,e.nrocip,e.feccipvig,e.liccond,e.flgempliea,e.observac,e.codcargo,e.email,p.vigente "
                    + "FROM PERSONA p INNER JOIN EMPLEADO e ON p.CODPERSONA=e.CODEMPLEADO "+"WHERE E.CODCIA="+varCodCiaGlobalDeLogin+" order by p.codcia asc";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Empleado emp = new Empleado();
                emp.setCodCia(rs.getInt(1));
                emp.setCodPersona(rs.getInt(2));
                emp.setTipPersona(rs.getString(3).charAt(0));
                emp.setDesPersona(rs.getString(4));
                emp.setDescorta(rs.getString(5));
                emp.setDesAlterna(rs.getString(6));
                emp.setDesCortaAlt(rs.getString(7));
                emp.setDirecc(rs.getString(8));
                emp.setCelular(rs.getString(9));
                emp.setHobby(rs.getString(10));
                emp.setFoto(rs.getBytes(11));
                emp.setFecNac(rs.getDate(12));
                emp.setDni(rs.getString(13));
                emp.setNroCIP(rs.getString(14));
                emp.setFecCIPVig(rs.getDate(15));
                emp.setLicCond(rs.getString(16).charAt(0));
                emp.setFlgEmplIEA(rs.getString(17).charAt(0));
                emp.setObservac(rs.getString(18));
                emp.setCodCargo(rs.getInt(19));
                emp.setEmail(rs.getString(20));
                emp.setVigente(rs.getString(21).charAt(0));
                lista.add(emp);
            }
            //rs.close();
            //ps.close();
            //con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println(conexion.getValue());
        return lista;
    }
    
    public Empleado listarId(int id){
        Empleado emp = new Empleado();
        String sql="SELECT p.codCia,p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "e.direcc,e.celular,e.hobby,e.foto,e.fecnac,e.dni,e.nrocip,e.FECCIPVIG,e.liccond,e.flgempliea,e.observac,e.codcargo,e.email,p.vigente "
                    + "FROM PERSONA p INNER JOIN EMPLEADO e ON p.CODPERSONA=e.CODEMPLEADO WHERE e.CODEMPLEADO="+id;
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
                emp.setDirecc(rs.getString(8));
                emp.setCelular(rs.getString(9));
                emp.setHobby(rs.getString(10));
                emp.setFoto(rs.getBytes(11));
                emp.setFecNac(rs.getDate(12));
                emp.setDni(rs.getString(13));
                emp.setNroCIP(rs.getString(14));
                emp.setFecCIPVig(rs.getDate(15));
                emp.setLicCond(rs.getString(16).charAt(0));
                emp.setFlgEmplIEA(rs.getString(17).charAt(0));
                emp.setObservac(rs.getString(18));
                emp.setCodCargo(rs.getInt(19));
                emp.setEmail(rs.getString(20));
                emp.setVigente(rs.getString(21).charAt(0));
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

    @Override
    public int add(Empleado e) {
        try{
           con=conexion.conectar();
           cstmt=con.prepareCall("{call INSERTAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           cstmt.setInt(1, e.getCodCia());
           cstmt.setString(2, String.valueOf(e.getTipPersona()));
           cstmt.setString(3, e.getDesPersona());
           cstmt.setString(4, e.getDescorta());
           cstmt.setString(5, e.getDesAlterna());
           cstmt.setString(6, e.getDesCortaAlt());
           cstmt.setString(7, String.valueOf(e.getVigente()));
           cstmt.setString(8, e.getDirecc());
           cstmt.setString(9, e.getCelular());
           cstmt.setString(10, e.getHobby());
           cstmt.setBytes(11, e.getFoto());
           cstmt.setDate(12, e.getFecNac());
           cstmt.setString(13, e.getDni());
           cstmt.setString(14, e.getNroCIP());
           cstmt.setDate(15, e.getFecCIPVig());
           cstmt.setString(16, String.valueOf(e.getLicCond()));
           cstmt.setString(17, String.valueOf(e.getFlgEmplIEA()));
           cstmt.setString(18, e.getObservac());
           cstmt.setInt(19, e.getCodCargo());
           cstmt.setString(20, e.getEmail());
           System.out.println("{call INSERTAR_EMPLEADO("+e.getCodCia()+","+String.valueOf(e.getTipPersona())+","+e.getDesPersona()+","+e.getDescorta()+","+e.getDesAlterna()+","+e.getDesCortaAlt()+","+String.valueOf(e.getVigente())+","+e.getDirecc()+","+e.getCelular()+","+e.getHobby()+",FOTO,"+e.getFecNac()+","+e.getDni()+","+e.getNroCIP()+","+e.getFecCIPVig()+","+String.valueOf(e.getLicCond())+","+String.valueOf(e.getFlgEmplIEA())+","+e.getObservac()+","+e.getCodCargo()+","+e.getEmail()+")}");
           cstmt.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            return 0;
        }
       return 1;
    }
    
    @Override
    public int actualizar(Empleado e){
        String sql1= "update PERSONA set TIPPERSONA=?,DESPERSONA=?,DESCORTA=?,DESCALTERNA=?,DesCortaAlt=?,VIGENTE=? where CODPERSONA=?";
        
        System.out.println(sql1);
        r=0;
        String sql2 = "update EMPLEADO set DIRECC=?,CELULAR=?,HOBBY=?,FOTO=?,FECNAC=?,DNI=?,NROCIP=?,FECCIPVIG=?,LICCOND=?,FLGEMPLIEA=?,OBSERVAC=?,CODCARGO=?,EMAIL=? where CODEMPLEADO=?";
        
        System.out.println(sql2);
        
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           pst.setString(1, e.getTipPersona()+"");
           pst.setString(2, e.getDesPersona());
           pst.setString(3, e.getDescorta());
           pst.setString(4, e.getDesAlterna());
           pst.setString(5, e.getDesCortaAlt());
           pst.setString(6, e.getVigente()+"");
           pst.setInt(7, e.getCodPersona());
           pst.execute();
           
           pst=con.prepareStatement(sql2);
           pst.setString(1, e.getDirecc());
           pst.setString(2, e.getCelular());
           pst.setString(3, e.getHobby());
           pst.setBytes(4, e.getFoto());
           pst.setDate(5, e.getFecNac());
           pst.setString(6, e.getDni());
           pst.setString(7, e.getNroCIP());
           pst.setDate(8, e.getFecCIPVig());
           pst.setString(9, e.getLicCond()+"");
           pst.setString(10, e.getFlgEmplIEA()+"");
           pst.setString(11, e.getObservac());
           pst.setInt(12, e.getCodCargo());
           pst.setString(13, e.getEmail());
           pst.setInt(14, e.getCodPersona());
           pst.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            return 0;
        }
       return 1;
    }

    @Override
    public void eliminar(int id) {
        String sql1="delete from EMPLEADO where CODEMPLEADO="+id;
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
        }
    }

    public List listarPorCodCia(int codCia) {
        List<Empleado> lista = new ArrayList<>();
        String sql="SELECT p.codCia, p.codpersona,p.tippersona,p.despersona,p.descorta,p.descalterna,p.DesCortaAlt, "
                    + "e.direcc,e.celular,e.hobby,e.foto,e.fecnac,e.dni,e.nrocip,e.feccipvig,e.liccond,e.flgempliea,e.observac,e.codcargo,e.email,p.vigente "
                    + "FROM PERSONA p INNER JOIN EMPLEADO e ON p.CODPERSONA=e.CODEMPLEADO WHERE p.CODCIA ="+codCia+" order by p.codcia asc";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Empleado emp = new Empleado();
                emp.setCodCia(rs.getInt(1));
                emp.setCodPersona(rs.getInt(2));
                emp.setTipPersona(rs.getString(3).charAt(0));
                emp.setDesPersona(rs.getString(4));
                emp.setDescorta(rs.getString(5));
                emp.setDesAlterna(rs.getString(6));
                emp.setDesCortaAlt(rs.getString(7));
                emp.setDirecc(rs.getString(8));
                emp.setCelular(rs.getString(9));
                emp.setHobby(rs.getString(10));
                emp.setFoto(rs.getBytes(11));
                emp.setFecNac(rs.getDate(12));
                emp.setDni(rs.getString(13));
                emp.setNroCIP(rs.getString(14));
                emp.setFecCIPVig(rs.getDate(15));
                emp.setLicCond(rs.getString(16).charAt(0));
                emp.setFlgEmplIEA(rs.getString(17).charAt(0));
                emp.setObservac(rs.getString(18));
                emp.setCodCargo(rs.getInt(19));
                emp.setEmail(rs.getString(20));
                emp.setVigente(rs.getString(21).charAt(0));
                lista.add(emp);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public boolean getPerteneceACia(int codCia, int codEmpleado){
        String sql="SELECT * FROM EMPLEADO WHERE CODCIA="+codCia+" and CODEMPLEADO="+codEmpleado;
        System.out.println(sql);
        boolean bandera=false;
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                bandera=true;
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
            bandera=false;
        }
        return bandera;
    }
}