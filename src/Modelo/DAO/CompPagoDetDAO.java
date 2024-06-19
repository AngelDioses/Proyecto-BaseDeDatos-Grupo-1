package Modelo.DAO;

import Modelo.Comp_PagoDet;
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

public class CompPagoDetDAO implements CRUD<Comp_PagoDet>{

    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    
    @Override
    public List listar() {
        List<Comp_PagoDet> lista = new ArrayList<>();
        String sql="SELECT * FROM COMP_PAGODET order by *agregar dato ordenar*";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Comp_PagoDet compPagD = new Comp_PagoDet();
                //aqui agregas los de tu clase
                lista.add(compPagD);
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
    public int add(Comp_PagoDet cpd) {
        System.out.println("{call INSERTAR_COMP_PAGODET(?,?,?,?,?,?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_COMP_PAGODET(?,?,?,?,?,?,?,?,?,?,?)}");
           myCall.setInt(1, cpd.getCodCia());
           myCall.setInt(2, cpd.getCodProveedor());
           myCall.setString(3, cpd.getNroCP());
           myCall.setInt(4, cpd.getSec());
           myCall.setString(5, cpd.getIngEgr());
           myCall.setInt(6, cpd.getCodPartida());
           myCall.setDouble(7, cpd.getImpNetoMN());
           myCall.setDouble(8, cpd.getImpIGVMN());
           myCall.setDouble(9, cpd.getImpTotalMN());
           myCall.setInt(10, cpd.getSemilla());
           myCall.setInt(11, cpd.getCodPytoAuxiliar());
           System.out.println("{call INSERTAR_COMP_PAGODET("+cpd.getCodCia()+","+cpd.getCodProveedor()+","+cpd.getNroCP()+","+cpd.getSec()+","+cpd.getIngEgr()+","+cpd.getCodPartida()+","+cpd.getImpNetoMN()+","+cpd.getImpIGVMN()+","+cpd.getImpTotalMN()+","+cpd.getSemilla()+")}");
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
    public int actualizar(Comp_PagoDet t) {
        String sql1= "update COMP_PAGODET set *agregar datos* where CODELEM=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           //agregar
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
        String sql1="delete from COMP_PAGODET where *agregar codigo*="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "COMP_PAGODET eliminadO con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    public int buscarIdSecMaximo(){
        int idSec=0;
        String sql="select max(SEC) from COMP_PAGODET";
        try{
            con=conexion.conectar();
            ps=con.createStatement();
            rs=ps.executeQuery(sql);
           while(rs.next()){
               idSec = rs.getInt(1);
           }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return idSec;
    }
    
}
