
package Purchase;

import Product.Product;
import Utils.Formatter;

public class PurchaseDetail {

    private Product product;
    private int quantity;
    private double importPrice;
    private double subTotal;

    public PurchaseDetail() {
    }

    public PurchaseDetail(Product product, int quantity, double importPrice) {
        this.product = product;
        this.quantity = quantity;
        this.importPrice = importPrice;
        calculateSubTotal();
    }

    //================ Getter & Setter =================

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateSubTotal();
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
        calculateSubTotal();
    }

    public double getSubTotal() {
        return subTotal;
    }

    //================ Business Method =================

    public void calculateSubTotal() {
        subTotal = quantity * importPrice;
    }

    public void displayDetail() {

        System.out.printf("%-12s %-25s %-10d %-15s %-15s\n",
                product.getProductId(),
                product.getProductName(),
                quantity,
                Formatter.currency(importPrice),
                Formatter.currency(subTotal));

    }

    @Override
    public String toString() {

        return String.format("%-12s %-25s %-10d %-15.2f %-15.2f",
                product.getProductId(),
                product.getProductName(),
                quantity,
                Formatter.currency(importPrice),
                Formatter.currency(subTotal));

    }

}
