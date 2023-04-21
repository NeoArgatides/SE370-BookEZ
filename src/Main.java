import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.*;

// import javax.json.*;
// import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
		
	/*private static String DB_URL;
    private static String USER;
    private static String PASS;

	static ConfigurationLoader loader = new ConfigurationLoader(DB_URL, USER, PASS);*/

	
    public static void main(String[] args) {

		//create a connection tot the mySql database
		try{
			
			DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            ReceiptDAO receiptDAO = new ReceiptDAO(connection);

			/********************************************* */
			// Example login
            String username = "testuser";
            String password = "testpassword";

			// sign in the user
			User user = userDAO.signIn(username, password);
			if (user != null) {
                // get the receipts for the user
                List<Receipt> receipts = receiptDAO.getReceiptsForUser(user);
                
                for (Receipt receipt : receipts) {
                    // print out the receipt information
                    System.out.println(receipt.getId() + ", " + receipt.getUserId() + ", " + receipt.getOrderNumber() + ", " + receipt.getTotal() + ", " + receipt.getShippingCost() + ", " + receipt.getPrice() + ", " + receipt.getShippingPaid() + ", " + receipt.getTax());
                }
            } else {
                System.out.println("Invalid username or password");
            }
						
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