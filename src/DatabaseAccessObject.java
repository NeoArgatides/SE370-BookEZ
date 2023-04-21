import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.JOptionPane;  


/*******************
 he UserDAO and ReceiptDAO classes are typically used to interact with the 
 database and perform CRUD (Create, Read, Update, and Delete) operations on 
 the users and receipts tables, respectively 
*******************/
public class DatabaseAccessObject implements dbAO_IF{
    private static DatabaseAccessObject instance = null; //needs work
    private static Connection myConn = null;

    // static final String DB_URL = "jdbc:mysql://localhost:3306/bookez_db";
    // static final String USER = "root";
    // static final String PASS = "bookez471";
    // static final String databaseName = "bookez_db";

    DatabaseAccessObject(Connection conn){
        DatabaseAccessObject.myConn = conn; 
    }
    
    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE username=?";
        try (PreparedStatement statement = myConn.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                }
            }
        }
        return user;
    }
    // public User login(String username, String password) throws SQLException {
    //     PreparedStatement statement = myConn.prepareStatement("SELECT * FROM users WHERE user_name = ? AND password = ?");
    //     statement.setString(1, username);
    //     statement.setString(2, password);
    //     ResultSet rs = statement.executeQuery();

    //     if (rs.next()) {
    //         User user = new User();
    //         user.setId(rs.getInt("id"));
    //         user.setUsername(rs.getString("username"));
    //         user.setPassword(rs.getString("password"));

    //         ReceiptDAO receiptDAO = new ReceiptDAO(myConn);
    //         List<Receipt> receipts = receiptDAO.getReceiptsByUserId(username);
    //         user.setReceipts(receipts);

    //         return user;
    //     } else {
    //         return null;
    //     }
    // }


/**************************
 public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        }
    }

 ********************/
/*********************
 public User getUserByUsername(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE username=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                    );
                }
            }
        }
        return user;
    }
 **********************/




    // public User getUserById(int id) {   
    //     try {
    //         String query = "SELECT * FROM users WHERE id = ?";
    //         PreparedStatement stmt = myConn.prepareStatement(query);
    //         stmt.setInt(1, id);
    //         ResultSet rs = stmt.executeQuery();
    //         if (rs.next()) {
    //             String username = rs.getString("username");
    //             String password = rs.getString("password");
    //             return new User(id, username, password);
    //         }
    //     } catch (SQLException e) {
    //         System.err.println("Error: " + e.getMessage());
    //     }
    //     return null;
    // }

    // public static DatabaseAccessObject getInstance(){
    //     if(instance == null){
    //         instance = new DatabaseAccessObject(myConn);
    //     }  
    //     return instance;
    // }

    // public Connection getConnection(){
    //     return myConn;
    // }


    //get user information 
    
    /*public static void main(String arg[])
    {
        DatabaseAccessObject d = new DatabaseAccessObject();
        User user = d.getUserById(1);
        System.out.println("User " + user.getId() + ": " + user.getUsername() + ", " + user.getPassword());

    }*/
}

