import java.awt.EventQueue;
//import java.sql.*;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
    	/*try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/", "user1", "pass");
    	         Statement stmt = conn.createStatement();
    	      ) {		      
    	         String sql = "CREATE DATABASE";
    	         stmt.executeUpdate(sql);
    	         System.out.println("Database created successfully...");   	  
    	      } catch (SQLException e) {
    	         e.printStackTrace();
    	      }
    	
    	/*Connection c = null;
	    ///SE370-BookEZ/src
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:account.db");
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    System.out.println("Opened database successfully");*/
    	
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
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}