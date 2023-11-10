package com.cga.concesionario;

import java.sql.SQLException;

import com.cga.concesionario.modelo.Cliente;
import com.cga.concesionario.repositorio.ClienteImpl;
import com.cga.concesionario.repositorio.EmpleadoImpl;
import com.cga.concesionario.repositorio.VehiculoImpl;
import com.cga.concesionario.repositorio.VentaImpl;

public class Principal {

	public static void main(String[] args) throws SQLException {
		ClienteImpl c = new ClienteImpl();
		VentaImpl vent = new VentaImpl();
		EmpleadoImpl e = new EmpleadoImpl();
		VehiculoImpl v = new VehiculoImpl();
		System.out.println(c.listar());
		System.out.println(vent.listar());
		System.out.println(e.listar());
		System.out.println(v.listar());
		Cliente clienteNuevo = new Cliente(0, "Prueba2", "Prueba2", "21136560C", "666666666", "prueba@gmail.com", "sucasa");
		c.guardar(clienteNuevo);
		System.out.println(c.listar());
	}
}
