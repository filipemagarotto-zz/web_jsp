package br.com.webjsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection conectar() {
		
		Connection con = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_jsp", "root", "toor");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro na conexão\n " + e);
		}
		return con;
		
	}
}
