/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.ClienteDTO;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import presentacion.Registro;

/**
 *
 * @author JOSUE GOMEZ
 */
public class CuentaDAO {

    public CuentaDAO() {
    }
    
  Conexion con = new Conexion();
  Registro res;
  
  public boolean restriccionesRegistro(Calendar selectedDate, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio) {
    int selectedYear = selectedDate.get(Calendar.YEAR);
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    if (selectedDate == null || nombre.trim().isEmpty() || apellidoPaterno.trim().isEmpty() || apellidoMaterno.trim().isEmpty() || domicilio.trim().isEmpty()) {
        JOptionPane.showMessageDialog(res, "Por favor, complete todos los campos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    } else if (selectedYear > currentYear) {
        JOptionPane.showMessageDialog(res, "Por favor, seleccione una fecha válida", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    } else if (nombre.matches(".*\\d.*") || apellidoPaterno.matches(".*\\d.*") || apellidoMaterno.matches(".*\\d.*")) {
        JOptionPane.showMessageDialog(res, "Los campos de nombre y apellidos no deben contener números.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
}
  
  public int calcularEdad(Date fechaNacimiento) {
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);

        Calendar fechaActual = Calendar.getInstance();

        int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        // Comprobar si el día de hoy es anterior al día de nacimiento
        if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNac.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        return edad;
    }
    public void agregarCuenta(ClienteDTO cliente){
          try (Connection conn = con.estableceConexion();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO Clientes (idCliente, nombre, apellidoPaterno, apellidoMaterno, estaEliminado, fechaHoraRegistro) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, cliente.getIdCliente());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getApellidoPaterno());
            statement.setString(4, cliente.getApellidoMaterno());
            statement.setTimestamp(6, new Timestamp(cliente.getFechaNacimiento().getTime()));
            statement.executeUpdate();
            System.out.println("Cliente agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
