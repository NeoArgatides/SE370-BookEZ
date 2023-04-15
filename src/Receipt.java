public class Receipt {

    private int id;
    private int userId;
    private int orderId;

    private double total;
    private double shippingCost;
    private double price;
    private double shippingPaid;
    private double tax;

    public Receipt(int id,int userId, int orderId, double total, double shippingCost, double price, double shippingPaid, double tax) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.total = total;
        this.shippingCost = shippingCost;
        this.price = price;
        this.shippingPaid = shippingPaid;
        this.tax = tax;
    }

    public int getId(){
        return id;
    }

    public void setId(int i){
        this.id = i;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int u){
        this.userId = u;
    }

    public int getOrderNumber() {
        return orderId;
    }

    public void setOrderId(int i){
        this.orderId = i;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double t){
        this.total = t;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double s){
        this.shippingCost = s;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double p){
        this.price = p;
    }

    public double getShippingPaid() {
        return shippingPaid;
    }

    public void setShippingPaid(double s){
        this.shippingPaid = s;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double t){
        this.tax = t;
    }

}