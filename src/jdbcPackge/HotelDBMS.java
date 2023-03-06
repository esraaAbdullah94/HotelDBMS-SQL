package jdbcPackge;

import java.sql.*;
import java.util.Scanner;

public class HotelDBMS {
	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=HotelDBMS;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		//User name&password
		String user = "sa";
		String pass = "root";

		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			// Get user input for number of hotels to add
						System.out.print("How many hotels do you want to add? ");
						Scanner scanner = new Scanner(System.in);
						int numHotels = scanner.nextInt();

						// Loop through each hotel and get details from user
						for (int i = 1; i <= numHotels; i++) {
						    System.out.println("Enter details for hotel " + i + ":");

						    // Get hotel details from user
						    System.out.print("Enter hotel's id: ");
						    int hotelId = scanner.nextInt();
						    System.out.print("Enter hotel's name: ");
						    String hotelName = scanner.next();
						    System.out.print("Hotel Location: ");
						    String hotelLocation = scanner.next();
						    System.out.print("Created Date (yyyy-mm-dd): ");
						    String createdDate = scanner.next();
						    System.out.print("Updated Date (yyyy-mm-dd, leave blank for none): ");
						    String updatedDate = scanner.nextLine();
						    if (updatedDate.isEmpty()) {
						        updatedDate = "NULL";
						    } else {
						        updatedDate = "'" + updatedDate + "'";
						    }
						    System.out.print("Is Active (0 or 1): ");
						    String isActive = scanner.next();

						    // Create and execute INSERT statement
						    String insertHotel = "INSERT INTO Hotels (id, hotel_name, hotel_location, created_date, updated_date, is_Active)"
						            + " VALUES (" + hotelId + ", '" + hotelName + "', '" + hotelLocation + "', '" + createdDate + "', " + updatedDate + ", " + isActive + ")";
						    st.executeUpdate(insertHotel);
						}


						// Print 10 hotels
						System.out.println("Printing 10 hotels:");
						String selectHotels = "SELECT TOP 10 * FROM Hotels";
						ResultSet rs = st.executeQuery(selectHotels);
						while (rs.next()) {
						    int hotelId = rs.getInt("id");
						    String hotelName = rs.getString("hotel_name");
						    String hotelLocation = rs.getString("hotel_location");
						    String createdDate = rs.getString("created_date");
						    String updatedDate = rs.getString("updated_date");
						    String isActive = rs.getString("is_Active");
						    System.out.println("Hotel " + hotelId + ": " + hotelName + " (" + hotelLocation + "), Created: " + createdDate + ", Updated: " + updatedDate + ", Active: " + isActive);
						}
						
						// Print 10 hotels where is_Active is false
						System.out.println("Printing 10 inactive hotels:");
						String selectInactiveHotels = "SELECT TOP 10 * FROM Hotels WHERE is_Active = 0";
						ResultSet rs2 = st.executeQuery(selectInactiveHotels);
						while (rs2.next()) {
						    int hotelId = rs2.getInt("id");
						    String hotelName = rs2.getString("hotel_name");
						    String hotelLocation = rs2.getString("hotel_location");
						    String createdDate = rs2.getString("created_date");
						    String updatedDate = rs2.getString("updated_date");
						    String isActive = rs2.getString("is_Active");
						    System.out.println("Hotel " + hotelId + ": " + hotelName + " (" + hotelLocation + "), Created: " + createdDate + ", Updated: " + updatedDate + ", Active: " + isActive);
						}

						// Close resources
						rs.close();
						rs2.close();
						st.close();
						con.close();
						scanner.close();


			


		} catch (Exception ex) {
			System.err.println(ex);

		}
	}
}