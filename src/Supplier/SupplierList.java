package Supplier;


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

}