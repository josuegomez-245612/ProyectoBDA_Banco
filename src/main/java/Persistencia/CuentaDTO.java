/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author JOSUE GOMEZ
 */
public class CuentaDTO {
      private static ArrayList<CuentaDTO> cuentas = new ArrayList<>();
  private int numeroCuenta;
    private Date fechaApertura;
    private BigDecimal saldo;
    private int idCliente;

    // Constructor
    public CuentaDTO(int numeroCuenta, Date fechaApertura, BigDecimal saldo, int idCliente) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.idCliente = idCliente;
         cuentas.add(this);
    }

    public CuentaDTO() {
    }

    // Getters y Setters
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
      public static ArrayList<CuentaDTO> getCuentas() {
        return cuentas;
    }

    @Override
    public String toString() {
        return "CuentaDTO{" + "numeroCuenta=" + numeroCuenta + ", fechaApertura=" + fechaApertura + ", saldo=" + saldo + ", idCliente=" + idCliente + '}';
    }
      
}