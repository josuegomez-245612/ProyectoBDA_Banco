/*
 * Esta clase representa una cuenta en un sistema bancario.
 */
package Persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author JOSUE GOMEZ
 * La clase CuentaDTO representa una cuenta en un sistema bancario.
 */
public class CuentaDTO {
    
    private static ArrayList<CuentaDTO> cuentas = new ArrayList<>();
    
    private int numeroCuenta;
    private Date fechaApertura;
    private float saldo;
    private int idCliente;

    // Constructor
    public CuentaDTO(int numeroCuenta, Date fechaApertura, float saldo, int idCliente) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.idCliente = idCliente;
        cuentas.add(this);
    }

    public CuentaDTO() {
    }

    // Getters y Setters
    /**
     * Obtiene el número de la cuenta.
     * @return el número de la cuenta.
     */
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de la cuenta.
     * @param numeroCuenta el número de la cuenta.
     */
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene la fecha de apertura de la cuenta.
     * @return la fecha de apertura de la cuenta.
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Establece la fecha de apertura de la cuenta.
     * @param fechaApertura la fecha de apertura de la cuenta.
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Obtiene el saldo de la cuenta.
     * @return el saldo de la cuenta.
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo de la cuenta.
     * @param saldo el saldo de la cuenta.
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtiene el ID del cliente asociado a la cuenta.
     * @return el ID del cliente asociado a la cuenta.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente asociado a la cuenta.
     * @param idCliente el ID del cliente asociado a la cuenta.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    /**
     * Obtiene la lista de todas las cuentas.
     * @return la lista de todas las cuentas.
     */
    public static ArrayList<CuentaDTO> getCuentas() {
        return cuentas;
    }

    /**
     * Representación en forma de cadena de texto de un objeto CuentaDTO.
     * @return una cadena de texto que representa el objeto CuentaDTO.
     */
    @Override
    public String toString() {
        return "CuentaDTO{" + "numeroCuenta=" + numeroCuenta + ", fechaApertura=" + fechaApertura + ", saldo=" + saldo + ", idCliente=" + idCliente + '}';
    }
}
