import java.util.ArrayList;
import java.util.List;

/* =========================
   Product Class
   ========================= */
class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}


/* =========================
   ShoppingCart Class
   ========================= */
class ShoppingCart {

    private List<Product> productList;

    public ShoppingCart() {
        productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProducts() {
        return productList;
    }

    public double calculateTotalPrice() {
        double total = 0;

        for (Product product : productList) {
            total += product.getPrice();
        }

        return total;
    }
}


/* =========================
   Invoice Printer
   ========================= */
class InvoicePrinter {

    public void printInvoice(ShoppingCart cart) {
        System.out.println("----- INVOICE -----");

        for (Product product : cart.getProducts()) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }

        System.out.println("-------------------");
        System.out.println("Total: $" + cart.calculateTotalPrice());
    }
}


/* =========================
   Persistence Abstraction
   ========================= */
interface Persistence {
    void save(ShoppingCart cart);
}


/* =========================
   SQL Persistence
   ========================= */
class SQLPersistence implements Persistence {

    @Override
    public void save(ShoppingCart cart) {
        System.out.println("\nSaving cart to SQL Database...");

        for (Product product : cart.getProducts()) {
            System.out.println("INSERT INTO products VALUES ('"
                    + product.getName() + "', "
                    + product.getPrice() + ")");
        }

        System.out.println("Cart saved to SQL successfully.");
    }
}


/* =========================
   File Persistence
   ========================= */
class FilePersistence implements Persistence {

    @Override
    public void save(ShoppingCart cart) {
        System.out.println("\nSaving cart to File...");

        for (Product product : cart.getProducts()) {
            System.out.println("Writing to file: "
                    + product.getName() + " - $"
                    + product.getPrice());
        }

        System.out.println("Cart saved to File successfully.");
    }
}


/* =========================
   Main Class
   ========================= */
public class OpenClosePrinciple {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 1500));
        cart.addProduct(new Product("Mouse", 50));

        // Print invoice
        InvoicePrinter printer = new InvoicePrinter();
        printer.printInvoice(cart);

        // Create multiple persistence objects
        Persistence sqlPersistence = new SQLPersistence();
        Persistence filePersistence = new FilePersistence();

        // Call save on both
        sqlPersistence.save(cart);
        filePersistence.save(cart);
    }
}
