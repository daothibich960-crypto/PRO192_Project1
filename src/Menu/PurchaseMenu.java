package Menu;

import Inventory.Inventory;
import List.PurchaseList;
import Product.Product;
import Purchase.PurchaseDetail;
import Purchase.PurchaseReceipt;
import Supplier.Supplier;
import Supplier.SupplierList;
import java.util.Date;
import java.util.Scanner;

public class PurchaseMenu {

    private Scanner sc;
    private PurchaseList purchaseList;
    private SupplierList supplierList;
    private Inventory inventory;

    public PurchaseMenu(Inventory inventory, SupplierList supplierList,
            Scanner sc) {
        this.sc = sc;
        this.inventory = inventory;
        this.supplierList = supplierList;
        this.purchaseList = new PurchaseList();

    }

    public void show() {

        int choice;

        do {

            System.out.println("\n========== QUẢN LÝ NHẬP HÀNG ==========");
            System.out.println("1. Quản lý nhà cung cấp");
            System.out.println("2. Tạo phiếu nhập hàng");
            System.out.println("3. Hiển thị phiếu nhập hàng");
            System.out.println("4. Hiển thị tất cả sản phẩm trong kho");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    SupplierMenu supplierMenu = new SupplierMenu(supplierList, sc);
                    supplierMenu.show();

                    break;

                case 2:

                    createReceipt();

                    break;

                case 3:

                    purchaseList.displayAllReceipt();

                    break;

                case 4:

                    inventory.displayInventory();

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Lựa chọn không hợp lệ!");

            }

        } while (choice != 0);

    }

    private void createReceipt() {
        System.out.println("\n========== TẠO PHIẾU NHẬP HÀNG ==========");

        //========================================
        // Chọn Supplier
        //========================================
        if (supplierList.getList().isEmpty()) {
            System.out.println("Chưa có nhà cung cấp nào. Vui lòng thêm nhà cung cấp trước.");
            return;
        }

        System.out.print("Nhập mã nhà cung cấp: ");
        String supplierId = sc.nextLine().trim();
        Supplier supplier = supplierList.searchSupplier(supplierId);

        if (supplier == null) {
            System.out.println("Không tìm thấy nhà cung cấp với mã: " + supplierId);
            return;
        }

        //========================================
        // Sinh mã phiếu nhập
        //========================================
        String receiptId = "PR" + System.currentTimeMillis();
        Date importDate = new Date();

        PurchaseReceipt receipt = new PurchaseReceipt(receiptId, importDate, supplier);

        System.out.println("Mã phiếu nhập: " + receiptId);

        //========================================
        // Vòng lặp thêm sản phẩm vào phiếu
        //========================================
        boolean addingMore = true;
        while (addingMore) {

            System.out.print("\nNhập mã sản phẩm (hoặc 'x' để kết thúc): ");
            String productId = sc.nextLine().trim();

            if (productId.equalsIgnoreCase("x")) {
                addingMore = false;
                continue;
            }

            Product product = inventory.searchProduct(productId);

            if (product == null) {
                System.out.println("Không tìm thấy sản phẩm với mã: " + productId);
                continue;
            }

            System.out.println("Sản phẩm: " + product.getProductName());

            System.out.print("Số lượng nhập: ");
            int quantity;
            try {
                quantity = Integer.parseInt(sc.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println("Số lượng phải lớn hơn 0.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Số lượng không hợp lệ.");
                continue;
            }

            System.out.print("Giá nhập: ");
            double importPrice;
            try {
                importPrice = Double.parseDouble(sc.nextLine().trim());
                if (importPrice < 0) {
                    System.out.println("Giá nhập không hợp lệ.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá nhập không hợp lệ.");
                continue;
            }

            PurchaseDetail detail = new PurchaseDetail(product, quantity, importPrice);
            receipt.addDetail(detail);

            System.out.println("Đã thêm sản phẩm vào phiếu nhập.");
        }

        //========================================
        // Kiểm tra phiếu có sản phẩm không
        //========================================
        if (receipt.getDetailList().isEmpty()) {
            System.out.println("Phiếu nhập không có sản phẩm nào. Hủy tạo phiếu.");
            return;
        }

        //========================================
        // Hiển thị xác nhận trước khi lưu
        //========================================
        receipt.displayReceipt();

        System.out.print("\nXác nhận lưu phiếu nhập? (y/n): ");
        String confirm = sc.nextLine().trim();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Đã hủy tạo phiếu nhập.");
            return;
        }

        //========================================
        // Cập nhật tồn kho + lưu dữ liệu
        //========================================
        for (PurchaseDetail detail : receipt.getDetailList()) {
            inventory.stockIn(detail.getProduct().getProductId(), detail.getQuantity());
        }

        purchaseList.addReceipt(receipt);

        inventory.saveToFile("Data/product.txt");

        System.out.println();
        System.out.println("========================================");
        System.out.println("    TẠO PHIẾU NHẬP THÀNH CÔNG!");
        System.out.println("========================================");
    }

}