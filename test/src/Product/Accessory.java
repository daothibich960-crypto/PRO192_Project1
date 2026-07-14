package Product;

import Enum.ProductStatus;
import Enum.Unit;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;

/**
 * ============================================================ Lớp Accessory
 * ------------------------------------------------------------ Đại diện cho các
 * phụ kiện trà trong hệ thống.
 *
 * Ví dụ: - Khải - Tống - Khăn trà - Tea Pet - Gắp chén - Lọc trà
 *
 * @author Long ============================================================
 */
public class Accessory extends Product {

    //==========================================================
    // Attributes (Thuộc tính)
    //==========================================================
    /**
     * Loại phụ kiện.
     *
     * Ví dụ: - Tea Pet - Khải - Tống - Khăn trà - Gắp chén
     */
    private String accessoryType;

    /**
     * Chất liệu của phụ kiện.
     *
     * Ví dụ: - Gỗ - Tre - Sứ - Đồng
     */
    private String material;

    /**
     * Kích thước của phụ kiện.
     *
     * Ví dụ: - Small - Medium - Large - 20x30 cm
     */
    private String size;

    /**
     * Màu sắc.
     *
     * Ví dụ: - Đen - Nâu - Trắng
     */
    private String color;

    /**
     * Thương hiệu.
     *
     * Ví dụ: - Bát Tràng - Minh Long
     */
    private String brand;

    //==========================================================
    // Constructors
    //==========================================================
    /**
     * Constructor mặc định.
     */
    public Accessory() {

    }

    /**
     * Constructor khi mới tạo sản phẩm.
     *
     * @param productId Mã sản phẩm
     * @param productName Tên sản phẩm
     */
    public Accessory(String productId,
            String productName) {

        super(productId, productName);

    }

    /**
     * Constructor khi có thông tin cơ bản.
     */
    public Accessory(String productId,
            String productName,
            double importPrice,
            double sellingPrice) {

        super(productId,
                productName,
                importPrice,
                sellingPrice);

    }

    /**
     * Constructor đầy đủ.
     */
    public Accessory(String productId,
            String productName,
            double importPrice,
            double sellingPrice,
            int stockQuantity,
            Unit unit,
            ProductStatus status,
            String description,
            Supplier supplier,
            String accessoryType,
            String material,
            String size,
            String color,
            String brand) {

        super(productId,
                productName,
                importPrice,
                sellingPrice,
                stockQuantity,
                unit,
                status,
                description,
                supplier);

        this.accessoryType = accessoryType;
        this.material = material;
        this.size = size;
        this.color = color;
        this.brand = brand;

    }

    //==========================================================
    // Getter & Setter
    //==========================================================
    public String getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(String accessoryType) {
        this.accessoryType = accessoryType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //==========================================================
    // Business Methods
    //==========================================================
    /**
     * Trả về tên hiển thị của phụ kiện.
     *
     * Ví dụ: Tea Pet - Gốm
     *
     * @return Tên hiển thị.
     */
    public String getDisplayName() {

        return accessoryType + " - " + material;

    }

    //==========================================================
    // Override Methods
    //==========================================================
    @Override
    public String toString() {

        return super.toString()
                + "\n========== ACCESSORY INFORMATION =========="
                + "\nAccessory Type : " + accessoryType
                + "\nMaterial       : " + material
                + "\nSize           : " + size
                + "\nColor          : " + color
                + "\nBrand          : " + brand
                + "\nDisplay Name   : " + getDisplayName();

    }

}
