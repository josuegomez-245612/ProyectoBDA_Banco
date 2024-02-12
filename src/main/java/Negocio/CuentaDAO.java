/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.ClienteDTO;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
 public static int NumeroDeCuenta;
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
  
 public boolean restriccionInicioDeSesion(String numeroCuenta, String contrasena) throws SQLException {
        if (numeroCuenta.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Error: Los campos no pueden estar vacíos.");
            return false;
        }

        try (Connection conn = con.estableceConexion();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT * FROM Cliente c " +
                     "JOIN Cuenta cu ON c.ID_Cliente = cu.ID_Cliente " +
                     "WHERE cu.Numero_Cuenta = ? AND c.Contraseña = ?")) {
            statement.setString(1, numeroCuenta);
            statement.setString(2, contrasena);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true; // Credenciales correctas
            } else {
                System.out.println("Las credenciales son incorrectas.");
                return false;
            }
        }
    }

    public static String generarNumeroCuenta() {
        Random random = new Random();
        // Generar un número aleatorio de 8 dígitos
        int numero = random.nextInt(90000000) + 10000000;
        NumeroDeCuenta = numero;
        return String.valueOf(numero);
        
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
    public void agregarCuenta(ClienteDTO cliente) throws SQLException{
           try (Connection conn = con.estableceConexion();
         PreparedStatement statement = conn.prepareStatement(
                 "INSERT INTO Cliente (ID_Cliente, Nombre, Apellido_Paterno, Apellido_Materno, Domicilio, Fecha_Nacimiento, Edad, Contraseña) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
        statement.setInt(1, cliente.getIdCliente());
        statement.setString(2, cliente.getNombre());
        statement.setString(3, cliente.getApellidoPaterno());
        statement.setString(4, cliente.getApellidoMaterno());
        statement.setString(5, cliente.getDomicilio());
        statement.setTimestamp(6, new Timestamp(cliente.getFechaNacimiento().getTime()));
        statement.setInt(7, cliente.getEdad());
        statement.setString(8, cliente.getContraseña());
        statement.executeUpdate();
        System.out.println("Cliente agregado correctamente.");

      try (PreparedStatement statement2 = conn.prepareStatement(
    "INSERT INTO Cuenta (Numero_Cuenta, Fecha_Apertura, Saldo) VALUES (?, DEFAULT, DEFAULT)")) {

    statement2.setString(1, generarNumeroCuenta());
    statement2.executeUpdate();
    System.out.println("Cuenta agregada correctamente.");
    JOptionPane.showMessageDialog(null," Numero de cuenta creado: "+NumeroDeCuenta);
} catch (SQLException e) {
    e.printStackTrace();
}

       }
              }
        }
