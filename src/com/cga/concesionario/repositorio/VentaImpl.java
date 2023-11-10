package com.cga.concesionario.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cga.concesionario.conexion.ConexionBBDD;
import com.cga.concesionario.modelo.Venta;

public class VentaImpl implements DAO<Venta> { 

	private Connection conectar() throws SQLException{
		return ConexionBBDD.conectar();
	}
	
	@Override
	public List<Venta> listar() {
		List<Venta> ventas = new ArrayList<Venta>();
		try (Statement stmt = conectar().createStatement()){
			ResultSet resultado = stmt.executeQuery("SELECT * FROM ventas");
			while (resultado.next()) {
				Venta venta = metVenta(resultado);
				ventas.add(venta);
			}
		} catch (SQLException e) {
		
		}
		return ventas;
	}

	@Override
	public Venta buscarId(int Id) {
		Venta venta = null;
		try (PreparedStatement stmt = conectar().prepareStatement("SELECT * FROM ventas "+"WHERE VentaID=?")){
			stmt.setInt(1,Id);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				venta = metVenta(resultado);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venta;
	}

	@Override
	public void guardar(Venta t) throws SQLException {
		if (buscarId(t.getVentaID())==null || t.getVentaID()==0) {
			PreparedStatement stmt = conectar().prepareStatement("INSERT INTO ventas (VentaID, VehiculoID, ClienteID, EmpleadoID, FechaVenta, PrecioFinal) VALUES (?,?,?,?,?,?)");
			stmt.setInt(1,t.getVentaID());
			stmt.setInt(2,t.getVehiculoID());
			stmt.setInt(3,t.getClienteID());
			stmt.setInt(4,t.getEmpleadoID());
			stmt.setDate(5, t.getFechaVenta());
			stmt.setFloat(6,t.getPrecioFinal());
			stmt.executeUpdate();			
		} else {
			PreparedStatement stmt = conectar().prepareStatement("UPDATE ventas SET VehiculoID=?, ClienteID=?, EmpleadoID=?, FechaVenta=?, PrecioFinal=? "+ "WHERE VentaID="+t.getVentaID());
			stmt.setInt(1,t.getVehiculoID());
			stmt.setInt(2,t.getClienteID());
			stmt.setInt(3,t.getEmpleadoID());
			stmt.setDate(4, t.getFechaVenta());
			stmt.setFloat(5,t.getPrecioFinal());
			stmt.executeUpdate();	
		}
	}

	@Override
	public void eliminar(int id) {
		try (PreparedStatement stmt = conectar().prepareStatement("DELETE FROM ventas "+"WHERE VentaID = "+id)){
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Venta metVenta(ResultSet results) throws SQLException {
		Venta venta = new Venta();
		venta.setVentaID(results.getInt(1));
		venta.setVehiculoID(results.getInt(2));
		venta.setClienteID(results.getInt(3));
		venta.setEmpleadoID(results.getInt(4));
		venta.setFechaVenta(results.getDate(5));
		venta.setPrecioFinal(results.getFloat(6));

		return venta;
	}
}
