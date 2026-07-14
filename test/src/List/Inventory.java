
package List;

import Product.Product;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products;

    public Inventory() {

        products = new ArrayList<>();

    }

    // Thêm sản phẩm vào kho
    public void addProduct(Product product) {

        if (product != null) {

            products.add(product);

        }

    }

    // Xóa sản phẩm
    public void removeProduct(String productId) {

        Product product = searchProduct(productId);

        if (product != null) {

            products.remove(product);

        }

    }

    // Tìm sản phẩm theo mã
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

            System.out.println("Không tìm thấy sản phẩm.");

            return;

        }

        product.setStockQuantity(
                product.getStockQuantity() + quantity);

        System.out.println("Nhập kho thành công.");

    }

    // Xuất kho
    public void stockOut(String productId, int quantity) {

        Product product = searchProduct(productId);

        if (product == null) {

            System.out.println("Không tìm thấy sản phẩm.");

            return;

        }

        if (product.getStockQuantity() < quantity) {

            System.out.println("Số lượng trong kho không đủ.");

            return;

        }

        product.setStockQuantity(
                product.getStockQuantity() - quantity);

        System.out.println("Xuất kho thành công.");

    }

    // Cập nhật số lượng
    public void updateProductQuantity(String productId,
            int quantity) {

        Product product = searchProduct(productId);

        if (product != null) {

            product.setStockQuantity(quantity);

        }

    }

    // Lấy số lượng hiện tại
    public int getCurrentQuantity(String productId) {

        Product product = searchProduct(productId);

        if (product == null) {

            return 0;

        }

        return product.getStockQuantity();

    }

    // Kiểm tra sắp hết hàng
    public void checkLowStock(int minimumStock) {

        System.out.println("\n===== SẢN PHẨM SẮP HẾT HÀNG =====");

        boolean found = false;

        for (Product product : products) {

            if (product.getStockQuantity() <= minimumStock) {

                System.out.println(product.getProductId()
                        + " - "
                        + product.getProductName()
                        + " ("
                        + product.getStockQuantity()
                        + ")");

                found = true;

            }

        }

        if (!found) {

            System.out.println("Không có sản phẩm nào sắp hết hàng.");

        }

    }

    // Hiển thị tồn kho
    public void displayInventory() {

        if (products.isEmpty()) {

            System.out.println("Kho hiện không có sản phẩm.");

            return;

        }

        System.out.println("\n=================== TỒN KHO ===================");

        System.out.printf("%-10s %-30s %-10s\n",
                "MÃ",
                "TÊN SẢN PHẨM",
                "SỐ LƯỢNG");

        for (Product product : products) {

            System.out.printf("%-10s %-30s %-10d\n",
                    product.getProductId(),
                    product.getProductName(),
                    product.getStockQuantity());

        }

    }

    // Lấy toàn bộ danh sách sản phẩm
    public ArrayList<Product> getProducts() {

        return products;

    }

}