/*
 * Brendan Howell
 * CSC-151
 * 
 *  Needed for JDBC classes
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *  
 * Program creates a database and drop tables in the 
 * database and create a table with records
 * 
 */

public class CreateCoffeeDBStudent {

	public static void main(String[] args) {
		// create a named constant for URl
		final String DB_URL = "jdbc:derby:CoffeeDBStudent; create=true";
		
		try{
			//connection
			Connection conn = DriverManager.getConnection(DB_URL);
			System.out.println("Connection successful");
			
			// drop table
			dropTable(conn);
			
			
			// build table and insert records
			BuildCoffeeTable(conn);
			
			conn.close();
			System.out.println("Connection closed");
			
		}
		
		catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}

	} //end main
	
	
	
	
	
	/*
	 * Method to build and insert records
	 */
	public static void BuildCoffeeTable( Connection conn){
		
		try{
			// create a statement object
			Statement stmt = conn.createStatement();
			
			// create table
			stmt.execute("CREATE TABLE Coffee(" +
			"Description Char(25), " +
			"ProdNum Char(10) NOT NULL PRIMARY KEY, " +
			"Price DOUBLE " +
			")");
			
			System.out.println("Coffee table created.");
			
			// insert records
			stmt.execute("INSERT INTO Coffee VALUES (" + //caps is SQL language
					" 'Bolivian Dark', " +
					" '14-002', " +
					"8.95)");
			
			stmt.execute("INSERT INTO Coffee VALUES (" + 
					" 'Jamaican Blue Mt', " +
					" '15-002', " +
					"50.95)");	
			System.out.println("Records updated.");
			
		} //end try
		
		catch (Exception ex){
			System.out.println("Error: Table did not insert records");
		} //end catch
		
	} //end method
	
	
	
	
	/*
	 * Method to drop Coffee table
	 */
	public static void dropTable(Connection conn){
		
		try{
			Statement stmt = conn.createStatement();
			
			stmt.execute("DROP TABLE Coffee");
			System.out.println("Table Deleted");	
		}
		
		catch (SQLException ex){
			System.out.println("Error: " + ex.getMessage());
		}
		
	} //end method

} //end class