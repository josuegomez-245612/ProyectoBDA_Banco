/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.ClienteDTO;
import Persistencia.CuentaDTO;

/**
 *
 * @author JOSUE GOMEZ
 */
public class OperacionesDAO {

    CuentaDTO c = new CuentaDTO();

    public OperacionesDAO() {
    }

    public boolean restriccionesTransaccion(ClienteDTO cliente, String monto) {

        if (Integer.parseInt(monto) > 0 && monto < Integer.parseInt(c.getSaldo())) {

        }
        return false;

    }

    public void Transaccion() {

    }
}
