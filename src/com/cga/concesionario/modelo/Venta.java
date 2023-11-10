package com.cga.concesionario.modelo;

import java.sql.Date;

public class Venta {

	private int ventaID;
	private int vehiculoID;
	private int clienteID;
	private int empleadoID;
	private Date fechaVenta;
	private float precioFinal;
	
	public Venta(int ventaID, int vehiculoID, int clienteID, int empleadoID, Date fechaVenta, float precioFinal) {
		this.ventaID = ventaID;
		this.vehiculoID = vehiculoID;
		this.clienteID = clienteID;
		this.empleadoID = empleadoID;
		this.fechaVenta = fechaVenta;
		this.precioFinal = precioFinal;
	}
	
	public Venta() {}

	public int getVentaID() {
		return ventaID;
	}

	public void setVentaID(int ventaID) {
		this.ventaID = ventaID;
	}

	public int getVehiculoID() {
		return vehiculoID;
	}

	public void setVehiculoID(int vehiculoID) {
		this.vehiculoID = vehiculoID;
	}

	public int getClienteID() {
		return clienteID;
	}

	public void setClienteID(int clienteID) {
		this.clienteID = clienteID;
	}

	public int getEmpleadoID() {
		return empleadoID;
	}

	public void setEmpleadoID(int empleadoID) {
		this.empleadoID = empleadoID;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public float getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(float precioFinal) {
		this.precioFinal = precioFinal;
	}

	@Override
	public String toString() {
		return "Venta [ventaID=" + ventaID + ", vehiculoID=" + vehiculoID + ", clienteID=" + clienteID + ", empleadoID="
				+ empleadoID + ", fechaVenta=" + fechaVenta + ", precioFinal=" + precioFinal + "]";
	}
}
