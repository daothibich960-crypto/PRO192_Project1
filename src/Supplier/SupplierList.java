package Supplier;

import FileIO.FileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SupplierList {

    private List<Supplier> list;

    public SupplierList() {
        list = new ArrayList<>();
    }

    // Thêm nhà cung cấp
    public void addSupplier(Supplier supplier) {
        if (containsSupplier(supplier.getSupplierId())) {
            System.out.println("Mã nhà cung cấp đã tồn tại!");
            return;
        }
        list.add(supplier);
        System.out.println("Thêm nhà cung cấp thành công.");
    }

    // Xóa
    public void removeSupplier(String supplierId) {
        Supplier supplier = searchSupplier(supplierId);
        if (supplier == null) {
            System.out.println("Không tìm thấy nhà cung cấp.");
            return;
        }
        list.remove(supplier);
        System.out.println("Xóa thành công.");
    }

    // Tìm theo ID
    public Supplier searchSupplier(String supplierId) {
        if (!list.isEmpty()) {
            for (Supplier supplier : list) {
                if (supplier.getSupplierId().equalsIgnoreCase(supplierId)) {
                    return supplier;
                }
            }
        }
        return null;
    }

    // Kiểm tra tồn tại
    public boolean containsSupplier(String supplierId) {
        return searchSupplier(supplierId) != null;
    }

    // Hiển thị
    public void displayAllSupplier() {
        if (list.isEmpty()) {
            System.out.println("Danh sách nhà cung cấp đang trống.");
            return;
        }
        System.out.println("=====================================================================================================================");
        System.out.printf("%-10s %-40s %-15s %-30s %-25s\n",
                "MÃ",
                "TÊN",
                "SỐ ĐIỆN THOẠI",
                "EMAIL",
                "ĐỊA CHỈ");
        System.out.println("=====================================================================================================================");
        for (Supplier supplier : list) {
            supplier.displaySupplier();
        }
    }

    // Cập nhật
    public void updateSupplier(String supplierId,
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

    // Đếm số lượng
    public int getTotalSupplier() {
        return list.size();
    }

    // Xóa toàn bộ
    public void clear() {
        list.clear();
    }

    // Lấy danh sách
    public List<Supplier> getList() {
        return list;
    }

    // Đọc dữ liệu từ file
    public void loadFromFile(String filePath) {
        list.clear();
        List<Supplier> loaded = FileIO.readFile(filePath, fields -> new Supplier(
                fields[0].trim(), // supplierId
                fields[1].trim(), // supplierName
                fields[2].trim(), // phone
                fields[3].trim(), // email
                fields[4].trim() // address
        ));
        list.addAll(loaded);
    }

    public void saveToFile(String filePath) {
        FileIO.writeFile(filePath, list, s
                -> s.getSupplierId() + "|"
                + s.getSupplierName() + "|"
                + s.getPhone() + "|"
                + s.getEmail() + "|"
                + s.getAddress()
        );
    }
}