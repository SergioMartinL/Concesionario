package com.cga.concesionario.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

	private static String url = "jdbc:mysql://localhost:3306/concesionario";
	private static String user = "root";
	private static String password = "CGA2023";
	private static Connection con;
	
	public static Connection conectar() {
		
		if (con == null) {
			try {
				con = DriverManager.getConnection(url, user, password);				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return con;
	}
}
