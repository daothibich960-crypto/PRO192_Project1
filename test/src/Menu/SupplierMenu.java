package Menu;

import Supplier.Supplier;
import Supplier.SupplierList;
import java.util.Scanner;

public class SupplierMenu {

    private Scanner sc;
    private SupplierList supplierList;

    public SupplierMenu(SupplierList supplierList) {

        this.sc = new Scanner(System.in);
        this.supplierList = supplierList;

    }

    public void menu() {

        int choice;

        do {

            System.out.println("\n================ QUẢN LÝ NHÀ CUNG CẤP ================");
            System.out.println("1. Thêm nhà cung cấp");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Tìm nhà cung cấp");
            System.out.println("4. Cập nhật nhà cung cấp");
            System.out.println("5. Xóa nhà cung cấp");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn: ");

            try {

                choice = Integer.parseInt(sc.nextLine());

            } catch (Exception e) {

                choice = -1;

            }

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

                    System.out.println("Quay lại.");

                    break;

                default:

                    System.out.println("Lựa chọn không hợp lệ!");

            }

        } while (choice != 0);

    }

    private void addSupplier() {

        System.out.println("\n===== THÊM NHÀ CUNG CẤP =====");

        System.out.print("Mã NCC: ");
        String id = sc.nextLine();

        System.out.print("Tên NCC: ");
        String name = sc.nextLine();

        System.out.print("Số điện thoại: ");
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Địa chỉ: ");
        String address = sc.nextLine();

        Supplier supplier =
                new Supplier(id, name, phone, email, address);

        supplierList.addSupplier(supplier);

        supplierList.saveToFile();

    }

    private void searchSupplier() {

        System.out.print("Nhập mã NCC: ");

        String id = sc.nextLine();

        Supplier supplier = supplierList.searchSupplier(id);

        if (supplier == null) {

            System.out.println("Không tìm thấy.");

            return;

        }

        supplier.displaySupplier();

    }

    private void updateSupplier() {

        System.out.print("Nhập mã NCC: ");

        String id = sc.nextLine();

        if (!supplierList.containsSupplier(id)) {

            System.out.println("Không tìm thấy.");

            return;

        }

        System.out.print("Tên mới: ");
        String name = sc.nextLine();

        System.out.print("SĐT mới: ");
        String phone = sc.nextLine();

        System.out.print("Email mới: ");
        String email = sc.nextLine();

        System.out.print("Địa chỉ mới: ");
        String address = sc.nextLine();

        supplierList.updateSupplier(
                id,
                name,
                phone,
                email,
                address);

        supplierList.saveToFile();

    }

    private void deleteSupplier() {

        System.out.print("Nhập mã NCC: ");

        String id = sc.nextLine();

        supplierList.removeSupplier(id);

        supplierList.saveToFile();

    }

}