package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.VtaComp_PagoDet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

public class VtaComp_PagoDetDAO implements CRUD<VtaComp_PagoDet> {
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    
    @Override
    public List listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int add(VtaComp_PagoDet e) {
        System.out.println("{call INSERTAR_VTACOMP_PAGODET(?,?,?,?,?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_VTACOMP_PAGODET(?,?,?,?,?,?,?,?,?,?)}");
           myCall.setInt(1, e.getCodCia());
           myCall.setString(2, e.getNroCP());
           myCall.setInt(3, e.getSec());
           myCall.setString(4, e.getIngEgr());
           myCall.setInt(5, e.getCodPartida());
           myCall.setDouble(6, e.getImpNetoMN());
           myCall.setDouble(7, e.getImpIGVMN());
           myCall.setDouble(8, e.getImpTotalMN());
           myCall.setInt(9, e.getSemilla());
           myCall.setInt(10, e.getCodPytoAuxiliar());
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
    public int actualizar(VtaComp_PagoDet t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
