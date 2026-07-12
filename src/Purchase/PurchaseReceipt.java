package Purchase;

import Supplier.Supplier;
import java.util.ArrayList;
import java.util.Date;

public class PurchaseReceipt {

    private String receiptId;
    private Date importDate;
    private Supplier supplier;
    private ArrayList<PurchaseDetail> detailList;
    private double totalAmount;

    public PurchaseReceipt() {
        detailList = new ArrayList<>();
    }

    public PurchaseReceipt(String receiptId,
            Date importDate,
            Supplier supplier) {

        this.receiptId = receiptId;
        this.importDate = importDate;
        this.supplier = supplier;
        this.detailList = new ArrayList<>();
        this.totalAmount = 0;

    }

    //================ Getter & Setter =================

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ArrayList<PurchaseDetail> getDetailList() {
        return detailList;
    }

    public double getTotalAmount() {
        calculateTotal();
        return totalAmount;
    }

    //================ Business Method =================

    // Thêm sản phẩm vào phiếu nhập
    public void addDetail(PurchaseDetail detail) {

        detailList.add(detail);

        calculateTotal();

    }

    // Xóa sản phẩm khỏi phiếu nhập
    public void removeDetail(PurchaseDetail detail) {

        detailList.remove(detail);

        calculateTotal();

    }

    // Tính tổng tiền
    public void calculateTotal() {

        totalAmount = 0;

        for (PurchaseDetail detail : detailList) {

            totalAmount += detail.getSubTotal();

        }

    }

    // Hiển thị phiếu nhập
    public void displayReceipt() {

        System.out.println("==============================================================");
        System.out.println("PURCHASE RECEIPT");
        System.out.println("==============================================================");

        System.out.println("Receipt ID : " + receiptId);
        System.out.println("Import Date: " + importDate);

        if (supplier != null) {

            System.out.println("Supplier   : "
                    + supplier.getSupplierName());

        }

        System.out.println("--------------------------------------------------------------");

        System.out.printf("%-12s %-25s %-10s %-15s %-15s\n",
                "ID",
                "Product",
                "Quantity",
                "Import Price",
                "Sub Total");

        for (PurchaseDetail detail : detailList) {

            detail.displayDetail();

        }

        System.out.println("--------------------------------------------------------------");

        calculateTotal();

        System.out.printf("TOTAL AMOUNT : %.2f\n", totalAmount);

    }

    @Override
    public String toString() {

        return "Receipt ID: " + receiptId
                + ", Import Date: " + importDate
                + ", Supplier: "
                + supplier.getSupplierName()
                + ", Total: "
                + totalAmount;

    }
}

    
