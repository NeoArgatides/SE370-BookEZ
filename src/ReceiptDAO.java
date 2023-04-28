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

    public void addReceiptToDatabase(User user, String orderNum, String total, String shipCost, String soldPrice, String shipPaid, String tax) throws SQLException {
        String query = "INSERT INTO Receipts (user_id, order_id, total, shipping_cost, price, shipping_paid, tax) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        
        PreparedStatement statement = connection.prepareStatement(query);
        // orderNum = "3120-312321-3";

        // total = "12000000";
        // double totalValue = Double.parseDouble(total);
        // shipCost = "12000000";
        // double shippingCost = Double.parseDouble(shipCost);
        // soldPrice = "12000000";
        // double price = Double.parseDouble(soldPrice);
        // shipPaid = "12000000";
        // double shippingPaid = Double.parseDouble(shipPaid);
        // tax = "12000000";
        // double taxValue = Double.parseDouble(tax);


        // statement.setInt(1, user.getId());
        // "3120-312321-3"
        statement.setInt(1, user.getId());//user1
        statement.setString(2, orderNum);
        statement.setDouble(3, Double.parseDouble(total));
        statement.setDouble(4, Double.parseDouble(shipCost));
        statement.setDouble(5, Double.parseDouble(soldPrice));
        statement.setDouble(6, Double.parseDouble(shipPaid));
        statement.setDouble(7, Double.parseDouble(tax));
        // int result = statement.executeUpdate();
        statement.executeUpdate();
        System.out.println("finished uploading");
    }
}
