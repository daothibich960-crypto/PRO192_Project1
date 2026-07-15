package Inventory;

import Enum.ProductStatus;
import Enum.Unit;
import FileIO.FileIO;
import Product.Accessory;
import Product.Product;
import Product.Tea;
import Product.TeaCup;
import Product.TeaPot;
import Supplier.Supplier;
import Supplier.SupplierList;
import Utils.Formatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private ArrayList<Product> products;

    public Inventory() {

        products = new ArrayList<>();

    }

    // Add Product
    public void addProduct(Product product) {

        products.add(product);

    }

    // Remove Product
    public void removeProduct(String productId) {

        Product product = searchProduct(productId);

        if (product != null) {

            products.remove(product);

        }

    }

    // Search
    public Product searchProduct(String productId) {

        for (Product product : products) {

            if (product.getProductId().equalsIgnoreCase(productId)) {

                return product;

            }

        }

        return null;

    }

    // Nhập kho
    public void stockIn(String productId, int quantity) {

        Product product = searchProduct(productId);

        if (product == null) {

            return;

        }

        product.setStockQuantity(
                product.getStockQuantity() + quantity);

    }

    // Xuất kho
    public void stockOut(String productId, int quantity) {

        Product product = searchProduct(productId);

        if (product == null) {

            return;

        }

        if (product.getStockQuantity() < quantity) {

            System.out.println("Not enough quantity.");

            return;

        }

        product.setStockQuantity(
                product.getStockQuantity() - quantity);

    }

    // Update
    public void updateProductQuantity(String productId,
            int quantity) {

        Product product = searchProduct(productId);

        if (product != null) {

            product.setStockQuantity(quantity);

        }

    }

    // So luong hien tai
    public int getCurrentQuantity(String productId) {

        Product product = searchProduct(productId);

        if (product == null) {

            return 0;

        }

        return product.getStockQuantity();

    }

    // sắp hêt hàng
    public void checkLowStock(int minimumStock) {

        System.out.println("LOW STOCK");

        for (Product product : products) {

            if (product.getStockQuantity() <= minimumStock) {

                System.out.println(product.getProductId()
                        + " - "
                        + product.getProductName());

            }

        }

    }

    // Display
    public void displayInventory() {

    if (products.isEmpty()) {
        System.out.println("Kho hiện không có sản phẩm!");
        return;
    }

    System.out.println("=================================================================================================================");
    System.out.printf("%-10s %-30s %-18s %-18s %-10s %-18s%n",
            "Mã SP",
            "Tên sản phẩm",
            "Giá nhập",
            "Giá bán",
            "Tồn kho",
            "Giá trị tồn");
    System.out.println("-----------------------------------------------------------------------------------------------------------------");

    for (Product product : products) {

        double inventoryValue = product.getImportPrice() * product.getStockQuantity();

        System.out.printf("%-10s %-30s %-18s %-18s %-10d %-18s%n",
                product.getProductId(),
                product.getProductName(),
                Formatter.currency(product.getImportPrice()),
                Formatter.currency(product.getSellingPrice()),
                product.getStockQuantity(),
                Formatter.currency(inventoryValue));
    }

    System.out.println("=================================================================================================================");
}
    //
    public Product getProduct(String productID) {
        Product d = null;
        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(productID)) {
                d = p;
            }
        }
        return d;
    }

    //========================================
// GET ALL PRODUCTS
//========================================
    public ArrayList<Product> getProducts() {

        return products;

    }

    public void loadFromFile(String filePath, SupplierList supplierList) {
        products.clear();
        List<Product> loaded = FileIO.readFile(filePath, fields -> {
            String type = fields[0].trim();

            String productId = fields[1].trim();
            String productName = fields[2].trim();
            double importPrice = Double.parseDouble(fields[3].trim());
            double sellingPrice = Double.parseDouble(fields[4].trim());
            int stockQty = Integer.parseInt(fields[5].trim());
            Unit unit = Unit.fromString(fields[6].trim());
            ProductStatus status = ProductStatus.fromString(fields[7].trim());
            String description = fields[8].trim();

            String supplierId = fields[9].trim();
            Supplier supplier = supplierId.equals("NONE") ? null : supplierList.searchSupplier(supplierId);

            Product p;

            if (type.equals("TEA")) {
                String teaType = fields[10].trim();
                String origin = fields[11].trim();
                int harvestYear = Integer.parseInt(fields[12].trim());
                double netWeight = Double.parseDouble(fields[13].trim());
                String teaGrade = fields[14].trim();
                int brewingTemperature = Integer.parseInt(fields[15].trim());
                String brewingTime = fields[16].trim();
                LocalDate expiryDate = LocalDate.parse(fields[17].trim());

                p = new Tea(productId, productName, importPrice, sellingPrice,
                        stockQty, unit, status, description, supplier,
                        teaType, origin, harvestYear, netWeight, teaGrade,
                        brewingTemperature, brewingTime, expiryDate);

            } else if (type.equals("TEAPOT")) {
                String clayType = fields[10].trim();
                String shape = fields[11].trim();
                int capacity = Integer.parseInt(fields[12].trim());
                String material = fields[13].trim();
                String color = fields[14].trim();
                String brand = fields[15].trim();
                String origin = fields[16].trim();

                p = new TeaPot(productId, productName, importPrice, sellingPrice,
                        stockQty, unit, status, description, supplier,
                        clayType, shape, capacity, material, color, brand, origin);

            } else if (type.equals("TEACUP")) {
                String cupType = fields[10].trim();
                int capacity = Integer.parseInt(fields[11].trim());
                String material = fields[12].trim();
                String color = fields[13].trim();
                int quantityPerSet = Integer.parseInt(fields[14].trim());

                p = new TeaCup(productId, productName, importPrice, sellingPrice,
                        stockQty, unit, status, description, supplier,
                        cupType, capacity, material, color, quantityPerSet);

            } else { // ACCESSORY
                String accessoryType = fields[10].trim();
                String material = fields[11].trim();
                String size = fields[12].trim();
                String color = fields[13].trim();
                String brand = fields[14].trim();

                p = new Accessory(productId, productName, importPrice, sellingPrice,
                        stockQty, unit, status, description, supplier,
                        accessoryType, material, size, color, brand);
            }
            return p;
        });
        products.addAll(loaded);
    }

    public void saveToFile(String filePath) {
        FileIO.writeFile(filePath, products, p -> {
            String supplierId = (p.getSupplier() != null) ? p.getSupplier().getSupplierId() : "NONE";

            String base = p.getProductId() + "|"
                    + p.getProductName() + "|"
                    + p.getImportPrice() + "|"
                    + p.getSellingPrice() + "|"
                    + p.getStockQuantity() + "|"
                    + p.getUnit() + "|"
                    + p.getStatus() + "|"
                    + p.getDescription() + "|"
                    + supplierId;

            if (p instanceof Tea) {
                Tea t = (Tea) p;
                return "TEA|" + base + "|"
                        + t.getTeaType() + "|"
                        + t.getOrigin() + "|"
                        + t.getHarvestYear() + "|"
                        + t.getNetWeight() + "|"
                        + t.getTeaGrade() + "|"
                        + t.getBrewingTemperature() + "|"
                        + t.getBrewingTime() + "|"
                        + t.getExpiryDate();

            } else if (p instanceof TeaPot) {
                TeaPot tp = (TeaPot) p;
                return "TEAPOT|" + base + "|"
                        + tp.getClayType() + "|"
                        + tp.getShape() + "|"
                        + tp.getCapacity() + "|"
                        + tp.getMaterial() + "|"
                        + tp.getColor() + "|"
                        + tp.getBrand() + "|"
                        + tp.getOrigin();

            } else if (p instanceof TeaCup) {
                TeaCup tc = (TeaCup) p;
                return "TEACUP|" + base + "|"
                        + tc.getCupType() + "|"
                        + tc.getCapacity() + "|"
                        + tc.getMaterial() + "|"
                        + tc.getColor() + "|"
                        + tc.getQuantityPerSet();

            } else {
                Accessory ac = (Accessory) p;
                return "ACCESSORY|" + base + "|"
                        + ac.getAccessoryType() + "|"
                        + ac.getMaterial() + "|"
                        + ac.getSize() + "|"
                        + ac.getColor() + "|"
                        + ac.getBrand();
            }
        });
    }
}
