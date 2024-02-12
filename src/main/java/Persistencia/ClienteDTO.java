package Persistencia;

import java.util.ArrayList;
import java.util.Date;

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

    public ClienteDTO() {
    }

    public ClienteDTO(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String contraseña, int edad) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.contraseña = contraseña;
        this.edad = edad;
    }

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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public static ArrayList<ClienteDTO> getClientes() {
        return clientes;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", domicilio=" + domicilio + ", fechaNacimiento=" + fechaNacimiento + ", contrase\u00f1a=" + contraseña + ", edad=" + edad + '}';
    }
    
}
