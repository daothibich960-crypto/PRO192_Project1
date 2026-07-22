package Purchase;

import Supplier.Supplier;
import Utils.Formatter;
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
        System.out.println("PHIẾU NHẬP HÀNG");
        System.out.println("==============================================================");
        System.out.println("Mã phiếu    : " + receiptId);
        System.out.println("Ngày nhập   : " + importDate);
        System.out.println("Nhà cung cấp: "
                + (supplier != null ? supplier.getSupplierName() : "Không có"));
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-10s %-15s %-15s\n",
                "MÃ",
                "SẢN PHẨM",
                "SỐ LƯỢNG",
                "GIÁ NHẬP",
                "THÀNH TIỀN");
        for (PurchaseDetail detail : detailList) {
            detail.displayDetail();
        }
        System.out.println("--------------------------------------------------------------");
        calculateTotal();
        System.out.printf("TỔNG TIỀN : %s\n", Formatter.currency(totalAmount));
    }

    @Override
    public String toString() {
        return "Mã phiếu: " + receiptId
                + ", Ngày nhập: " + importDate
                + ", Nhà cung cấp: "
                + (supplier != null ? supplier.getSupplierName() : "Không có")
                + ", Tổng tiền: "
                + Formatter.currency(totalAmount);
    }
}