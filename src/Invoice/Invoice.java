package Invoice;

import Enum.PayMethod;
import Enum.InvoiceStatus;
import Product.Product;
import Utils.DateUtil;
import Utils.Formatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Invoice {

    private String invoiceID;
    private String employeeID;
    private String customerPhone;
    private LocalDateTime invoiceDate;
    private ArrayList<InvoiceDetail> detail;
    private double totalAmount; // tong hoa don
    private double discountAmount; // số tiền được chiết khấu 
    private PayMethod payMethod;
    private InvoiceStatus status;

    public Invoice() {

    }

    public Invoice(String invoiceID, String employeeID, String customerPhone) {
        this.invoiceID = invoiceID;
        this.employeeID = employeeID;
        this.customerPhone = customerPhone;
        this.invoiceDate = LocalDateTime.now();
        this.detail = new ArrayList<>();
        this.totalAmount = 0;
        this.discountAmount = 0;
        this.status = InvoiceStatus.PENDING;
    }

    public void addProduct(String productID, int quantity, Product product) {
        InvoiceDetail exis = findDetail(productID);
        if (exis != null) {
            exis.increaseQuantity(quantity);
        } else {
            InvoiceDetail detail1 = new InvoiceDetail(product, quantity);
            detail.add(detail1);
        }
        calculateTotal();
    }

    public void removePrroduct(String productID) {
        InvoiceDetail exis = findDetail(productID);
        if (exis != null) {
            detail.remove(exis);
            calculateTotal();
        }

    }

    public void updateQuantity(String productID, int quantity) {
        InvoiceDetail exis = findDetail(productID);
        if (exis != null) {
            exis.setQuantity(quantity); // set trực tiếp số lượng mới
            calculateTotal();
        }
    }

    public void updateAddQuantity(String productID, int quantity) {
        InvoiceDetail exis = findDetail(productID);
        if (exis != null) {
            exis.increaseQuantity(quantity);
            calculateTotal();
        }
    }

    public void updateDesceaseQuantity(String productID, int quantity) {
        InvoiceDetail exis = findDetail(productID);
        if (exis != null) {
            exis.descreaseQuantity(quantity);
            calculateTotal();
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (InvoiceDetail i : detail) {
            total += i.getSubTotal();
        }
        this.totalAmount = total;
        return total;
    }

    // tính chiết khấu háo đơn 
    public void chietKhau(double point) {
        if (point < 0) {
            point = 0;
        }
        if (point > totalAmount) {
            point = totalAmount;
        }
        this.discountAmount = point;
    }

    // cấp nhập số tiền cần trả của khách hàng sau khi tính tổng hóa đơn và chiết khấu 
    public double getFinalAmount() {
        return totalAmount - discountAmount;
    }

    public void displayInvoice() {
        System.out.println("================== HÓA ĐƠN ==================");
        System.out.println("Mã HĐ: " + invoiceID);
        System.out.println("Nhân viên: " + employeeID);
        System.out.println("Khách hàng: " + customerPhone);
        System.out.println("Ngày lập: " + DateUtil.format(invoiceDate));
        System.out.println("---------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-10s %-10s%n", "ProductID", "ProoductName",
                "Quantity", "Price", "Total");
        for (InvoiceDetail d : detail) {
            d.display();
        }
        System.out.println("--------------------------------------------");
        System.out.println("Tổng tiền hàng : " + Formatter.currency(totalAmount));

        if (discountAmount > 0) {
            System.out.println("Chiết khấu :" + Formatter.currency(discountAmount));
        }
        System.out.println("Thành Tiền: " + Formatter.currency(getFinalAmount()));
        System.out.println("Thanh toán: " + payMethod);
        System.out.println("Trạng thái: " + status);
    }

    private InvoiceDetail findDetail(String productID) {
        if (!detail.isEmpty()) {
            for (InvoiceDetail i : detail) {
                if (i.getProductID().equalsIgnoreCase(productID)) {
                    return i;
                }
            }
        }
        return null;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<InvoiceDetail> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<InvoiceDetail> detail) {
        this.detail = detail;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
