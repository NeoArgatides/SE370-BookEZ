import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.*;

public class Main {
	static final String DB_URL = "jdbc:mysql://localhost:3306/bookez_db";
    static final String USER = "root";
    static final String PASS = "bookez471";
    static final String databaseName = "bookez_db";

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
            Connection conn = DriverManager.getConnection(DB_URL,USER, PASS);
            System.out.println("DB connection successful to: " + DB_URL);
			
			//a DatabaseAccessObject instance using the connection 
			DatabaseAccessObject userDatabaseAccessObject = new DatabaseAccessObject(conn);

			//TESTING !!!!
			//// Retrieve a user from the database and print their username and password
			User user = userDatabaseAccessObject.getUserById(1);
			System.out.println("User " + user.getId() + ": " + user.getUsername() + ", " + user.getPassword());

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