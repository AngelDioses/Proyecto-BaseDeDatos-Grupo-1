package Modelo.DAO;

import Modelo.Cia;
import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CiaDAO implements CRUD<Cia>{
    //ConectarOracle conexion = new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement cstmt;
    int r;
    
    public int maxId(){
        int r=0;
        String sql="select MAX(codcia) from cia";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                r=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return r;
    }
    
    @Override
    public List listar() {
        List<Cia> lista = new ArrayList<>();
        String sql="SELECT * FROM CIA order by codcia asc";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                    Cia c = new Cia.Builder()
                        .codCia(rs.getInt(1))
                        .desCia(rs.getString(2))
                        .desCorta(rs.getString(3))
                        .vigente(rs.getString(4).charAt(0))
                        .build();
                    
                lista.add(c);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return lista;
    }
    
    @Override
    public int add(Cia t) {
        r=1;
        try{
           con=conexion.conectar();
           cstmt=con.prepareCall("{call INSERTAR_CIA(?,?,?,?)}");
           cstmt.setString(1, t.getCodCia()+"");
           cstmt.setString(2, t.getDesCia());
           cstmt.setString(3, t.getDesCorta());
           cstmt.setString(4, t.getVigente()+"");
           cstmt.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            return 0;
        }
       return 1;
    }
    
     public boolean busID(String user){
        boolean band = false;
        int id;
        id = Integer.parseInt(user);
        String sql="SELECT CODCIA from CIA c WHERE c.CODCIA ="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                band = true;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return band;
    }
    @Override
    public int actualizar(Cia t) {
        String sql1= "update CIA set DESCIA=?,DESCORTA=?,VIGENTE=? where CODCIA=?";
        
        System.out.println(sql1);
        r=0;
        
        
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           pst.setString(1, t.getDesCia());
           pst.setString(2, t.getDesCorta());
           pst.setString(3, t.getVigente()+"");
           pst.setInt(4, t.getCodCia());
           pst.execute();
           
           pst.close();
           con.close();
        }catch(SQLException ex){
            System.out.println(ex.toString());
            r=1;
            return 0;
        }
       return 1;
    }

    @Override
    public void eliminar(int id) {
        String sql1="delete from CLIENTE where CODCIA="+id;  //COMPROBAR ORDEN CORRECTO
        String sql2="delete from EMPRESA_VTA where CODCIA="+id;
        String sql3="delete from EMPLEADO where CODCIA="+id;
        String sql4="delete from PROVEEDOR where CODCIA="+id;
        String sql5="delete from PERSONA where CODCIA="+id;
        String sql6="delete from CIA where CODCIA="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
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
            rs=ps.executeQuery(sql6);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Cia eliminada con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
        }
    }
    
    public Cia listarId(int id){
//                Cia c = new Cia();
        Cia c = new Cia.Builder().build();
        String sql="SELECT * FROM CIA WHERE CODCIA="+id;
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
//                c.setCodCia(rs.getInt(1));
//                c.setDesCia(rs.getString(2));
//                c.setDesCorta(rs.getString(3));
//                c.setVigente(rs.getString(4).charAt(0));
                c = new Cia.Builder()
                    .codCia(rs.getInt(1))
                    .desCia(rs.getString(2))
                    .desCorta(rs.getString(3))
                    .vigente(rs.getString(4).charAt(0))
                    .build();
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return c;
    }
    
    public String getCiaNombre(int id){
        String sql="SELECT DESCIA FROM CIA WHERE CODCIA="+id;
        String ciaNombre="Error_En_CiaDAO";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                ciaNombre = rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            return ciaNombre;
        }
        return ciaNombre;
    }
    
    public int getCodCiaWithDesCia(String desCia){
        String sql="SELECT DISTINCT CODCIA FROM CIA WHERE UPPER(DESCIA)=UPPER('"+desCia+"')";
        System.out.println(sql);
        int codCia=-1;
        conexion.setValue("-------------SE REPETIRA");
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                codCia = rs.getInt(1);
                System.out.println("Encontre la CIA");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            return codCia;
        }
        return codCia;
    }
}
