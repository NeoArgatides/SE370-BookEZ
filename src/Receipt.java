public class Receipt {
    private int orderId;
    private double total;
    private double shippingCost;
    private double price;
    private boolean shippingPaid;
    private double tax;

    public Receipt(int orderId, double total, double shippingCost, double price, boolean shippingPaid, double tax) {
        this.orderId = orderId;
        this.total = total;
        this.shippingCost = shippingCost;
        this.price = price;
        this.shippingPaid = shippingPaid;
        this.tax = tax;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotal() {
        return total;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getPrice() {
        return price;
    }

    public boolean isShippingPaid() {
        return shippingPaid;
    }

    public double getTax() {
        return tax;
    }

    // other methods as needed
}