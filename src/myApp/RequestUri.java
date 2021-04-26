package myApp;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class RequestUri {

	List<Car> carList = new ArrayList<Car>();;

	public List<Car> returnArray() {
		return carList;
	}

//---> URI @PUT
	public void putUri(String licensePlate, String make, String type, String price, String engine, String doors) {
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/A00252699KarolisV/rest/car/" + licensePlate).build();
			// System.out.println(uri.toString());
			HttpPut httpPut = new HttpPut(uri);
			httpPut.setHeader("Accept", "text/html");
			CloseableHttpClient httpClient = HttpClients.createDefault();

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("licensePlate", licensePlate));
			nameValuePairs.add(new BasicNameValuePair("make", make));
			nameValuePairs.add(new BasicNameValuePair("type", type));
			nameValuePairs.add(new BasicNameValuePair("price", price));
			nameValuePairs.add(new BasicNameValuePair("engine", engine));
			nameValuePairs.add(new BasicNameValuePair("doors", doors));
			httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			CloseableHttpResponse response = httpClient.execute(httpPut);
			System.out.println("Client @PUT");

			response.close();
		} catch (Exception e) {
			System.out.println("Error: putUri Exception");
		}
	}

//---> URI @POST
	public void postUri(String licensePlate, String make, String type, String price, String engine, String doors) {
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/A00252699KarolisV/rest/car/" + licensePlate).build();
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("Accept", "text/html");
			CloseableHttpClient httpClient = HttpClients.createDefault();

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("licensePlate", licensePlate));
			nameValuePairs.add(new BasicNameValuePair("make", make));
			nameValuePairs.add(new BasicNameValuePair("type", type));
			nameValuePairs.add(new BasicNameValuePair("price", price));
			nameValuePairs.add(new BasicNameValuePair("engine", engine));
			nameValuePairs.add(new BasicNameValuePair("doors", doors));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			CloseableHttpResponse response = httpClient.execute(httpPost);
			System.out.println("Client @POST");

			response.close();
		} catch (Exception e) {
			System.out.println("Error: postUri Exception");
		}
	}


//---> Delete 
	public void deleteUri(String licensePlate) {
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/A00252699KarolisV/rest/car/" + licensePlate).build();
			HttpDelete httpDelete = new HttpDelete(uri);
			httpDelete.setHeader("Accept", "text/html");

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(httpDelete);
			System.out.println("Client @Delete");

			response.close();
		} catch (Exception e) {
			System.out.println("Error: deleteUri Exception");
		}
	}

//---> Uri @GET
	// get all
	public void getAllUri() {
		carList.clear();
		try {
			// URI link
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/A00252699KarolisV/rest/car").build();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			String text = EntityUtils.toString(entity);
	
			// Parse the xml file
			carList = new ParseCar().doParseBooks(text);
			System.out.println("Client @GET");
//			for(int i = 0; i< carList.size(); i++) {
//				System.out.println(carList.get(i).getLicencePlate());
//			}
			response.close();
		} catch (Exception e) {
			System.out.println("Error: getAllUri Exception");
		}
	}

	//get by license plate
	public void byLicensePlateUri(String plate) {
		carList.clear();
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/A00252699KarolisV/rest/car/" + plate).build();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			String text = EntityUtils.toString(entity);

			carList = new ParseCar().doParseBooks(text);
			System.out.println("Client @GET by plate");

			response.close();
		} catch (Exception e) {
			System.out.println("Error: get by plate Exception");
		}
	}

	// get by engine size
	public void bySizeUri(String size) {
		carList.clear();
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/A00252699KarolisV/rest/car/size/" + size).build();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			String text = EntityUtils.toString(entity);

			carList = new ParseCar().doParseBooks(text);
			System.out.println("Client Get by size.");

			response.close();
		} catch (Exception e) {
			System.out.println("Error: get by size Exception");
		}
	}

	// get by make of the car
	public void byMakeUri(String make) {
		carList.clear();
		try {

			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/A00252699KarolisV/rest/car/make/" + make).build();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			String text = EntityUtils.toString(entity);

			carList = new ParseCar().doParseBooks(text);
			System.out.println("Client Get by make.");

			response.close();
		} catch (Exception e) {
			System.out.println("Error: get by make Exception");
		}
	}
}

