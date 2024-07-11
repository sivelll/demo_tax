// 產品類別
public class Product {
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // 計算單項產品的總價
    public double getTotalPrice() {
        return price * quantity;
    }

    // 取得產品名稱
    public String getName() {
        return name;
    }

    // 取得產品數量
    public int getQuantity() {
        return quantity;
    }

    // 取得產品類別
    public String getCategory() {
        return category;
    }
}
