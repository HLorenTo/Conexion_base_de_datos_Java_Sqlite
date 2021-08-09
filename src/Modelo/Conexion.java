package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    String url = "jdbc:sqlite:C:\\Users\\loren\\Desktop\\MISIONTIC\\Ciclo2\\Java\\Bases de datos SQLite\\reto5.db";
    Connection connect; //declarar el objeto de conexion
    
    public Connection getConexion(){
        try {
            connect = DriverManager.getConnection(url);
            if (connect != null) {
                JOptionPane.showMessageDialog(null, "Conectado a la DB.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage().toString());
        }
        return connect; //retornando si se conecto o si no se conecto
    }
}



