import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAO {

    //
    private static ReceiptDAO instance;

    private final Connection myConn;

    private ReceiptDAO(Connection myConn) {
        this.myConn = myConn;
    }

    //
    public static ReceiptDAO getInstance(Connection myConn) {
        if (instance == null) {
            instance = new ReceiptDAO(myConn);
        }
        return instance;
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

    public List<Receipt> getReceiptsForUser(User user, Connection connection) throws SQLException {
        List<Receipt> receipts = new ArrayList<>();
        String query = "SELECT * FROM receipts WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int receiptId = resultSet.getInt("id");
            int userId = resultSet.getInt("user_id");
            int orderId = resultSet.getInt("order_id");
            double total = resultSet.getDouble("total");
            double shippingCost = resultSet.getDouble("shipping_cost");
            double price = resultSet.getDouble("price");
            double shippingPaid = resultSet.getDouble("shipping_paid");
            double tax = resultSet.getDouble("tax");
            Receipt receipt = new Receipt(receiptId, userId, orderId, total, shippingCost, price, shippingPaid, tax);
            receipts.add(receipt);
        }
        resultSet.close();
        statement.close();
        return receipts;
    }
    

    // public void insertReceipt(User user, int orderId, double total, double shippingCost, double price, double shippingPaid, double tax, Connection connection) throws SQLException {
    //     String query = "INSERT INTO receipts (user_id, order_id, total, shipping_cost, price, shipping_paid, tax) VALUES (?, ?, ?, ?, ?, ?, ?)";
    //     PreparedStatement statement = connection.prepareStatement(query);
    //     statement.setInt(1, user.getId());
    //     statement.setInt(2, orderId);
    //     statement.setDouble(3, total);
    //     statement.setDouble(4, shippingCost);
    //     statement.setDouble(5, price);
    //     statement.setDouble(6, shippingPaid);
    //     statement.setDouble(7, tax);
    //     statement.executeUpdate();
    //     statement.close();
    // }
    


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