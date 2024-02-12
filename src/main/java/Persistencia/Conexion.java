/*
 * Este es un ejemplo de una clase en Java que establece una conexión a una base de datos MySQL.
 * Asegúrate de tener el controlador JDBC de MySQL en tu proyecto.
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    // Paso 1: Declaración de variables
    Connection conectar = null;
    String usuario = "root";
    String contraseña = "manejotsuru21";
    String bd = "banco";
    String ip = "127.0.0.1";
    String puerto = "3306";

    // Paso 2: Construcción de la cadena de conexión
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    // Paso 3: Método para establecer la conexión
    public Connection estableceConexion() {
        try {
            // Paso 4: Cargar el controlador JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Paso 5: Establecer la conexión
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);

            // Paso 6: Mostrar mensaje de conexión exitosa
            JOptionPane.showMessageDialog(null, "Se conectó a la base de datos con éxito");
        } catch (Exception e) {
            // Paso 7: Manejo de excepciones en caso de error
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. Error: " + e.toString());
        }
        
        // Paso 8: Devolver la conexión (puede ser nula si hubo un error)
        return conectar;
    }
}
