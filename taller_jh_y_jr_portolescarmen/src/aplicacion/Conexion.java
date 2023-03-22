package aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

	private static final String USER="root";
	private static final String PASSWORD="1234";
	
	public static Connection getMySQLConexion() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientes", USER, PASSWORD);
			return conn;
		}catch(ClassNotFoundException | SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
		
	}
}
