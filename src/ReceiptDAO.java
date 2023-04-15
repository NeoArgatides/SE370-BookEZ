import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAO {

    private final Connection myConn;

    public ReceiptDAO(Connection myConn) {
        this.myConn = myConn;
    }

    // Add a receipt to the database
    public void addReceipt(Receipt receipt) throws SQLException {
        String query = "INSERT INTO receipts (user_id, order_number, total, shipping_cost, price, shipping_paid, tax) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = myConn.prepareStatement(query);
        statement.setInt(1, receipt.getUserId());
        statement.setInt(2, receipt.getOrderNumber());
        statement.setDouble(3, receipt.getTotal());
        statement.setDouble(4, receipt.getShippingCost());
        statement.setDouble(5, receipt.getPrice());
        statement.setDouble(6, receipt.getShippingPaid());
        statement.setDouble(7, receipt.getTax());
        statement.executeUpdate();
    }

    // // Get all receipts from the database
    // public List<Receipt> getAllReceipts() throws SQLException {
    //     List<Receipt> receipts = new ArrayList<>();
    //     String query = "SELECT * FROM receipts";
    //     PreparedStatement statement = myConn.prepareStatement(query);
    //     ResultSet rs = statement.executeQuery();
    //     while (rs.next()) {
    //         int id = rs.getInt("id");
    //         int userId = rs.getInt("user_id");
    //         int orderNumber = rs.getInt("order_number");
    //         double total = rs.getDouble("total");
    //         double shippingCost = rs.getDouble("shipping_cost");
    //         double price = rs.getDouble("price");
    //         double shippingPaid = rs.getDouble("shipping_paid");
    //         double tax = rs.getDouble("tax");
    //         Receipt receipt = new Receipt(id, userId, orderNumber, total, shippingCost, price, shippingPaid, tax);
    //         receipts.add(receipt);
    //     }
    //     return receipts;
    // }

    // Get all receipts for a specific user from the database
    // public List<Receipt> getReceiptsByUserId(int userId) throws SQLException {
    //     List<Receipt> receipts = new ArrayList<>();
    //     String query = "SELECT * FROM receipts WHERE user_id = ?";
    //     PreparedStatement statement = myConn.prepareStatement(query);
    //     statement.setInt(1, userId); //user is 
    //     ResultSet rs = statement.executeQuery();
    //     while (rs.next()) {
    //         int id = rs.getInt("id");
    //         int orderNumber = rs.getInt("order_number");
    //         double total = rs.getDouble("total");
    //         double shippingCost = rs.getDouble("shipping_cost");
    //         double price = rs.getDouble("price");
    //         double shippingPaid = rs.getDouble("shipping_paid");
    //         double tax = rs.getDouble("tax");
    //         Receipt receipt = new Receipt(id, userId, orderNumber, total, shippingCost, price, shippingPaid, tax);
    //         receipts.add(receipt);
    //     }
    //     return receipts;
    // }
}