/*
 * Brendan Howell
 * CSC-251
 * 
 * This program displays the records that were entered into the
 * DTCC database then updates the records and displays the 
 * updated records.
 */


// Needed for JDBC classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;




public class DTCCTableViewer {

	public static void main(String[] args) {

		// Create a named constant for the URL.

		final String DB_URL = "jdbc:derby:DTCC;create=true";

		try
		{
			// Create a connection to the database.
			Connection conn = DriverManager.getConnection(DB_URL);

			// view data
			viewStudentsTable(conn);

			// modify record
			modStudentsTable(conn);

			//view modified table
			viewStudentsTable(conn);

			// Close the connection.
			conn.close();

		}
		catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	} //end main




	public static void viewStudentsTable(Connection conn){
		//create var for result set
		ResultSet resultset = null;

		try
		{
			// Get a Statement object.
			Statement stmt = conn.createStatement();

			// View the table.
			resultset = stmt.executeQuery("SELECT * FROM Students");

			// process results
			ResultSetMetaData metaData = resultset.getMetaData();

			System.out.println("Data from Students Table:");

			int numberOfColumns = metaData.getColumnCount();
			// for loop to field names
			for (int i = 1; i <= numberOfColumns; i++){
				System.out.printf("%s\t", metaData.getColumnName(i));
			}
			System.out.println();

			// while loop to display data
			while (resultset.next()){
				for (int i = 1; i <= numberOfColumns; i++){
					System.out.printf("%s\t", resultset.getObject(i));
				}
				System.out.println();
			}

		}
		catch (SQLException ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}

	} //end viewStudentTable



	public static void modStudentsTable(Connection conn){
		//create var for result set
		ResultSet resultset = null;

		try
		{
			// Get a Statement object.
			Statement stmt = conn.createStatement();

			// update record
			stmt.executeUpdate("UPDATE Students SET PlanOfStudy = 'Web Technologies' WHERE Student_Id = '890101030'");
			stmt.executeUpdate("UPDATE Students Set FirstName = 'Elite', GPA = 4.0 Where Student_Id = '807223230'");
			//stmt.executeUpdate("UPDATE Students Set GPA = 4.0 Where Student_Id = '807223230'");
			
			String sql = "DELETE FROM Students WHERE Student_Id = '899090111'";
			stmt.executeUpdate(sql);
			System.out.println("Record(s) updated");

		}
		catch (SQLException ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	} //end modStudentsTable

} //end class
