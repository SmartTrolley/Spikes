package alasdairsjdbcspike;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlConnection {

	//Essential properties for creating the connection
	private String ip;
	private String userName;
	private String password;

	//Class compiles this to adhere to the standard
	private String url;

	private Connection connection;

	//constructor sets up the properties for the connection 
	public SqlConnection(String ip,String userName,String password){
		
		try {
			Class.forName("java.sql.DriverManager");
		} catch (ClassNotFoundException e) {
			System.out.println("couldnt launch sql driver");
		}

		//set essential properties
		this.ip = ip;
		this.userName = userName;
		this.password = password;

		compileUrl();
	}


	/**
	 * 
	 * Executes SQL query that returns the information for a particular product (by name)
	 * 
	 * @param productName
	 * @return resultSet
	 */
	public Product getProductByName(String productName){

		//TODO: Genericize to function for other fields other than product name

		Product product = new Product();

		openConnection();

		String query = "SELECT * FROM products WHERE Name = '" + productName+"';";

		try {
			
			ResultSet results = sendQuery(query);
			
			while (results.next()) {
				//get id
				product.setId(results.getInt("ProductID"));

				// get Name
				product.setName(results.getString("Name"));

				// get Image
				product.setImage(results.getString("Image"));

				// get Price
				product.setPrice(results.getFloat("Price"));
			}
			
		} catch (SQLException ex) {
			System.out.println("Product could not be found");
			SqlExceptionHandler.print(ex);
		}
		
		closeConnection();
		return product;
	}


	
	/**
	 * Send a query to the predefined database and returns the results returned by the server
	 * 
	 * @param query
	 * @return results
	 * @throws SQLException
	 */
	private ResultSet sendQuery(String query) throws SQLException{
		
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
		return results;
	}

	

	/**
	 * Opens a connection (if not already open) for sending mysql queries to the database
	 * @throws SQLException
	 */
	private void openConnection(){	

		try{
			connection = null;
			connection = DriverManager.getConnection(url, userName,password);
		} catch (SQLException ex) {
			
			System.out.println("Broken url");
			SqlExceptionHandler.print(ex);
			
		}
	}


	/**
	 * provides public access to close the connection
	 * @throws SQLException
	 */
	private void closeConnection(){
		try {
			connection.close();
		} catch (SQLException ex) {
			SqlExceptionHandler.print(ex);
		}
	}

	private void compileUrl() {
		//construct the url assuming use of mysql and the standard port.
		url = "jdbc:mysql://" + ip  + "/" + userName + "?";
	}
}
