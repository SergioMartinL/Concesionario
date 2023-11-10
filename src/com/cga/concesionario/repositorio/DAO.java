package com.cga.concesionario.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T> {
	
	//Método listar devuelve una lista
		List<T> listar();
		//Método de búsqueda por ID
		T buscarId(int Id);
		//Método de guardar
		void guardar(T t) throws SQLException;
		//Método de eliminación por ID
		void eliminar(int id);
}
