package myApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class Gui {

	private JFrame frame;
	private JTextField txt_plate;
	private JTextField txt_Make;
	private JTextField txt_Type;
	private JTextField txt_Price;
	private JTextField txt_Engine;
	private JTextField txt_Doors;
	private JTextField txtGetPlate;
	private JTextField txtGetMake;
	private JTextField txtGetEngine;
	private JTextField txtPostPlate;
	private JTextField txtPostMake;
	private JTextField txtPostType;
	private JTextField txtPostPrice;
	private JTextField txtPostEngine;
	private JTextField txtPostDoors;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel modelTable;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}
	
	public void  clear() {
		txt_plate.setText("");
		txt_Make.setText("");
		txt_Type.setText("");
		txt_Price.setText("");
		txt_Engine.setText("");
		txt_Doors.setText("");
		txtGetPlate.setText("");
		txtGetMake.setText("");
		txtGetEngine.setText("");
		txtPostPlate.setText("");
		txtPostMake.setText("");
		txtPostType.setText("");
		txtPostPrice.setText("");
		txtPostEngine.setText("");
		txtPostDoors.setText("");
		textField.setText("");
	}
	
	//Get all data in the table
	public void refreshTable() {
		modelTable.setRowCount(0);
		RequestUri uri = new RequestUri();
		uri.getAllUri();
		Iterator<Car> itr = uri.returnArray().iterator();
		while (itr.hasNext()) {
			Car rs = itr.next();
			Object rowData[] = new Object[6];
			rowData[0] = rs.getLicencePlate();
			rowData[1] = rs.getMake();
			rowData[2] = rs.getType();
			rowData[3] = rs.getPrice();
			rowData[4] = rs.getEngine();
			rowData[5]= rs.getDoors();
			modelTable.addRow(rowData);
		}
		clear();
	}
	
//Export to cvs
	public void exportToCVS() {
		try {
			FileWriter file = new FileWriter("D:\\java.projects\\distributed systems java\\A00252699KarolisV\\cvcExport.txt", false);
			BufferedWriter buf = new BufferedWriter(file);
			PrintWriter pw = new PrintWriter(buf);

			RequestUri uri = new RequestUri();
			uri.getAllUri();
			Iterator<Car> itr = uri.returnArray().iterator();

			while (itr.hasNext()) {
				Car rs = itr.next();
				pw.println("License plate -> "+rs.getLicencePlate() + ", Make-> " + rs.getMake() + ", Type-> " + rs.getType() + ", Price €-> " + rs.getPrice() + ", Engine-> "
						+ rs.getEngine() + ", Doors-> " + rs.getDoors());

			}
			pw.flush();
			pw.close();
		} catch (Exception e1) {
			System.out.println("Error: exception write to file.");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1202, 753);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 66, 1162, 347);
		frame.getContentPane().add(scrollPane);
		
		modelTable = new DefaultTableModel(new String[] { "License_Plate", "Make", "Type", "Price", "Engine", "Doors" }, 0);

		scrollPane.setViewportView(table);

		table_1 = new JTable(modelTable);
		scrollPane.setViewportView(table_1);
		
		
		
		JLabel car_dealership_label = new JLabel("A00252699 Car Dealership!");
		car_dealership_label.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 22));
		car_dealership_label.setBounds(384, 11, 278, 44);
		frame.getContentPane().add(car_dealership_label);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 476, 283, 185);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPlateNumber = new JLabel("Plate Number->");
		lblPlateNumber.setBounds(10, 11, 137, 21);
		panel.add(lblPlateNumber);
		lblPlateNumber.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		
		txt_plate = new JTextField();
		txt_plate.setBounds(136, 10, 127, 21);
		panel.add(txt_plate);
		txt_plate.setColumns(10);
		
		JLabel lblMake = new JLabel("Make->");
		lblMake.setBounds(10, 33, 137, 21);
		panel.add(lblMake);
		lblMake.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		
		txt_Make = new JTextField();
		txt_Make.setBounds(136, 32, 127, 21);
		panel.add(txt_Make);
		txt_Make.setColumns(10);
		
		JLabel lblType = new JLabel("Type->");
		lblType.setBounds(10, 54, 137, 21);
		panel.add(lblType);
		lblType.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		
		txt_Type = new JTextField();
		txt_Type.setBounds(136, 53, 127, 21);
		panel.add(txt_Type);
		txt_Type.setColumns(10);
		
		txt_Price = new JTextField();
		txt_Price.setBounds(136, 75, 127, 21);
		panel.add(txt_Price);
		txt_Price.setColumns(10);
		
		txt_Engine = new JTextField();
		txt_Engine.setBounds(136, 97, 127, 21);
		panel.add(txt_Engine);
		txt_Engine.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price \u20AC->");
		lblPrice.setBounds(10, 76, 137, 21);
		panel.add(lblPrice);
		lblPrice.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		
		JLabel lblEngine = new JLabel("Engine->");
		lblEngine.setBounds(10, 98, 137, 21);
		panel.add(lblEngine);
		lblEngine.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		
		JLabel lblDoors = new JLabel("Doors->");
		lblDoors.setBounds(10, 119, 137, 21);
		panel.add(lblDoors);
		lblDoors.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		
		txt_Doors = new JTextField();
		txt_Doors.setBounds(136, 118, 127, 21);
		panel.add(txt_Doors);
		txt_Doors.setColumns(10);
		
		JButton btnNewButton = new JButton("@POST");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plate = txt_plate.getText();
				String make = txt_Make.getText();
				String type = txt_Type.getText();
				String price = txt_Price.getText();
				String engine = txt_Engine.getText();
				String doors = txt_Doors.getText();
				RequestUri uri = new RequestUri();
				uri.putUri(plate, make, type, price, engine, doors);
				refreshTable();
				clear();
			}
		});
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setBounds(93, 151, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("@POST Create A New Car");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 432, 172, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(303, 476, 283, 185);
		frame.getContentPane().add(panel_1);
		
		JLabel lblPlateNumber_1 = new JLabel("Plate Number->");
		lblPlateNumber_1.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblPlateNumber_1.setBounds(10, 11, 137, 21);
		panel_1.add(lblPlateNumber_1);
		
		txtGetPlate = new JTextField();
		txtGetPlate.setColumns(10);
		txtGetPlate.setBounds(136, 10, 127, 21);
		panel_1.add(txtGetPlate);
		
		JLabel lblMake_1 = new JLabel("Make->");
		lblMake_1.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblMake_1.setBounds(10, 33, 137, 21);
		panel_1.add(lblMake_1);
		
		txtGetMake = new JTextField();
		txtGetMake.setColumns(10);
		txtGetMake.setBounds(136, 32, 127, 21);
		panel_1.add(txtGetMake);
		
		txtGetEngine = new JTextField();
		txtGetEngine.setColumns(10);
		txtGetEngine.setBounds(136, 53, 127, 21);
		panel_1.add(txtGetEngine);
		
		JLabel lblEngine_1 = new JLabel("Engine->");
		lblEngine_1.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblEngine_1.setBounds(10, 53, 137, 21);
		panel_1.add(lblEngine_1);
		
		JButton btngetByMake = new JButton("@GET by Make");
		btngetByMake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String make = txtGetMake.getText();
				modelTable.setRowCount(0);
				Iterator<Car> itr = null;
				RequestUri uri = new RequestUri();
				uri.byMakeUri(make);
				itr = uri.returnArray().iterator();
				while (itr.hasNext()) {
					Car rs = itr.next();
					Object rowData[] = new Object[6];
					rowData[0] = rs.getLicencePlate();
					rowData[1] = rs.getMake();
					rowData[2] = rs.getType();
					rowData[3] = rs.getPrice();
					rowData[4] = rs.getEngine();
					rowData[5]= rs.getDoors();
					modelTable.addRow(rowData);
				}
				clear();
			}
		});
		btngetByMake.setBackground(SystemColor.info);
		btngetByMake.setBounds(10, 119, 137, 23);
		panel_1.add(btngetByMake);
		
		JButton btngetByPlate = new JButton("@GET by Plate");
		btngetByPlate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plate = txtGetPlate.getText();
				modelTable.setRowCount(0);
				Iterator<Car> itr = null;
				RequestUri uri = new RequestUri();
				uri.byLicensePlateUri(plate);
				itr = uri.returnArray().iterator();
				while (itr.hasNext()) {
					Car rs = itr.next();
					Object rowData[] = new Object[6];
					rowData[0] = rs.getLicencePlate();
					rowData[1] = rs.getMake();
					rowData[2] = rs.getType();
					rowData[3] = rs.getPrice();
					rowData[4] = rs.getEngine();
					rowData[5]= rs.getDoors();
					modelTable.addRow(rowData);
				}
				clear();
				
			}
		});
		btngetByPlate.setBackground(SystemColor.info);
		btngetByPlate.setBounds(156, 119, 117, 23);
		panel_1.add(btngetByPlate);
		
		JButton btngetByEngine = new JButton("@GET by Engine");
		btngetByEngine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String engine = txtGetEngine.getText();
				modelTable.setRowCount(0);
				Iterator<Car> itr = null;
				RequestUri uri = new RequestUri();
				uri.bySizeUri(engine);
				itr = uri.returnArray().iterator();
				while (itr.hasNext()) {
					Car rs = itr.next();
					Object rowData[] = new Object[6];
					rowData[0] = rs.getLicencePlate();
					rowData[1] = rs.getMake();
					rowData[2] = rs.getType();
					rowData[3] = rs.getPrice();
					rowData[4] = rs.getEngine();
					rowData[5]= rs.getDoors();
					modelTable.addRow(rowData);
				}
				clear();
			}
		});
		btngetByEngine.setBackground(SystemColor.info);
		btngetByEngine.setBounds(84, 153, 117, 23);
		panel_1.add(btngetByEngine);
		
		JLabel lblgetCars = new JLabel("@GET cars");
		lblgetCars.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblgetCars.setBounds(319, 432, 172, 32);
		frame.getContentPane().add(lblgetCars);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(596, 476, 283, 185);
		frame.getContentPane().add(panel_2);
		
		JLabel lblPlateNumber_2 = new JLabel("Plate Number->");
		lblPlateNumber_2.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblPlateNumber_2.setBounds(10, 11, 137, 21);
		panel_2.add(lblPlateNumber_2);
		
		txtPostPlate = new JTextField();
		txtPostPlate.setColumns(10);
		txtPostPlate.setBounds(136, 10, 127, 21);
		panel_2.add(txtPostPlate);
		
		JLabel lblMake_2 = new JLabel("Make->");
		lblMake_2.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblMake_2.setBounds(10, 33, 137, 21);
		panel_2.add(lblMake_2);
		
		txtPostMake = new JTextField();
		txtPostMake.setColumns(10);
		txtPostMake.setBounds(136, 32, 127, 21);
		panel_2.add(txtPostMake);
		
		JLabel lblType_1 = new JLabel("Type->");
		lblType_1.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblType_1.setBounds(10, 54, 137, 21);
		panel_2.add(lblType_1);
		
		txtPostType = new JTextField();
		txtPostType.setColumns(10);
		txtPostType.setBounds(136, 53, 127, 21);
		panel_2.add(txtPostType);
		
		txtPostPrice = new JTextField();
		txtPostPrice.setColumns(10);
		txtPostPrice.setBounds(136, 75, 127, 21);
		panel_2.add(txtPostPrice);
		
		txtPostEngine = new JTextField();
		txtPostEngine.setColumns(10);
		txtPostEngine.setBounds(136, 97, 127, 21);
		panel_2.add(txtPostEngine);
		
		JLabel lblPrice_1 = new JLabel("Price \u20AC->");
		lblPrice_1.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblPrice_1.setBounds(10, 76, 137, 21);
		panel_2.add(lblPrice_1);
		
		JLabel lblEngine_2 = new JLabel("Engine->");
		lblEngine_2.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblEngine_2.setBounds(10, 98, 137, 21);
		panel_2.add(lblEngine_2);
		
		JLabel lblDoors_1 = new JLabel("Doors->");
		lblDoors_1.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblDoors_1.setBounds(10, 119, 137, 21);
		panel_2.add(lblDoors_1);
		
		txtPostDoors = new JTextField();
		txtPostDoors.setColumns(10);
		txtPostDoors.setBounds(136, 118, 127, 21);
		panel_2.add(txtPostDoors);
		
		JButton btnpost = new JButton("@PUT");
		btnpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plate = txtPostPlate.getText();
				String make = txtPostMake.getText();
				String type = txtPostType.getText();
				String price = txtPostPrice.getText();
				String engine = txtPostEngine.getText();
				String doors = txtPostDoors.getText();
				RequestUri uri = new RequestUri();
				uri.postUri(plate, make, type, price, engine, doors);
				clear();
				refreshTable();
			
			}
		});
		btnpost.setBackground(SystemColor.info);
		btnpost.setBounds(93, 151, 89, 23);
		panel_2.add(btnpost);
		
		JLabel lblpostUpdateCar = new JLabel("@PUT update Car");
		lblpostUpdateCar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblpostUpdateCar.setBounds(615, 432, 172, 32);
		frame.getContentPane().add(lblpostUpdateCar);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(SystemColor.inactiveCaption);
		panel_2_1.setBounds(889, 476, 287, 77);
		frame.getContentPane().add(panel_2_1);
		
		JLabel lblPlateNumber_2_1 = new JLabel("Plate Number->");
		lblPlateNumber_2_1.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblPlateNumber_2_1.setBounds(10, 11, 137, 21);
		panel_2_1.add(lblPlateNumber_2_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(136, 10, 127, 21);
		panel_2_1.add(textField);
		
		JButton btndelete = new JButton("@DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plate = textField.getText();
				RequestUri uri = new RequestUri();
				uri.deleteUri(plate);
				clear();
				refreshTable();
			}
		});
		btndelete.setBackground(SystemColor.info);
		btndelete.setBounds(94, 43, 89, 23);
		panel_2_1.add(btndelete);
		
		JLabel lbldeleteCar = new JLabel("@DELETE car");
		lbldeleteCar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbldeleteCar.setBounds(912, 432, 172, 32);
		frame.getContentPane().add(lbldeleteCar);
		
		JButton btnNewButton_1 = new JButton("Refresh Data");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTable();
			}
		});
		btnNewButton_1.setBounds(912, 604, 134, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Export to CVS");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportToCVS();
			}
		});
		btnNewButton_1_1.setBounds(912, 638, 134, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Exit");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_2.setBounds(912, 680, 134, 23);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JLabel lblOtherOptions = new JLabel("Other Options!");
		lblOtherOptions.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblOtherOptions.setBounds(915, 564, 111, 32);
		frame.getContentPane().add(lblOtherOptions);
	}
}
