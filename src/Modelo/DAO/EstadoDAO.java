package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    
    public List listar() {
        List<Estado> lista = new ArrayList<>();
        String sql="SELECT * FROM ESTADO order by CODESTADO";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                Estado est = new Estado();
                est.setTabEstado(rs.getInt(1));
                est.setCodEstado(rs.getInt(2));
                est.setDesEstado(rs.getString(3));
                est.setDesCorta(rs.getString(4));
                est.setVigente(rs.getString(5));
                lista.add(est);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println(conexion.getValue());
        return lista;
    }
}
