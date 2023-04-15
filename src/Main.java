import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.*;

import javax.json.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
		
	private static String DB_URL;
    private static String USER;
    private static String PASS;
	static ConfigurationLoader loader = new ConfigurationLoader(DB_URL, USER, PASS);

    public static void main(String[] args) {
		//create a connection tot the mySql database
		try{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error");
			}  
			loader.loadConfiguration();  
            Connection conn = DriverManager.getConnection(loader.getDBURL(), loader.getUser(), loader.getPass());
            System.out.println("DB connection successful to: " + loader.getDBURL());
			
			//a DatabaseAccessObject instance using the connection 
			DatabaseAccessObject userDatabaseAccessObject = new DatabaseAccessObject(conn);

			//TESTING !!!!
			//// Retrieve a user from the database and print their username and password
			// User user = userDatabaseAccessObject.getUserById(1);
			// System.out.println("User " + user.getId() + ": " + user.getUsername() + ", " + user.getPassword());
			// List<Receipt> receipts = user.getReceipts();

			// // Iterate over the list of receipts and do something with them
			// for (Receipt receipt : receipts) {
			// 	System.out.println("Receipt ID: " + receipt.getId());
			// 	System.out.println("User ID: " + receipt.getUserId());
			// 	System.out.println("Order ID: " + receipt.getOrderNumber());
			// 	System.out.println("Total: " + receipt.getTotal());
			// 	System.out.println("Shipping Cost: " + receipt.getShippingCost());
			// 	System.out.println("Price: " + receipt.getPrice());
			// 	System.out.println("Shipping Paid: " + receipt.getShippingPaid());
			// 	System.out.println("Tax: " + receipt.getTax());
			// 	System.out.println();
			// }

 			// Close the database connection
            conn.close();
		}	catch(SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    	EventQueue.invokeLater(new Runnable()
		{
			
			public void run()
			{	
				try
				{
					MainFrame frame = new MainFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setSize(800, 450);
					//frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}