package Service;

import Enum.InvoiceStatus;
import Inventory.Inventory;
import Invoice.Invoice;
import Invoice.InvoiceDetail;
import List.CustomerList;

import List.InvoiceList;
import Product.Product;
import Utils.IDGenerator;

public class SaleService {

    private InvoiceList invoiceList;
    private CustomerList customerList;
    private Inventory inventory;
    private MemberShipService member;

    public SaleService(InvoiceList invoiceList, CustomerList customerList, Inventory inventory, MemberShipService member) {
        this.invoiceList = invoiceList;
        this.customerList = customerList;
        this.inventory = inventory;
        this.member = member;
    }

    
    //Tạo hóa đơn đưa trên ID của nhân viên
    public Invoice createInvoice(String employeeID) {
        String invoiceID = IDGenerator.generateInvoiceID() ; // sinh Id cua hoa don
        Invoice i = new Invoice(invoiceID, employeeID, null);
        return i;
    }

    public boolean checkout(Invoice invoice, int status) {
        if (invoice.getDetail().isEmpty()) {
            System.out.println("Hóa đơn trông, Không thể thanh toan!!");
            return false;
        }
        if (status < 0 ){
            return false;
        }
        double total = invoice.calculateTotal();
        if (invoice.getCustomerPhone() != null && !invoice.getCustomerPhone().isEmpty() ){
            member.earnPoint(invoice.getCustomerPhone(), total);
        }
        
        // update trạng thái của hóa đơn
        invoice.setStatus(InvoiceStatus.COMPLETE);
        // lưu hóa đơn vào danh sách hóa đơn
        saveInvoice(invoice);
        // giảm tồn kho của sản phẩm ở trong danh sách
        // update hàng hóa ở trong kho hàng
        updateInventory(invoice);
        return true;
    }

    public void cancelInvoice(Invoice invoice) {
        if (invoice.getStatus() == InvoiceStatus.COMPLETE){
            for (InvoiceDetail i: invoice.getDetail()){
                inventory.stockIn(i.getProductID(), i.getQuantity());
            }
            invoiceList.removeInvoice(invoice.getInvoiceID());
        }
        invoice.setStatus(InvoiceStatus.CANCEL);

    }
// trừ kho 
    private void updateInventory(Invoice invoice) {
        for (InvoiceDetail i: invoice.getDetail()){
            inventory.stockOut(i.getProductID(), i.getQuantity());
        }

    }

    private void saveInvoice(Invoice invoice) {
        invoiceList.addInvoice(invoice);
        System.out.println("Đã lưu hóa dơn: " + invoice.getInvoiceID());

    }
    public void addProduct(Invoice invoice, String productID, int quantity) {
    Product product = inventory.searchProduct(productID.toUpperCase());
    if (product == null) {
        System.out.println("Sản phẩm không tồn tại trong kho!");
        return;
    }
    if (inventory.getCurrentQuantity(productID) < quantity) {
            System.out.println("Không đủ hàng trong kho!");
            return;
        }
    invoice.addProduct(productID, quantity, product);
}

public void removeProduct(Invoice invoice, String productID) {
    invoice.removePrroduct(productID);
}

public void updateQuantity(Invoice invoice, String productID, int quantity) {
    Product product = inventory.searchProduct(productID);
    if (product == null) {
        System.out.println("Sản phẩm không tồn tại trong kho!");
        return;
    }
    if (quantity >= 0) {
        invoice.updateAddQuantity(productID, quantity);
    } else {
        invoice.updateDesceaseQuantity(productID, Math.abs(quantity));
    }
}

    public InvoiceList getInvoiceList() { return invoiceList; }
    public CustomerList getCustomerList() { return customerList; }
    public Inventory getInventory() { return inventory; }
}

