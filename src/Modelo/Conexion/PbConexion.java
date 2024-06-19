package Modelo.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PbConexion {

    //Estos datos se encuentran en sus propios Oracle
    private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
    private static String USER = "USUARIO_ANGELD";   //ADMINISTRADORPROYECTO
    private static String PASWORD = "angel0204";    //admin1234

    public static Connection cadena;

    public PbConexion() {
         this.cadena = null;
    }

    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL,USER,PASWORD);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }

    public void desconectar() {
        try {
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            cadena = DriverManager.getConnection(URL,USER,PASWORD);
            if (cadena != null) {
                System.out.println("Conexion Exitosa");
            }
        } catch (ClassNotFoundException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Conexion Erronea " + e.getMessage());
        }
        return cadena;
    }
    
    public static void main(String[] args) {
        PbConexion.getConnection();//probamos que la conexión se inicie
    }

}