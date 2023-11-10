package com.cga.concesionario.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cga.concesionario.conexion.ConexionBBDD;
import com.cga.concesionario.modelo.Vehiculo;

public class VehiculoImpl implements DAO<Vehiculo> {

	private Connection conectar() throws SQLException{
		return ConexionBBDD.conectar();
	}
	
	@Override
	public List<Vehiculo> listar() {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try (Statement stmt = conectar().createStatement()){
			ResultSet resultado = stmt.executeQuery("SELECT * FROM vehiculos");
			while (resultado.next()) {
				Vehiculo vehiculo = metVehiculo(resultado);
				vehiculos.add(vehiculo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehiculos;
	}

	@Override
	public Vehiculo buscarId(int id) {
		Vehiculo vehiculo = null;
		try (PreparedStatement stmt = conectar().prepareStatement("SELECT * FROM vehiculos "+"WHERE VehiculoID=?")){
			stmt.setInt(1,id);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				vehiculo = metVehiculo(resultado);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehiculo;
	}

	@Override
	public void guardar(Vehiculo t) throws SQLException {
		if (buscarId(t.getVehiculoID())==null || t.getVehiculoID()==0) {
			PreparedStatement stmt = conectar().prepareStatement("INSERT INTO vehiculos (VehiculoID, Marca, Modelo, Año, Precio, Tipo, Kilometraje, Matricula) VALUES (?,?,?,?,?,?,?,?)");
			stmt.setInt(1,t.getVehiculoID());
			stmt.setString(2,t.getMarca());
			stmt.setString(3,t.getModelo());
			stmt.setInt(4,t.getAño());
			stmt.setFloat(5,t.getPrecio());
			stmt.setString(6,t.getTipo());
			stmt.setInt(7,t.getKilometraje());
			stmt.setString(8,t.getMatricula());
			stmt.executeUpdate();			
		} else {
			PreparedStatement stmt = conectar().prepareStatement("UPDATE vehiculos SET Marca=?, Modelo=?, Año=?, Precio=?, Tipo=?, Kilometraje=?, Matricula=? "+ "WHERE VehiculoID="+t.getVehiculoID());
			stmt.setString(1,t.getMarca());
			stmt.setString(2,t.getModelo());
			stmt.setInt(3,t.getAño());
			stmt.setFloat(4,t.getPrecio());
			stmt.setString(5,t.getTipo());
			stmt.setInt(6,t.getKilometraje());
			stmt.setString(7,t.getMatricula());
		}
	}

	@Override
	public void eliminar(int id) {
		try (PreparedStatement stmt = conectar().prepareStatement("DELETE FROM vehiculos "+"WHERE VehiculoID = "+id)){
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Vehiculo metVehiculo(ResultSet results) throws SQLException {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setVehiculoID(results.getInt(1));
		vehiculo.setMarca(results.getString(2));
		vehiculo.setModelo(results.getString(3));
		vehiculo.setAño(results.getInt(4));
		vehiculo.setPrecio(results.getFloat(5));
		vehiculo.setTipo(results.getString(6));
		vehiculo.setKilometraje(results.getInt(7));
		vehiculo.setMatricula(results.getString(8));
		
		return vehiculo;
	}
}
