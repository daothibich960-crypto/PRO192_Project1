package Service;

import Inventory.Inventory;
import Product.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Product Service
 *
 * @author Long
 */
public class ProductService {

    private Inventory inventory;

    public ProductService(Inventory inventory) {

        this.inventory = inventory;

    }

    //========================================
    // GET ALL PRODUCTS
    //========================================
    public List<Product> getAllProducts() {

        return inventory.getProducts();

    }

    //========================================
    // DISPLAY ALL PRODUCTS
    //========================================
    public void displayAllProducts() {

        if (inventory.getProducts().isEmpty()) {

            System.out.println("Danh sách sản phẩm đang trống.");

            return;

        }
        System.out.println("=================================================================================================");
        System.out.printf("%-8s %-25s %-12s %-10s %-12s %-8s%n",
                "ID", "Product Name", "Price", "Unit", "Status", "Stock");
        System.out.println("=================================================================================================");

        for (Product p : inventory.getProducts()) {
            p.displaySummary();
        }

        System.out.println("=================================================================================================");

       

    }

    //========================================
    // ADD PRODUCT
    //========================================
    public boolean addProduct(Product product) {

        if (product == null) {

            return false;

        }

        if (isProductIdExists(product.getProductId())) {

            return false;

        }

        inventory.addProduct(product);

        return true;

    }

    //========================================
    // UPDATE PRODUCT
    //========================================
    public boolean updateProduct(Product updatedProduct) {

        Product oldProduct = findProductById(updatedProduct.getProductId());

        if (oldProduct == null) {

            return false;

        }

        oldProduct.setProductName(updatedProduct.getProductName());

        oldProduct.setImportPrice(updatedProduct.getImportPrice());

        oldProduct.setSellingPrice(updatedProduct.getSellingPrice());

        oldProduct.setUnit(updatedProduct.getUnit());

        oldProduct.setStatus(updatedProduct.getStatus());

        oldProduct.setDescription(updatedProduct.getDescription());

        oldProduct.setSupplier(updatedProduct.getSupplier());

        return true;

    }

    //========================================
    // DELETE PRODUCT
    //========================================
    public boolean deleteProduct(String productId) {

        Product product = findProductById(productId);

        if (product == null) {

            return false;

        }

        inventory.removeProduct(productId);

        return true;

    }

    //========================================
    // FIND PRODUCT BY ID
    //========================================
    public Product findProductById(String productId) {

        return inventory.searchProduct(productId);

    }

    //========================================
    // SEARCH BY NAME
    //========================================
    public List<Product> searchByName(String keyword) {

        List<Product> result = new ArrayList<>();

        for (Product product : inventory.getProducts()) {

            if (product.getProductName().toLowerCase()
                    .contains(keyword.toLowerCase())) {

                result.add(product);

            }

        }

        return result;

    }

    //========================================
    // DISPLAY SEARCH RESULT
    //========================================
    public void displaySearchResult(String keyword) {

        List<Product> result = searchByName(keyword);

        if (result.isEmpty()) {

            System.out.println("Không tìm thấy sản phẩm.");

            return;

        }

        for (Product product : result) {

            System.out.println(product);

        }

    }

    //========================================
    // CHECK PRODUCT ID
    //========================================
    public boolean isProductIdExists(String productId) {

        return inventory.searchProduct(productId) != null;

    }

    //========================================
    // GENERATE PRODUCT ID
    //========================================
    public String generateProductId(String prefix) {

        int max = 0;

        for (Product product : inventory.getProducts()) {

            if (product.getProductId().startsWith(prefix)) {

                String number
                        = product.getProductId().substring(prefix.length());

                int value = Integer.parseInt(number);

                if (value > max) {

                    max = value;

                }

            }

        }

        return String.format("%s%03d", prefix, max + 1);

    }

}
