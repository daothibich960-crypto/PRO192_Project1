package Menu;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Service.ProductService;

import Inventory.Inventory;

import Supplier.Supplier;
import Supplier.SupplierList;

import Product.Product;
import Product.Tea;
import Product.TeaCup;
import Product.TeaPot;
import Product.Accessory;

import Enum.Unit;
import Enum.ProductStatus;

/**
 * Product Management Menu
 *
 * Chỉ hiển thị giao diện. Không xử lý dữ liệu.
 *
 * @author Long
 */
public class ProductMenu {

    //========================================
    // SERVICES
    //========================================
    private ProductService productService;

    private SupplierList supplierList;

    private Inventory inventory;

    //========================================
    // SCANNER
    //========================================
    private Scanner scanner;

    //========================================
    // MENU CONSTANT
    //========================================
    private static final int VIEW_PRODUCT = 1;
    private static final int ADD_PRODUCT = 2;
    private static final int UPDATE_PRODUCT = 3;
    private static final int DELETE_PRODUCT = 4;
    private static final int SEARCH_PRODUCT = 5;
    private static final int BACK = 0;

    //========================================
    // CONSTRUCTOR
    //========================================
    public ProductMenu(Inventory inventory, Scanner sc) {
        this.inventory = inventory;
        this.scanner = sc;

        this.supplierList = new SupplierList();
//        this.supplierList.loadFromFile(); // Cần load dữ liệu nhà cung cấp từ file!
        productService = new ProductService(inventory);
    }

    //========================================
    // MAIN MENU
    //========================================
    public void show() {

        int choice;

        do {

            clearScreen();

            printProductMenu();
// inputChoice()
            choice = inputChoice();

            switch (choice) {

                case VIEW_PRODUCT:

                    viewAllProducts();
                    pause();
                    break;

                case ADD_PRODUCT:

                    addProductMenu();
                    break;

                case UPDATE_PRODUCT:

                    updateProduct();
                    pause();
                    break;

                case DELETE_PRODUCT:

                    deleteProduct();
                    pause();
                    break;

                case SEARCH_PRODUCT:

                    searchProduct();
                    pause();
                    break;

                case BACK:

                    System.out.println("Quay lại...");
                    break;

                default:

                    System.out.println("Vui lòng chọn đúng chức năng!");
                    pause();

            }

        } while (choice != BACK);

    }

    //========================================
    // PRODUCT MENU
    //========================================
    private void printProductMenu() {

        printHeader("\n===== QUẢN LÝ SẢN PHẨM =====");
        System.out.println("1. Xem danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Tìm kiếm sản phẩm");
        System.out.println("0. Quay lại");

        System.out.println("-------------------------------------");

    }

    //========================================
    // INPUT CHOICE
    //========================================
    private int inputChoice() {

        while (true) {

            try {

                System.out.print("Chọn: ");

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Vui lòng nhập số hợp lệ.");

            }

        }

    }

//========================================
// INPUT STRING
//========================================
    private String inputString(String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {

                return value;

            }

            System.out.println("Vui lòng không để trống!");

        }

    }

//========================================
// INPUT INTEGER
//========================================
    private int inputInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Vui lòng nhập số nguyên hợp lệ!");

            }

        }

    }

//========================================
// INPUT DOUBLE
//========================================
    private double inputDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Vui lòng nhập số hợp lệ!");

            }

        }

    }

//========================================
// INPUT POSITIVE DOUBLE
//========================================
    private double inputPositiveDouble(String message) {

        while (true) {

            double value = inputDouble(message);

            if (value >= 0) {

                return value;

            }

            System.out.println("Giá trị phải lớn hơn hoặc bằng 0!");

        }

    }
    //========================================
// INPUT DATE
//========================================

    /**
     * Nhập ngày theo định dạng dd/MM/yyyy.
     *
     * @param message thông báo nhập
     * @return LocalDate
     */
    private LocalDate inputDate(String message) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {

            try {

                System.out.print(message);

                String input = scanner.nextLine().trim();

                return LocalDate.parse(input, formatter);

            } catch (DateTimeParseException e) {

                System.out.println("Ngày không hợp lệ!");
                System.out.println("Vui lòng nhập theo định dạng: dd/MM/yyyy");
                System.out.println("Ví dụ: 31/12/2026");

            }

        }

    }
//========================================
// UPDATE STRING
//========================================
private String updateString(String message, String oldValue) {

    System.out.print(message + " [" + oldValue + "]: ");

    String input = scanner.nextLine().trim();

    if (input.isEmpty()) {

        return oldValue;

    }

    return input;

}
//========================================
// UPDATE INTEGER
//========================================
private int updateInt(String message, int oldValue) {

    while (true) {

        System.out.print(message + " [" + oldValue + "]: ");

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {

            return oldValue;

        }

        try {

            return Integer.parseInt(input);

        } catch (NumberFormatException e) {

            System.out.println("Vui lòng nhập số nguyên hợp lệ!");

        }

    }

}
//========================================
// UPDATE DOUBLE
//========================================
private double updateDouble(String message, double oldValue) {

    while (true) {

        System.out.print(message + " [" + oldValue + "]: ");

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {

            return oldValue;

        }

        try {

            double value = Double.parseDouble(input);

            if (value >= 0) {

                return value;

            }

            System.out.println("Giá trị phải lớn hơn hoặc bằng 0!");

        } catch (NumberFormatException e) {

            System.out.println("Vui lòng nhập số hợp lệ!");

        }

    }

}
//========================================
// UPDATE DATE
//========================================
private LocalDate updateDate(String message, LocalDate oldValue) {

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    while (true) {

        System.out.print(message + " [" + oldValue.format(formatter) + "]: ");

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {

            return oldValue;

        }

        try {

            return LocalDate.parse(input, formatter);

        } catch (DateTimeParseException e) {

            System.out.println("Ngày không hợp lệ!");
            System.out.println("Định dạng: dd/MM/yyyy");

        }

    }

}

//========================================
// UPDATE UNIT
//========================================
private Unit updateUnit(Unit oldUnit) {

    System.out.println();
    System.out.println("Đơn vị hiện tại: " + oldUnit.getSymbol());
    System.out.println("Nhấn ENTER để giữ nguyên.");

    while (true) {

        System.out.println("1. Gam (g)");
        System.out.println("2. Kilôgam (Kg)");
        System.out.println("3. Cái");
        System.out.println("4. Bộ");
        System.out.println("5. Hộp");
        System.out.println("6. Chai");
        System.out.println("7. Bánh");

        System.out.print("Lựa chọn: ");

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {

            return oldUnit;

        }

        switch (input) {

            case "1":
                return Unit.GRAM;

            case "2":
                return Unit.KILOGRAM;

            case "3":
                return Unit.PIECE;

            case "4":
                return Unit.SET;

            case "5":
                return Unit.BOX;

            case "6":
                return Unit.BOTTLE;

            case "7":
                return Unit.CAKE;

            default:
                System.out.println("Lựa chọn không hợp lệ!");

        }

    }

}
//========================================
// UPDATE STATUS
//========================================
private ProductStatus updateStatus(ProductStatus oldStatus) {

    System.out.println();
    System.out.println("Trạng thái hiện tại: " + oldStatus.getDisplayName());
    System.out.println("Nhấn ENTER để giữ nguyên.");

    while (true) {

        System.out.println("1. Còn hàng");
        System.out.println("2. Hết hàng");
        System.out.println("3. Ngừng kinh doanh");

        System.out.print("Lựa chọn: ");

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {

            return oldStatus;

        }

        switch (input) {

            case "1":
                return ProductStatus.AVAILABLE;

            case "2":
                return ProductStatus.OUT_OF_STOCK;

            case "3":
                return ProductStatus.DISCONTINUED;

            default:
                System.out.println("Lựa chọn không hợp lệ!");

        }

    }

}
//========================================
// UPDATE SUPPLIER
//========================================
private Supplier updateSupplier(Supplier oldSupplier) {

    while (true) {

        printHeader("DANH SÁCH NHÀ CUNG CẤP");

        supplierList.displayAllSupplier();

        System.out.println();
        System.out.println("Nhà cung cấp hiện tại: "
                + oldSupplier.getSupplierName());

        System.out.println("Nhấn ENTER để giữ nguyên.");

        System.out.print("Nhập mã nhà cung cấp: ");

        String supplierId = scanner.nextLine().trim();

        if (supplierId.isEmpty()) {

            return oldSupplier;

        }

        Supplier supplier = supplierList.searchSupplier(supplierId);

        if (supplier != null) {

            return supplier;

        }

        System.out.println("Không tìm thấy nhà cung cấp!");

    }

}
//========================================
// CHOOSE UNIT
//========================================
    private Unit chooseUnit() {

        while (true) {

            printHeader("CHỌN ĐƠN VỊ ");
            System.out.println("1. Gam (g)");
            System.out.println("2. Kilôgam (kg)");
            System.out.println("3. Cái");
            System.out.println("4. Bộ");
            System.out.println("5. Hộp");
            System.out.println("6. Chai");
            System.out.println("7. Bánh trà");

            int choice = inputChoice();

            switch (choice) {

                case 1:
                    return Unit.GRAM;

                case 2:
                    return Unit.KILOGRAM;

                case 3:
                    return Unit.PIECE;

                case 4:
                    return Unit.SET;

                case 5:
                    return Unit.BOX;

                case 6:
                    return Unit.BOTTLE;

                case 7:
                    return Unit.CAKE;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        }

    }
//========================================
// CHOOSE PRODUCT STATUS
//========================================

    private ProductStatus chooseStatus() {

        while (true) {

            printHeader("CHỌN TRẠNG THÁI SẢN PHẨM");

            System.out.println("1. Còn hàng");
            System.out.println("2. Hết hàng");
            System.out.println("3. Ngừng kinh doanh");

            int choice = inputChoice();

            switch (choice) {

                case 1:
                    return ProductStatus.AVAILABLE;

                case 2:
                    return ProductStatus.OUT_OF_STOCK;

                case 3:
                    return ProductStatus.DISCONTINUED;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        }

    }

    //========================================
// CHOOSE SUPPLIER
//========================================
    private Supplier chooseSupplier() {

        while (true) {

            printHeader("DANH SÁCH NHÀ CUNG CẤP");

            supplierList.displayAllSupplier();

            String supplierId = inputString("Nhập mã nhà cung cấp: ");

            Supplier supplier = supplierList.searchSupplier(supplierId);

            if (supplier != null) {

                return supplier;

            }

            System.out.println();
            System.out.println("Nhà cung cấp không tồn tại!");

            String choice = inputString("Bạn có muốn thêm nhà cung cấp mới không? (Y/N): ");

            if (choice.equalsIgnoreCase("Y")) {

                addSupplier();

            } else {

                System.out.println("Vui lòng nhập lại mã nhà cung cấp.");

            }

            pause();

        }

    }

    //========================================
// ADD SUPPLIER
//========================================
    private void addSupplier() {

        printHeader("THÊM NHÀ CUNG CẤP");

        Supplier supplier = new Supplier();

        supplier.setSupplierId(
                inputString("Mã nhà cung cấp: "));

        supplier.setSupplierName(
                inputString("Tên nhà cung cấp: "));

        supplier.setPhone(
                inputString("Số điện thoại: "));

        supplier.setEmail(
                inputString("Email: "));

        supplier.setAddress(
                inputString("Địa chỉ: "));

        supplierList.addSupplier(supplier);

        System.out.println();
        System.out.println("========================================");
        System.out.println(" THÊM NHÀ CUNG CẤP THÀNH CÔNG!");
        System.out.println("========================================");

    }
//========================================
// INPUT PRODUCT INFORMATION
//========================================

    /**
     * Nhập các thông tin chung của một sản phẩm.
     *
     * @param product sản phẩm cần nhập thông tin
     */
    private void inputProductInfo(Product product) {

        System.out.println("\n===== THÔNG TIN SẢN PHẨM =====");

        // Tên sản phẩm
        product.setProductName(
                inputString("Tên sản phẩm: "));

        // Giá nhập
        product.setImportPrice(
                inputPositiveDouble("Giá nhập: "));

        // Giá bán
        product.setSellingPrice(
                inputPositiveDouble("Giá bán: "));

        // Số lượng ban đầu
        //product.setStockQuantity(
        //   inputInt("Số lượng trong kho: "));
        product.setStockQuantity(0);
        // Đơn vị tính
        product.setUnit(
                chooseUnit());

        // Trạng thái
        product.setStatus(
                chooseStatus());

        // Mô tả
        product.setDescription(
                inputString("Mô tả: "));

        // Nhà cung cấp
        product.setSupplier(
                chooseSupplier());

    }

   //========================================
// UPDATE COMMON PRODUCT INFO
//========================================
private void updateProductInfo(Product product) {

    System.out.println("\n===== CẬP NHẬT THÔNG TIN SẢN PHẨM =====");

    product.setProductName(
            updateString("Tên sản phẩm", product.getProductName()));

    product.setImportPrice(
            updateDouble("Giá nhập", product.getImportPrice()));

    product.setSellingPrice(
            updateDouble("Giá bán", product.getSellingPrice()));

    // Không cập nhật Stock Quantity
    // Kho sẽ quản lý số lượng

    product.setUnit(
            updateUnit(product.getUnit()));

    product.setStatus(
            updateStatus(product.getStatus()));

    product.setDescription(
            updateString("Mô tả", product.getDescription()));

    product.setSupplier(
            updateSupplier(product.getSupplier()));

}
//========================================
// ADD TEA
//========================================
    /**
     * Thêm mới sản phẩm trà.
     */
    private void addTea() {

        printHeader("Thêm trà");

        //========================================
        // Tạo đối tượng Tea
        //========================================
        Tea tea = new Tea();

        //========================================
        // Sinh mã sản phẩm tự động
        //========================================
        tea.setProductId(productService.generateProductId("TEA"));

        System.out.println("Mã sản phẩm: " + tea.getProductId());

        //========================================
        // Nhập thông tin chung của Product
        //========================================
        inputProductInfo(tea);

        //========================================
        // Nhập thông tin riêng của Tea
        //========================================
        tea.setTeaType(
                inputString("Loại trà: "));

        tea.setOrigin(
                inputString("Xuất xứ: "));

        tea.setHarvestYear(
                inputInt("Năm thu hoạch: "));

        tea.setNetWeight(
                inputPositiveDouble("Khối lượng tịnh (g): "));

        tea.setTeaGrade(
                inputString("Phân hạng trà: "));

        tea.setBrewingTemperature(
                inputInt("Nhiệt độ pha (°C): "));

        tea.setBrewingTime(
                inputString("Thời gian pha (giây): "));

        tea.setExpiryDate(
                inputDate("Hạn sử dụng (dd/MM/yyyy): "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(tea);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      THÊM TRÀ THÀNH CÔNG!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("        THÊM TRÀ THẤT BẠI!");
            System.out.println("========================================");

        }

    }

//========================================
// ADD TEA POT
//========================================
    /**
     * Thêm mới ấm trà.
     */
    private void addTeaPot() {

        printHeader("THÊM ẤM TRÀ");

        //========================================
        // Tạo đối tượng TeaPot
        //========================================
        TeaPot teaPot = new TeaPot();

        //========================================
        // Sinh mã sản phẩm
        //========================================
        teaPot.setProductId(productService.generateProductId("POT"));

        System.out.println("Mã sản phẩm: " + teaPot.getProductId());

        //========================================
        // Nhập thông tin chung
        //========================================
        inputProductInfo(teaPot);

        //========================================
        // Nhập thông tin riêng của TeaPot
        //========================================
        teaPot.setClayType(
                inputString("Loại đất: "));

        teaPot.setShape(
                inputString("Kiểu dáng: "));

        teaPot.setCapacity(
                inputInt("Dung tích (ml): "));

        teaPot.setMaterial(
                inputString("Chất liệu: "));

        teaPot.setColor(
                inputString("Màu sắc: "));

        teaPot.setBrand(
                inputString("Thương hiệu: "));

        teaPot.setOrigin(
                inputString("Xuất xứ: "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(teaPot);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("    THÊM ẤM TRÀ THÀNH CÔNG!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       THÊM ẤM TRÀ THẤT BẠI!");
            System.out.println("========================================");

        }

    }

    //========================================
// ADD TEA CUP
//========================================
    /**
     * Thêm mới chén trà.
     */
    private void addTeaCup() {

        printHeader("THÊM CHÉN TRÀ");

        //========================================
        // Tạo đối tượng TeaCup
        //========================================
        TeaCup teaCup = new TeaCup();

        //========================================
        // Sinh mã sản phẩm
        //========================================
        teaCup.setProductId(productService.generateProductId("CUP"));

        System.out.println("Mã sản phầm: " + teaCup.getProductId());

        //========================================
        // Nhập thông tin chung
        //========================================
        inputProductInfo(teaCup);

        //========================================
        // Nhập thông tin riêng của TeaCup
        //========================================
        teaCup.setCupType(
                inputString("Loại chén: "));

        teaCup.setCapacity(
                inputInt("Dung tích (ml): "));

        teaCup.setMaterial(
                inputString("Chất liệu: "));

        teaCup.setColor(
                inputString("Màu sắc: "));

        teaCup.setQuantityPerSet(
                inputInt("Số lượng trong bộ: "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(teaCup);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("   THÊM CHÉN TRÀ THÀNH CÔNG!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       THÊM CHÉN TRÀ THẤT BẠI!");
            System.out.println("========================================");

        }

    }

//========================================
// ADD ACCESSORY
//========================================
    /**
     * Thêm mới phụ kiện trà.
     */
    private void addAccessory() {

        printHeader("THÊM PHỤ KIỆN");

        //========================================
        // Tạo đối tượng Accessory
        //========================================
        Accessory accessory = new Accessory();

        //========================================
        // Sinh mã sản phẩm
        //========================================
        accessory.setProductId(productService.generateProductId("ACC"));

        System.out.println("Mã sản phẩm: " + accessory.getProductId());

        //========================================
        // Nhập thông tin chung
        //========================================
        inputProductInfo(accessory);

        //========================================
        // Nhập thông tin riêng của Accessory
        //========================================
        accessory.setAccessoryType(
                inputString("Loại phụ kiện: "));

        accessory.setMaterial(
                inputString("Chất liệu: "));

        accessory.setSize(
                inputString("Kích thước: "));

        accessory.setColor(
                inputString("Màu sắc: "));

        accessory.setBrand(
                inputString("Thương hiệu: "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(accessory);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println(" THÊM PHỤ KIỆN THÀNH CÔNG!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("    THÊM PHỤ KIỆN THẤT BẠI!");
            System.out.println("========================================");

        }

    }

    //========================================
    // HEADER
    //========================================
    private void printHeader(String title) {

        System.out.println();

        System.out.println("========================================");
        System.out.printf("%20s%n", title);
        System.out.println("========================================");

    }

    //========================================
    // PAUSE
    //========================================
    private void pause() {

        System.out.println();
        System.out.print("Nhấn ENTER để tiếp tục...");
        scanner.nextLine();

    }

    //========================================
    // CLEAR SCREEN
    //========================================
    private void clearScreen() {

        for (int i = 0; i < 3; i++) {

            System.out.println();

        }

    }

    //========================================
    // VIEW PRODUCT
    //========================================
    private void viewAllProducts() {

        printHeader("DANH SÁCH SẢN PHẨM");

        productService.displayAllProducts();

    }

    //========================================
// ADD PRODUCT MENU
//========================================
    private void addProductMenu() {

        int choice;

        do {

            clearScreen();

            printHeader("THÊM SẢN PHẨM");

            System.out.println("1. Trà");
            System.out.println("2. Ấm trà");
            System.out.println("3. Tách trà");
            System.out.println("4. Phụ kiện");
            System.out.println("0. Quay lại");

            choice = inputChoice();

            switch (choice) {

                case 1:

                    addTea();
                    pause();
                    break;

                case 2:

                    addTeaPot();
                    pause();
                    break;

                case 3:

                    addTeaCup();
                    pause();
                    break;

                case 4:

                    addAccessory();
                    pause();
                    break;

                case 0:

                    break;

                default:

                    System.out.println("Lựa chọn không hợp lệ!");
                    pause();

            }

        } while (choice != 0);

    }

    //========================================
// UPDATE TEA
//========================================
    /**
     * Cập nhật thông tin Tea.
     *
     * @param tea Tea cần cập nhật
     */
    private void updateTea(Tea tea) {

        printHeader("CẬP NHẬT TRÀ");

        System.out.println("Mã sản phẩm: " + tea.getProductId());

        //========================================
        // Cập nhật thông tin chung
        //========================================
        updateProductInfo(tea);

        //========================================
        // Cập nhật thông tin riêng của Tea
        //========================================
        tea.setTeaType(
        updateString("Loại trà", tea.getTeaType()));

tea.setOrigin(
        updateString("Xuất xứ", tea.getOrigin()));

tea.setHarvestYear(
        updateInt("Năm thu hoạch", tea.getHarvestYear()));

tea.setNetWeight(
        updateDouble("Khối lượng tịnh (g)", tea.getNetWeight()));

tea.setTeaGrade(
        updateString("Phân hạng trà", tea.getTeaGrade()));

tea.setBrewingTemperature(
        updateInt("Nhiệt độ pha (°C)", tea.getBrewingTemperature()));

tea.setBrewingTime(
        updateString("Thời gian pha (giây)", tea.getBrewingTime()));

tea.setExpiryDate(
        updateDate("Hạn sử dụng", tea.getExpiryDate()));

        //========================================
        // Lưu thay đổi
        //========================================
        if (productService.updateProduct(tea)) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("    CẬP NHẬT TRÀ THÀNH CÔNG!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      CẬP NHẬT TRÀ THẤT BẠI!");
            System.out.println("========================================");

        }

    }
//========================================
// UPDATE TEA POT
//========================================

    private void updateTeaPot(TeaPot teaPot) {

        printHeader("CẬP NHẬT ẤM TRÀ");

        System.out.println("Mã sản phẩm: " + teaPot.getProductId());

        updateProductInfo(teaPot);

        teaPot.setClayType(
            updateString("Loại đất", teaPot.getClayType()));

    teaPot.setShape(
            updateString("Kiểu dáng", teaPot.getShape()));

    teaPot.setCapacity(
            updateInt("Dung tích (ml)", teaPot.getCapacity()));

    teaPot.setMaterial(
            updateString("Chất liệu", teaPot.getMaterial()));

    teaPot.setColor(
            updateString("Màu sắc", teaPot.getColor()));

    teaPot.setBrand(
            updateString("Thương hiệu", teaPot.getBrand()));

    teaPot.setOrigin(
            updateString("Xuất xứ", teaPot.getOrigin()));

        if (productService.updateProduct(teaPot)) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("    CẬP NHẬT ẤM TRÀ THÀNH CÔNG!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("    CẬP NHẬT ẤM TRÀ THẤT BẠI!");
            System.out.println("========================================");

        }

    }
//========================================
// UPDATE TEA CUP
//========================================

    private void updateTeaCup(TeaCup teaCup) {

        printHeader("CẬP NHẬT CHÉN TRÀ");

        System.out.println("Mã sản phẩm: " + teaCup.getProductId());

        updateProductInfo(teaCup);

       teaCup.setCupType(
            updateString("Loại chén", teaCup.getCupType()));

    teaCup.setCapacity(
            updateInt("Dung tích (ml)", teaCup.getCapacity()));

    teaCup.setMaterial(
            updateString("Chất liệu", teaCup.getMaterial()));

    teaCup.setColor(
            updateString("Màu sắc", teaCup.getColor()));

    teaCup.setQuantityPerSet(
            updateInt("Số lượng trong bộ", teaCup.getQuantityPerSet()));

        if (productService.updateProduct(teaCup)) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("    CẬP NHẬT CHÉN TRÀ THÀNH CÔNG!");
            System.out.println("========================================");

        } else {
            System.out.println();
            System.out.println("========================================");
            System.out.println("    CẬP NHẬT CHÉN TRÀ THẤT BẠI!");
            System.out.println("========================================");
        }

    }
//========================================
// UPDATE ACCESSORY
//========================================

    private void updateAccessory(Accessory accessory) {

        printHeader("CẬP NHẬT PHỤ KIỆN");

        System.out.println("Mã sản phẩm: " + accessory.getProductId());

        updateProductInfo(accessory);

       accessory.setAccessoryType(
            updateString("Loại phụ kiện", accessory.getAccessoryType()));

    accessory.setMaterial(
            updateString("Chất liệu", accessory.getMaterial()));

    accessory.setSize(
            updateString("Kích thước", accessory.getSize()));

    accessory.setColor(
            updateString("Màu sắc", accessory.getColor()));

    accessory.setBrand(
            updateString("Thương hiệu", accessory.getBrand()));

        if (productService.updateProduct(accessory)) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("    CẬP NHẬT PHỤ KIỆN THÀNH CÔNG!");
            System.out.println("========================================");
        } else {
            System.out.println();
            System.out.println("========================================");
            System.out.println("    CẬP NHẬT PHỤ KIỆN THẤT BẠI!");
            System.out.println("========================================");
        }

    }

    //========================================
// UPDATE PRODUCT
//========================================
    private void updateProduct() {

        printHeader("CẬP NHẬT SẢM PHẨM");

        String productId = inputString("Nhập mã sản phẩm: ");

        Product product = productService.findProductById(productId);

        if (product == null) {

            System.out.println("Không tìm thấy sản phẩm!");
            return;

        }

        if (product instanceof Tea) {

            updateTea((Tea) product);

        } else if (product instanceof TeaPot) {

            updateTeaPot((TeaPot) product);

        } else if (product instanceof TeaCup) {

            updateTeaCup((TeaCup) product);

        } else if (product instanceof Accessory) {

            updateAccessory((Accessory) product);

        }

    }

    //========================================
// DELETE PRODUCT
//========================================
    private void deleteProduct() {

        printHeader("XÓA SẢN PHẨM");

        String productId = inputString("Nhập mã sản phẩm: ");

        Product product = productService.findProductById(productId);

        if (product == null) {

            System.out.println("Không tìm thấy sản phẩm!");

            return;

        }

        System.out.println(product);

        String confirm = inputString("Bạn có chắc muốn xóa sản phẩm này? (Y/N):: ");

        if (confirm.equalsIgnoreCase("Y")) {

            if (productService.deleteProduct(productId)) {
                System.out.println();
                System.out.println("========================================");
                System.out.println("    XÓA SẢN PHẨM THÀNH CÔNG!");
                System.out.println("========================================");
            } else {
                System.out.println();
                System.out.println("========================================");
                System.out.println("    XÓA SẢN PHẨM THẤT BẠI!");
                System.out.println("========================================");
            }

        } else {

            System.out.println("Đã hủy thao tác.");

        }

    }

    //========================================
// SEARCH PRODUCT
//========================================
    private void searchProduct() {

        printHeader("TÌM KIẾM SẢN PHẨM");

        String keyword = inputString("Nhập tên sản phẩm: ");

        productService.displaySearchResult(keyword);

    }

}
