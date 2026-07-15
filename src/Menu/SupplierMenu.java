package Menu;

import Supplier.Supplier;
import Supplier.SupplierList;
import java.util.Scanner;

public class SupplierMenu {

    private SupplierList supplierList;
    private Scanner sc;

    public SupplierMenu(SupplierList supplierList, Scanner sc) {
        this.supplierList = supplierList;
        this.sc = sc;   // không tự new Scanner nữa
    }

    public void show() {
        int choice;
        do {
            System.out.println("\n========== QUẢN LÝ NHÀ CUNG CẤP ==========");
            System.out.println("1. Thêm nhà cung cấp");
            System.out.println("2. Hiển thị nhà cung cấp");
            System.out.println("3. Tìm kiếm nhà cung cấp");
            System.out.println("4. Cập nhật nhà cung cấp");
            System.out.println("5. Xóa nhà cung cấp");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addSupplier();
                    break;
                case 2:
                    supplierList.displayAllSupplier();
                    break;
                case 3:
                    searchSupplier();
                    break;
                case 4:
                    updateSupplier();
                    break;
                case 5:
                    deleteSupplier();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private void addSupplier() {
        System.out.print("Mã nhà cung cấp: ");
        String id = sc.nextLine();
        System.out.print("Tên nhà cung cấp: ");
        String name = sc.nextLine();
        System.out.print("Số điện thoại: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Địa chỉ: ");
        String address = sc.nextLine();

        Supplier supplier = new Supplier(id, name, phone, email, address);
        supplierList.addSupplier(supplier);
        supplierList.saveToFile("Data/supplier.txt");
    }

    private void searchSupplier() {
        System.out.print("Mã nhà cung cấp: ");
        String id = sc.nextLine();
        Supplier supplier = supplierList.searchSupplier(id);
        if (supplier == null) {
            System.out.println("Không tìm thấy nhà cung cấp.");
        } else {
            supplier.displaySupplier();
        }
    }

    private void updateSupplier() {
        System.out.print("Mã nhà cung cấp: ");
        String id = sc.nextLine();
        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Số điện thoại mới: ");
        String phone = sc.nextLine();
        System.out.print("Email mới: ");
        String email = sc.nextLine();
        System.out.print("Địa chỉ mới: ");
        String address = sc.nextLine();

        supplierList.updateSupplier(id, name, phone, email, address);
        supplierList.saveToFile("Data/supplier.txt");
    }

    private void deleteSupplier() {
        System.out.print("Mã nhà cung cấp: ");
        String id = sc.nextLine();
        supplierList.removeSupplier(id);
        supplierList.saveToFile("Data/supplier.txt");
    }
}
