package Service;

import List.ProductList;
import Product.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * Xử lý các nghiệp vụ liên quan đến Supplier.
 *
 * @author Long
 */
public class SupplierService {

    /**
     * Constructor
     */
    public SupplierService() {

    }

    /**
     * Lấy toàn bộ danh sách Supplier.
     *
     * @return danh sách Supplier
     */
    public List<Supplier> getAllSuppliers() {

        return ProductList.suppliers;

    }

    /**
     * Hiển thị danh sách Supplier.
     */
    public void displayAllSuppliers() {

        if (ProductList.suppliers.isEmpty()) {

            System.out.println("Supplier list is empty.");

            return;

        }

        System.out.println("==============================================================");
        System.out.printf("%-8s %-30s %-15s%n",
                "ID",
                "SUPPLIER NAME",
                "PHONE");
        System.out.println("==============================================================");

        for (Supplier supplier : ProductList.suppliers) {

            System.out.println(supplier);

        }

        System.out.println("==============================================================");

    }

    /**
     * Thêm Supplier.
     *
     * @param supplier Supplier cần thêm
     * @return true nếu thành công
     */
    public boolean addSupplier(Supplier supplier) {

        if (supplier == null) {

            return false;

        }

        if (findSupplierById(supplier.getSupplierId()) != null) {

            return false;

        }

        ProductList.suppliers.add(supplier);

        return true;

    }

    /**
     * Cập nhật Supplier.
     *
     * @param updatedSupplier Supplier mới
     * @return true nếu thành công
     */
    public boolean updateSupplier(Supplier updatedSupplier) {

        Supplier supplier = findSupplierById(updatedSupplier.getSupplierId());

        if (supplier == null) {

            return false;

        }

        supplier.setSupplierName(updatedSupplier.getSupplierName());
        supplier.setPhone(updatedSupplier.getPhone());
        supplier.setEmail(updatedSupplier.getEmail());
        supplier.setAddress(updatedSupplier.getAddress());
        supplier.setDescription(updatedSupplier.getDescription());

        return true;

    }

    /**
     * Xóa Supplier.
     *
     * @param supplierId Supplier ID
     * @return true nếu thành công
     */
    public boolean deleteSupplier(String supplierId) {

        Supplier supplier = findSupplierById(supplierId);

        if (supplier == null) {

            return false;

        }

        ProductList.suppliers.remove(supplier);

        return true;

    }

    /**
     * Tìm Supplier theo ID.
     *
     * @param supplierId Supplier ID
     * @return Supplier hoặc null
     */
    public Supplier findSupplierById(String supplierId) {

        for (Supplier supplier : ProductList.suppliers) {

            if (supplier.getSupplierId().equalsIgnoreCase(supplierId)) {

                return supplier;

            }

        }

        return null;

    }

    /**
     * Tìm Supplier theo tên.
     *
     * @param keyword từ khóa
     * @return danh sách Supplier
     */
    public List<Supplier> searchByName(String keyword) {

        List<Supplier> result = new ArrayList<>();

        for (Supplier supplier : ProductList.suppliers) {

            if (supplier.getSupplierName()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                result.add(supplier);

            }

        }

        return result;

    }

    /**
     * Hiển thị kết quả tìm kiếm.
     *
     * @param keyword từ khóa
     */
    public void displaySearchResult(String keyword) {

        List<Supplier> result = searchByName(keyword);

        if (result.isEmpty()) {

            System.out.println("Supplier not found.");

            return;

        }

        System.out.println("==============================================================");
        System.out.printf("%-8s %-30s %-15s%n",
                "ID",
                "SUPPLIER NAME",
                "PHONE");
        System.out.println("==============================================================");

        for (Supplier supplier : result) {

            System.out.println(supplier);

        }

        System.out.println("==============================================================");

    }

    /**
     * Kiểm tra Supplier ID đã tồn tại.
     *
     * @param supplierId Supplier ID
     * @return true nếu tồn tại
     */
    public boolean isSupplierIdExists(String supplierId) {

        return findSupplierById(supplierId) != null;

    }

}