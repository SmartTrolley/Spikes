/**
 * 
 */
package alasdairsjdbcspike;

import java.sql.SQLException;

/**
 * @author Alasdair
 *
 */
public class SqlExceptionHandler {
	
	/**
	 * prints meaningful error messages for SQL exceptions
	 * @param ex
	 */
	public static void print(SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		System.out.println("SQLState: " + ex.getSQLState());
		System.out.println("VendorError: " + ex.getErrorCode());
	}
}
