/*
 * Esta clase representa un objeto de transferencia de datos (DTO) para los clientes.
 * Proporciona métodos para acceder y manipular los datos de los clientes, como nombre, apellido, domicilio, etc.
 */
package Persistencia;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author JOSUE GOMEZ
 * La clase ClienteDTO representa un objeto de transferencia de datos para los clientes.
 */
public class ClienteDTO {
    
    private static ArrayList<ClienteDTO> clientes = new ArrayList<>();

    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private Date fechaNacimiento;
    private String contraseña;
    private int edad;

    /**
     * Constructor por defecto de la clase ClienteDTO.
     */
    public ClienteDTO() {
    }

    /**
     * Constructor de la clase ClienteDTO con parámetros.
     * @param idCliente el ID del cliente.
     * @param nombre el nombre del cliente.
     * @param apellidoPaterno el apellido paterno del cliente.
     * @param apellidoMaterno el apellido materno del cliente.
     * @param domicilio el domicilio del cliente.
     * @param contraseña la contraseña del cliente.
     * @param edad la edad del cliente.
     */
    public ClienteDTO(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String contraseña, int edad) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.contraseña = contraseña;
        this.edad = edad;
    }

    /**
     * Constructor de la clase ClienteDTO con parámetros que incluyen la fecha de nacimiento.
     * @param idCliente el ID del cliente.
     * @param nombre el nombre del cliente.
     * @param apellidoPaterno el apellido paterno del cliente.
     * @param apellidoMaterno el apellido materno del cliente.
     * @param domicilio el domicilio del cliente.
     * @param fechaNacimiento la fecha de nacimiento del cliente.
     * @param contraseña la contraseña del cliente.
     * @param edad la edad del cliente.
     */
    public ClienteDTO(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, Date fechaNacimiento, String contraseña, int edad) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.contraseña = contraseña;
        this.edad = edad;
        clientes.add(this); 
    }

    /**
     * Obtiene el ID del cliente.
     * @return el ID del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente.
     * @param idCliente el ID del cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return el nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre el nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del cliente.
     * @return el apellido paterno del cliente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del cliente.
     * @param apellidoPaterno el apellido paterno del cliente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del cliente.
     * @return el apellido materno del cliente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del cliente.
     * @param apellidoMaterno el apellido materno del cliente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el domicilio del cliente.
     * @return el domicilio del cliente.
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Establece el domicilio del cliente.
     * @param domicilio el domicilio del cliente.
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     * @return la fecha de nacimiento del cliente.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     * @param fechaNacimiento la fecha de nacimiento del cliente.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la contraseña del cliente.
     * @return la contraseña del cliente.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del cliente.
     * @param contraseña la contraseña del cliente.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene la edad del cliente.
     * @return la edad del cliente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del cliente.
     * @param edad la edad del cliente.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Obtiene la lista de todos los clientes.
     * @return la lista de todos los clientes.
     */
    public static ArrayList<ClienteDTO> getClientes() {
        return clientes;
    }

    /**
     * Representación en forma de cadena de texto de un objeto ClienteDTO.
     * @return una cadena de texto que representa el objeto ClienteDTO.
     */
    @Override
    public String toString() {
        return "idCliente=" + idCliente + "\n nombre=" + nombre + "\n apellidoPaterno=" + apellidoPaterno + "\n apellidoMaterno=" + apellidoMaterno + "\n domicilio=" + domicilio + "\n fechaNacimiento=" + fechaNacimiento.getDay()+"/"+fechaNacimiento.getMonth() +"/" +fechaNacimiento.getYear() +"\n edad=" + edad ;
    }
    
}
