package Product;

import Product.Product;
import Enum.ProductStatus;
import Enum.Unit;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;

/**
 * ============================================================ Lớp TeaCup
 * ------------------------------------------------------------ Đại diện cho sản
 * phẩm Chén trà trong hệ thống.
 *
 * TeaCup kế thừa từ Product và bổ sung những thông tin đặc trưng của chén trà.
 *
 * Ví dụ: - Chén Chủ - Chén Quân - Chén Thưởng Trà
 *
 * @author Long ============================================================
 */
public class TeaCup extends Product {

    //==========================================================
    // Attributes (Thuộc tính)
    //==========================================================
    /**
     * Loại chén.
     *
     * Ví dụ: - Chén Chủ - Chén Quân - Chén Thưởng Trà
     */
    private String cupType;

    /**
     * Dung tích của chén (ml).
     *
     * Ví dụ: 30ml 50ml 80ml
     */
    private int capacity;

    /**
     * Chất liệu của chén.
     *
     * Ví dụ: - Sứ - Tử sa - Thủy tinh - Gốm
     */
    private String material;

    /**
     * Màu sắc của chén.
     *
     * Ví dụ: - Trắng - Đen - Xanh ngọc
     */
    private String color;

    /**
     * Số lượng chén trong một bộ.
     *
     * Ví dụ: Bộ 2 chén Bộ 4 chén Bộ 6 chén
     */
    private int quantityPerSet;

    //==========================================================
    // Constructors (Hàm khởi tạo)
    //==========================================================
    /**
     * Constructor mặc định.
     */
    public TeaCup() {

    }

    /**
     * Constructor khi mới tạo sản phẩm.
     *
     * @param productId Mã sản phẩm
     * @param productName Tên sản phẩm
     */
    public TeaCup(String productId, String productName) {

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
    public TeaCup(String productId,
            String productName,
            double importPrice,
            double sellingPrice
    ) {

        super(productId,
                productName,
                importPrice,
                sellingPrice
        );

    }

    /**
     * Constructor đầy đủ.
     */
    public TeaCup(String productId,
            String productName,
            double importPrice,
            double sellingPrice,
            int stockQuantity,
            Unit unit,
            ProductStatus status,
            String description,
            Supplier supplier,
            String cupType,
            int capacity,
            String material,
            String color,
            int quantityPerSet) {

        super(productId,
                productName,
                importPrice,
                sellingPrice,
                stockQuantity,
                unit,
                status,
                description,
                supplier);

        this.cupType = cupType;
        this.capacity = capacity;
        this.material = material;
        this.color = color;
        this.quantityPerSet = quantityPerSet;

    }

    //==========================================================
    // Getter & Setter
    //==========================================================
    /**
     * Lấy loại chén.
     *
     * @return Loại chén.
     */
    public String getCupType() {
        return cupType;
    }

    /**
     * Cập nhật loại chén.
     *
     * @param cupType Loại chén.
     */
    public void setCupType(String cupType) {
        this.cupType = cupType;
    }

    /**
     * Lấy dung tích.
     *
     * @return Dung tích.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Cập nhật dung tích.
     *
     * @param capacity Dung tích.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Lấy chất liệu.
     *
     * @return Chất liệu.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Cập nhật chất liệu.
     *
     * @param material Chất liệu.
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Lấy màu sắc.
     *
     * @return Màu sắc.
     */
    public String getColor() {
        return color;
    }

    /**
     * Cập nhật màu sắc.
     *
     * @param color Màu sắc.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Lấy số lượng chén trong một bộ.
     *
     * @return Số lượng.
     */
    public int getQuantityPerSet() {
        return quantityPerSet;
    }

    /**
     * Cập nhật số lượng chén trong một bộ.
     *
     * @param quantityPerSet Số lượng.
     */
    public void setQuantityPerSet(int quantityPerSet) {
        this.quantityPerSet = quantityPerSet;
    }

    //==========================================================
    // Business Methods (Phương thức nghiệp vụ)
    //==========================================================
    /**
     * Trả về tên hiển thị của chén.
     *
     * Ví dụ: Chén Chủ - 50ml
     *
     * @return Tên hiển thị.
     */
    public String getDisplayName() {

        return cupType + " - " + capacity + "ml";

    }

    //==========================================================
    // Override Methods
    //==========================================================
    /**
     * Hiển thị toàn bộ thông tin của chén trà.
     *
     * @return Chuỗi thông tin TeaCup.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\n========== TEA CUP INFORMATION =========="
                + "\nCup Type       : " + cupType
                + "\nCapacity       : " + capacity + " ml"
                + "\nMaterial       : " + material
                + "\nColor          : " + color
                + "\nQuantity / Set : " + quantityPerSet
                + "\nDisplay Name   : " + getDisplayName();

    }

}
