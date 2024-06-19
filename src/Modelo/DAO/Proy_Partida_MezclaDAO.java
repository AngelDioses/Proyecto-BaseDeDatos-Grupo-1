package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.Proy_Partida_Mezcla;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Proy_Partida_MezclaDAO implements CRUD <Proy_Partida_Mezcla>{

    //ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;
    @Override
    public List listar() {
       List<Proy_Partida_Mezcla> lista = new ArrayList<>();
        String sql="SELECT * FROM PROY_PARTIDA_MEZCLA order by CODCIA";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proy_Partida_Mezcla ppm = new Proy_Partida_Mezcla();
                ppm.setCodCia(rs.getInt(1));
                ppm.setCodPyto(rs.getInt(2));
                ppm.setIngEgr(rs.getString(3));
                ppm.setNroVersion(rs.getInt(4));
                ppm.setCodPartida(rs.getInt(5));
                ppm.setCorr(rs.getInt(6));
                ppm.setPadCodPartida(rs.getInt(7));
                ppm.settUnitMed(rs.getString(8));
                ppm.seteUnitMed(rs.getString(9));
                ppm.setNivel(rs.getInt(10));
                ppm.setOrden(rs.getInt(11));
                ppm.setCostoUnit(rs.getFloat(12));
                ppm.setCant(rs.getInt(13));
                ppm.setCostoTot(rs.getFloat(14));
                lista.add(ppm);
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
    public int add(Proy_Partida_Mezcla p) {
        System.out.println("{call INSERTAR_PROY_PARTIDA_MEZCLA(?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_PROY_PARTIDA_MEZCLA(?,?,?,?,?,?)}");
           myCall.setInt(1, p.getCodCia());
           myCall.setInt(2, p.getCodPyto());
           myCall.setInt(3, p.getNroVersion());
           myCall.setInt(4, p.getPadCodPartida());
           myCall.setString(5, p.getIngEgr());
           myCall.setFloat(6, p.getCant());
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
    public int actualizar(Proy_Partida_Mezcla p) {
        String sql1= "update PROY_PARTIDA_MEZCLA set NROVERSION=?,NIVEL=?,ORDEN=?,COSTOUNIT=?,CANT=?,COSTOTOT=? where CODCIA=? AND CODPYTO=? AND INGEGR=? AND CORR=? AND CONDPARTIDA=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           myCall.setInt(1, p.getNroVersion());
           myCall.setInt(2, p.getNivel());
           myCall.setInt(3, p.getOrden());
           myCall.setFloat(4, p.getCostoUnit());
           myCall.setFloat(5, p.getCant());
           myCall.setFloat(6, p.getCostoTot());
           
           myCall.setInt(7, p.getCodCia());
           myCall.setInt(8, p.getCodPyto());
           myCall.setString(9, p.getIngEgr());
           myCall.setInt(10, p.getCorr());
           myCall.setInt(11, p.getCodPartida());
           
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
       String sql1="DELETE from PROY_PARTIDA_MEZCLA where CORR="+id;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Proy_Partida_Mezcla eliminado con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    public List listarPorCodCia(int id, String tip){
        List<Proy_Partida_Mezcla> lista = new ArrayList<>();
        String sql="SELECT "
                + "codpyto,nroversion,CORR,codpartida,padcodpartida,nivel,orden,tunimed,eunimed,costounit,cant,costotot "
                + "FROM PROY_PARTIDA_MEZCLA WHERE codcia="+id+" AND ingegr='"+tip+"' order by CORR";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proy_Partida_Mezcla ppm = new Proy_Partida_Mezcla();
                ppm.setCodPyto(rs.getInt(1));
                ppm.setNroVersion(rs.getInt(2));
                ppm.setCorr(rs.getInt(3));
                ppm.setCodPartida(rs.getInt(4));
                ppm.setPadCodPartida(rs.getInt(5));
                ppm.setNivel(rs.getInt(6));
                ppm.setOrden(rs.getInt(7));
                ppm.settUnitMed(rs.getString(8));
                ppm.seteUnitMed(rs.getString(9));
                ppm.setCostoUnit(rs.getFloat(10));
                ppm.setCant(rs.getInt(11));
                ppm.setCostoTot(rs.getFloat(12));
                lista.add(ppm);
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
    
    public List listarCodPartida_Mezcla(int id,String tip,int pyto){
        List<Proy_Partida_Mezcla> lista = new ArrayList<>();
        String sql="SELECT "
                + "codpartida"
                + " FROM PROY_PARTIDA_MEZCLA WHERE codcia="+id+" AND ingegr='"+tip+"' AND CODPYTO="+pyto+" order by codpartida";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
                pm.setCodPartida(rs.getInt(1));
                lista.add(pm);
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
    
    public List listarNiveles(int codC, int codProy, String tipo){
        List<Proy_Partida_Mezcla> lista = new ArrayList<>();
        String sql="SELECT CODPARTIDA, NIVEL FROM PROY_PARTIDA_MEZCLA WHERE codcia="+codC+" AND codpyto="+codProy+" AND ingegr='"+tipo+"' order by CORR";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proy_Partida_Mezcla ppm = new Proy_Partida_Mezcla();
                ppm.setCodPartida(rs.getInt(1));
                ppm.setNivel(rs.getInt(2));
                lista.add(ppm);
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
    
    public Proy_Partida_Mezcla listarId(int cia,String tipo, int id,int corr,int ver,int pyto){
        Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
        String sql="SELECT CODPYTO,NROVERSION,CODPARTIDA,PADCODPARTIDA,NIVEL,ORDEN,COSTOUNIT,"
                + "CANT FROM PROY_PARTIDA_MEZCLA WHERE CODCIA="+cia+" AND INGEGR='"+tipo+"' "
                + "AND CODPARTIDA="+id+" AND CORR="+corr+" AND NROVERSION="+ver+" AND CODPYTO="+pyto;
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setPadCodPartida(rs.getInt(4));
                pm.setNivel(rs.getInt(5));
                pm.setOrden(rs.getInt(6));
                pm.setCostoUnit(rs.getFloat(7));
                pm.setCant(rs.getInt(8));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return pm;
    }   
    
    public Proy_Partida_Mezcla listarId2(int cia,String tipo, int id,int pyto){
        Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
        String sql="SELECT NROVERSION,CORR,ORDEN"
                + " FROM PROY_PARTIDA_MEZCLA WHERE CODCIA="+cia+" AND INGEGR='"+tipo+"' "
                + "AND CODPARTIDA="+id+" AND CODPYTO="+pyto;
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                pm.setNroVersion(rs.getInt(1));
                pm.setCorr(rs.getInt(2));
                pm.setOrden(rs.getInt(3));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return pm;
    }     
    
    public void eliminarDatos(int cia,int cod,String tipo,int corr,int pyto,int ver) {
       String sql1="DELETE from PROY_PARTIDA_MEZCLA where CODCIA="+cia+" AND"
               + " INGEGRE='"+tipo+"' AND CODPARTIDA="+cod+" AND CORR="+corr+" AND CODPYTO="+pyto+" AND NROVERSION="+ver;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Partida_Mezcla eliminado con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    public int asignarOrden(String tip,int nivel,int cia, int pyto){
        int orden=0;
        String sql="SELECT * FROM (SELECT ORDEN FROM PROY_PARTIDA_MEZCLA WHERE INGEGR='"+tip+"' AND NIVEL="+nivel+" AND CODCIA="+cia+" AND CODPYTO="+pyto+" ORDER BY ORDEN DESC) WHERE ROWNUM=1";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                orden = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return orden+1;
    }
    
    public int asignarOrdenNoNULL(String tip,int cod,int cia,int pyto){
        int orden=0;
        String sql="SELECT * FROM (SELECT ORDEN FROM PROY_PARTIDA_MEZCLA WHERE INGEGR='"+tip+"' AND PADCODPARTIDA="+cod+" AND CODCIA="+cia+" AND CODPYTO="+pyto+" ORDER BY ORDEN DESC) WHERE ROWNUM=1";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                orden = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return orden+1;
    }
    
    public int asignarNivelNoNULL(String tip,int cod,int cia,int pyto){
        int nivel=0;
        String sql="SELECT * FROM (SELECT NIVEL FROM PROY_PARTIDA_MEZCLA WHERE INGEGR='"+tip+"' AND CODPARTIDA="+cod+" AND CODCIA="+cia+" AND CODPYTO="+pyto+" ORDER BY ORDEN DESC) WHERE ROWNUM=1";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                nivel = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return nivel+1;
    }
    
    public List listarCodPartida(int id,String tip,int pyto){
        List<Proy_Partida_Mezcla> lista = new ArrayList<>();
        String sql="SELECT "
                + "codpartida"
                + " FROM PROY_PARTIDA_MEZCLA WHERE codcia="+id+" AND ingegr='"+tip+"' AND CODPYTO="+pyto+" order by codpartida";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
                pm.setCodPartida(rs.getInt(1));
                lista.add(pm);
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
    
    public float buscarTotalCodPartCodProy(String pyto, String codpart){
        float costo=0;
        String sql="SELECT "
                + "COSTOTOT FROM PROY_PARTIDA_MEZCLA WHERE CODPYTO="+pyto+" AND CODPARTIDA="+codpart;
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                costo=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return costo;
    }
    
    public List eliminarCodPartida(List<Proy_Partida_Mezcla> lista,int id,String tip,int pyto){
        
        String sql="SELECT DISTINCT PADCODPARTIDA FROM PROY_PARTIDA_MEZCLA WHERE CODCIA="+id+" AND "
                + "INGEGR='"+tip+"' AND CODPYTO="+pyto+" AND PADCODPARTIDA IS NOT NULL ORDER BY PADCODPARTIDA";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
                pm.setCodPartida(rs.getInt(1));
                for(int i=0;i<lista.size();i++){
                    if(lista.get(i).getCodPartida() == pm.getCodPartida()){
                        lista.remove(i);
                    }
                }
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
