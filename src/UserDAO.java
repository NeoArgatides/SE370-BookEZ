import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public User signIn(String username, String password) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String pass = rs.getString("password");
                user = new User(id, name, pass);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return user;
    }

    public boolean createUser(String username, String password) throws SQLException {
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                success = true;
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return success;
    }
    
    public User getUserByUsername(String username) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM users WHERE username=?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String pass = rs.getString("password");
                user = new User(id, name, pass);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return user;
    }
    
    
}
