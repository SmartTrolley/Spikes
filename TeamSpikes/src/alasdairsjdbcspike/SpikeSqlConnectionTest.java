/**
 * 
 */
package alasdairsjdbcspike;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alasdair
 * 
 * Test case for testing the sql spike
 * 
 * This was NOT Test Driven!
 *
 */
public class SpikeSqlConnectionTest {

	private SqlConnection productsDatabase;
	private String ip = "79.170.44.157", userName = "cl36-st", password = "Smarttrolley";


	@Test
	public void productByNameTest() {
		
		productsDatabase = new SqlConnection(ip, userName, password);
		
		// retrieve results from server
		Product product = productsDatabase.getProductByName("Ariel");

		assertEquals(product.getId(), 1);

	}
}
