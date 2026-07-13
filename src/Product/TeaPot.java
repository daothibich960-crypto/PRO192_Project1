package Product;

import Enum.ProductStatus;
import Enum.Unit;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;
import Supplier.Supplier;

/**
 * ============================================================ Lớp TeaPot
 * ------------------------------------------------------------ Đại diện cho sản
 * phẩm Ấm trà trong hệ thống.
 *
 * TeaPot kế thừa từ Product và bổ sung những thông tin đặc trưng của ấm trà.
 *
 * Ví dụ: - Ấm Tây Thi - Ấm Thạch Biều - Ấm Lục Phương - Ấm Tử Sa
 *
 * @author Long ============================================================
 */
public class TeaPot extends Product {

    //==========================================================
    // Attributes (Thuộc tính)
    //==========================================================
    /**
     * Loại đất làm ấm.
     *
     * Ví dụ: - Tử Nê - Đoàn Nê - Lục Nê - Đại Hồng Bào - Bạch Ngọc Đoàn Nê
     */
    private String clayType;

    /**
     * Dáng của ấm.
     *
     * Ví dụ: - Tây Thi - Thạch Biều - Lục Phương - Tứ Phương
     */
    private String shape;

    /**
     * Dung tích của ấm (ml).
     *
     * Ví dụ: 80ml 100ml 200ml
     */
    private int capacity;

    /**
     * Chất liệu của ấm.
     *
     * Ví dụ: - Tử Sa - Sứ - Gốm - Thủy tinh
     */
    private String material;

    /**
     * Màu sắc của ấm.
     *
     * Ví dụ: - Đỏ - Nâu - Đen
     */
    private String color;

    /**
     * Thương hiệu của ấm.
     *
     * Ví dụ: - Bát Tràng - Nghi Hưng - Kim Quy
     */
    private String brand;

    /**
     * Xuất xứ của ấm.
     *
     * Ví dụ: - Việt Nam - Trung Quốc
     */
    private String origin;

    //==========================================================
    // Constructors (Hàm khởi tạo)
    //==========================================================
    /**
     * Constructor mặc định.
     */
    public TeaPot() {

    }

    /**
     * Constructor khi mới tạo sản phẩm.
     *
     * @param productId Mã sản phẩm
     * @param productName Tên sản phẩm
     */
    public TeaPot(String productId,
            String productName) {

        super(productId, productName);

    }

    /**
     * Constructor khi đã có thông tin cơ bản.
     *
     * @param productId Mã sản phẩm
     * @param productName Tên sản phẩm
     * @param importPrice Giá nhập
     * @param sellingPrice Giá bán
     * @param unit Đơn vị bán
     */
    public TeaPot(String productId,
            String productName,
            double importPrice,
            double sellingPrice
    ) {

        super(productId,
                productName,
                importPrice,
                sellingPrice);

    }

    /**
     * Constructor đầy đủ.
     */
    public TeaPot(String productId,
            String productName,
            double importPrice,
            double sellingPrice,
            int stockQuantity,
            Unit unit,
            ProductStatus status,
            String description,
            Supplier supplier,
            String clayType,
            String shape,
            int capacity,
            String material,
            String color,
            String brand,
            String origin) {

        super(productId,
                productName,
                importPrice,
                sellingPrice,
                stockQuantity,
                unit,
                status,
                description,
                supplier);

        this.clayType = clayType;
        this.shape = shape;
        this.capacity = capacity;
        this.material = material;
        this.color = color;
        this.brand = brand;
        this.origin = origin;
    }

    //==========================================================
    // Getter & Setter
    //==========================================================
    /**
     * Lấy loại đất.
     */
    public String getClayType() {
        return clayType;
    }

    /**
     * Cập nhật loại đất.
     */
    public void setClayType(String clayType) {
        this.clayType = clayType;
    }

    /**
     * Lấy dáng ấm.
     */
    public String getShape() {
        return shape;
    }

    /**
     * Cập nhật dáng ấm.
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     * Lấy dung tích.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Cập nhật dung tích.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Lấy chất liệu.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Cập nhật chất liệu.
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Lấy màu sắc.
     */
    public String getColor() {
        return color;
    }

    /**
     * Cập nhật màu sắc.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Lấy thương hiệu.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Cập nhật thương hiệu.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Lấy xuất xứ.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Cập nhật xuất xứ.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    //==========================================================
    // Business Methods (Phương thức nghiệp vụ)
    //==========================================================
    /**
     * Trả về tên hiển thị của ấm trà.
     *
     * Ví dụ: Tây Thi - 150ml
     *
     * @return Tên hiển thị.
     */
    public String getDisplayName() {

        return shape + " - " + capacity + "ml";

    }

    //==========================================================
    // Override Methods
    //==========================================================
    /**
     * Hiển thị toàn bộ thông tin của ấm trà.
     *
     * @return Chuỗi thông tin TeaPot.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\n========== TEA POT INFORMATION =========="
                + "\nShape         : " + shape
                + "\nClay Type     : " + clayType
                + "\nMaterial      : " + material
                + "\nCapacity      : " + capacity + " ml"
                + "\nColor         : " + color
                + "\nBrand         : " + brand
                + "\nOrigin        : " + origin
                + "\nDisplay Name  : " + getDisplayName();

    }

}
