package Supplier;

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

            System.out.println("Supplier ID already exists!");
            return;

        }

        list.add(supplier);

        System.out.println("Add supplier successfully.");

    }


    // Xóa

    public void removeSupplier(String supplierId) {

        Supplier supplier = searchSupplier(supplierId);

        if (supplier == null) {

            System.out.println("Supplier not found.");
            return;

        }

        list.remove(supplier);

        System.out.println("Delete successfully.");

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

            System.out.println("Supplier list is empty.");
            return;

        }

        System.out.println("===============================================================");
        System.out.printf("%-10s %-25s %-15s %-30s %-25s\n",
                "ID",
                "NAME",
                "PHONE",
                "EMAIL",
                "ADDRESS");
        System.out.println("===============================================================");

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

            System.out.println("Supplier not found.");
            return;

        }

        supplier.setSupplierName(supplierName);
        supplier.setPhone(phone);
        supplier.setEmail(email);
        supplier.setAddress(address);

        System.out.println("Update successfully.");

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
public void loadFromFile() {

    list.clear();

    try {

        BufferedReader br = new BufferedReader(new FileReader("data/supplier.txt"));

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split("\\|");

            Supplier supplier = new Supplier(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4]);

            list.add(supplier);

        }

        br.close();

        System.out.println("Load supplier successfully.");

    } catch (IOException e) {

        System.out.println("Cannot read supplier.txt");

    }

}// Lưu dữ liệu xuống file
public void saveToFile() {

    try {

        PrintWriter pw = new PrintWriter(new FileWriter("data/supplier.txt"));

        for (Supplier supplier : list) {

            pw.println(
                    supplier.getSupplierId() + "|"
                    + supplier.getSupplierName() + "|"
                    + supplier.getPhone() + "|"
                    + supplier.getEmail() + "|"
                    + supplier.getAddress());

        }

        pw.close();

        System.out.println("Save supplier successfully.");

    } catch (IOException e) {

        System.out.println("Cannot save supplier.txt");

    }

}

}