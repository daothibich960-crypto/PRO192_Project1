/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import List.Inventory;
import Supplier.SupplierList;
import java.util.Scanner;

public class ProductMenu {

    private Inventory inventory;
    private SupplierList supplierList;
    private Scanner sc;

    public ProductMenu(Inventory inventory, Scanner sc) {

        this.inventory = inventory;
        this.sc = sc;

        supplierList = new SupplierList();
        supplierList.loadFromFile();
    }

    public void show() {

        int choice = -1;

        do {

            System.out.println("\n========== QUẢN LÝ SẢN PHẨM ==========");
            System.out.println("1. Quản lý nhà cung cấp");
            System.out.println("2. Hiển thị tồn kho");
            System.out.println("3. Nhập hàng");
            System.out.println("4. Kiểm tra sản phẩm sắp hết");
            System.out.println("0. Quay lại");

            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số.");
                continue;
            }

            switch (choice) {

                case 1:
                    new SupplierMenu(supplierList).menu();
                    break;

                case 2:
                    inventory.displayInventory();
                    break;

                case 3:
                    importProduct();
                    break;

                case 4:
                    System.out.print("Nhập mức tồn tối thiểu: ");

                    try {
                        int min = Integer.parseInt(sc.nextLine());
                        inventory.checkLowStock(min);
                    } catch (NumberFormatException e) {
                        System.out.println("Số không hợp lệ.");
                    }

                    break;

                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }

    /**
     * Nhập hàng (xử lý ngầm - thay thế PurchaseMenu)
     */
    private void importProduct() {

        System.out.println("\n========== NHẬP HÀNG ==========");

        System.out.print("Nhập mã sản phẩm: ");
        String productId = sc.nextLine();

        if (inventory.searchProduct(productId) == null) {
            System.out.println("Không tìm thấy sản phẩm.");
            return;
        }

        try {

            System.out.print("Nhập số lượng: ");
            int quantity = Integer.parseInt(sc.nextLine());

            int current = inventory.getCurrentQuantity(productId);

            inventory.updateProductQuantity(productId, current + quantity);

            System.out.println("Nhập hàng thành công.");

        } catch (NumberFormatException e) {

            System.out.println("Dữ liệu không hợp lệ.");

        }
    }
}
