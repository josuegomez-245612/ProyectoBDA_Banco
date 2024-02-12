/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.ClienteDTO;
import Persistencia.CuentaDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSUE GOMEZ
 */
public class OperacionesDAO {

    CuentaDTO c = new CuentaDTO();

    public OperacionesDAO() {
    }

    public boolean restriccionesTransaccion(ClienteDTO cliente, String monto) {

    float montoFloat = Float.parseFloat(monto);

    
    if (montoFloat > 0) {
       
return true;
        
        }else if (montoFloat < c.getSaldo()) {
           
            return true;
        }else{
        return false;
      
    }
    
    }

    public void Transaccion() {

    }
}
