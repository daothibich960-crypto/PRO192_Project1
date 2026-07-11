package Product;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;

/**
 * ============================================================ Lớp Supplier
 * ------------------------------------------------------------ Đại diện cho nhà
 * cung cấp trong hệ thống quản lý kinh doanh trà.
 *
 * Một nhà cung cấp có thể cung cấp nhiều sản phẩm khác nhau như trà, ấm trà,
 * chén trà hoặc các loại trà cụ.
 *
 * @author Long ============================================================
 */
public class Supplier {

    //==========================================================
    // Attributes (Thuộc tính)
    //==========================================================
    /**
     * Mã nhà cung cấp.
     *
     * Đây là mã duy nhất dùng để phân biệt các nhà cung cấp.
     *
     * Ví dụ: SUP001 SUP002
     */
    private String supplierId;

    /**
     * Tên nhà cung cấp.
     *
     * Ví dụ: Công ty Trà Thái Nguyên Xưởng Ấm Tử Sa Nghi Hưng
     */
    private String supplierName;

    /**
     * Số điện thoại liên hệ.
     */
    private String phone;

    /**
     * Địa chỉ email.
     */
    private String email;

    /**
     * Địa chỉ của nhà cung cấp.
     */
    private String address;

    /**
     * Ghi chú thêm về nhà cung cấp.
     *
     * Ví dụ: Chuyên cung cấp trà Shan Tuyết.
     */
    private String description;

    //==========================================================
    // Constructors (Hàm khởi tạo)
    //==========================================================
    /**
     * Constructor mặc định.
     *
     * Dùng khi chưa có thông tin.
     */
    public Supplier() {

    }

    /**
     * Constructor khi chỉ biết mã và tên nhà cung cấp.
     *
     * @param supplierId Mã nhà cung cấp
     * @param supplierName Tên nhà cung cấp
     */
    public Supplier(String supplierId,
            String supplierName) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;

    }

    /**
     * Constructor đầy đủ thông tin.
     *
     * @param supplierId Mã nhà cung cấp
     * @param supplierName Tên nhà cung cấp
     * @param phone Số điện thoại
     * @param email Email
     * @param address Địa chỉ
     * @param description Ghi chú
     */
    public Supplier(String supplierId,
            String supplierName,
            String phone,
            String email,
            String address,
            String description) {

        this(supplierId, supplierName);

        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;

    }

    //==========================================================
    // Getter & Setter
    //==========================================================
    /**
     * Lấy mã nhà cung cấp.
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * Cập nhật mã nhà cung cấp.
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * Lấy tên nhà cung cấp.
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Cập nhật tên nhà cung cấp.
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * Lấy số điện thoại.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Cập nhật số điện thoại.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Lấy email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Cập nhật email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Lấy địa chỉ.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Cập nhật địa chỉ.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Lấy ghi chú.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Cập nhật ghi chú.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    //==========================================================
    // Override Methods
    //==========================================================
    /**
     * Hiển thị thông tin nhà cung cấp.
     */
    @Override
    public String toString() {

        return "Supplier{"
                + "supplierId='" + supplierId + '\''
                + ", supplierName='" + supplierName + '\''
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + ", address='" + address + '\''
                + ", description='" + description + '\''
                + '}';
    }

}
