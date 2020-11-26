public class Bid {

    private double amount;
    private double price;

    public Bid() { }

    public Bid(double price, double amount) {
        this.amount = amount;
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "amount=" + amount +
                ", price=" + price +
                '}';
    }
}