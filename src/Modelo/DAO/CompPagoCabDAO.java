package Modelo.DAO;

import Modelo.Comp_PagoCab;
import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.Proyecto;
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

public class CompPagoCabDAO implements CRUD<Comp_PagoCab>{

    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    
    @Override
    public List listar() {
        List<Comp_PagoCab> lista = new ArrayList<>();
        String sql="SELECT * FROM COMP_PAGOCAB ORDER BY NROCP";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Comp_PagoCab compPagC = new Comp_PagoCab();
                //aqui agregas los de tu clase
                compPagC.setCodCia(rs.getInt(1));
                compPagC.setCodProveedor(rs.getInt(2));
                compPagC.setNroCP(rs.getString(3));
                compPagC.setCodPyto(rs.getInt(4));
                compPagC.setNroPago(rs.getInt(5));
                compPagC.settCompPago(rs.getString(6));
                compPagC.seteCompPago(rs.getString(7));
                compPagC.setFecCP(rs.getDate(8));
                compPagC.settMoneda(rs.getString(9));
                compPagC.seteMoneda(rs.getString(10));
                compPagC.setTipCambio(rs.getDouble(11));
                compPagC.setImpMO(rs.getDouble(12));
                compPagC.setImpNetoMN(rs.getDouble(13));
                compPagC.setImpIGVMN(rs.getDouble(14));
                compPagC.setImpTotalMN(rs.getDouble(15));
                compPagC.setFotoCP(rs.getString(16));
                compPagC.setFotoAbono(rs.getString(17));
                compPagC.setFecAbono(rs.getDate(18));
                compPagC.setDesAbono(rs.getString(19));
                compPagC.setSemilla(rs.getInt(20));
                compPagC.setTabEstado(rs.getString(21));
                compPagC.setCodEstado(rs.getString(22));
                lista.add(compPagC);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return lista;
    }
    public List listarPorCodPyto(int id){
        List<Proyecto> lista = new ArrayList<>();
        String sql="SELECT * FROM proyecto  WHERE CODCIA="+id+" order by CODCIA";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proyecto pyto = new Proyecto.Builder()
                        .codCia(rs.getInt(1))
                        .codPyto(rs.getInt(2))
                        .nomPyto(rs.getString(3))
                        .emplJefeProy(rs.getInt(4))
                        .ciaContrata(rs.getInt(5))
                        .codCliente(rs.getInt(6))
                        .fecReg(rs.getDate(7))
                        .estPyto(rs.getInt(8))
                        .fecEstado(rs.getDate(9))
                        .valRefer(rs.getDouble(10))
                        .costoTotSinIGV(rs.getDouble(11))
                        .impIGV(rs.getDouble(12))
                        .costoTotal(rs.getDouble(13))
                        .observac(rs.getString(14))
                        .annoIni(rs.getInt(15))
                        .annoFin(rs.getInt(16))
                        .logoProy(rs.getBytes(17))
                        .vigente(rs.getString(18).charAt(0))
                        .build();
                
                lista.add(pyto);
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
    public int add(Comp_PagoCab cpc) {
        System.out.println("{call INSERTAR_COMP_PAGOCAB(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_COMP_PAGOCAB(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           //agregas los call
           myCall.setInt(1, cpc.getCodCia());
           myCall.setInt(2, cpc.getCodProveedor());
           myCall.setString(3, cpc.getNroCP());
           myCall.setInt(4, cpc.getCodPyto());
           myCall.setInt(5, cpc.getNroPago());
           myCall.setString(6, cpc.gettCompPago());
           myCall.setString(7, cpc.geteCompPago());
           myCall.setDate(8, cpc.getFecCP());
           myCall.setString(9, cpc.gettMoneda());
           myCall.setString(10, cpc.geteMoneda());
           myCall.setDouble(11, cpc.getTipCambio());
           myCall.setDouble(12, cpc.getImpMO());
           myCall.setDouble(13, cpc.getImpNetoMN());
           myCall.setDouble(14, cpc.getImpIGVMN());
           myCall.setDouble(15, cpc.getImpTotalMN());
           myCall.setString(16, cpc.getFotoCP());
           myCall.setString(17, cpc.getFotoAbono());
           myCall.setDate(18, cpc.getFecAbono());
           myCall.setString(19, cpc.getDesAbono());
           myCall.setInt(20, cpc.getSemilla());
           myCall.setString(21, cpc.getTabEstado());
           myCall.setString(22, cpc.getCodEstado());
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
    public int actualizar(Comp_PagoCab t) {
        String sql1= "update COMP_PAGOCAB set "
                + "CODCIA=?,CODPROVEEDOR=?,CODPYTO=?,NROPAGO=?,TCOMPPAGO=?,ECOMPPAGO=?,FECCP=?,TMONEDA=?,EMONEDA=?,TIPCAMBIO=?,IMPMO=?,IMPNETOMN=?,IMPIGVMN=?,IMPTOTAL=?,FOTOCP=?,FOTOABONO=?,FECABONO=?,DESABONO=?,SEMILLA=?,TABESTADO=?,CODESTADO=?"
                + " where NROCP=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           //agregar
           pst.setInt(1, t.getCodCia());
           pst.setInt(2, t.getCodProveedor());
           pst.setInt(3, t.getCodPyto());
           pst.setInt(4, t.getNroPago());
           pst.setString(5, t.gettCompPago());
           pst.setString(6, t.geteCompPago());
           pst.setDate(7, t.getFecCP());
           pst.setString(8, t.gettMoneda());
           pst.setString(9, t.geteMoneda());
           pst.setDouble(10, t.getTipCambio());
           pst.setDouble(11, t.getImpMO());
           pst.setDouble(12, t.getImpNetoMN());
           pst.setDouble(13, t.getImpIGVMN());
           pst.setDouble(14, t.getImpTotalMN());
           pst.setString(15, t.getFotoCP());
           pst.setString(16, t.getFotoAbono());
           pst.setDate(17, t.getFecAbono());
           pst.setString(18, t.getDesAbono());
           pst.setInt(19, t.getSemilla());
           pst.setString(20, t.getTabEstado());
           pst.setString(21, t.getCodEstado());
           pst.setString(22, t.getNroCP());
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
        String sql1="delete from COMP_PAGOCAB where NROCP="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "COMP_PAGOCAB eliminadO con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    public int contarCompras(){
        int comp=0;
        String sql="select count(nrocp) from comp_pagocab where codcia="+varCodCiaGlobalDeLogin;
        try {
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                comp=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return comp;
    }
    
}
