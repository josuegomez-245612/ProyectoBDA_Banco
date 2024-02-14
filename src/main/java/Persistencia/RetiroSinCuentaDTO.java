/**
 * La clase RetiroSinCuentaDTO representa un retiro sin cuenta realizado en el sistema bancario.
 * Contiene información como el folio de la operación, el monto, la fecha y hora del retiro,
 * el número de cuenta asociado y la contraseña utilizada para el retiro sin cuenta.
 */
package Persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase RetiroSinCuentaDTO representa un retiro sin cuenta realizado en el sistema bancario.
 * Contiene información como el folio de la operación, el monto, la fecha y hora del retiro,
 * el número de cuenta asociado y la contraseña utilizada para el retiro sin cuenta.
 */
public class RetiroSinCuentaDTO {
   
    private int folioOperacion;
    private BigDecimal monto;
    private Date fechaHora;
    private int numeroCuenta;
    private String contraseña;
    private ArrayList<RetiroSinCuentaDTO> retirosSinCuenta;

    /**
     * Obtiene el folio de la operación.
     * @return El folio de la operación.
     */
    public int getFolioOperacion() {
        return folioOperacion;
    }

    /**
     * Establece el folio de la operación.
     * @param folioOperacion El folio de la operación.
     */
    public void setFolioOperacion(int folioOperacion) {
        this.folioOperacion = folioOperacion;
    }

    /**
     * Obtiene el monto del retiro.
     * @return El monto del retiro.
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Establece el monto del retiro.
     * @param monto El monto del retiro.
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la fecha y hora del retiro.
     * @return La fecha y hora del retiro.
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora del retiro.
     * @param fechaHora La fecha y hora del retiro.
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el número de cuenta asociado al retiro.
     * @return El número de cuenta asociado al retiro.
     */
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de cuenta asociado al retiro.
     * @param numeroCuenta El número de cuenta asociado al retiro.
     */
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene la contraseña utilizada para el retiro sin cuenta.
     * @return La contraseña utilizada para el retiro sin cuenta.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña utilizada para el retiro sin cuenta.
     * @param contraseña La contraseña utilizada para el retiro sin cuenta.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene la lista de retiros sin cuenta.
     * @return La lista de retiros sin cuenta.
     */
    public ArrayList<RetiroSinCuentaDTO> getRetirosSinCuenta() {
        return retirosSinCuenta;
    }

    /**
     * Establece la lista de retiros sin cuenta.
     * @param retirosSinCuenta La lista de retiros sin cuenta.
     */
    public void setRetirosSinCuenta(ArrayList<RetiroSinCuentaDTO> retirosSinCuenta) {
        this.retirosSinCuenta = retirosSinCuenta;
    }

    /**
     * Devuelve una representación en cadena de texto del retiro sin cuenta.
     * @return Una cadena de texto que representa el retiro sin cuenta.
     */
    @Override
    public String toString() {
        return "RetiroSinCuentaDTO{" + "folioOperacion=" + folioOperacion + ", monto=" + monto + ", fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + ", contraseña=" + contraseña + ", retirosSinCuenta=" + retirosSinCuenta + '}';
    }
}
