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
            // Cargar el controlador JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Establecer la conexión
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);

            // Mostrar mensaje de conexión exitosa
            JOptionPane.showMessageDialog(null, "Se conectó a la base de datos con éxito");
        } catch (Exception e) {
            // Manejo de excepciones en caso de error
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. Error: " + e.toString());
        }
        
        // Devolver la conexión (puede ser nula si hubo un error)
        return conectar;
    }
}
