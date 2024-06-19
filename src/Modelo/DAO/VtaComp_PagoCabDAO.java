package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.VtaComp_PagoCab;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

public class VtaComp_PagoCabDAO implements CRUD<VtaComp_PagoCab> {
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
    public int add(VtaComp_PagoCab e) {
        System.out.println("2-el cod clietne que tengo es = "+e.getCodCliente());
        System.out.println("{call INSERTAR_VTACOMP_PAGOCAB(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_VTACOMP_PAGOCAB(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           myCall.setInt(1, e.getCodCia());
           myCall.setString(2, e.getNroCP());
           myCall.setInt(3, e.getCodPyto());
           myCall.setInt(4, e.getCodCliente());
           myCall.setInt(5, e.gettCompPago());
           myCall.setInt(6, e.geteCompPago());
           myCall.setDate(7, e.getFecCP());
           myCall.setInt(8, e.gettMoneda());
           myCall.setInt(9, e.geteMoneda());
           myCall.setDouble(10, e.getTipCambio());
           myCall.setDouble(11, e.getImpMO());
           myCall.setDouble(12, e.getImpNetoMN());
           myCall.setDouble(13, e.getImpIGVMN());
           myCall.setDouble(14, e.getImpTotalMN());
           myCall.setString(15, e.getFotoCP()); //Ruta en String
           myCall.setString(16, e.getFotoAbono()); //Ruta en String
           myCall.setDate(17, e.getFecAbono());
           myCall.setString(18, e.getDesAbono());
           myCall.setInt(19, e.getSemilla());
           myCall.setString(20, e.getTabEstado());
           myCall.setString(21, e.getCodEstado());
           System.out.println("{call INSERTAR_VTACOMP_PAGOCAB("+e.getCodCia()+","+e.getCodPyto()+","+e.getCodCliente()+","+e.gettCompPago()+","+e.geteCompPago()+","+e.getFecCP()+","+e.gettMoneda()+","+e.geteMoneda()+","+e.getTipCambio()+","+e.getImpMO()+","+e.getImpNetoMN()+","+e.getImpIGVMN()+","+e.getImpTotalMN()+","+e.getFotoCP()+","+e.getFotoAbono()+","+e.getFecAbono()+","+e.getDesAbono()+","+e.getSemilla()+","+e.getTabEstado()+","+e.getCodEstado()+")}");
           System.out.println("MyCALL_TOSTRING: "+myCall.toString());
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
    public int actualizar(VtaComp_PagoCab t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int contarVentas(){
        int vent=0;
        String sql="select count(nrocp) from vtacomp_pagocab where codcia="+varCodCiaGlobalDeLogin;
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                vent=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return vent;
    }
}
