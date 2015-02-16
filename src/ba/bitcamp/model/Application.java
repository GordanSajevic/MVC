package ba.bitcamp.model;
import java.sql.*;

public class Application {
	protected static Connection db;
	
	public static void init() throws SQLException
	{
		db = DriverManager.getConnection("jdbc:sqlite:phonebook.db");
	}
	
	protected ResultSet find(int id, String tableName)
	{
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT * FROM %s where id = '%d' ;", tableName, id);
			return 	stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
