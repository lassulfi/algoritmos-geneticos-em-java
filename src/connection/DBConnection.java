package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection conector() {
		Connection connection = null;
		String driver = "com.mysql.cj.jdbc.Driver";

		String url = "jdbc:mysql://localhost:3390/products?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String password = "root";

		try {
			Class.forName(driver);
			Properties info = new Properties();
			info.put("user", user);
			info.put("password", password);
			connection = DriverManager.getConnection(url, info);
		} catch (SQLException e) {
			System.out.println("Erro no driver de conexão: " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Erro na classe do driver de conexão: " + e.getMessage());
			e.printStackTrace();
		}

		return connection;
	}

}
