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
 * @author RAUL EDUARDO GOMEZ
 */
public class RetiroSinCuentaDTO {
   
    private int folioOperacion;
    private BigDecimal monto;
    private Date fechaHora;
    private int numeroCuenta;
    private String contraseña;
    private ArrayList<RetiroSinCuentaDTO> retirosSinCuenta;

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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ArrayList<RetiroSinCuentaDTO> getRetirosSinCuenta() {
        return retirosSinCuenta;
    }

    public void setRetirosSinCuenta(ArrayList<RetiroSinCuentaDTO> retirosSinCuenta) {
        this.retirosSinCuenta = retirosSinCuenta;
    }

    @Override
    public String toString() {
        return "RetiroSinCuentaDTO{" + "folioOperacion=" + folioOperacion + ", monto=" + monto + ", fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + ", contrase\u00f1a=" + contraseña + ", retirosSinCuenta=" + retirosSinCuenta + '}';
    }
    
    
}
