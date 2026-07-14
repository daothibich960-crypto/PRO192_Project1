package Product;

import Product.Product;
import Enum.ProductStatus;
import Enum.Unit;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;
import java.time.LocalDate;

import java.time.LocalDate;

/**
 * ============================================================ Lớp Tea
 * ------------------------------------------------------------ Đại diện cho sản
 * phẩm Trà trong hệ thống.
 *
 * Tea kế thừa từ lớp Product và bổ sung những thông tin đặc trưng chỉ có ở trà.
 *
 * Ví dụ: - Trà Ô Long - Lục Trà - Hồng Trà - Bạch Trà - Phổ Nhĩ
 *
 * @author Long ============================================================
 */
public class Tea extends Product {

    //==========================================================
    // Attributes (Thuộc tính)
    //==========================================================
    /**
     * Loại trà.
     *
     * Ví dụ: - Lục Trà - Hồng Trà - Bạch Trà - Ô Long - Phổ Nhĩ
     */
    private String teaType;

    /**
     * Nguồn gốc của trà.
     *
     * Ví dụ: - Thái Nguyên - Hà Giang - Phúc Kiến - Vân Nam
     */
    private String origin;

    /**
     * Năm thu hoạch.
     *
     * Ví dụ: 2024 2025
     */
    private int harvestYear;

    /**
     * Khối lượng tịnh của sản phẩm.
     *
     * Ví dụ: 100 (gram) 200 (gram) 500 (gram)
     *
     * Đơn vị sẽ được xác định bởi thuộc tính Unit trong lớp Product.
     */
    private double netWeight;
    /**
     * Cấp chất lượng của trà.
     *
     * Dùng để phân loại chất lượng sản phẩm, giúp định giá và giới thiệu với
     * khách hàng.
     *
     * Ví dụ: - Standard - Premium - Special - First Grade - Luxury
     */
    private String teaGrade;

    /**
     * Nhiệt độ pha trà khuyến nghị (°C).
     *
     * Ví dụ: 80°C 85°C 90°C
     */
    private int brewingTemperature;

    /**
     * Thời gian hãm trà (giây).
     *
     * Ví dụ: 20 giây 30 giây 45 giây
     */
    private String brewingTime;

    /**
     * Hạn sử dụng của trà.
     *
     * Kiểu dữ liệu LocalDate giúp quản lý ngày tháng chính xác hơn String.
     */
    private LocalDate expiryDate;

    //==========================================================
    // Constructors (Hàm khởi tạo)
    //==========================================================
    /**
     * Constructor mặc định.
     */
    public Tea() {

    }

    /**
     * Constructor khi mới tạo sản phẩm.
     *
     * @param productId Mã sản phẩm
     * @param productName Tên sản phẩm
     */
    public Tea(String productId,
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
    public Tea(String productId,
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
    public Tea(String productId,
            String productName,
            double importPrice,
            double sellingPrice,
            int stockQuantity,
            Unit unit,
            ProductStatus status,
            String description,
            Supplier supplier,
            String teaType,
            String origin,
            int harvestYear,
            double netWeight,
            String teaGrade,
            int brewingTemperature,
            String brewingTime,
            LocalDate expiryDate) {

        super(productId,
                productName,
                importPrice,
                sellingPrice,
                stockQuantity,
                unit,
                status,
                description,
                supplier);

        this.teaType = teaType;
        this.origin = origin;
        this.harvestYear = harvestYear;
        this.netWeight = netWeight;
        this.teaGrade = teaGrade;
        this.brewingTemperature = brewingTemperature;
        this.brewingTime = brewingTime;
        this.expiryDate = expiryDate;

    }

    //==========================================================
    // Getter & Setter
    //==========================================================
    /**
     * Lấy loại trà.
     */
    public String getTeaType() {
        return teaType;
    }

    /**
     * Cập nhật loại trà.
     */
    public void setTeaType(String teaType) {
        this.teaType = teaType;
    }

    /**
     * Lấy nguồn gốc.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Cập nhật nguồn gốc.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Lấy năm thu hoạch.
     */
    public int getHarvestYear() {
        return harvestYear;
    }

    /**
     * Cập nhật năm thu hoạch.
     */
    public void setHarvestYear(int harvestYear) {
        this.harvestYear = harvestYear;
    }

    /**
     * Lấy khối lượng tịnh.
     */
    public double getNetWeight() {
        return netWeight;
    }

    /**
     * Cập nhật khối lượng tịnh.
     */
    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * Lấy cấp chất lượng của trà.
     *
     * @return Tea grade.
     */
    public String getTeaGrade() {
        return teaGrade;
    }

    /**
     * Cập nhật cấp chất lượng của trà.
     *
     * @param teaGrade Tea grade.
     */
    public void setTeaGrade(String teaGrade) {
        this.teaGrade = teaGrade;
    }

    /**
     * Lấy nhiệt độ pha.
     */
    public int getBrewingTemperature() {
        return brewingTemperature;
    }

    /**
     * Cập nhật nhiệt độ pha.
     */
    public void setBrewingTemperature(int brewingTemperature) {
        this.brewingTemperature = brewingTemperature;
    }

    /**
     * Lấy thời gian hãm.
     */
    public String getBrewingTime() {
        return brewingTime;
    }

    /**
     * Cập nhật thời gian hãm.
     */
    public void setBrewingTime(String brewingTime) {
        this.brewingTime = brewingTime;
    }

    /**
     * Lấy hạn sử dụng.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Cập nhật hạn sử dụng.
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    //==========================================================
// Business Methods (Phương thức nghiệp vụ)
//==========================================================
    /**
     * Trả về hướng dẫn pha trà.
     *
     * Hướng dẫn được tạo dựa trên nhiệt độ và thời gian hãm của từng loại trà.
     *
     * @return Hướng dẫn pha trà.
     */
    public String getBrewingGuide() {

        return "Pha ở "
                + brewingTemperature
                + "°C trong "
                + brewingTime
                + ".";

    }

    //==========================================================
    // Override Methods
    //==========================================================
    /**
     * Hiển thị toàn bộ thông tin của sản phẩm trà.
     */
    @Override
    public String toString() {

        return super.toString()
                + "\n========== TEA INFORMATION =========="
                + "\nTea Type      : " + teaType
                + "\nOrigin        : " + origin
                + "\nTea Grade     : " + teaGrade
                + "\nNet Weight    : " + netWeight + " " + getUnit()
                + "\nHarvest Year  : " + harvestYear
                + "\nBrew Temp     : " + brewingTemperature + "°C"
                + "\nBrew Time     : " + brewingTime
                + "\nExpiry Date   : " + expiryDate
                + "\nGuide         : " + getBrewingGuide();

    }

}
