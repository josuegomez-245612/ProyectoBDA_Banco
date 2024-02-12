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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import presentacion.PantallaPrincipal;
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

    public ClienteDTO restriccionInicioDeSesion(String numeroCuenta, String contrasena) throws SQLException {
        if (numeroCuenta.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Error: Los campos no pueden estar vacíos.");
            return null;
        }

        try (Connection conn = con.estableceConexion(); PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM Cliente WHERE ID_Cliente = ? AND Contraseña = ?"
                )) {
            statement.setString(1, numeroCuenta);
                statement.setString(2, contrasena);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {

                    return new ClienteDTO(rs.getInt("ID_Cliente"), rs.getString("Nombre"), rs.getString("Apellido_Paterno"), rs.getString("Apellido_Materno"), rs.getString("Domicilio"), rs.getTimestamp("Fecha_Nacimiento"), rs.getString("Contraseña"), rs.getInt("Edad")); // Credenciales correctas
                } else {
                    System.out.println("Las credenciales son incorrectas.");
                    return null;
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

    public void agregarCuenta(ClienteDTO cliente) throws SQLException {
        try (Connection conn = con.estableceConexion(); PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Cliente (Nombre, Apellido_Paterno, Apellido_Materno, Domicilio, Fecha_Nacimiento, Edad, Contraseña) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellidoPaterno());
            statement.setString(3, cliente.getApellidoMaterno());
            statement.setString(4, cliente.getDomicilio());
            statement.setTimestamp(5, new Timestamp(cliente.getFechaNacimiento().getTime()));
            statement.setInt(6, cliente.getEdad());
            statement.setString(7, cliente.getContraseña());

            int clientesInsertados = statement.executeUpdate();

            if (clientesInsertados > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setIdCliente(generatedKeys.getInt(1));
                        System.out.println(cliente.getIdCliente());
                        try (PreparedStatement statement2 = conn.prepareStatement(
                                "INSERT INTO Cuenta (Numero_Cuenta, ID_Cliente, Fecha_Apertura, Saldo) VALUES (?, ?, DEFAULT, DEFAULT)")) {

                            statement2.setString(1, generarNumeroCuenta());
                            statement2.setInt(2, cliente.getIdCliente());

                            statement2.executeUpdate();

                            System.out.println("Cliente y cuenta agregados correctamente.");
                            JOptionPane.showMessageDialog(null, "ID creado: " + cliente.getIdCliente());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void agregarCuentaVinculacion(ClienteDTO cliente) throws SQLException {
        try (Connection conn = con.estableceConexion(); PreparedStatement statement2 = conn.prepareStatement(
                "INSERT INTO Cuenta (Numero_Cuenta, ID_Cliente, Fecha_Apertura, Saldo) VALUES (?, ?, DEFAULT, DEFAULT)")) {

            statement2.setString(1, generarNumeroCuenta());
            statement2.setInt(2, cliente.getIdCliente());
            System.out.println(cliente.getIdCliente());
            statement2.executeUpdate();

            System.out.println("Cliente y cuenta agregados correctamente.");
            JOptionPane.showMessageDialog(null, "ID creado: " + cliente.getIdCliente());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> devolverListaNumeroCuentas() throws SQLException {
        ArrayList<Integer> listaCuentas = new ArrayList();

        try (Connection conn = con.estableceConexion(); PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM Cuenta where ID_CLIENTE = ?")) {
            statement.setInt(1, PantallaPrincipal.cliente.getIdCliente());
            System.out.println(PantallaPrincipal.cliente.getIdCliente());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                listaCuentas.add(rs.getInt("Numero_Cuenta"));

            }
        }
        return listaCuentas;
    }

}
