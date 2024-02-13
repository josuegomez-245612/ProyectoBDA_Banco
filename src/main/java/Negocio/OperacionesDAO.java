/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import static Negocio.CuentaDAO.NumeroDeCuenta;
import Persistencia.ClienteDTO;
import Persistencia.Conexion;
import Persistencia.CuentaDTO;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Random;
import javax.swing.JOptionPane;

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
}