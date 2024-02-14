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
 * @author JOSUE GOMEZ
 * La clase CuentaDAO proporciona métodos para acceder y manipular datos relacionados con cuentas en la base de datos.
 */
public class CuentaDAO {

    Conexion con = new Conexion();
    Registro res;
    public static int NumeroDeCuenta;

    /**
     * Constructor por defecto de la clase CuentaDAO.
     */
    public CuentaDAO() {
    }

    /**
     * Verifica las restricciones de registro antes de crear una nueva cuenta.
     * @param selectedDate la fecha de nacimiento seleccionada por el usuario.
     * @param nombre el nombre del cliente.
     * @param apellidoPaterno el apellido paterno del cliente.
     * @param apellidoMaterno el apellido materno del cliente.
     * @param domicilio el domicilio del cliente.
     * @return true si se cumplen todas las restricciones, false de lo contrario.
     */
    public boolean restriccionesRegistro(Calendar selectedDate, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String contrasena) {
        int selectedYear = selectedDate.get(Calendar.YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Verificar si la fecha de nacimiento no ha sido seleccionada
        if (selectedYear == 0 || nombre.trim().isEmpty() || apellidoPaterno.trim().isEmpty() || apellidoMaterno.trim().isEmpty() || domicilio.trim().isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (selectedYear > currentYear) { // Verificar si la fecha de nacimiento es en el futuro
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha válida", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (nombre.matches(".*\\d.*") || apellidoPaterno.matches(".*\\d.*") || apellidoMaterno.matches(".*\\d.*")) { // Verificar si nombre o apellidos contienen números
            JOptionPane.showMessageDialog(null, "Los campos de nombre y apellidos no deben contener números.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else { // Verificar si la persona es mayor o igual a 18 años
            // Calcular la edad
            int edad = currentYear - selectedYear;

            // Verificar si la edad es menor que 18
            if (edad < 18) {
                JOptionPane.showMessageDialog(null, "La persona debe tener al menos 18 años de edad.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }


    /**
     * Verifica las credenciales de inicio de sesión del cliente.
     * @param numeroCuenta el número de cuenta del cliente.
     * @param contrasena la contraseña del cliente.
     * @return un objeto ClienteDTO si las credenciales son correctas, null de lo contrario.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public ClienteDTO restriccionInicioDeSesion(String numeroCuenta, String contrasena) throws SQLException {
        if (numeroCuenta.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Error: Los campos no pueden estar vacíos.");
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
                    JOptionPane.showMessageDialog(null,"Las credenciales son incorrectas.");
                    return null;
                }
            }
        }
    
    

    /**
     * Genera un número de cuenta aleatorio de 8 dígitos.
     * @return el número de cuenta generado.
     */
    public static String generarNumeroCuenta() {
        Random random = new Random();
        // Generar un número aleatorio de 8 dígitos
        int numero = random.nextInt(90000000) + 10000000;
        NumeroDeCuenta = numero;
        return String.valueOf(numero);

    }

    /**
     * Calcula la edad de una persona a partir de su fecha de nacimiento.
     * @param fechaNacimiento la fecha de nacimiento de la persona.
     * @return la edad de la persona.
     */
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

    /**
     * Agrega una nueva cuenta para el cliente especificado.
     * @param cliente el cliente para el cual se agrega la cuenta.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
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

    /**
     * Agrega una cuenta vinculada para el cliente especificado.
     * @param cliente el cliente para el cual se agrega la cuenta vinculada.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
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

    /**
     * Devuelve una lista de números de cuenta asociados al cliente actualmente logueado.
     * @return una lista de números de cuenta.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
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