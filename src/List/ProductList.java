package List;

import java.util.ArrayList;
import java.util.List;

import Product.Product;
import Product.Supplier;
import java.time.LocalDate;

import Enum.ProductStatus;
import Enum.Unit;
import Product.Tea;
import Product.TeaPot;
import Product.TeaCup;
import Product.Accessory;

/**
 * ============================================================ DataStore
 * ------------------------------------------------------------ Lưu trữ toàn bộ
 * dữ liệu của hệ thống trong bộ nhớ (RAM).
 *
 * Đây là "Database giả lập" của chương trình. Tất cả các module (Product,
 * Inventory, Sales, Purchase...) đều sử dụng chung dữ liệu tại đây.
 *
 * @author Long ============================================================
 */
public class ProductList {

    /**
     * Danh sách toàn bộ sản phẩm.
     */
    public static List<Product> products = new ArrayList<>();

    /**
     * Danh sách toàn bộ nhà cung cấp.
     */
    public static List<Supplier> suppliers = new ArrayList<>();

    /**
     * Khởi tạo dữ liệu mẫu cho toàn bộ hệ thống.
     */
    public static void initialize() {

        // Nếu đã có dữ liệu thì không khởi tạo lại
        if (!products.isEmpty() || !suppliers.isEmpty()) {
            return;
        }

        loadSuppliers();
        loadProducts();

    }

    /**
     * Khởi tạo danh sách nhà cung cấp.
     */
    private static void loadSuppliers() {

        Supplier supplier;

        // =====================================================
        // SUP001 - Trà Tân Cương
        // =====================================================
        supplier = new Supplier();
        supplier.setSupplierId("SUP001");
        supplier.setSupplierName("Trà Tân Cương Thái Nguyên");
        supplier.setPhone("0208 3855 999");
        supplier.setEmail("contact@tancuong.vn");
        supplier.setAddress("Thái Nguyên, Việt Nam");
        supplier.setDescription("Chuyên cung cấp các loại trà xanh và trà đặc sản Thái Nguyên.");
        suppliers.add(supplier);

        // =====================================================
        // SUP002 - Shan Tuyết
        // =====================================================
        supplier = new Supplier();
        supplier.setSupplierId("SUP002");
        supplier.setSupplierName("Shan Tuyết Hà Giang");
        supplier.setPhone("0219 3888 666");
        supplier.setEmail("sales@shantuyet.vn");
        supplier.setAddress("Hà Giang, Việt Nam");
        supplier.setDescription("Chuyên cung cấp trà Shan Tuyết cổ thụ.");
        suppliers.add(supplier);

        // =====================================================
        // SUP003 - Gốm Bát Tràng
        // =====================================================
        supplier = new Supplier();
        supplier.setSupplierId("SUP003");
        supplier.setSupplierName("Gốm Bát Tràng");
        supplier.setPhone("024 3876 1234");
        supplier.setEmail("info@battrang.vn");
        supplier.setAddress("Gia Lâm, Hà Nội");
        supplier.setDescription("Chuyên sản xuất chén trà, khải, tống và trà cụ bằng gốm.");
        suppliers.add(supplier);

        // =====================================================
        // SUP004 - Nghi Hưng Tử Sa
        // =====================================================
        supplier = new Supplier();
        supplier.setSupplierId("SUP004");
        supplier.setSupplierName("Nghi Hưng Tử Sa");
        supplier.setPhone("0868 888 888");
        supplier.setEmail("support@yixing.cn");
        supplier.setAddress("Nghi Hưng, Giang Tô, Trung Quốc");
        supplier.setDescription("Chuyên sản xuất ấm tử sa cao cấp.");
        suppliers.add(supplier);

        // =====================================================
        // SUP005 - Tea Grace Việt Nam
        // =====================================================
        supplier = new Supplier();
        supplier.setSupplierId("SUP005");
        supplier.setSupplierName("Tea Grace Việt Nam");
        supplier.setPhone("0909 123 456");
        supplier.setEmail("contact@teagrace.vn");
        supplier.setAddress("TP. Hồ Chí Minh");
        supplier.setDescription("Chuyên phụ kiện trà đạo và quà tặng.");
        suppliers.add(supplier);

        // =====================================================
        // SUP006 - Minh Long Ceramics
        // =====================================================
        supplier = new Supplier();
        supplier.setSupplierId("SUP006");
        supplier.setSupplierName("Minh Long Ceramics");
        supplier.setPhone("0274 3866 999");
        supplier.setEmail("info@minhlong.vn");
        supplier.setAddress("Bình Dương");
        supplier.setDescription("Chuyên bộ ấm chén sứ cao cấp.");
        suppliers.add(supplier);

        // =====================================================
        // SUP007 - Lộc Tân Cương Tea
        // =====================================================
        supplier = new Supplier();
        supplier.setSupplierId("SUP007");
        supplier.setSupplierName("Lộc Tân Cương Tea");
        supplier.setPhone("0208 3765 555");
        supplier.setEmail("contact@loctancuong.vn");
        supplier.setAddress("Thái Nguyên");
        supplier.setDescription("Chuyên trà đặc sản và trà quà tặng.");
        suppliers.add(supplier);

    }

    /**
     * Khởi tạo danh sách sản phẩm.
     */
    private static void loadProducts() {

        Tea tea;

//========================================================
// T001 - Trà Ô Long Cao Cấp
//========================================================
        tea = new Tea();

        tea.setProductId("T001");
        tea.setProductName("Trà Ô Long Cao Cấp");
        tea.setImportPrice(250000);
        tea.setSellingPrice(320000);
        tea.setStockQuantity(50);
        tea.setUnit(Unit.BOX);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Trà Ô Long cao cấp, hương thơm tự nhiên.");
        tea.setSupplier(findSupplierById("SUP001"));

        tea.setTeaType("Ô Long");
        tea.setTeaGrade("Đặc Biệt");
        tea.setOrigin("Lâm Đồng");
        tea.setHarvestYear(2025);
        tea.setNetWeight(200);
        tea.setBrewingTemperature(90);
        tea.setBrewingTime("30 - 45 giây");
        tea.setExpiryDate(LocalDate.of(2027, 6, 30));

        products.add(tea);

//========================================================
// T002 - Thiết Quan Âm
//========================================================
        tea = new Tea();

        tea.setProductId("T002");
        tea.setProductName("Thiết Quan Âm");
        tea.setImportPrice(280000);
        tea.setSellingPrice(360000);
        tea.setStockQuantity(40);
        tea.setUnit(Unit.BOX);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Trà bán lên men nổi tiếng của Phúc Kiến.");
        tea.setSupplier(findSupplierById("SUP001"));

        tea.setTeaType("Ô Long");
        tea.setTeaGrade("Thượng Hạng");
        tea.setOrigin("Phúc Kiến");
        tea.setHarvestYear(2025);
        tea.setNetWeight(200);
        tea.setBrewingTemperature(95);
        tea.setBrewingTime("20 - 30 giây");
        tea.setExpiryDate(LocalDate.of(2027, 5, 20));

        products.add(tea);

//========================================================
// T003 - Đông Phương Mỹ Nhân
//========================================================
        tea = new Tea();

        tea.setProductId("T003");
        tea.setProductName("Đông Phương Mỹ Nhân");
        tea.setImportPrice(450000);
        tea.setSellingPrice(560000);
        tea.setStockQuantity(25);
        tea.setUnit(Unit.BOX);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Trà Ô Long cao cấp có hương mật ong.");
        tea.setSupplier(findSupplierById("SUP002"));

        tea.setTeaType("Ô Long");
        tea.setTeaGrade("Premium");
        tea.setOrigin("Đài Loan");
        tea.setHarvestYear(2024);
        tea.setNetWeight(150);
        tea.setBrewingTemperature(90);
        tea.setBrewingTime("40 giây");
        tea.setExpiryDate(LocalDate.of(2027, 4, 30));

        products.add(tea);

//========================================================
// T004 - Bạch Trà Shan Tuyết
//========================================================
        tea = new Tea();

        tea.setProductId("T004");
        tea.setProductName("Bạch Trà Shan Tuyết");
        tea.setImportPrice(320000);
        tea.setSellingPrice(420000);
        tea.setStockQuantity(30);
        tea.setUnit(Unit.BOX);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Bạch trà cổ thụ Hà Giang.");
        tea.setSupplier(findSupplierById("SUP002"));

        tea.setTeaType("Bạch Trà");
        tea.setTeaGrade("Đặc Biệt");
        tea.setOrigin("Hà Giang");
        tea.setHarvestYear(2025);
        tea.setNetWeight(200);
        tea.setBrewingTemperature(85);
        tea.setBrewingTime("40 giây");
        tea.setExpiryDate(LocalDate.of(2027, 8, 20));

        products.add(tea);

//========================================================
// T005 - Hồng Trà Ceylon
//========================================================
        tea = new Tea();

        tea.setProductId("T005");
        tea.setProductName("Hồng Trà Ceylon");
        tea.setImportPrice(180000);
        tea.setSellingPrice(250000);
        tea.setStockQuantity(60);
        tea.setUnit(Unit.BOX);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Hồng trà nhập khẩu từ Sri Lanka.");
        tea.setSupplier(findSupplierById("SUP001"));

        tea.setTeaType("Hồng Trà");
        tea.setTeaGrade("Loại 1");
        tea.setOrigin("Sri Lanka");
        tea.setHarvestYear(2025);
        tea.setNetWeight(200);
        tea.setBrewingTemperature(95);
        tea.setBrewingTime("2 phút");
        tea.setExpiryDate(LocalDate.of(2027, 3, 30));

        products.add(tea);

//========================================================
// T006 - Lục Trà Thái Nguyên
//========================================================
        tea = new Tea();

        tea.setProductId("T006");
        tea.setProductName("Lục Trà Thái Nguyên");
        tea.setImportPrice(150000);
        tea.setSellingPrice(220000);
        tea.setStockQuantity(80);
        tea.setUnit(Unit.BOX);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Đặc sản trà xanh Thái Nguyên.");
        tea.setSupplier(findSupplierById("SUP001"));

        tea.setTeaType("Lục Trà");
        tea.setTeaGrade("Đặc Sản");
        tea.setOrigin("Thái Nguyên");
        tea.setHarvestYear(2025);
        tea.setNetWeight(200);
        tea.setBrewingTemperature(80);
        tea.setBrewingTime("30 giây");
        tea.setExpiryDate(LocalDate.of(2027, 5, 15));

        products.add(tea);

//========================================================
// T007 - Phổ Nhĩ Chín 2018
//========================================================
        tea = new Tea();

        tea.setProductId("T007");
        tea.setProductName("Phổ Nhĩ Chín 2018");
        tea.setImportPrice(520000);
        tea.setSellingPrice(680000);
        tea.setStockQuantity(20);
        tea.setUnit(Unit.CAKE);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Bánh trà Phổ Nhĩ chín lâu năm.");
        tea.setSupplier(findSupplierById("SUP002"));

        tea.setTeaType("Phổ Nhĩ Chín");
        tea.setTeaGrade("Premium");
        tea.setOrigin("Vân Nam");
        tea.setHarvestYear(2018);
        tea.setNetWeight(357);
        tea.setBrewingTemperature(100);
        tea.setBrewingTime("20 giây");
        tea.setExpiryDate(LocalDate.of(2050, 12, 31));

        products.add(tea);

//========================================================
// T008 - Phổ Nhĩ Sống 2020
//========================================================
        tea = new Tea();

        tea.setProductId("T008");
        tea.setProductName("Phổ Nhĩ Sống 2020");
        tea.setImportPrice(480000);
        tea.setSellingPrice(620000);
        tea.setStockQuantity(18);
        tea.setUnit(Unit.CAKE);
        tea.setStatus(ProductStatus.AVAILABLE);
        tea.setDescription("Bánh trà Phổ Nhĩ sống có thể lưu trữ lâu năm.");
        tea.setSupplier(findSupplierById("SUP002"));

        tea.setTeaType("Phổ Nhĩ Sống");
        tea.setTeaGrade("Premium");
        tea.setOrigin("Vân Nam");
        tea.setHarvestYear(2020);
        tea.setNetWeight(357);
        tea.setBrewingTemperature(95);
        tea.setBrewingTime("15 giây");
        tea.setExpiryDate(LocalDate.of(2055, 12, 31));

        products.add(tea);
        
        
        //========================================================
// P001 - Ấm Tây Thi Tử Sa
//========================================================
TeaPot teaPot = new TeaPot();

teaPot.setProductId("P001");
teaPot.setProductName("Ấm Tây Thi Tử Sa 180ml");

teaPot.setImportPrice(650000);
teaPot.setSellingPrice(890000);

teaPot.setStockQuantity(12);

teaPot.setUnit(Unit.PIECE);

teaPot.setStatus(ProductStatus.AVAILABLE);

teaPot.setDescription("Ấm Tây Thi đất Tử Sa thích hợp pha Ô Long.");

teaPot.setSupplier(findSupplierById("SUP004"));

teaPot.setClayType("Tử Sa");
teaPot.setShape("Tây Thi");
teaPot.setCapacity(180);
teaPot.setMaterial("Đất nung");
teaPot.setColor("Tím");
teaPot.setBrand("Nghi Hưng");
teaPot.setOrigin("Nghi Hưng - Trung Quốc");

products.add(teaPot);

//========================================================
// P002 - Ấm Thạch Biều
//========================================================
teaPot = new TeaPot();

teaPot.setProductId("P002");
teaPot.setProductName("Ấm Thạch Biều 200ml");

teaPot.setImportPrice(580000);
teaPot.setSellingPrice(780000);

teaPot.setStockQuantity(15);

teaPot.setUnit(Unit.PIECE);

teaPot.setStatus(ProductStatus.AVAILABLE);

teaPot.setDescription("Ấm Thạch Biều truyền thống.");

teaPot.setSupplier(findSupplierById("SUP004"));

teaPot.setClayType("Chu Nê");
teaPot.setShape("Thạch Biều");
teaPot.setCapacity(200);
teaPot.setMaterial("Đất nung");
teaPot.setColor("Đỏ");
teaPot.setBrand("Nghi Hưng");
teaPot.setOrigin("Nghi Hưng - Trung Quốc");

products.add(teaPot);

//========================================================
// P003 - Ấm Lục Phương
//========================================================
teaPot = new TeaPot();

teaPot.setProductId("P003");
teaPot.setProductName("Ấm Lục Phương 220ml");

teaPot.setImportPrice(720000);
teaPot.setSellingPrice(980000);

teaPot.setStockQuantity(8);

teaPot.setUnit(Unit.PIECE);

teaPot.setStatus(ProductStatus.AVAILABLE);

teaPot.setDescription("Ấm Lục Phương dáng cổ.");

teaPot.setSupplier(findSupplierById("SUP004"));

teaPot.setClayType("Đoàn Nê");
teaPot.setShape("Lục Phương");
teaPot.setCapacity(220);
teaPot.setMaterial("Đất nung");
teaPot.setColor("Vàng đất");
teaPot.setBrand("Nghi Hưng");
teaPot.setOrigin("Nghi Hưng - Trung Quốc");

products.add(teaPot);

//========================================================
// P004 - Ấm Chuyết Cầu
//========================================================
teaPot = new TeaPot();

teaPot.setProductId("P004");
teaPot.setProductName("Ấm Chuyết Cầu 150ml");

teaPot.setImportPrice(450000);
teaPot.setSellingPrice(620000);

teaPot.setStockQuantity(20);

teaPot.setUnit(Unit.PIECE);

teaPot.setStatus(ProductStatus.AVAILABLE);

teaPot.setDescription("Ấm nhỏ dành cho trà xanh.");

teaPot.setSupplier(findSupplierById("SUP003"));

teaPot.setClayType("Gốm");
teaPot.setShape("Chuyết Cầu");
teaPot.setCapacity(150);
teaPot.setMaterial("Gốm sứ");
teaPot.setColor("Nâu");
teaPot.setBrand("Bát Tràng");
teaPot.setOrigin("Bát Tràng - Việt Nam");

products.add(teaPot);

//========================================================
// P005 - Ấm Tứ Phương
//========================================================
teaPot = new TeaPot();

teaPot.setProductId("P005");
teaPot.setProductName("Ấm Tứ Phương 250ml");

teaPot.setImportPrice(820000);
teaPot.setSellingPrice(1100000);

teaPot.setStockQuantity(6);

teaPot.setUnit(Unit.PIECE);

teaPot.setStatus(ProductStatus.AVAILABLE);

teaPot.setDescription("Ấm Tứ Phương cao cấp.");

teaPot.setSupplier(findSupplierById("SUP004"));

teaPot.setClayType("Thanh Đoàn");
teaPot.setShape("Tứ Phương");
teaPot.setCapacity(250);
teaPot.setMaterial("Đất nung");
teaPot.setColor("Xám");
teaPot.setBrand("Nghi Hưng");
teaPot.setOrigin("Nghi Hưng - Trung Quốc");

products.add(teaPot);

//========================================================
// P006 - Ấm Cân Văn
//========================================================
teaPot = new TeaPot();

teaPot.setProductId("P006");
teaPot.setProductName("Ấm Cân Văn 180ml");

teaPot.setImportPrice(520000);
teaPot.setSellingPrice(720000);

teaPot.setStockQuantity(10);

teaPot.setUnit(Unit.PIECE);

teaPot.setStatus(ProductStatus.AVAILABLE);

teaPot.setDescription("Ấm Cân Văn kiểu cổ.");

teaPot.setSupplier(findSupplierById("SUP003"));

teaPot.setClayType("Gốm");
teaPot.setShape("Cân Văn");
teaPot.setCapacity(180);
teaPot.setMaterial("Gốm sứ");
teaPot.setColor("Trắng ngà");
teaPot.setBrand("Bát Tràng");
teaPot.setOrigin("Bát Tràng - Việt Nam");

products.add(teaPot);


//========================================================
// C001 - Chén Chủ Men Trắng
//========================================================
TeaCup teaCup = new TeaCup();

teaCup.setProductId("C001");
teaCup.setProductName("Chén Chủ Men Trắng");

teaCup.setImportPrice(60000);
teaCup.setSellingPrice(90000);

teaCup.setStockQuantity(40);

teaCup.setUnit(Unit.PIECE);

teaCup.setStatus(ProductStatus.AVAILABLE);

teaCup.setDescription("Chén chủ dùng cho người pha trà.");

teaCup.setSupplier(findSupplierById("SUP003"));

teaCup.setCupType("Chén Chủ");
teaCup.setCapacity(80);
teaCup.setMaterial("Sứ");
teaCup.setColor("Trắng");
teaCup.setQuantityPerSet(1);

products.add(teaCup);

//========================================================
// C002 - Chén Quân Men Trắng
//========================================================
teaCup = new TeaCup();

teaCup.setProductId("C002");
teaCup.setProductName("Chén Quân Men Trắng");

teaCup.setImportPrice(40000);
teaCup.setSellingPrice(65000);

teaCup.setStockQuantity(80);

teaCup.setUnit(Unit.PIECE);

teaCup.setStatus(ProductStatus.AVAILABLE);

teaCup.setDescription("Chén quân dùng tiếp khách.");

teaCup.setSupplier(findSupplierById("SUP003"));

teaCup.setCupType("Chén Quân");
teaCup.setCapacity(45);
teaCup.setMaterial("Sứ");
teaCup.setColor("Trắng");
teaCup.setQuantityPerSet(1);

products.add(teaCup);

//========================================================
// C003 - Bộ Chén Sứ 6 Cái
//========================================================
teaCup = new TeaCup();

teaCup.setProductId("C003");
teaCup.setProductName("Bộ Chén Sứ 6 Cái");

teaCup.setImportPrice(220000);
teaCup.setSellingPrice(320000);

teaCup.setStockQuantity(20);

teaCup.setUnit(Unit.SET);

teaCup.setStatus(ProductStatus.AVAILABLE);

teaCup.setDescription("Bộ chén sứ gồm 6 chén.");

teaCup.setSupplier(findSupplierById("SUP006"));

teaCup.setCupType("Chén Quân");
teaCup.setCapacity(50);
teaCup.setMaterial("Sứ Cao Cấp");
teaCup.setColor("Trắng");
teaCup.setQuantityPerSet(6);

products.add(teaCup);

//========================================================
// C004 - Chén Thiên Mục
//========================================================
teaCup = new TeaCup();

teaCup.setProductId("C004");
teaCup.setProductName("Chén Thiên Mục");

teaCup.setImportPrice(180000);
teaCup.setSellingPrice(260000);

teaCup.setStockQuantity(18);

teaCup.setUnit(Unit.PIECE);

teaCup.setStatus(ProductStatus.AVAILABLE);

teaCup.setDescription("Chén Thiên Mục hoa văn cổ.");

teaCup.setSupplier(findSupplierById("SUP006"));

teaCup.setCupType("Thiên Mục");
teaCup.setCapacity(70);
teaCup.setMaterial("Gốm");
teaCup.setColor("Đen");
teaCup.setQuantityPerSet(1);

products.add(teaCup);

//========================================================
// C005 - Chén Tử Sa
//========================================================
teaCup = new TeaCup();

teaCup.setProductId("C005");
teaCup.setProductName("Chén Tử Sa");

teaCup.setImportPrice(90000);
teaCup.setSellingPrice(140000);

teaCup.setStockQuantity(25);

teaCup.setUnit(Unit.PIECE);

teaCup.setStatus(ProductStatus.AVAILABLE);

teaCup.setDescription("Chén Tử Sa đồng bộ với ấm.");

teaCup.setSupplier(findSupplierById("SUP004"));

teaCup.setCupType("Tử Sa");
teaCup.setCapacity(60);
teaCup.setMaterial("Đất Tử Sa");
teaCup.setColor("Nâu");
teaCup.setQuantityPerSet(1);

products.add(teaCup);

//========================================================
// C006 - Bộ Chén Bạch Ngọc
//========================================================
teaCup = new TeaCup();

teaCup.setProductId("C006");
teaCup.setProductName("Bộ Chén Bạch Ngọc");

teaCup.setImportPrice(320000);
teaCup.setSellingPrice(450000);

teaCup.setStockQuantity(12);

teaCup.setUnit(Unit.SET);

teaCup.setStatus(ProductStatus.AVAILABLE);

teaCup.setDescription("Bộ chén sứ trắng cao cấp.");

teaCup.setSupplier(findSupplierById("SUP006"));

teaCup.setCupType("Bạch Ngọc");
teaCup.setCapacity(55);
teaCup.setMaterial("Sứ Cao Cấp");
teaCup.setColor("Trắng Ngọc");
teaCup.setQuantityPerSet(6);

products.add(teaCup);

//========================================================
// A001 - Tea Pet Cá Chép
//========================================================
Accessory accessory = new Accessory();

accessory.setProductId("A001");
accessory.setProductName("Tea Pet Cá Chép");

accessory.setImportPrice(180000);
accessory.setSellingPrice(280000);

accessory.setStockQuantity(15);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Tea Pet trang trí bàn trà.");

accessory.setSupplier(findSupplierById("SUP003"));

accessory.setAccessoryType("Tea Pet");
accessory.setMaterial("Đất Tử Sa");
accessory.setSize("8 cm");
accessory.setColor("Nâu");
accessory.setBrand("Bát Tràng");

products.add(accessory);

//========================================================
// A002 - Tea Pet Thiềm Thừ
//========================================================
accessory = new Accessory();

accessory.setProductId("A002");
accessory.setProductName("Tea Pet Thiềm Thừ");

accessory.setImportPrice(220000);
accessory.setSellingPrice(350000);

accessory.setStockQuantity(10);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Tea Pet Thiềm Thừ phong thủy.");

accessory.setSupplier(findSupplierById("SUP003"));

accessory.setAccessoryType("Tea Pet");
accessory.setMaterial("Đất Tử Sa");
accessory.setSize("10 cm");
accessory.setColor("Nâu Đỏ");
accessory.setBrand("Bát Tràng");

products.add(accessory);

//========================================================
// A003 - Khay Trà Tre
//========================================================
accessory = new Accessory();

accessory.setProductId("A003");
accessory.setProductName("Khay Trà Tre");

accessory.setImportPrice(320000);
accessory.setSellingPrice(450000);

accessory.setStockQuantity(12);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Khay trà bằng tre tự nhiên.");

accessory.setSupplier(findSupplierById("SUP003"));

accessory.setAccessoryType("Khay Trà");
accessory.setMaterial("Tre");
accessory.setSize("40 x 25 cm");
accessory.setColor("Vàng Gỗ");
accessory.setBrand("Bát Tràng");

products.add(accessory);

//========================================================
// A004 - Khăn Trà
//========================================================
accessory = new Accessory();

accessory.setProductId("A004");
accessory.setProductName("Khăn Trà Cotton");

accessory.setImportPrice(35000);
accessory.setSellingPrice(60000);

accessory.setStockQuantity(50);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Khăn lau bàn trà.");

accessory.setSupplier(findSupplierById("SUP005"));

accessory.setAccessoryType("Khăn Trà");
accessory.setMaterial("Cotton");
accessory.setSize("30 x 30 cm");
accessory.setColor("Nâu");
accessory.setBrand("Tea Grace");

products.add(accessory);

//========================================================
// A005 - Kẹp Gắp Chén
//========================================================
accessory = new Accessory();

accessory.setProductId("A005");
accessory.setProductName("Kẹp Gắp Chén Tre");

accessory.setImportPrice(45000);
accessory.setSellingPrice(80000);

accessory.setStockQuantity(35);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Kẹp gắp chén khi tráng nước nóng.");

accessory.setSupplier(findSupplierById("SUP003"));

accessory.setAccessoryType("Dụng Cụ");
accessory.setMaterial("Tre");
accessory.setSize("18 cm");
accessory.setColor("Vàng");
accessory.setBrand("Bát Tràng");

products.add(accessory);

//========================================================
// A006 - Muỗng Xúc Trà
//========================================================
accessory = new Accessory();

accessory.setProductId("A006");
accessory.setProductName("Muỗng Xúc Trà");

accessory.setImportPrice(30000);
accessory.setSellingPrice(55000);

accessory.setStockQuantity(40);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Muỗng xúc trà bằng tre.");

accessory.setSupplier(findSupplierById("SUP003"));

accessory.setAccessoryType("Dụng Cụ");
accessory.setMaterial("Tre");
accessory.setSize("16 cm");
accessory.setColor("Vàng");
accessory.setBrand("Bát Tràng");

products.add(accessory);

//========================================================
// A007 - Gaiwan (Khải)
//========================================================
accessory = new Accessory();

accessory.setProductId("A007");
accessory.setProductName("Khải Sứ Trắng 150ml");

accessory.setImportPrice(180000);
accessory.setSellingPrice(260000);

accessory.setStockQuantity(18);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Khải pha trà truyền thống.");

accessory.setSupplier(findSupplierById("SUP006"));

accessory.setAccessoryType("Khải");
accessory.setMaterial("Sứ");
accessory.setSize("150 ml");
accessory.setColor("Trắng");
accessory.setBrand("Minh Long");

products.add(accessory);

//========================================================
// A008 - Tống Trà
//========================================================
accessory = new Accessory();

accessory.setProductId("A008");
accessory.setProductName("Tống Sứ 250ml");

accessory.setImportPrice(150000);
accessory.setSellingPrice(220000);

accessory.setStockQuantity(20);

accessory.setUnit(Unit.PIECE);

accessory.setStatus(ProductStatus.AVAILABLE);

accessory.setDescription("Tống dùng chia đều nước trà.");

accessory.setSupplier(findSupplierById("SUP006"));

accessory.setAccessoryType("Tống");
accessory.setMaterial("Sứ");
accessory.setSize("250 ml");
accessory.setColor("Trắng");
accessory.setBrand("Minh Long");

products.add(accessory);
    }

    /**
     * Tìm nhà cung cấp theo mã.
     *
     * @param supplierId Mã nhà cung cấp
     * @return Supplier nếu tìm thấy, ngược lại trả về null
     */
    public static Supplier findSupplierById(String supplierId) {

        for (Supplier supplier : suppliers) {

            if (supplier.getSupplierId().equalsIgnoreCase(supplierId)) {
                return supplier;
            }

        }

        return null;

    }

    /**
     * Tìm sản phẩm theo mã.
     *
     * @param productId Mã sản phẩm
     * @return Product nếu tìm thấy, ngược lại trả về null
     */
    public static Product findProductById(String productId) {

        for (Product product : products) {

            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }

        }

        return null;

    }

}
