package com.cga.concesionario.modelo;

public class Cliente {

	private int clienteID;
	private String nombre;
	private String apellido;
	private String DNI;
	private String telefono;
	private String email;
	private String direccion;
	
	public Cliente(int clienteID, String nombre, String apellido, String dNI, String telefono, String email,
			String direccion) {
		this.clienteID = clienteID;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
	}
	
	public Cliente() {}

	public int getClienteID() {
		return clienteID;
	}

	public void setClienteID(int clienteID) {
		this.clienteID = clienteID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Cliente [clienteID=" + clienteID + ", nombre=" + nombre + ", apellido=" + apellido + ", DNI=" + DNI
				+ ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + "]";
	}
}
