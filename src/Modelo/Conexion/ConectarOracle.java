package Modelo.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectarOracle {
    
    //Declaraciones
    private static ConectarOracle instanceConexion;
    private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
    private static String USER = "USUARIO_ANGELD";
    private static String PASWORD = "angel0204";
    public static Connection cadena;
    private String value;
    
    //private para evitar instancia mediante el operador new
    private ConectarOracle(){
        this.cadena = null;
    }
    
    //Para obtener la instancia mediante este unico metodo 
    //la palabra reservada "static" hace posible el acceso mediante Clase.metodo 
    public static synchronized ConectarOracle getInstance(){
        
        if(instanceConexion==null){
            instanceConexion = new ConectarOracle();
        }
        return instanceConexion;
    }

    //Metodo conectar
    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL,USER,PASWORD);

        }catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }

    //metodo desconectar
    public void desconectar() {
        try {
            this.cadena.close();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //metodo getConnection
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }    
}
