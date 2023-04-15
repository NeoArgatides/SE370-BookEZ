import java.sql.*;
//import javax.swing.JOptionPane;  

public class DatabaseAccessObject implements dbAO_IF{
    private static DatabaseAccessObject instance = null;
    private static Connection myConn = null;

    // static final String DB_URL = "jdbc:mysql://localhost:3306/bookez_db";
    // static final String USER = "root";
    // static final String PASS = "bookez471";
    // static final String databaseName = "bookez_db";

    DatabaseAccessObject(Connection conn){
        DatabaseAccessObject.myConn = conn; 
    }
    
    public User getUserById(int id) {   
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new User(id, username, password);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static DatabaseAccessObject getInstance(){
        if(instance == null){
            instance = new DatabaseAccessObject(myConn);
        }  
        return instance;
    }

    public Connection getConnection(){
        return myConn;
    }


    //get user information 
    
    /*public static void main(String arg[])
    {
        DatabaseAccessObject d = new DatabaseAccessObject();
        User user = d.getUserById(1);
        System.out.println("User " + user.getId() + ": " + user.getUsername() + ", " + user.getPassword());

    }*/
}

