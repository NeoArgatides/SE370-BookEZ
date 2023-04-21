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
			
			// Get an instance of the UserDao
			UserDAO userDao = UserDAO.getInstance();
			// Get an instance of the DBConnection
            DBConnection dbConnection = DBConnection.getInstance();
            System.out.println("DB Connection successful.");

			/********************************************* */
			// Example login
            String username = "testuser";
            String password = "testpassword";

			User user = userDao.getUserByUsernameAndPassword(username, password);
			int userId = user.getId();

            if (user != null) {
                System.out.println("User login successful.");
            } else {
                System.out.println("User login failed.");
            }
			ReceiptDAO receiptDAO = ReceiptDAO.getInstance();

			List<Receipt> receipts = receiptDAO.getReceiptsForUser(userId);
			for (Receipt receipt : receipts) {
				System.out.println(receipt);
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