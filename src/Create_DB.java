import java.sql.*;
//import javax.swing.JOptionPane;  


public class Create_DB {
    private static Create_DB instance = null;
    private Connection conn = null;

    static final String DB_URL = "jdbc:mysql://localhost:3306/bookez_db";
    static final String USER = "root";
    static final String PASS = "bookez471";
    static final String databaseName = "bookez_db";

    private Create_DB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    
            conn = DriverManager.getConnection(DB_URL,USER, PASS);
            System.out.println("DB connection successful to: " + DB_URL);

            //testing getting username from table 
            // Execute a query to retrieve the usernames from the users table
            String query = "SELECT username FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println(username);
             }
    
             // Close the database connection
             conn.close();
            //order, total, shipping cost, price, shipping Paid, tax

            //created the database
            // String sql = "CREATE DATABASE " + databaseName;
            // Statement statement = conn.createStatement();
            // statement.executeUpdate(sql);
            // statement.close();
            // JOptionPane.showMessageDialog(null, databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Create_DB getInstance(){
        if(instance == null){
            instance = new Create_DB();
        }  
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }
    /*public static void main(String arg[])
    {
        //bookez471
        //Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    
            conn = DriverManager.getConnection(DB_URL,USER, PASS);
            System.out.println("DB connection successful to: " + DB_URL);

            //testing getting username from table 
            // Execute a query to retrieve the usernames from the users table
            String query = "SELECT username FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println(username);
             }
    
             // Close the database connection
             conn.close();
            //order, total, shipping cost, price, shipping Paid, tax



            //created the database
            // String sql = "CREATE DATABASE " + databaseName;
            // Statement statement = conn.createStatement();
            // statement.executeUpdate(sql);
            // statement.close();
            // JOptionPane.showMessageDialog(null, databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}

