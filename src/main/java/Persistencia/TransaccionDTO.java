/**
 * La clase TransaccionDTO representa una transacción realizada en el sistema bancario.
 * Contiene información como el folio de la operación, el monto, la fecha y hora de la transacción,
 * el número de cuenta origen y el número de cuenta destino.
 */
package Persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase TransaccionDTO representa una transacción realizada en el sistema bancario.
 * Contiene información como el folio de la operación, el monto, la fecha y hora de la transacción,
 * el número de cuenta origen y el número de cuenta destino.
 */
public class TransaccionDTO {
     private int folioOperacion;
    private BigDecimal monto;
    private Date fechaHora;
    private int numeroCuenta;
    private int idCuentaDestino;
    private ArrayList<TransaccionDTO> transacciones;

    /**
     * Constructor de la clase TransaccionDTO.
     * @param folioOperacion El folio de la operación.
     * @param monto El monto de la transacción.
     * @param fechaHora La fecha y hora de la transacción.
     * @param numeroCuenta El número de cuenta origen.
     * @param idCuentaDestino El número de cuenta destino.
     * @param transacciones La lista de transacciones.
     */
    public TransaccionDTO(int folioOperacion, BigDecimal monto, Date fechaHora, int numeroCuenta, int idCuentaDestino, ArrayList<TransaccionDTO> transacciones) {
        this.folioOperacion = folioOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
        this.idCuentaDestino = idCuentaDestino;
        this.transacciones = transacciones;
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
     * Obtiene el monto de la transacción.
     * @return El monto de la transacción.
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la transacción.
     * @param monto El monto de la transacción.
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la fecha y hora de la transacción.
     * @return La fecha y hora de la transacción.
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la transacción.
     * @param fechaHora La fecha y hora de la transacción.
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el número de cuenta origen.
     * @return El número de cuenta origen.
     */
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de cuenta origen.
     * @param numeroCuenta El número de cuenta origen.
     */
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene el número de cuenta destino.
     * @return El número de cuenta destino.
     */
    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    /**
     * Establece el número de cuenta destino.
     * @param idCuentaDestino El número de cuenta destino.
     */
    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    /**
     * Obtiene la lista de transacciones.
     * @return La lista de transacciones.
     */
    public ArrayList<TransaccionDTO> getTransacciones() {
        return transacciones;
    }

    /**
     * Establece la lista de transacciones.
     * @param transacciones La lista de transacciones.
     */
    public void setTransacciones(ArrayList<TransaccionDTO> transacciones) {
        this.transacciones = transacciones;
    }

    /**
     * Devuelve una representación en cadena de texto de la transacción.
     * @return Una cadena de texto que representa la transacción.
     */
    @Override
    public String toString() {
        return "TransaccionDTO{" + "folioOperacion=" + folioOperacion + ", monto=" + monto + ", fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + ", idCuentaDestino=" + idCuentaDestino + ", transacciones=" + transacciones + '}';
    }

    
}
