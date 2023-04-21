import java.sql.*;

public class UserDAO {

    
    private DBConnection dbConnection;
    
    private static UserDAO instance;
    
    //singleton instance
    private UserDAO() throws SQLException {
        dbConnection = DBConnection.getInstance();
    }

    //returns the singleton instance of the class
    public static synchronized UserDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
    
        
        ResultSet rs = null;
        User user = null;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try(Connection conn = dbConnection.getConnection();PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } 
        return user;

    }
    // public User getUserByUsername(String username) throws SQLException {
    //     String query = "SELECT * FROM users WHERE username = ?";
    //     try (PreparedStatement statement = connection.prepareStatement(query)) {
    //         statement.setString(1, username);
    //         try (ResultSet resultSet = statement.executeQuery()) {
    //             if (resultSet.next()) {
    //                 int id = resultSet.getInt("id");
    //                 String password = resultSet.getString("password");
    //                 return new User(id, username, password);
    //             } else {
    //                 return null;
    //             }
    //         }
    //     }
    // }
  

}
