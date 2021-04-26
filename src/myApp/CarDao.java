package myApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public enum CarDao {
	instance;

	Connection con;
	Statement stmt;

//Connection
	private CarDao() {
		try {
			System.out.println("Connection Succesfull.");
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "Passw0rd");
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Error: connecting to database");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: driver databse");
			e.printStackTrace();
		}
	}

//------------------------------------------------> @GET
	// Select all Cars
	public List<Car> getCars() {
		// Creating the List
		List<Car> cars = new ArrayList<Car>();
		try {
			System.out.println("Retrieving all the cars -> @GET");
			// SQL command to get the cars
			ResultSet rs = stmt.executeQuery("select * from CARS");
			// Storing all the cars in the list
			while (rs.next()) {
				String lcPlate = rs.getString(1);
				String make = rs.getString(2);
				String type = rs.getString(3);
				double price = rs.getDouble(4);
				double engine = rs.getDouble(5);
				int doors = rs.getInt(6);
				Car carObj = new Car(lcPlate, make, type, price, engine, doors);
				cars.add(carObj);
			}
		} catch (SQLException e) {
			System.out.println("Error: SQLException");
		}
		// return cars
		return cars;
	}

	// Select a car by licence plate
	public List<Car> getCarByLicencePlate(String plate) {
		List<Car> cars = new ArrayList<Car>();
		try {
			System.out.println("Retrieve car by its licence plate -> @GET");
			ResultSet rs = stmt.executeQuery("select * from cars where licensePlate = '" + plate + "'");
			while (rs.next()) {
				String lcPlate = rs.getString(1);
				String make = rs.getString(2);
				String type = rs.getString(3);
				double price = rs.getDouble(4);
				double engine = rs.getDouble(5);
				int doors = rs.getInt(6);
				Car carObj = new Car(lcPlate, make, type, price, engine, doors);
				cars.add(carObj);
			}
		} catch (SQLException e) {
			System.out.println("Error: SQLException");
		}
		return cars;
	}

	// Get cars by engine size
	public List<Car> getCarByEngine(double size) {
		List<Car> cars = new ArrayList<Car>();
		try {
			System.out.println("Retrieve car by its engine size -> @GET");
			ResultSet rs = stmt.executeQuery("select * from cars where engine = '" + size + "'");
			while (rs.next()) {
				String lcPlate = rs.getString(1);
				String make = rs.getString(2);
				String type = rs.getString(3);
				double price = rs.getDouble(4);
				double engine = rs.getDouble(5);
				int doors = rs.getInt(6);
				Car carObj = new Car(lcPlate, make, type, price, engine, doors);
				cars.add(carObj);
			}
		} catch (SQLException e) {
			System.out.println("Error: SQLException");
		}
		return cars;
	}

	// Get cars by make
	public List<Car> getCarByMake(String make1) {
		List<Car> cars = new ArrayList<Car>();
		try {
			System.out.println("Retrieve car by make -> @GET");
			ResultSet rs = stmt.executeQuery("select * from cars where make = '" + make1 + "'");
			while (rs.next()) {
				String lcPlate = rs.getString(1);
				String make = rs.getString(2);
				String type = rs.getString(3);
				double price = rs.getDouble(4);
				double engine = rs.getDouble(5);
				int doors = rs.getInt(6);
				Car carObj = new Car(lcPlate, make, type, price, engine, doors);
				cars.add(carObj);
			}
		} catch (SQLException e) {
			System.out.println("Error: SQLException");
		}
		return cars;
	}

//--------------------------> Insert new Car to DAO @PUT

	void createCar(Car car) {
		try {
			System.out.println("Insert new Car -> @PUT");
			stmt.executeUpdate("insert into CARS values('" + car.getLicencePlate() + "', '" + car.getMake() + "', '"
					+ car.getType() + "', " + car.getPrice() + ", " + car.getEngine() + ", "+ car.getDoors() + ")");
		} catch (SQLException e) {
			System.out.println("Error: SQLException");
		}
	}

//------------------------->Update Car @POST
	public void updateCar(Car car) {
		try {
			System.out.println("Update car -> @POST");
			// SQL Query
			stmt.executeUpdate("UPDATE cars SET make = '" + car.getMake() + "', type = '" + car.getType()
			+ "', price = '" + car.getPrice() + "', engine = '" + car.getEngine() + "', doors = '" + car.getDoors() + "' WHERE licensePlate = '"
					+ car.getLicencePlate()+"'");
		} catch (SQLException e) {
			System.out.println("Error: SQLException");
		}
	}


//----------------------->Delete Car @DELETE
	public void deleteCar(String plate) {
		try {
			System.out.println("Delete car by licence plate @DELETE");
			stmt.executeUpdate("DELETE FROM cars where licensePlate= '" + plate+"'");
		} catch (SQLException e) {
			System.out.println("Error: SQLException");
		}
	}

}
