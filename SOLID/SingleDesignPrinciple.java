import java.util.ArrayList;
import java.util.List;

class Product {
    public double price;
    public String name;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class ShoppingCart {

    List<Product> productList;

    public ShoppingCart() {
        productList = new ArrayList<>();
    }

    void addProduct(Product p) {
        productList.add(p);
    }

    List<Product> getProducts() {
        return productList;
    }

    double calculateTotalPrice() {
        double totalPrice = 0;

        for (Product p : productList) {
            totalPrice += p.price;
        }

        return totalPrice;
    }
}

class InvoicePrinter {

    public void printInvoice(ShoppingCart cart) {
        for (Product p : cart.getProducts()) {
            System.out.println(p.name + " - $" + p.price);
        }

        System.out.println("Total: $" + cart.calculateTotalPrice());
    }
}

public class SingleDesignPrinciple {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 1500));
        cart.addProduct(new Product("Mouse", 50));

        InvoicePrinter printer = new InvoicePrinter();
        printer.printInvoice(cart);
    }
}

