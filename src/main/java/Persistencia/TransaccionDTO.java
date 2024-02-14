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
public class TransaccionDTO {
     private int folioOperacion;
    private BigDecimal monto;
    private Date fechaHora;
    private int numeroCuenta;
    private int idCuentaDestino;
    private ArrayList<TransaccionDTO> transacciones;

    public TransaccionDTO(int folioOperacion, BigDecimal monto, Date fechaHora, int numeroCuenta, int idCuentaDestino, ArrayList<TransaccionDTO> transacciones) {
        this.folioOperacion = folioOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
        this.idCuentaDestino = idCuentaDestino;
        this.transacciones = transacciones;
    }

    public int getFolioOperacion() {
        return folioOperacion;
    }

    public void setFolioOperacion(int folioOperacion) {
        this.folioOperacion = folioOperacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public ArrayList<TransaccionDTO> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<TransaccionDTO> transacciones) {
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "TransaccionDTO{" + "folioOperacion=" + folioOperacion + ", monto=" + monto + ", fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + ", idCuentaDestino=" + idCuentaDestino + ", transacciones=" + transacciones + '}';
    }

    
}
