package com.cga.concesionario.modelo;

public class Empleado {

	private int empleadoID;
	private String nombre;
	private String apellido;
	private String cargo;
	private String telefono;
	private String email;
	
	public Empleado(int empleadoID, String nombre, String apellido, String cargo, String telefono, String email) {
		this.empleadoID = empleadoID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
		this.telefono = telefono;
		this.email = email;
	}
	
	public Empleado() {}

	public int getEmpleadoID() {
		return empleadoID;
	}

	public void setEmpleadoID(int empleadoID) {
		this.empleadoID = empleadoID;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	@Override
	public String toString() {
		return "Empleado [empleadoID=" + empleadoID + ", nombre=" + nombre + ", apellido=" + apellido + ", cargo="
				+ cargo + ", telefono=" + telefono + ", email=" + email + "]";
	}
}
