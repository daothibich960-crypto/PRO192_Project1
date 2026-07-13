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
    private static final int SUPPLIER_MENU = 6;
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

                case SUPPLIER_MENU:

                    supplierMenu();
                    pause();
                    break;

                case BACK:

                    System.out.println("Back...");
                    break;

                default:

                    System.out.println("Invalid choice!");
                    pause();

            }

        } while (choice != BACK);

    }

    //========================================
    // PRODUCT MENU
    //========================================
    private void printProductMenu() {

        printHeader("\n===== QUẢN LÝ SẢN PHẨM =====");
        System.out.println("1. Xem tất cả sản phẩm");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Tìm kiếm sản phẩm");
        System.out.println("6. Quản lý nhà cung cấp");
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

            System.out.println("Input cannot be empty!");

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

                System.out.println("Please enter a valid integer!");

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

                System.out.println("Please enter a valid number!");

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

            System.out.println("Value must be greater than or equal to 0!");

        }

    }
    //========================================
// INPUT DATE
//========================================

    /**
     * Nhập ngày theo định dạng yyyy-MM-dd.
     *
     * @param message thông báo nhập
     * @return LocalDate
     */
    private LocalDate inputDate(String message) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {

            try {

                System.out.print(message);

                String input = scanner.nextLine();

                return LocalDate.parse(input, formatter);

            } catch (DateTimeParseException e) {

                System.out.println("Invalid date! Please use yyyy-MM-dd");
                System.out.println("Example: 2026-12-31");

            }

        }

    }

//========================================
// CHOOSE UNIT
//========================================
    private Unit chooseUnit() {

        while (true) {

            printHeader("CHỌN ĐƠN VỊ ");

            System.out.println("1. Gram");
            System.out.println("2. Kilogram");
            System.out.println("3. Piece");
            System.out.println("4. Set");
            System.out.println("5. Box");
            System.out.println("6. Bottle");
            System.out.println("7. Cake (357g)");

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
                    System.out.println("Invalid choice!");
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
                    System.out.println("Invalid choice!");
            }

        }

    }

    //========================================
// CHOOSE SUPPLIER
//========================================
    private Supplier chooseSupplier() {

        while (true) {

            printHeader("SUPPLIER LIST");

            supplierList.displayAllSupplier();

            String supplierId = inputString("Enter Supplier ID: ");

            Supplier supplier = supplierList.searchSupplier(supplierId);

            if (supplier != null) {

                return supplier;

            }

            System.out.println("Supplier does not exist!");
            pause();

            addSupplier();

        }

    }

    private void addSupplier() {
        System.out.println("Thêm nhà cung cấp");
        System.out.print("Nhập mã nhà cung cấp: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên nhà cung cấp: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số điện thoại nhà cung cấp: ");
        String phone = scanner.nextLine();
        System.out.print("Nhập email nhà cung cấp: ");
        String mail = scanner.nextLine();
        System.out.print("Nhập địa chỉ  nhà cung cấp: ");
        String address = scanner.nextLine();
        Supplier sup = new Supplier(id, name, phone, mail, address);
        supplierList.addSupplier(sup);
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

        System.out.println("\n===== PRODUCT INFORMATION =====");

        // Tên sản phẩm
        product.setProductName(
                inputString("Product Name: "));

        // Giá nhập
        product.setImportPrice(
                inputPositiveDouble("Import Price: "));

        // Giá bán
        product.setSellingPrice(
                inputPositiveDouble("Selling Price: "));

        // Số lượng ban đầu
        product.setStockQuantity(
                inputInt("Stock Quantity: "));

        // Đơn vị tính
        product.setUnit(
                chooseUnit());

        // Trạng thái
        product.setStatus(
                chooseStatus());

        // Mô tả
        product.setDescription(
                inputString("Description: "));

        // Nhà cung cấp
        product.setSupplier(
                chooseSupplier());

    }

    //========================================
// UPDATE COMMON PRODUCT INFO
//========================================
    private void updateProductInfo(Product product) {

        System.out.println("\n===== UPDATE PRODUCT =====");

        product.setProductName(
                inputString("Product Name: "));

        product.setImportPrice(
                inputPositiveDouble("Import Price: "));

        product.setSellingPrice(
                inputPositiveDouble("Selling Price: "));

        // Không cập nhật Stock Quantity
        // Kho sẽ quản lý số lượng
        product.setUnit(
                chooseUnit());

        product.setStatus(
                chooseStatus());

        product.setDescription(
                inputString("Description: "));

        product.setSupplier(
                chooseSupplier());

    }

//========================================
// ADD TEA
//========================================
    /**
     * Thêm mới sản phẩm trà.
     */
    private void addTea() {

        printHeader("ADD TEA");

        //========================================
        // Tạo đối tượng Tea
        //========================================
        Tea tea = new Tea();

        //========================================
        // Sinh mã sản phẩm tự động
        //========================================
        tea.setProductId(productService.generateProductId("TEA"));

        System.out.println("Product ID: " + tea.getProductId());

        //========================================
        // Nhập thông tin chung của Product
        //========================================
        inputProductInfo(tea);

        //========================================
        // Nhập thông tin riêng của Tea
        //========================================
        tea.setTeaType(
                inputString("Tea Type: "));

        tea.setOrigin(
                inputString("Origin: "));

        tea.setHarvestYear(
                inputInt("Harvest Year: "));

        tea.setNetWeight(
                inputPositiveDouble("Net Weight (g): "));

        tea.setTeaGrade(
                inputString("Tea Grade: "));

        tea.setBrewingTemperature(
                inputInt("Brewing Temperature (°C): "));

        tea.setBrewingTime(
                inputString("Brewing Time (seconds): "));

        tea.setExpiryDate(
                inputDate("Expiry Date (yyyy-MM-dd): "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(tea);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      ADD TEA SUCCESSFULLY!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("        ADD TEA FAILED!");
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

        printHeader("ADD TEA POT");

        //========================================
        // Tạo đối tượng TeaPot
        //========================================
        TeaPot teaPot = new TeaPot();

        //========================================
        // Sinh mã sản phẩm
        //========================================
        teaPot.setProductId(productService.generateProductId("POT"));

        System.out.println("Product ID: " + teaPot.getProductId());

        //========================================
        // Nhập thông tin chung
        //========================================
        inputProductInfo(teaPot);

        //========================================
        // Nhập thông tin riêng của TeaPot
        //========================================
        teaPot.setClayType(
                inputString("Clay Type: "));

        teaPot.setShape(
                inputString("Shape: "));

        teaPot.setCapacity(
                inputInt("Capacity (ml): "));

        teaPot.setMaterial(
                inputString("Material: "));

        teaPot.setColor(
                inputString("Color: "));

        teaPot.setBrand(
                inputString("Brand: "));

        teaPot.setOrigin(
                inputString("Origin: "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(teaPot);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("   ADD TEA POT SUCCESSFULLY!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      ADD TEA POT FAILED!");
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

        printHeader("ADD TEA CUP");

        //========================================
        // Tạo đối tượng TeaCup
        //========================================
        TeaCup teaCup = new TeaCup();

        //========================================
        // Sinh mã sản phẩm
        //========================================
        teaCup.setProductId(productService.generateProductId("CUP"));

        System.out.println("Product ID: " + teaCup.getProductId());

        //========================================
        // Nhập thông tin chung
        //========================================
        inputProductInfo(teaCup);

        //========================================
        // Nhập thông tin riêng của TeaCup
        //========================================
        teaCup.setCupType(
                inputString("Cup Type: "));

        teaCup.setCapacity(
                inputInt("Capacity (ml): "));

        teaCup.setMaterial(
                inputString("Material: "));

        teaCup.setColor(
                inputString("Color: "));

        teaCup.setQuantityPerSet(
                inputInt("Quantity Per Set: "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(teaCup);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("   ADD TEA CUP SUCCESSFULLY!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      ADD TEA CUP FAILED!");
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

        printHeader("ADD ACCESSORY");

        //========================================
        // Tạo đối tượng Accessory
        //========================================
        Accessory accessory = new Accessory();

        //========================================
        // Sinh mã sản phẩm
        //========================================
        accessory.setProductId(productService.generateProductId("ACC"));

        System.out.println("Product ID: " + accessory.getProductId());

        //========================================
        // Nhập thông tin chung
        //========================================
        inputProductInfo(accessory);

        //========================================
        // Nhập thông tin riêng của Accessory
        //========================================
        accessory.setAccessoryType(
                inputString("Accessory Type: "));

        accessory.setMaterial(
                inputString("Material: "));

        accessory.setSize(
                inputString("Size: "));

        accessory.setColor(
                inputString("Color: "));

        accessory.setBrand(
                inputString("Brand: "));

        //========================================
        // Lưu vào DataStore
        //========================================
        boolean result = productService.addProduct(accessory);

        if (result) {

            System.out.println();
            System.out.println("========================================");
            System.out.println(" ADD ACCESSORY SUCCESSFULLY!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("    ADD ACCESSORY FAILED!");
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
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();

    }

    //========================================
    // CLEAR SCREEN
    //========================================
    private void clearScreen() {

        for (int i = 0; i < 30; i++) {

            System.out.println();

        }

    }

    //========================================
    // VIEW PRODUCT
    //========================================
    private void viewAllProducts() {

        printHeader("PRODUCT LIST");

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

                    System.out.println("Invalid choice!");
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

        printHeader("UPDATE TEA");

        System.out.println("Product ID: " + tea.getProductId());

        //========================================
        // Cập nhật thông tin chung
        //========================================
        updateProductInfo(tea);

        //========================================
        // Cập nhật thông tin riêng của Tea
        //========================================
        tea.setTeaType(
                inputString("Tea Type: "));

        tea.setOrigin(
                inputString("Origin: "));

        tea.setHarvestYear(
                inputInt("Harvest Year: "));

        tea.setNetWeight(
                inputPositiveDouble("Net Weight (g): "));

        tea.setTeaGrade(
                inputString("Tea Grade: "));

        tea.setBrewingTemperature(
                inputInt("Brewing Temperature (°C): "));

        tea.setBrewingTime(
                inputString("Brewing Time (seconds): "));

        tea.setExpiryDate(
                inputDate("Expiry Date (yyyy-MM-dd): "));

        //========================================
        // Lưu thay đổi
        //========================================
        if (productService.updateProduct(tea)) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("    UPDATE TEA SUCCESSFULLY!");
            System.out.println("========================================");

        } else {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      UPDATE TEA FAILED!");
            System.out.println("========================================");

        }

    }
//========================================
// UPDATE TEA POT
//========================================

    private void updateTeaPot(TeaPot teaPot) {

        printHeader("UPDATE TEA POT");

        System.out.println("Product ID: " + teaPot.getProductId());

        updateProductInfo(teaPot);

        teaPot.setClayType(
                inputString("Clay Type: "));

        teaPot.setShape(
                inputString("Shape: "));

        teaPot.setCapacity(
                inputInt("Capacity (ml): "));

        teaPot.setMaterial(
                inputString("Material: "));

        teaPot.setColor(
                inputString("Color: "));

        teaPot.setBrand(
                inputString("Brand: "));

        teaPot.setOrigin(
                inputString("Origin: "));

        if (productService.updateProduct(teaPot)) {

            System.out.println("\nUPDATE TEA POT SUCCESSFULLY!");

        } else {

            System.out.println("\nUPDATE FAILED!");

        }

    }
//========================================
// UPDATE TEA CUP
//========================================

    private void updateTeaCup(TeaCup teaCup) {

        printHeader("UPDATE TEA CUP");

        System.out.println("Product ID: " + teaCup.getProductId());

        updateProductInfo(teaCup);

        teaCup.setCupType(
                inputString("Cup Type: "));

        teaCup.setCapacity(
                inputInt("Capacity (ml): "));

        teaCup.setMaterial(
                inputString("Material: "));

        teaCup.setColor(
                inputString("Color: "));

        teaCup.setQuantityPerSet(
                inputInt("Quantity Per Set: "));

        if (productService.updateProduct(teaCup)) {

            System.out.println("\nUPDATE TEA CUP SUCCESSFULLY!");

        } else {

            System.out.println("\nUPDATE FAILED!");

        }

    }
//========================================
// UPDATE ACCESSORY
//========================================

    private void updateAccessory(Accessory accessory) {

        printHeader("UPDATE ACCESSORY");

        System.out.println("Product ID: " + accessory.getProductId());

        updateProductInfo(accessory);

        accessory.setAccessoryType(
                inputString("Accessory Type: "));

        accessory.setMaterial(
                inputString("Material: "));

        accessory.setSize(
                inputString("Size: "));

        accessory.setColor(
                inputString("Color: "));

        accessory.setBrand(
                inputString("Brand: "));

        if (productService.updateProduct(accessory)) {

            System.out.println("\nUPDATE ACCESSORY SUCCESSFULLY!");

        } else {

            System.out.println("\nUPDATE FAILED!");

        }

    }

    //========================================
// UPDATE PRODUCT
//========================================
    private void updateProduct() {

        printHeader("UPDATE PRODUCT");

        String productId = inputString("Enter Product ID: ");

        Product product = productService.findProductById(productId);

        if (product == null) {

            System.out.println("Product not found!");
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

        printHeader("DELETE PRODUCT");

        String productId = inputString("Enter Product ID: ");

        Product product = productService.findProductById(productId);

        if (product == null) {

            System.out.println("Product not found!");

            return;

        }

        System.out.println(product);

        String confirm = inputString("Delete this product? (Y/N): ");

        if (confirm.equalsIgnoreCase("Y")) {

            if (productService.deleteProduct(productId)) {

                System.out.println("Delete successfully!");

            } else {

                System.out.println("Delete failed!");

            }

        } else {

            System.out.println("Cancelled.");

        }

    }

    //========================================
// SEARCH PRODUCT
//========================================
    private void searchProduct() {

        printHeader("SEARCH PRODUCT");

        String keyword = inputString("Enter Product Name: ");

        productService.displaySearchResult(keyword);

    }

    //========================================
    // SUPPLIER MENU
    //========================================
    private void supplierMenu() {

        printHeader("SUPPLIER MENU");

        // TODO
    }
}
