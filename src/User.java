//user object 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public List<Receipt> getReceipts() {
    //     List<Receipt> receipts = new ArrayList<>();
    //     try {
    //         // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "myuser", "mypassword");
    //         PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM receipts WHERE user_id = ?");

    //         stmt.setInt(1, this.id);
    //         ResultSet rs = stmt.executeQuery();
    //         while (rs.next()) {
    //             int id = rs.getInt("id");
    //             int user_id = rs.getInt("user_id");
    //             int orderId = rs.getInt("order_id");
    //             double total = rs.getDouble("total");
    //             double shippingCost = rs.getDouble("shipping_cost");
    //             double price = rs.getDouble("price");
    //             double shippingPaid = rs.getDouble("shipping_paid");
    //             double tax = rs.getDouble("tax");
    //             receipts.add(new Receipt(id, user_id, orderId, total, shippingCost, price, shippingPaid, tax));
    //         }
    //         rs.close();
    //         stmt.close();
    //         myConn.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return receipts;
    // }
}