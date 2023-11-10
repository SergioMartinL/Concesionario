package com.cga.concesionario.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.cga.concesionario.conexion.ConexionBBDD;
import com.cga.concesionario.modelo.Cliente;

public class ClienteImpl implements DAO<Cliente> {

	private Connection conectar() throws SQLException{
		return ConexionBBDD.conectar();
	}
	
	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try (Statement stmt = conectar().createStatement()){
			ResultSet resultado = stmt.executeQuery("SELECT * FROM clientes");
			while (resultado.next()) {
				Cliente cliente = metCliente(resultado);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public Cliente buscarId(int Id) {
		Cliente cliente = null;
		try (PreparedStatement stmt = conectar().prepareStatement("SELECT * FROM clientes "+"WHERE ClienteID=?")){
			stmt.setInt(1,Id);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				cliente = metCliente(resultado);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public void guardar(Cliente t) throws SQLException {
		
		boolean existsDNI = comprobarDNI(t);
		
		if (!existsDNI && validarDNI(t.getDNI())) {
			if (buscarId(t.getClienteID())==null || t.getClienteID()==0) {
				PreparedStatement stmt = conectar().prepareStatement("INSERT INTO clientes (ClienteID, Nombre, Apellido, DNI, Teléfono, Email, Direccion) VALUES (?,?,?,?,?,?,?)");
				stmt.setInt(1,t.getClienteID());
				stmt.setString(2,t.getNombre());
				stmt.setString(3,t.getApellido());
				stmt.setString(4,t.getDNI());
				stmt.setString(5,t.getTelefono());
				stmt.setString(6,t.getEmail());
				stmt.setString(7,t.getDireccion());
				stmt.executeUpdate();			
			} else {
				PreparedStatement stmt = conectar().prepareStatement("UPDATE clientes SET Nombre=?, Apellido=?, DNI=?, Teléfono=?, Email=?, Direccion=? "+ "WHERE ClienteID="+t.getClienteID());
				stmt.setString(1,t.getNombre());
				stmt.setString(2,t.getApellido());
				stmt.setString(3,t.getDNI());
				stmt.setString(4,t.getTelefono());
				stmt.setString(5,t.getEmail());
				stmt.setString(6,t.getDireccion());
			}			
		} else {
			System.err.println("EL DNI DEL CLIENTE YA EXISTE O NO ES UN DNI VALIDO");
		}
	}

	@Override
	public void eliminar(int id) {
		try (PreparedStatement stmt = conectar().prepareStatement("DELETE FROM clientes "+"WHERE ClienteID = "+id)){
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Cliente metCliente(ResultSet results) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setClienteID(results.getInt(1));
		cliente.setNombre(results.getString(2));
		cliente.setApellido(results.getString(3));
		cliente.setDNI(results.getString(4));
		cliente.setTelefono(results.getString(5));
		cliente.setEmail(results.getString(6));
		cliente.setDireccion(results.getString(7));
		
		return cliente;
	}
	
	private boolean comprobarDNI(Cliente cliente) {
		List<Cliente> lista = listar();
		for (int i = 0; i <= lista.size()-1 ; i++) {
			if (lista.get(i).getDNI().equals(cliente.getDNI())) {
				return true;
			}
		}
		return false;
	}
	

	public static boolean validarDNI(String dni) {
		Pattern regexp = Pattern.compile("[0-9]{8}[A-Z]");
		String digitoControl = "TRWAGMYFPDXBNJZSQVHLCKE";
		String[] invalidos = new String[] { "00000000T", "00000001R", "99999999R" };
		return Arrays.binarySearch(invalidos, dni) < 0
				&& regexp.matcher(dni).matches()
				&& dni.charAt(8) == digitoControl.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
	}
}
