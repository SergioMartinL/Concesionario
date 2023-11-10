package com.cga.concesionario.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cga.concesionario.conexion.ConexionBBDD;
import com.cga.concesionario.modelo.Empleado;

public class EmpleadoImpl implements DAO<Empleado> {

	private Connection conectar() throws SQLException{
		return ConexionBBDD.conectar();
	}
	
	@Override
	public List<Empleado> listar() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		try (Statement stmt = conectar().createStatement()){
			ResultSet resultado = stmt.executeQuery("SELECT * FROM empleados");
			while (resultado.next()) {
				Empleado empleado = metEmpleado(resultado);
				empleados.add(empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleados;
	}

	@Override
	public Empleado buscarId(int Id) {
		Empleado empleado = null;
		try (PreparedStatement stmt = conectar().prepareStatement("SELECT * FROM empleados "+"WHERE EmpleadoID=?")){
			stmt.setInt(1,Id);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				empleado = metEmpleado(resultado);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleado;
	}

	@Override
	public void guardar(Empleado t) throws SQLException {
		if (buscarId(t.getEmpleadoID())==null || t.getEmpleadoID()==0) {
			PreparedStatement stmt = conectar().prepareStatement("INSERT INTO empleados (EmpleadoID, Nombre, Apellido, Cargo, Teléfono, Email) VALUES (?,?,?,?,?,?)");
			stmt.setInt(1,t.getEmpleadoID());
			stmt.setString(2,t.getNombre());
			stmt.setString(3,t.getApellido());
			stmt.setString(4,t.getCargo());
			stmt.setString(5,t.getTelefono());
			stmt.setString(6,t.getEmail());
			stmt.executeUpdate();			
		} else {
			PreparedStatement stmt = conectar().prepareStatement("UPDATE empleados SET Nombre=?, Apellido=?, Cargo=?, Teléfono=?, Email=? "+ "WHERE EmpleadoID="+t.getEmpleadoID());
			stmt.setString(1,t.getNombre());
			stmt.setString(2,t.getApellido());
			stmt.setString(3,t.getCargo());
			stmt.setString(4,t.getTelefono());
			stmt.setString(5,t.getEmail());
			stmt.executeUpdate();
		}
	}

	@Override
	public void eliminar(int id) {
		try (PreparedStatement stmt = conectar().prepareStatement("DELETE FROM empleados "+"WHERE EmpleadoID = "+id)){
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Empleado metEmpleado(ResultSet results) throws SQLException {
		Empleado empleado = new Empleado();
		empleado.setEmpleadoID(results.getInt(1));
		empleado.setNombre(results.getString(2));
		empleado.setApellido(results.getString(3));
		empleado.setCargo(results.getString(4));
		empleado.setTelefono(results.getString(5));
		empleado.setEmail(results.getString(6));

		return empleado;
	}
}
