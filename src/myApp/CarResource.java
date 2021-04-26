package myApp;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/car")
public class CarResource {

//-->@GET All cars
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Car> getCars() {
		return CarDao.instance.getCars();
	}

//-->@GET car by license plate
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{licensePlate}")
	public List<Car> getCarByLicencePlate(@PathParam("licensePlate") String licensePlate) {
		return CarDao.instance.getCarByLicencePlate((licensePlate));
	}

//-->@GET car by engine size
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/size/{size}")
	public List<Car> getCarByEngine(@PathParam("size") String size) {
		return CarDao.instance.getCarByEngine(Double.parseDouble(size));
	}

//-->@GET car by make
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/make/{make}")
	public List<Car> getCarByMake(@PathParam("make") String make) {
		return CarDao.instance.getCarByMake(make);
	}

//-->@PUT new car
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{licensePlate}")
	public void createCar(@PathParam("licensePlate") String lsplate, @FormParam("make") String make,
			@FormParam("type") String type, @FormParam("price") double price, @FormParam("engine") double engine,
			@FormParam("doors") int doors, @Context HttpServletResponse servletRes) throws IOException {

		Car car = new Car();
		car.setLicencePlate(lsplate);
		car.setMake(make);
		car.setType(type);
		car.setPrice(price);
		car.setEngine(engine);
		car.setDoors(doors);

		CarDao.instance.createCar(car);
	}

//-->@POST update car
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{licensePlate}")
	public void updateCar(@PathParam("licensePlate") String lsplate, @FormParam("make") String make,
			@FormParam("type") String type, @FormParam("price") double price, @FormParam("engine") double engine,
			@FormParam("doors") int doors, @Context HttpServletResponse servletRes) throws IOException {

		Car car = new Car();
		car.setLicencePlate(lsplate);
		car.setMake(make);
		car.setType(type);
		car.setPrice(price);
		car.setEngine(engine);
		car.setDoors(doors);

		CarDao.instance.updateCar(car);
	}

//-->@DELETE car
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("{licensePlate}")
	public void deleteFruit(@PathParam("licensePlate") String plate) {
		
		CarDao.instance.deleteCar(plate);
	}
}
