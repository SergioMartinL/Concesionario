package com.cga.concesionario.modelo;

public class Vehiculo {

	private int vehiculoID;
	private String marca;
	private String modelo;
	private int año;
	private float precio;
	private String tipo;
	private int kilometraje;
	private String matricula;
	
	public Vehiculo(int vehiculoID, String marca, String modelo, int año, float precio, String tipo, int kilometraje, String matricula) {
		this.vehiculoID = vehiculoID;
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
		this.precio = precio;
		this.tipo = tipo;
		this.kilometraje = kilometraje;
		this.matricula = matricula;
	}

	public Vehiculo() {}

	public int getVehiculoID() {
		return vehiculoID;
	}

	public void setVehiculoID(int vehiculoID) {
		this.vehiculoID = vehiculoID;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Vehiculo [vehiculoID=" + vehiculoID + ", marca=" + marca + ", modelo=" + modelo + ", año=" + año
				+ ", precio=" + precio + ", tipo=" + tipo + ", kilometraje=" + kilometraje + ", matricula=" + matricula
				+ "]";
	}
	
	
}
