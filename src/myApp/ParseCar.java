package myApp;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParseCar {
	boolean inCars = false;
	boolean inCar = false;
	
	boolean inLicensePlate = false;
	boolean inMake = false;
	boolean inType = false;
	boolean inPrice = false;
	boolean inEngine = false;
	boolean inDoors = false;

	Car currentBook;
	List<Car> currentBookList;

	public List<Car> doParseBooks(String s) throws Exception {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser pullParser = factory.newPullParser();
		pullParser.setInput(new StringReader(s));
		processDocument(pullParser);
		return currentBookList;
	}

	public void processDocument(XmlPullParser pullParser) throws XmlPullParserException, IOException {
		int eventType = pullParser.getEventType();
		do {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("Start Document");
			} else if (eventType == XmlPullParser.END_DOCUMENT) {
				System.out.println("End Document");
			} else if (eventType == XmlPullParser.START_TAG) {
				processStartElement(pullParser);
			} else if (eventType == XmlPullParser.END_TAG) {
				processEndElement(pullParser);
			} else if (eventType == XmlPullParser.TEXT) {
				processText(pullParser);
			}
			eventType = pullParser.next();
		} while (eventType != XmlPullParser.END_DOCUMENT);
	}

	public void processStartElement(XmlPullParser event) {
		String name = event.getName();
		if (name.equals("cars")) {
			inCars = true;
			currentBookList = new ArrayList<Car>();
		} else if (name.equals("car")) {
			inCar = true;
			currentBook = new Car();
		} else if (name.equals("licencePlate")) {
			inLicensePlate = true;
		} else if (name.equals("make")) {
			inMake = true;
		} else if (name.equals("type")) {
			inType = true;
		} else if (name.equals("price")) {
			inPrice = true;
		} else if (name.equals("engine")) {
			inEngine = true;
		} else if (name.equals("doors")) {
			inDoors = true;
		}
	}

	public void processEndElement(XmlPullParser event) {
		String name = event.getName();
		if (name.equals("cars")) {
			inCars = false;
		} else if (name.equals("car")) {
			inCar = false;
			currentBookList.add(currentBook);
		} else if (name.equals("licencePlate")) {
			inLicensePlate = false;
		} else if (name.equals("make")) {
			inMake = false;
		} else if (name.equals("type")) {
			inType = false;
		} else if (name.equals("price")) {
			inPrice = false;
		} else if (name.equals("engine")) {
			inEngine = false;
		} else if (name.equals("doors")) {
			inDoors = false;
		}
	}

	public void processText(XmlPullParser event) throws XmlPullParserException {
		if (inLicensePlate) {
			String s = event.getText();
			currentBook.setLicencePlate(s);
		}
		if (inMake) {
			String s = event.getText();
			currentBook.setMake(s);
		}
		if (inType) {
			String s = event.getText();
			currentBook.setType(s);
		}
		if (inPrice) {
			String s = event.getText();
			currentBook.setPrice(Double.parseDouble(s));
		}
		if (inEngine) {
			String s = event.getText();
			currentBook.setEngine(Double.parseDouble(s));
		}
		if (inDoors) {
			String s = event.getText();
			currentBook.setDoors(Integer.parseInt(s));
		}
	}
}
