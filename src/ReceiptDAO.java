import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAO {

    private Connection connection;

    public ReceiptDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Receipt> getReceiptsForUser(User user) throws SQLException {
        List<Receipt> receipts = new ArrayList<>();
        String query = "SELECT * FROM Receipts WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getId());
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            String orderId = rs.getString("order_id");
            double total = rs.getDouble("total");
            double shippingCost = rs.getDouble("shipping_cost");
            double price = rs.getDouble("price");
            double shippingPaid = rs.getDouble("shipping_paid");
            double tax = rs.getDouble("tax");
            Receipt receipt = new Receipt(id, userId, orderId, total, shippingCost, price, shippingPaid, tax);
            receipts.add(receipt);
        }
        return receipts;
    }

    /////

    public boolean addReceiptToDatabase(User user, String orderNum, String total, String shipCost, String soldPrice, String shipPaid, String tax) throws SQLException {
        PreparedStatement stmt = null;
        boolean success = false;
        
        try {

            // Check if receipt already exists in database
        stmt = connection.prepareStatement("SELECT * FROM receipts WHERE user_id = ? AND order_id = ?");
        stmt.setInt(1, user.getId());
        stmt.setString(2, orderNum);
        ResultSet rs = stmt.executeQuery();
        // If the receipt already exists, return true
        if (rs.next()) {
            System.out.println("Duplicated receipt");
            return false;//or false
        }
        stmt.close();

            // Prepare SQL statement with parameters
            stmt = connection.prepareStatement("INSERT INTO receipts (user_id, order_id, total, shipping_cost, price, shipping_paid, tax) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, user.getId());
            stmt.setString(2, orderNum);
            stmt.setDouble(3, Double.parseDouble(total));
            stmt.setDouble(4, Double.parseDouble(shipCost));
            stmt.setDouble(5, Double.parseDouble(soldPrice));
            stmt.setDouble(6, Double.parseDouble(shipPaid));
            stmt.setDouble(7, Double.parseDouble(tax));
            // Execute SQL statement
            int rows = stmt.executeUpdate();
            
            // Check if the SQL statement was successful
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
    
    
}