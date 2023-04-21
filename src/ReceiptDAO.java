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
            int orderId = rs.getInt("order_id");
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
}
