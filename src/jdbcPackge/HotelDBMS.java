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
			String Hotels = "CREATE TABLE Hotels " + "(id INTEGER NOT NULL PRIMARY KEY, "
					+ " hotel_name VARCHAR(255) NOT NULL, " + " hotel_location VARCHAR(255),"
					+ " created_date DATE NOT NULL, " + "  updated_date DATE," + "is_Active Bit NOT NULL)";

			String Room_Type = "CREATE TABLE Room_Type " + "(id INTEGER NOT NULL PRIMARY KEY, "
					+ "  room_type_name VARCHAR(255) NOT NULL, " + " created_date DATE NOT NULL, "
					+ "  updated_date DATE," + "is_Active Bit NOT NULL)";

			String rooms = "CREATE TABLE Rooms(" + "id INTEGER Primary Key, " + "room_type_id INTEGER , "
					+ "hotel_id INTEGER , " + " FOREIGN KEY (room_type_id) REFERENCES Room_Type(id) ,"
					+ " FOREIGN KEY (hotel_id) REFERENCES Hotels(id)," + "created_date date, " + "updated_date date, "
					+ "is_Active Bit not NULL)";

			String Guests = "CREATE TABLE Guests(" + "id INTEGER Primary Key, " + " guest_name TEXT not NULL, "
					+ " guest_phone TEXT not NULL, " + " guest_payment_amount INTEGER not NULL, " + "room_id INTEGER, "
					+ "hotel_id INTEGER , " + "FOREIGN KEY (room_id) REFERENCES Rooms(id) ,"
					+ "FOREIGN KEY (hotel_id) REFERENCES Hotels(id) ," + "created_date date, " + "updated_date date, "
					+ "is_Active Bit not NULL)";

			String employeeType = "CREATE TABLE Employee_Type(" + "id INTEGER Primary Key, "
					+ " employee_type_name TEXT, " + "created_date date, " + "updated_date date, "
					+ "is_Active Bit not NULL)";

			String Employees = "CREATE TABLE Employees(" + "id INTEGER Primary Key, " + "employee_type_id INTEGER , "
					+ "room_id INTEGER , " + "FOREIGN KEY (employee_type_id) REFERENCES Employee_Type(id) ,"
					+ "FOREIGN KEY (room_id) REFERENCES Hotels(id) ," + "created_date date, " + "updated_date date, "
					+ "is_Active Bit not NULL)";

			st.executeUpdate(Hotels);
			st.executeUpdate(Room_Type);
			st.executeUpdate(rooms);
			st.executeUpdate(Guests);
			st.executeUpdate(employeeType);
			st.executeUpdate(Employees);
			

		} catch (Exception ex) {
			System.err.println(ex);

		}
	}
}