package Product;

import Enum.ProductStatus;
import Enum.Unit;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;
import Supplier.Supplier;

/**
 * Abstract class Product
 *
 * Đại diện cho tất cả các sản phẩm trong cửa hàng trà. Các lớp Tea, TeaPot,
 * TeaCup và Accessory sẽ kế thừa lớp này.
 *
 * @author Long
 */
public abstract class Product {

    /**
     * Mã sản phẩm. Là duy nhất để phân biệt các sản phẩm. Ví dụ: TEA001,
     * POT001...
     */
    private String productId;

    /**
     * Tên sản phẩm. Ví dụ: - Trà Ô Long - Ấm Tây Thi
     */
    private String productName;

    /**
     * Giá nhập của một đơn vị sản phẩm. Dùng để tính lợi nhuận.
     */
    private double importPrice;

    /**
     * Giá bán của một đơn vị sản phẩm. Đây là giá bán cho khách hàng.
     */
    private double sellingPrice;

    /**
     * Số lượng hiện còn trong kho.
     *
     * Ví dụ: 5000 gram hoặc 12 chiếc
     */
    private int stockQuantity;

    /**
     * Đơn vị bán của sản phẩm.
     *
     * Ví dụ: GRAM KILOGRAM PIECE SET
     */
    private Unit unit;

    /**
     * Trạng thái hiện tại của sản phẩm.
     *
     * AVAILABLE OUT_OF_STOCK DISCONTINUED
     */
    private ProductStatus status;

    /**
     * Mô tả thêm về sản phẩm.
     *
     * Ví dụ: "Trà được hái mùa xuân."
     */
    private String description;

    /**
     * Nhà cung cấp của sản phẩm.
     *
     * Một Product thuộc về một Supplier.
     */
    private Supplier supplier;

    //==========================
    // Constructor
    //==========================
    /**
     * Constructor rỗng. Dùng khi chưa có dữ liệu.
     */
    public Product() {
    }

    /**
     * Constructor khi chỉ biết mã và tên sản phẩm.
     *
     * @param productId mã sản phẩm
     * @param productName tên sản phẩm
     */
    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    /**
     * Constructor khi đã biết giá nhập và giá bán.
     */
    public Product(String productId,
            String productName,
            double importPrice,
            double sellingPrice) {

        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.sellingPrice = sellingPrice;
    }

    /**
     * Constructor khi đã có thông tin kho.
     */
    public Product(String productId,
            String productName,
            double importPrice,
            double sellingPrice,
            int stockQuantity,
            Unit unit) {

        this(productId, productName, importPrice, sellingPrice);

        this.stockQuantity = stockQuantity;
        this.unit = unit;
    }

    /**
     * Constructor đầy đủ.
     */
    public Product(String productId,
            String productName,
            double importPrice,
            double sellingPrice,
            int stockQuantity,
            Unit unit,
            ProductStatus status,
            String description,
            Supplier supplier) {

        this(productId,
                productName,
                importPrice,
                sellingPrice,
                stockQuantity,
                unit);

        this.status = status;
        this.description = description;
        this.supplier = supplier;
    }

    //==========================
    // Getter & Setter
    //==========================
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /*==========================
    // Business Methods
    //==========================
    public double calculateProfit() {
        return sellingPrice - importPrice;
    }

    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }

    public void decreaseStock(int quantity) {

        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("========== PRODUCT INFORMATION ==========\n");
        builder.append("Product ID      : ").append(productId).append("\n");
        builder.append("Product Name    : ").append(productName).append("\n");
        builder.append("Import Price    : ").append(importPrice).append("\n");
        builder.append("Selling Price   : ").append(sellingPrice).append("\n");
        builder.append("Stock Quantity  : ").append(stockQuantity).append("\n");
        builder.append("Unit            : ").append(unit).append("\n");
        builder.append("Status          : ").append(status).append("\n");
        builder.append("Description     : ").append(description).append("\n");

        if (supplier != null) {
            builder.append("Supplier        : ")
                    .append(supplier.getSupplierName())
                    .append("\n");
        } else {
            builder.append("Supplier        : N/A\n");
        }

        return builder.toString();
    }

}
