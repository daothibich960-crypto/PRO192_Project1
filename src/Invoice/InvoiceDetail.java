
package Invoice;

import Product.Product;
import Utils.Formatter;

public class InvoiceDetail {
    private String productID;
    private String productName;
    private int quantity;
    private double price;
    private double subTotal;

    public InvoiceDetail() {
    }

    public InvoiceDetail( Product product,int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = product.getSellingPrice(); // lấy giá ở sản phẩm
        this.subTotal = calculateSubTotal();
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    public double calculateSubTotal(){
        double total = price * quantity;
        return total;
    }
    public void increaseQuantity( int quantity){
        int q = this.quantity + quantity ;
        setQuantity(q);
        calculateSubTotal();// cập nhật lại subTotal sau khi đổi số lượng
    }
    public void descreaseQuantity(int quantity ){
        if (this.quantity < quantity) {
            System.out.println("Số lượng không đủ để giảm!!");
            return ;
        }
        int q = this.quantity - quantity;
        setQuantity(q);
        calculateSubTotal();
    }
    
    public void display(){
        System.out.printf("%-10s %-20s %-3d %-10.3f %-10.3f",
                productID , productName ,quantity,Formatter.currency(price),
                Formatter.currency(subTotal));
    }
    
        
}
