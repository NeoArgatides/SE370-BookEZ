import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            // stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            // stmt.setString(1, username);
            // stmt.setString(2, password);
            // rs = stmt.executeQuery();
            // if (rs.next()) {
            //     int id = rs.getInt("id");
            //     String name = rs.getString("username");
            //     String pass = rs.getString("password");
            //     user = new User(id, name, pass);
            // }
            stmt = conn.prepareStatement("SELECT * FROM users WHERE username=?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("username");
            String hashedPassword = rs.getString("password");
            try {
                if (verifyPassword(password, hashedPassword)) {
                    user = new User(id, name, hashedPassword);
                }
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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

    //for password 
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    private boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        return hashedPassword.equals(hashPassword(password));
    }
    
    public boolean createUser(String username, String password) throws SQLException {
        PreparedStatement stmt = null;
        boolean success = false;
            // stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
         

    try {
        // Check if user already exists
        stmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            System.out.println("Error: User already exists.");
            return false;
        }
        
        // Insert new user
        stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, SHA2(?, 256))");
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.executeUpdate();
        return true;
    } finally {
        if (stmt != null) {
            stmt.close();
        }
    }
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
