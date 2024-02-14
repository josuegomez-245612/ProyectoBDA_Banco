/*
 * Esta clase establece una conexión a una base de datos MySQL.
 * Asegúrate de tener el controlador JDBC de MySQL en tu proyecto.
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author JOSUE GOMEZ
 * La clase Conexion establece una conexión a una base de datos MySQL.
 */
public class Conexion {
    
    // Declaración de variables
    Connection conectar = null;
    String usuario = "root";
    String contraseña = "manejotsuru21";
    String bd = "banco";
    String ip = "127.0.0.1";
    String puerto = "3306";

    // Cadena de conexión
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    /**
     * Establece una conexión a la base de datos.
     * @return la conexión establecida.
     */
    public  Connection estableceConexion() {
        try {
          
            Class.forName("com.mysql.jdbc.Driver");

            conectar = DriverManager.getConnection(cadena, usuario, contraseña);

           
        } catch (Exception e) {
          
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. Error: " + e.toString());
        }
        
       
        return conectar;
    }
}
