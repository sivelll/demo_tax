import java.util.ArrayList;
import java.util.List;

// 購物收據生成器
public class ReceiptGenerator {
    private List<Product> products;
    private String state;

    public ReceiptGenerator(String state) {
        this.state = state;
        this.products = new ArrayList<>();
    }

    // 添加購買的產品
    public void addProduct(Product product) {
        products.add(product);
    }

    // 計算總金額
    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getTotalPrice();
        }
        return total;
    }

    // 計算總稅金
    public double calculateTax() {
        double tax = 0;
        double taxRate = TaxCalculator.getTaxRate(state);
        for (Product product : products) {
            if (!TaxCalculator.isTaxExempt(state, product.getCategory())) {
                tax += product.getTotalPrice() * taxRate;
            }
        }
        return TaxCalculator.roundToNearest0_05(tax);
    }

    // 生成並印出收據
    public void generateReceipt() {
        System.out.println("item\tprice\tqty");
        for (Product product : products) {
            System.out.printf("%s\t$%.2f\t%d\n", product.getName(), product.getTotalPrice(), product.getQuantity());
        }
        double subtotal = calculateTotal();
        double tax = calculateTax();
        double total = subtotal + tax;

        System.out.printf("subtotal: $%.2f\n", subtotal);
        System.out.printf("tax: $%.2f\n", tax);
        System.out.printf("total: $%.2f\n", total);
    }

    // 主函式用來執行範例
    public static void main(String[] args) {
        // Use Case 1: Location: CA, 1 book at 17.99, 1 potato chips at 3.99
        ReceiptGenerator receipt1 = new ReceiptGenerator("CA");
        receipt1.addProduct(new Product("book", 17.99, 1, "general"));
        receipt1.addProduct(new Product("potato chips", 3.99, 1, "food"));
        receipt1.generateReceipt();

        System.out.println("\n");

        // Use Case 2: Location: NY, 1 book at 17.99, 3 pencils at 2.99
        ReceiptGenerator receipt2 = new ReceiptGenerator("NY");
        receipt2.addProduct(new Product("book", 17.99, 1, "general"));
        receipt2.addProduct(new Product("pencil", 2.99, 3, "general"));
        receipt2.generateReceipt();

        System.out.println("\n");

        // Use Case 3: Location: NY, 2 pencils at 2.99, 1 shirt at 29.99
        ReceiptGenerator receipt3 = new ReceiptGenerator("NY");
        receipt3.addProduct(new Product("pencil", 2.99, 2, "general"));
        receipt3.addProduct(new Product("shirt", 29.99, 1, "clothing"));
        receipt3.generateReceipt();
    }
}
