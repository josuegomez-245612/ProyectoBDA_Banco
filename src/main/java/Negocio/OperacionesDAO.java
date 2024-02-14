/**
 * La clase OperacionesDAO maneja las operaciones relacionadas con las transacciones y consultas de operaciones en la base de datos.
 * Utiliza la clase Conexion para establecer una conexión a la base de datos MySQL.
 */
package Negocio;

import Persistencia.ClienteDTO;
import Persistencia.Conexion;
import Persistencia.CuentaDTO;
import Persistencia.OperacionDTO;
import com.toedter.calendar.JCalendar;
import java.awt.List;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static presentacion.PantallaPrincipal.jTableOperaciones;

/**
 * La clase OperacionesDAO maneja las operaciones relacionadas con las transacciones y consultas de operaciones en la base de datos.
 * Utiliza la clase Conexion para establecer una conexión a la base de datos MySQL.
 */
public class OperacionesDAO {
    
    Conexion con = new Conexion();
    public static int numeroDeOperacion;

    /**
     * Constructor por defecto de la clase OperacionesDAO.
     */
    public OperacionesDAO() {
    }

    /**
     * Verifica las restricciones para realizar una transacción.
     * @param numeroCuentaOrigen El número de cuenta de origen.
     * @param numeroCuentaDestino El número de cuenta de destino.
     * @param monto El monto de la transacción.
     * @return true si se cumplen las restricciones, false en caso contrario.
     */
    public boolean restriccionesTransaccion(int numeroCuentaOrigen, int numeroCuentaDestino, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            // El monto debe ser mayor que cero
            return false;
        } else if (numeroCuentaOrigen == numeroCuentaDestino) {
            // El número de cuenta de origen no puede ser igual al número de cuenta de destino
            return false;
        } else {
            return true;
        }
    }

    /**
     * Verifica las restricciones para realizar una consulta de operaciones.
     * @param fechaInicial La fecha inicial de la consulta.
     * @param fechaFinal La fecha final de la consulta.
     * @return true si se cumplen las restricciones, false en caso contrario.
     */
    public boolean restriccionesListaOperaciones(Date fechaInicial, Date fechaFinal) {
        if (fechaInicial == null || fechaFinal == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (fechaFinal.before(fechaInicial)) {
            JOptionPane.showMessageDialog(null, "La fecha final no puede ser anterior a la fecha inicial.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Genera un número aleatorio de operación.
     * @return El número de operación generado.
     */
    public static String generarNumeroOperacion() {
        Random random = new Random();
        // Generar un número aleatorio de 8 dígitos
        int numero = random.nextInt(90000000) + 10000000;
        numeroDeOperacion = numero;
        return String.valueOf(numero);
    }

    /**
     * Realiza una transacción entre dos cuentas.
     * @param numeroCuentaOrigen El número de cuenta de origen.
     * @param numeroCuentaDestino El número de cuenta de destino.
     * @param monto El monto de la transacción.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void Transaccion(int numeroCuentaOrigen, int numeroCuentaDestino, BigDecimal monto) throws SQLException {
        try (Connection conn = con.estableceConexion(); CallableStatement statement = conn.prepareCall("{CALL transaccion(?, ?, ?)}")) {
            statement.setInt(1, numeroCuentaOrigen);
            statement.setInt(2, numeroCuentaDestino);
            statement.setBigDecimal(3, monto);

            statement.execute(); 
        }
    }

    /**
     * Inserta una nueva operación en la tabla Operacion.
     * @param tipoOperacion El tipo de operación.
     * @param monto El monto de la operación.
     * @param numeroCuenta El número de cuenta asociado a la operación.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void insertarOperacion(String tipoOperacion, BigDecimal monto, int numeroCuenta) throws SQLException {
        try (Connection conn = con.estableceConexion(); PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Operacion (Folio_Operacion, Tipo_Operacion, Monto, Numero_Cuenta) VALUES (?, ?, ?, ?)")) {

            int folioOperacion = Integer.parseInt(generarNumeroOperacion());
            statement.setInt(1, folioOperacion);
            statement.setString(2, tipoOperacion);
            statement.setBigDecimal(3, monto);
            statement.setInt(4, numeroCuenta);

            statement.executeUpdate();

            System.out.println("Registro insertado en la tabla Operacion con folio: " + folioOperacion);
        }
    }

    /**
     * Inserta una nueva transacción en la tabla Transaccion.
     * @param monto El monto de la transacción.
     * @param numeroCuenta El número de cuenta asociado a la transacción.
     * @param idCuentaDestino El ID de la cuenta de destino.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void insertarTransaccion(BigDecimal monto, int numeroCuenta, int idCuentaDestino) throws SQLException {
        try (Connection conn = con.estableceConexion(); PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Transaccion (Folio_Operacion, Monto, Numero_Cuenta, ID_Cuenta_Destino) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, numeroDeOperacion);
            statement.setBigDecimal(2, monto);
            statement.setInt(3, numeroCuenta);
            statement.setInt(4, idCuentaDestino);
            statement.executeUpdate();
            System.out.println("Registro insertado en la tabla Transaccion.");
        }
    }
    
    /**
     * Realiza una consulta de operaciones y las imprime en una tabla.
     * @param tipo El tipo de operaciones a consultar.
     * @param fechaInicio La fecha de inicio de la consulta.
     * @param fechaFinal La fecha final de la consulta.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void imprimirOperaciones(String tipo, Date fechaInicio, Date fechaFinal) throws SQLException {
        switch(tipo){
            case "Todas":
                DefaultTableModel model = new DefaultTableModel();
                jTableOperaciones = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(jTableOperaciones);
                try {
                    Connection conn = new Conexion().estableceConexion();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM operacion");
                    ResultSetMetaData metaData = rs.getMetaData();
                    int numColumns = metaData.getColumnCount();
                    for (int i = 1; i <= numColumns; i++) {
                        model.addColumn(metaData.getColumnName(i));
                    }

                    ArrayList<OperacionDTO> operaciones = new ArrayList<>();

                    while (rs.next()) {
                        OperacionDTO operacion = new OperacionDTO();
                        operacion.setFolioOperacion(rs.getInt("Folio_Operacion"));
                        operacion.setTipoOperacion(rs.getString("Tipo_Operacion"));
                        operacion.setMonto(rs.getBigDecimal("Monto"));
                        operacion.setFechaHora(rs.getDate("Fecha_y_Hora"));
                        operacion.setNumeroCuenta(rs.getInt("Numero_Cuenta"));
                        operaciones.add(operacion);
                        model.addRow(new Object[]{operacion.getFolioOperacion(), operacion.getTipoOperacion(), operacion.getMonto(), operacion.getFechaHora(), operacion.getNumeroCuenta()});
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
