/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import static Negocio.CuentaDAO.NumeroDeCuenta;
import Persistencia.ClienteDTO;
import Persistencia.Conexion;
import Persistencia.CuentaDTO;
import Persistencia.OperacionDTO;
import static Persistencia.OperacionDTO.operaciones;
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
 *
 * @author JOSUE GOMEZ
 */
public class OperacionesDAO {
Conexion con = new Conexion();
    public static int numeroDeOperacion;

    public OperacionesDAO() {
    }

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

    

    public static String generarNumeroOperacion() {
        Random random = new Random();
        // Generar un número aleatorio de 8 dígitos
        int numero = random.nextInt(90000000) + 10000000;
        numeroDeOperacion = numero;
        return String.valueOf(numero);
    }

  public void Transaccion(int numeroCuentaOrigen, int numeroCuentaDestino, BigDecimal monto) throws SQLException {
    try (Connection conn = con.estableceConexion(); CallableStatement statement = conn.prepareCall("{CALL transaccion(?, ?, ?)}")) {
        statement.setInt(1, numeroCuentaOrigen);
        statement.setInt(2, numeroCuentaDestino);
        statement.setBigDecimal(3, monto);
     
        statement.execute(); 

    }
}

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
    
 public void imprimirOperaciones(String tipo, Date fechaInicio, Date fechaFinal) throws SQLException {
     switch(tipo){
         case "Todas":
             DefaultTableModel model = new DefaultTableModel();
        jTableOperaciones = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(jTableOperaciones);
                try {
          Connection conn = new Conexion().estableceConexion();

            
            Statement stmt = conn.createStatement();

            // Ejecutar la consulta SQL
            ResultSet rs = stmt.executeQuery("SELECT * FROM operacion");

            // Obtener metadatos de la consulta (nombres de columnas)
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Crear una lista para almacenar los objetos OperacionDTO
            ArrayList<OperacionDTO> operaciones = new ArrayList<>();

            // Agregar filas a la tabla con los datos de la consulta
            while (rs.next()) {
                OperacionDTO operacion = new OperacionDTO();
                operacion.setFolioOperacion(rs.getInt("Folio_Operacion"));
                operacion.setTipoOperacion(rs.getString("Tipo_Operacion"));
                operacion.setMonto(rs.getBigDecimal("Monto"));
                operacion.setFechaHora(rs.getDate("Fecha_y_Hora"));
                operacion.setNumeroCuenta(rs.getInt("Numero_Cuenta"));
                operaciones.add(operacion);

                // Agregar una fila al modelo de tabla
                model.addRow(new Object[]{operacion.getFolioOperacion(), operacion.getTipoOperacion(), operacion.getMonto(), operacion.getFechaHora(), operacion.getNumeroCuenta()});
            }

            // Cerrar los recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
   
    }
   }


