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
public class OperacionDTO {
       private int folioOperacion;
    private String tipoOperacion;
    private BigDecimal monto;
    private Date fechaHora;
    private int numeroCuenta;
    public static ArrayList<OperacionDTO> operaciones;

    public OperacionDTO(int folioOperacion, String tipoOperacion, BigDecimal monto, Date fechaHora, int numeroCuenta) {
        this.folioOperacion = folioOperacion;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
    }

    public OperacionDTO() {
    }

    
    public int getFolioOperacion() {
        return folioOperacion;
    }

    public void setFolioOperacion(int folioOperacion) {
        this.folioOperacion = folioOperacion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
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

    public ArrayList<OperacionDTO> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(ArrayList<OperacionDTO> operaciones) {
        this.operaciones = operaciones;
    }

    @Override
    public String toString() {
        return "OperacionDTO{" + "folioOperacion=" + folioOperacion + ", tipoOperacion=" + tipoOperacion + ", monto=" + monto + ", fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + ", operaciones=" + operaciones + '}';
    }
    
    
}
