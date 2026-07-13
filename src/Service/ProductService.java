package Service;

import List.ProductList;
import java.util.ArrayList;
import java.util.List;
import Product.Product;

/**
 * Lớp xử lý các nghiệp vụ liên quan đến Product.
 *
 * Chỉ thao tác với dữ liệu.
 * Không nhập Scanner.
 * Không hiển thị Menu.
 *
 * @author Long
 */
public class ProductService {

    /**
     * Constructor
     */
    public ProductService() {
    }

    /**
     * Lấy toàn bộ danh sách sản phẩm.
     *
     * @return danh sách Product
     */
    public List<Product> getAllProducts() {
        return ProductList.products;
    }

    /**
     * Hiển thị toàn bộ sản phẩm.
     */
    public void displayAllProducts() {
        if (ProductList.products.isEmpty()) {
            System.out.println("Danh sách sản phẩm đang trống.");
            return;
        }

        for (Product product : ProductList.products) {
            System.out.println(product);
        }
    }

    /**
     * Thêm sản phẩm mới.
     *
     * @param product sản phẩm cần thêm
     * @return true nếu thành công
     */
    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }

        if (isProductIdExists(product.getProductId())) {
            return false;
        }

        ProductList.products.add(product);
        return true;
    }

    /**
     * Cập nhật thông tin sản phẩm.
     *
     * Không cập nhật stockQuantity.
     *
     * @param updatedProduct sản phẩm mới
     * @return true nếu thành công
     */
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

    /**
     * Xóa sản phẩm theo ID.
     *
     * @param productId mã sản phẩm
     * @return true nếu thành công
     */
    public boolean deleteProduct(String productId) {
        Product product = findProductById(productId);

        if (product == null) {
            return false;
        }

        ProductList.products.remove(product);
        return true;
    }

    /**
     * Tìm sản phẩm theo ID.
     *
     * @param productId mã sản phẩm
     * @return Product hoặc null
     */
    public Product findProductById(String productId) {
        for (Product product : ProductList.products) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }
        }

        return null;
    }

    /**
     * Tìm sản phẩm theo tên.
     *
     * @param keyword từ khóa
     * @return danh sách tìm được
     */
    public List<Product> searchByName(String keyword) {
        List<Product> result = new ArrayList<>();

        for (Product product : ProductList.products) {
            if (product.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }

        return result;
    }

    /**
     * Hiển thị danh sách kết quả tìm kiếm.
     *
     * @param keyword từ khóa
     */
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

    /**
     * Kiểm tra Product ID đã tồn tại.
     *
     * @param productId mã sản phẩm
     * @return true nếu đã tồn tại
     */
    public boolean isProductIdExists(String productId) {
        return findProductById(productId) != null;
    }

    /**
     * Sinh Product ID tự động.
     *
     * Ví dụ:
     * TEA001
     * POT002
     * CUP003
     * ACC004
     *
     * @param prefix tiền tố
     * @return Product ID
     */
    public String generateProductId(String prefix) {
        int max = 0;

        for (Product product : ProductList.products) {
            if (product.getProductId().startsWith(prefix)) {
                String number = product.getProductId().substring(prefix.length());
                int value = Integer.parseInt(number);

                if (value > max) {
                    max = value;
                }
            }
        }

        return String.format("%s%03d", prefix, max + 1);
    }
}