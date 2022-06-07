package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {
	public Connection conectaDataBase(){ // Faz conexão com o banco de dadaos
		try {
			String url="jdbc:mysql://localhost:3306/coisalinda";
			String user="root";
			String password="admin123";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			
			return connection;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
		
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			return null;
		}
	}
}
