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
        totalAmount = 0;

    }

    public PurchaseReceipt(String receiptId,
            Date importDate,
            Supplier supplier) {

        this.receiptId = receiptId;
        this.importDate = importDate;
        this.supplier = supplier;

        detailList = new ArrayList<>();
        totalAmount = 0;

    }

    //================ GETTER & SETTER ================

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

    //================ BUSINESS METHOD ================

    public void addDetail(PurchaseDetail detail) {

        detailList.add(detail);

        calculateTotal();

    }

    public void removeDetail(PurchaseDetail detail) {

        detailList.remove(detail);

        calculateTotal();

    }

    public void calculateTotal() {

        totalAmount = 0;

        for (PurchaseDetail detail : detailList) {

            totalAmount += detail.getSubTotal();

        }

    }

    public void displayReceipt() {

        calculateTotal();

        System.out.println("==============================================================");
        System.out.println("PHIẾU NHẬP HÀNG");
        System.out.println("==============================================================");

        System.out.println("Mã phiếu : " + receiptId);
        System.out.println("Ngày nhập: " + importDate);

        if (supplier != null) {

            System.out.println("Nhà cung cấp: "
                    + supplier.getSupplierName());

        }

        System.out.println("--------------------------------------------------------------");

        System.out.printf("%-10s %-25s %-10s %-15s %-15s\n",
                "Mã",
                "Sản phẩm",
                "SL",
                "Giá nhập",
                "Thành tiền");

        for (PurchaseDetail detail : detailList) {

            detail.displayDetail();

        }

        System.out.println("--------------------------------------------------------------");

        System.out.printf("TỔNG TIỀN: %.2f\n", totalAmount);

    }

    @Override
    public String toString() {

        String supplierName = "N/A";

        if (supplier != null) {

            supplierName = supplier.getSupplierName();

        }

        return "Receipt ID: "
                + receiptId
                + ", Supplier: "
                + supplierName
                + ", Total: "
                + totalAmount;

    }

} 
