package Supplier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SupplierList {

    private ArrayList<Supplier> list;

    public SupplierList() {

        list = new ArrayList<>();

    }

    // =====================================
    // Thêm nhà cung cấp
    // =====================================
    public void addSupplier(Supplier supplier) {

        if (containsSupplier(supplier.getSupplierId())) {

            System.out.println("Mã nhà cung cấp đã tồn tại!");
            return;

        }

        list.add(supplier);

        System.out.println("Thêm nhà cung cấp thành công.");

    }

    // =====================================
    // Xóa nhà cung cấp
    // =====================================
    public void removeSupplier(String supplierId) {

        Supplier supplier = searchSupplier(supplierId);

        if (supplier == null) {

            System.out.println("Không tìm thấy nhà cung cấp.");
            return;

        }

        list.remove(supplier);

        System.out.println("Xóa thành công.");

    }

    // =====================================
    // Tìm theo ID
    // =====================================
    public Supplier searchSupplier(String supplierId) {

        for (Supplier supplier : list) {

            if (supplier.getSupplierId()
                    .equalsIgnoreCase(supplierId)) {

                return supplier;

            }

        }

        return null;

    }

    // =====================================
    // Kiểm tra tồn tại
    // =====================================
    public boolean containsSupplier(String supplierId) {

        return searchSupplier(supplierId) != null;

    }

    // =====================================
    // Hiển thị danh sách
    // =====================================
    public void displayAllSupplier() {

        if (list.isEmpty()) {

            System.out.println("Danh sách nhà cung cấp trống.");
            return;

        }

        System.out.println("\n================ DANH SÁCH NHÀ CUNG CẤP ================");

        System.out.printf(
                "%-10s %-20s %-15s %-25s %-20s\n",
                "MÃ",
                "TÊN",
                "SĐT",
                "EMAIL",
                "ĐỊA CHỈ");

        System.out.println("--------------------------------------------------------------------------");

        for (Supplier supplier : list) {

            supplier.displaySupplier();

        }

    }

    // =====================================
    // Cập nhật
    // =====================================
    public void updateSupplier(
            String supplierId,
            String supplierName,
            String phone,
            String email,
            String address) {

        Supplier supplier = searchSupplier(supplierId);

        if (supplier == null) {

            System.out.println("Không tìm thấy nhà cung cấp.");
            return;

        }

        supplier.setSupplierName(supplierName);
        supplier.setPhone(phone);
        supplier.setEmail(email);
        supplier.setAddress(address);

        System.out.println("Cập nhật thành công.");

    }

    // =====================================
    // Số lượng NCC
    // =====================================
    public int getTotalSupplier() {

        return list.size();

    }

    // =====================================
    // Lấy danh sách
    // =====================================
    public ArrayList<Supplier> getList() {

        return list;

    }

    // =====================================
    // Xóa toàn bộ
    // =====================================
    public void clear() {

        list.clear();

    }

    // =====================================
    // Đọc file
    // =====================================
    public void loadFromFile() {

        list.clear();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader("Data/supplier.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length >= 5) {

                    Supplier supplier = new Supplier(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4]);

                    list.add(supplier);

                }

            }

            br.close();

            System.out.println("Tải dữ liệu nhà cung cấp thành công.");

        } catch (IOException e) {

            System.out.println("Không tìm thấy file supplier.txt");

        }

    }

    // =====================================
    // Lưu file
    // =====================================
    public void saveToFile() {

        try {

            PrintWriter pw =
                    new PrintWriter(
                            new FileWriter("Data/supplier.txt"));

            for (Supplier supplier : list) {

                pw.println(
                        supplier.getSupplierId() + "|"
                        + supplier.getSupplierName() + "|"
                        + supplier.getPhone() + "|"
                        + supplier.getEmail() + "|"
                        + supplier.getAddress());

            }

            pw.close();

            System.out.println("Lưu dữ liệu thành công.");

        } catch (IOException e) {

            System.out.println("Không thể lưu file supplier.txt");

        }

    }

}