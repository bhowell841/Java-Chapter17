/*
 * Brendan Howell
 * CSC-151
 * 3/1/16
 * 
 * Program creates a database called DTCC, drops tables in the 
 * database and create a table with records inserted.
 *  
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateDTCC {

	public static void main(String[] args) {
		// create a named constant for URL
		final String DTCC_URL = "jdbc:derby:DTCC; create=true";

		try{
			//connection
			Connection conn = DriverManager.getConnection(DTCC_URL);
			System.out.println("Connection successful");

			// drop table
			dropTable(conn);


			// build table and insert records
			BuildStudentTable(conn);

			conn.close();
			System.out.println("Connection closed");

		} //end try

		catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());					
		} //end catch

	} // end main


	/*
	 * Method to build and insert records
	 */
	public static void BuildStudentTable(Connection conn){

		try{
			// create a statement object
			Statement stmt = conn.createStatement();

			// create table
			stmt.execute("CREATE TABLE Students (Student_Id Char(10) NOT NULL PRIMARY KEY, LastName Char(20) , FirstName Char(15), PlanOfStudy Char(20), GPA Double)");

			System.out.println("Students table created.");

			// insert records
			stmt.execute("INSERT INTO Students VALUES (" 
					+ " '899090111'," 
					+ " 'Rothlisberger', " 
					+ " 'Ben', " 
					+ " 'CIT', " 
					+ " 3.7)");
			
			stmt.execute("INSERT INTO Students VALUES ("
					+ "'129020202', "
					+ "'Manning', "
					+ "'Peyton', "
					+ "'Computer Programming', "
					+ "3.8)");	
			stmt.execute("INSERT INTO Students VALUES ("
					+ "'890101030', "
					+ "'Brady', "
					+ "'Thomas', "
					+ "'Accounting', "
					+ "3.4)");
			stmt.execute("INSERT INTO Students VALUES ("
					+ "'980191919', "
					+ "'Rodgers', "
					+ "'Aaron', "
					+ "'Networking', "
					+ "3.2)");
			stmt.execute("INSERT INTO Students VALUES ("
					+ "'807223230', "
					+ "'Manning', "
					+ "'Eli', "
					+ "'Securities', "
					+ "3.7)");

			System.out.println("Records updated.");

		} //end try

		catch (Exception ex){
			System.out.println("Error: Table did not insert records");
		} //end catch

	} //end BuildStudentTable method



	/*
	 * Method to drop Student table
	 */
	public static void dropTable(Connection conn){

		try{
			Statement stmt = conn.createStatement();

			stmt.execute("DROP TABLE Students");
			System.out.println("Table Deleted");	
		}

		catch (SQLException ex){
			System.out.println("Error: " + ex.getMessage());
		}

	} //end method


} //end class
