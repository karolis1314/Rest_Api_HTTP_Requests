package myApp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="car")

@XmlType(propOrder= {"licencePlate", "make", "type", "price", "engine", "doors"})

public class Car {
	
	private String licencePlate;
	private String make;
	private String type;
	private double price;
	private double engine;
	private int doors;
	
	public Car() {}
	
	public Car(String lp, String make, String type, double price, double engine, int doors) {
		this.licencePlate = lp;
		this.make = make;
		this.type = type;
		this.price = price;
		this.engine = engine;
		this.doors = doors;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getEngine() {
		return engine;
	}

	public void setEngine(double engine) {
		this.engine = engine;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

}
