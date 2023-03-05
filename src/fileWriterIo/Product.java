package fileWriterIo;

public class Product {

    private static int id;
    private static String name;
    private static int quantity;
    private static double price;

    private boolean isDeleted = false;

    public static void setName(String productName) {
        name = productName;
    }

    public static void setQuantity(int productQuantity) {
        quantity = productQuantity;
    }

    public static void setPrice(double productPrice) {
        price = productPrice;
    }

    public static int getId() {
        return id;
    }

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(int id, String name, int quantity, double price, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isDeleted = isDeleted;
    }

    public  void delete() {
        this.isDeleted = true;
    }

    public int sell(int quantity) {
        this.quantity -= quantity;
        return quantity;
    }

    public String getMessageDetails() {
        return id + "," + name + "," + quantity + "," + price + "," + isDeleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
