/**
 * La clase OperacionDTO representa una operación realizada en el sistema bancario.
 * Contiene información como el folio de la operación, el tipo de operación, el monto,
 * la fecha y hora de la operación, y el número de cuenta asociado.
 */
package Persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase OperacionDTO representa una operación realizada en el sistema bancario.
 * Contiene información como el folio de la operación, el tipo de operación, el monto,
 * la fecha y hora de la operación, y el número de cuenta asociado.
 */
public class OperacionDTO {
    private int folioOperacion;
    private String tipoOperacion;
    private BigDecimal monto;
    private Date fechaHora;
    private int numeroCuenta;
    public static ArrayList<OperacionDTO> operaciones;

    /**
     * Constructor de la clase OperacionDTO.
     * @param folioOperacion El folio de la operación.
     * @param tipoOperacion El tipo de operación.
     * @param monto El monto de la operación.
     * @param fechaHora La fecha y hora de la operación.
     * @param numeroCuenta El número de cuenta asociado a la operación.
     */
    public OperacionDTO(int folioOperacion, String tipoOperacion, BigDecimal monto, Date fechaHora, int numeroCuenta) {
        this.folioOperacion = folioOperacion;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Constructor por defecto de la clase OperacionDTO.
     */
    public OperacionDTO() {
    }

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
     * Obtiene el tipo de operación.
     * @return El tipo de operación.
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Establece el tipo de operación.
     * @param tipoOperacion El tipo de operación.
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * Obtiene el monto de la operación.
     * @return El monto de la operación.
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la operación.
     * @param monto El monto de la operación.
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la fecha y hora de la operación.
     * @return La fecha y hora de la operación.
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la operación.
     * @param fechaHora La fecha y hora de la operación.
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el número de cuenta asociado a la operación.
     * @return El número de cuenta asociado a la operación.
     */
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de cuenta asociado a la operación.
     * @param numeroCuenta El número de cuenta asociado a la operación.
     */
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene la lista de operaciones.
     * @return La lista de operaciones.
     */
    public ArrayList<OperacionDTO> getOperaciones() {
        return operaciones;
    }

    /**
     * Establece la lista de operaciones.
     * @param operaciones La lista de operaciones.
     */
    public void setOperaciones(ArrayList<OperacionDTO> operaciones) {
        this.operaciones = operaciones;
    }

    /**
     * Devuelve una representación en cadena de texto de la operación.
     * @return Una cadena de texto que representa la operación.
     */
    @Override
    public String toString() {
        return "OperacionDTO{" + "folioOperacion=" + folioOperacion + ", tipoOperacion=" + tipoOperacion + ", monto=" + monto + ", fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + ", operaciones=" + operaciones + '}';
    }
}
