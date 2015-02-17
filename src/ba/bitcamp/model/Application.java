package ba.bitcamp.model;
import java.sql.*;

/*
* Class that enables connection with database
*/

public class Application {
	protected static Connection db;

/*
* Method initializes connection with database(name of database is hard coded) 
*/

	public static void init() throws SQLException {
		db = DriverManager.getConnection("jdbc:sqlite:BitBook.db");
	}
/*
* Method returns contact with given id.
* We use statement to communicate with database
* and return contact as ResultSet
*/
	protected static ResultSet find(int id, String tableName) {
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT * FROM %s WHERE id = '%d' ;",
					tableName, id);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

/*
* Method that inserts given values in database and saves it.
* Again we use statement to communicate with our database.
*/
	protected static boolean save(String tableName, String values) {

		Statement stmt = null;
		try {
			stmt = db.createStatement();
			String sql = String.format("INSERT INTO %s VALUES %s;", tableName, values );
			stmt.execute("begin;");
			stmt.execute(sql);
			stmt.execute("commit;");
			return true;
		} catch (SQLException e) {
			if (stmt != null) {
				try {
					stmt.execute("rollback;");
				} catch (SQLException e1) {
					System.err.println(e.getMessage());
					return false;
				}
			}
			System.err.println(e.getMessage());
			return false;
		}
		
	}
/*
* Method requests from database to print all contact in table.
* Than it returns all contacts as ResultSet
*/
	protected static ResultSet all(String tableName, String columnName)
	{
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT %s FROM %s;", columnName, tableName);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
